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
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="address_seq")
	@SequenceGenerator(name="address_seq", sequenceName="address_seq", initialValue = 1, allocationSize = 100)
	private int addressId;	
	@Column
	private String street;
	@Column
	private String suite;
	@Column
	private String city;
	@Column
	private String zipcode;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name = "personId", nullable = false)
	private Person personAddress;
	
	@OneToOne(mappedBy = "geoAddress",cascade=CascadeType.ALL)
	private Geo geo;

	public Address(){}
	

	public Address(String street, String suite, String city, String zipcode,Person personAddress,Geo geo) {
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo=geo;
		this.personAddress=personAddress;
		
	}



	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	

	public Person getPersonAddress() {
		return personAddress;
	}



	public void setPersonAddress(Person personAddress) {
		this.personAddress = personAddress;
	}



	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}

	
	
}
