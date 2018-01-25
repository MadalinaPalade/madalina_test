package pack.controller.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import pack.controller.IPersonController;
import pack.model.Address;
import pack.model.Company;
import pack.model.Geo;
import pack.model.Person;
import pack.service.IAddressService;
import pack.service.IPersonService;

@Controller
public class PersonController implements IPersonController {

	private HttpClient httpClient;
	private HttpGet request;
	private HttpResponse response;
	private ObjectMapper objectMapper;
	private List<Person> personsList;

	@Autowired
	private IPersonService personService;
	

	@PostConstruct
	public void init() {
		httpClient = HttpClientBuilder.create().build();
		request = new HttpGet("https://jsonplaceholder.typicode.com/users");
		objectMapper = new ObjectMapper();
		personsList=new ArrayList<>();
	}

	@RequestMapping(value = "/getPersons", method = RequestMethod.GET)
	@Override
	@ResponseBody
	public List<Person> getPersons() {
		return personService.getPersons();
	}

	@Override
	@RequestMapping(value = "/findPersonById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person findPersonById(@PathVariable("id") int id) {
		return personService.findPersonById(id);
	}

	@Override
	@RequestMapping(value = "/savePersonListFromJsonplaceholder", method = RequestMethod.POST)
	@ResponseBody
	public boolean savePersonListFromJsonplaceholder() {
		Person pers=new Person();
		for (Person p : getPersonsFromJsonplaceholder()) {
			
			pers.setName(p.getName());
			pers.setUsername(p.getUsername());
			pers.setEmail(p.getEmail());
			pers.setPhone(p.getPhone());
			pers.setWebsite(p.getWebsite());
		//	pers.setAddress(new Address(p.getAddress().getStreet(), p.getAddress().getSuite(), p.getAddress().getCity(), p.getAddress().getZipcode(), p, p.getAddress().getGeo()));
			//pers.setCompany(new Company(pers.getCompany().getName(), pers.getCompany().getCatchPhrase(), pers.getCompany().getBs(), p));
		
			pers.setAddress(p.getAddress());
			pers.setCompany(p.getCompany());
			personsList.add(pers);
		}
		
		return personService.savePersonListFromJsonplaceholder(personsList);
	}

	@Override
	@RequestMapping(value = "/getPersonsFromJsonplaceholder", method = RequestMethod.GET)
	@ResponseBody
	public List<Person> getPersonsFromJsonplaceholder() {
		try {
			response = httpClient.execute(request);
			String result = EntityUtils.toString(response.getEntity());
			return objectMapper.readValue(result,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));

		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
