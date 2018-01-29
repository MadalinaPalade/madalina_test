package pack.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import pack.model.Person;
import pack.service.IPersonService;
import pack.util.CustomException;
import pack.util.ResponseFormat;

@Controller
public class PersonController {

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
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
	}

	@RequestMapping(value = "/getPersons", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ObjectNode getPersons() throws JsonProcessingException, CustomException {
		return ResponseFormat.setResponse(objectMapper.writeValueAsString(personService.getPersons()));
	}

	@RequestMapping(value = "/findPersonById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ObjectNode findPersonById(@PathVariable("id") int id) throws CustomException, JsonProcessingException {
		return ResponseFormat.setResponse(objectMapper.writeValueAsString(personService.findPersonById(id)));
	}

	@RequestMapping(value = "/getPersonsFromJsonplaceholder", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ObjectNode getPersonsFromJsonplaceholder() throws ClientProtocolException, IOException {
		response = httpClient.execute(request);
		String result = EntityUtils.toString(response.getEntity());
		return ResponseFormat.setResponse(objectMapper.writeValueAsString(objectMapper.readValue(result,
				objectMapper.getTypeFactory().constructCollectionType(List.class, Person.class))));
	}

}
