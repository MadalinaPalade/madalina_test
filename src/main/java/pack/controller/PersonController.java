package pack.controller;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;

import pack.model.Person;
import pack.service.IPersonService;
import pack.util.CustomException;
import pack.util.Response;

@Controller
public class PersonController {

	private HttpClient httpClient;
	private HttpGet request;
	private HttpResponse response;

	@Autowired
	private IPersonService personService;

	@PostConstruct
	public void init() throws ClientProtocolException, IOException {
		httpClient = HttpClientBuilder.create().build();
		request = new HttpGet("https://jsonplaceholder.typicode.com/users");
		response = httpClient.execute(request);
	}

	@RequestMapping(value = "/getPersons", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response> getPersons() throws JsonProcessingException, CustomException {
		return new ResponseEntity<Response>(new Response().getResponse(personService.getPersons().toArray()),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/findPersonById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> findPersonById(@PathVariable("id") int id)
			throws CustomException, JsonProcessingException {
		return new ResponseEntity<Response>(new Response().getResponse(personService.findPersonById(id)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getPersonsFromJsonplaceholder", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response> getPersonsFromJsonplaceholder() throws ClientProtocolException, IOException {
		return new ResponseEntity<Response>(new Response().getResponse(EntityUtils.toString(response.getEntity())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/createPerson", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> createPerson(@RequestBody Person person) throws CustomException {
		return new ResponseEntity<Response>(new Response().getResponse(personService.createPerson(person)),
				HttpStatus.OK);

	}

}
