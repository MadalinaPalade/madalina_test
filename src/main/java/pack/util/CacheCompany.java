package pack.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import pack.model.Company;

public class CacheCompany {

	private Map<Object, Entry> cacheMap;
	private Long secondsToLive;
	private int maxSize;

	public CacheCompany(Long secondsToLive, int maxSize) {
		this.secondsToLive = secondsToLive;
		this.maxSize = maxSize;
		this.cacheMap = new HashMap<>(maxSize);

		if (secondsToLive > 0) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							isExpired();
							Thread.sleep(1000);

						} catch (InterruptedException ex) {
						}
					}
				}
			});
			t.start();
		}
	}

	public void isExpired() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		for (Object k : cacheMap.keySet()) {
			Entry entry = cacheMap.get(k);

			synchronized (cacheMap) {
				if (entry != null) {
					Timestamp lastAccessTs = entry.getLastAccessTimestamp();
					long elapsedSeconds = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - lastAccessTs.getTime());

					if (elapsedSeconds > this.secondsToLive) {
						this.remove(k);
					}
				}
			}
		}
	}

	public synchronized void put(Object key, Company value) {
		if(cacheMap.size() < maxSize)
			cacheMap.put(key, new Entry(value));
	}

	public synchronized Company get(Object key) {
		Entry entry = cacheMap.get(key);
		if (entry != null) {
			entry.setLastAccessTimestamp(new Timestamp(System.currentTimeMillis()));
			return entry.getCompany();
		} else {
			return null;
		}
	}

	private synchronized void remove(Object key) {
		Entry entry = cacheMap.get(key);
		if (entry != null) {
			cacheMap.remove(key);
		}
	}

	static class Entry {
		private Company company;
		private Timestamp lastAccessTimestamp;

		public Entry(Company company) {
			this.company = company;
			this.lastAccessTimestamp = new Timestamp(System.currentTimeMillis());
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		public Timestamp getLastAccessTimestamp() {
			return lastAccessTimestamp;
		}

		public void setLastAccessTimestamp(Timestamp lastAccessTimestamp) {
			this.lastAccessTimestamp = lastAccessTimestamp;
		}
	}

}
