package pack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "geoId")
public class Geo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geo_id")
	private int geoId;
	@Column
	private String lat;
	@Column
	private String lng;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address addresses;

	public Geo() {
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

	public Address getGeoAddresses() {
		return addresses;
	}

	public void setGeoAddresses(Address addresses) {
		this.addresses = addresses;
	}

}
