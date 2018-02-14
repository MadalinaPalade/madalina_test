package unitTests;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pack.model.Address;
import pack.model.Company;
import pack.model.Person;
import pack.service.impl.CompanyService;

public class CompanyControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	List<Person> persons;
	List<Address> addresses;
	Company company;
	
	@PostConstruct
	public void init(){
		persons=new ArrayList<>();
		addresses=new ArrayList<>();
		company=new Company();
	}
	
	@MockBean
	private CompanyService service;
	
	public Company getCompany(){
		company.setBs("bsTest11");
		company.setCatchPhrase("catchPhraseTest11");
		company.setCompanyId(1);
		company.setName("nameTest11");	
		addresses.add(new Address(1, "streetTest11", "suiteTest11", "cityTest11", "zipcodeTest11"));
		addresses.add(new Address(2, "streetTest22", "suiteTest22", "cityTest22", "zipcodeTest22"));
		persons.add(new Person(1, "nameTest11", "usernameTest11", "emailTest11", "phoneTest11", "websiteTest11", addresses));	
		company.setPersons(persons);
		
		return company;
	}

	@Test
	public void findCompanyByIdTest() throws Exception {
		Mockito.when( service.findCompanyById(Mockito.anyInt()) )
			   .thenReturn(getCompany());
		

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(
				"/findCompanyById/1").accept(
				MediaType.APPLICATION_JSON)).andReturn();

		System.out.println(result.getResponse().getContentAsString());
		Mockito.verify(service).findCompanyById(Mockito.anyInt());
	}
	
	@Test
	public void findPersonsForACompanyTest() throws Exception {
		Mockito.when(service.getPersonsForACompany(Mockito.anyInt()) )
		   .thenReturn(getCompany().getPersons());
	}
	


}
