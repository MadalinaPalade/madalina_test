package pack.controller;

import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pack.model.Person;
import pack.service.IPersonService;
import pack.util.CustomException;

@Controller
public class PersonController {

	final JsonNodeFactory factory = JsonNodeFactory.instance;
	ObjectNode node;
	ObjectNode child;
	private HttpClient httpClient;
	private HttpGet request;
	private HttpResponse response;
	private ObjectMapper objectMapper;

	@Autowired
	private IPersonService personService;

	@PostConstruct
	public void init() {
		httpClient = HttpClientBuilder.create().build();
		request = new HttpGet("https://jsonplaceholder.typicode.com/users");
		objectMapper = new ObjectMapper();
		node = factory.objectNode();
		child = factory.objectNode();
	}

	@RequestMapping(value = "/getPersons", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ObjectNode getPersons() throws CustomException, JsonProcessingException {

		try {
			String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(personService.getPersons());
			child.put("status", HttpStatus.ACCEPTED.getReasonPhrase());
			child.put("data", json);
			node.set("result", child);
			return node;
		} catch (CustomException e) {
			throw new CustomException();
		}
	}

	@RequestMapping(value = "/findPersonById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Person findPersonById(@PathVariable("id") int id) {
		try {
			return personService.findPersonById(id);
		} catch (CustomException e) {
			e.printStackTrace();
			return new Person();
		}
	}

	@RequestMapping(value = "/getPersonsFromJsonplaceholder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public List<Person> getPersonsFromJsonplaceholder() {
		try {
			response = httpClient.execute(request);
			String result = EntityUtils.toString(response.getEntity());
			return objectMapper.readValue(result,

					objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
