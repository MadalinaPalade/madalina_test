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
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="person_seq")
	@SequenceGenerator(name="person_seq", sequenceName="person_seq",initialValue = 1, allocationSize = 100)
	@Column(name="personId")
	private int id;
	@Column
	private String name;
	@Column
	private String username;
	@Column
	private String email;
	@Column
	private String phone;
	@Column
	private String website;
	
	@OneToOne(mappedBy = "personAddress",cascade=CascadeType.ALL)
	private Address address;
	
	@OneToOne(mappedBy = "personCompany",cascade=CascadeType.ALL)
	private Company company;
	
	public Person(){}
	
	

	public Person(int id,String name, String username, String email, String phone, String website,Address address,Company company) {
		this.id=id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.website = website;
		this.address=address;
		this.company=company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
}
