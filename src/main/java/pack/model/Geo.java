package pack.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@Entity
@JsonIdentityInfo(scope = Geo.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "geoId")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "lat", "lng" }) })
public class Geo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "geo_id", unique = true, nullable = false)
	private int geoId;
	@Column
	private String lat;
	@Column
	private String lng;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "geo")
	private Address addresses;

	public Geo() {
	}

	public Geo(int geoId, String lat, String lng, Address addresses) {
		super();
		this.geoId = geoId;
		this.lat = lat;
		this.lng = lng;
		this.addresses = addresses;
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
