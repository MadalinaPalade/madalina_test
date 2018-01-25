package pack.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Geo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="geo_seq")
	@SequenceGenerator(name="geo_seq", sequenceName="geo_seq",initialValue = 1, allocationSize = 100)
	private int geoId;
	@Column
	private String lat;
	@Column
	private String lng;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name = "addressId", nullable = false)
	private Geo geoAddress;
	
	
	public Geo(){}
	

	public Geo(String lat, String lng, Geo geoAddress) {
		this.lat = lat;
		this.lng = lng;
		this.geoAddress=geoAddress;
	}



	public int getGeoId() {
		return geoId;
	}

	public void setGeoId(int geoId) {
		this.geoId = geoId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}


	public Geo getGeoAddress() {
		return geoAddress;
	}


	public void setGeoAddress(Geo geoAddress) {
		this.geoAddress = geoAddress;
	}
	
	

}
