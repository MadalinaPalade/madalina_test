package pack.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import pack.model.Address;
import pack.model.Company;
import pack.model.Person;
import pack.repository.ICompanyRepository;
import pack.service.ICompanyService;
import pack.util.CustomException;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	private ICompanyRepository repository;

	@Autowired
	EhCacheCacheManager cacheManager;

	@Autowired
	Company saveCompany;

	@Override
	public List<Person> getPersonsForACompany(int companyId) throws CustomException {
		try {
			return repository.getPersonsForACompany(companyId);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	@Cacheable(value = "company", key = "#id", sync = true)
	public Company findCompanyById(int id) throws CustomException {
		try {
			return repository.findOne(id);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Company getCompanyByName(String name) throws CustomException {
		try {
			return repository.getCompanyByName(name);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	@Transactional
	@CachePut(value = "company", key = "#company.getCompanyId()")
	public Company createOrUpdateCompany(Company company) throws CustomException {
		try {
			Company newCompany = repository.getCompanyByName(company.getName());
			List<Person> persons = company.getPersons();

			for (Person person : persons) {
				for (Address address : person.getAddress()) {
					address.getGeo().setGeoAddresses(address);
					address.setPersons(person);
					if (newCompany != null) {
						person.setCompany(newCompany);
						saveCompany = repository.save(newCompany);
					} else {
						person.setCompany(company);
						saveCompany = repository.save(company);
					}
				}
			}
			return saveCompany;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

	@Scheduled(fixedDelay = 300000)
	@CacheEvict(value = "company", allEntries = true)
	public void deleteFromCache() {
	}

	public List<Company> getFromCache(String cacheName) throws CustomException {
		try {
			Ehcache cache = cacheManager.getCacheManager().getEhcache("company");
			List<Company> list = new ArrayList<>();
			if ( cache.getKeys() != null) {
				for (Object obj : cache.getKeys()) {
					Element element = cache.get(obj);
					list.add((Company) element.getObjectValue());
				}
				return list;
			}
			return Collections.emptyList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public String deleteCompany(int companyId) throws CustomException {
		try {
			Company deletedCompany = findCompanyById(companyId);
			if (deletedCompany != null) {
				repository.delete(deletedCompany);
				return deletedCompany.getName() + " deleted";
			}
			return "Company doesn't exist";

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

}
