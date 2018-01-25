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
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="company_seq")
	@SequenceGenerator(name="company_seq", sequenceName="company_seq",initialValue = 1, allocationSize = 100)
	private int companyId;
	@Column
	private String name;
	@Column
	private String catchPhrase;
	@Column
	private String bs;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name = "personId", nullable = false)
	private Person personCompany;
	
	
	public Company(){}
	
	

	public Company(String name, String catchPhrase, String bs, Person personCompany) {
		this.name = name;
		this.catchPhrase = catchPhrase;
		this.bs = bs;
		this.personCompany=personCompany;
	}



	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatchPhrase() {
		return catchPhrase;
	}

	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public Person getPersonCompany() {
		return personCompany;
	}

	public void setPersonCompany(Person personCompany) {
		this.personCompany = personCompany;
	}
	
	

}
