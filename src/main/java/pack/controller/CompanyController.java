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
import pack.model.Company;
import pack.service.ICompanyService;
import pack.util.CustomException;
import pack.util.Response;

@Controller
public class CompanyController {

	private HttpClient httpClient;
	private HttpGet request;
	private HttpResponse response;

	@Autowired
	private ICompanyService companyService;

	@PostConstruct
	public void init() throws ClientProtocolException, IOException {
		httpClient = HttpClientBuilder.create().build();
		request = new HttpGet("https://jsonplaceholder.typicode.com/users");
		response = httpClient.execute(request);
	}

	@RequestMapping(value = "/getPersonsForACompany/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response> getPersonsForACompany(@PathVariable("id") int id)
			throws JsonProcessingException, CustomException {
		return new ResponseEntity<Response>(
				new Response().getResponse(companyService.getPersonsForACompany(id).toArray()), HttpStatus.OK);

	}

	@RequestMapping(value = "/getFromCache/{cacheName}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response> getPersonsFromCache(@PathVariable("cacheName") String cacheName)
			throws CustomException {
		return new ResponseEntity<Response>(new Response().getResponse(companyService.getFromCache(cacheName)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/findCompanyById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> findCompanyById(@PathVariable("id") int id)
			throws CustomException, JsonProcessingException {
		return new ResponseEntity<Response>(new Response().getResponse(companyService.findCompanyById(id)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getPersonsFromJsonplaceholder", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Response> getPersonsFromJsonplaceholder() throws ClientProtocolException, IOException {
		return new ResponseEntity<Response>(new Response().getResponse(EntityUtils.toString(response.getEntity())),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/createOrUpdateCompany", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> createOrUpdateCompany(@RequestBody Company company) throws CustomException {
		return new ResponseEntity<Response>(new Response().getResponse(companyService.createOrUpdateCompany(company)),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteCompany/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteCompany(@PathVariable("id") int id) throws CustomException {
		return new ResponseEntity<Response>(new Response().getResponse(companyService.deleteCompany(id)),
				HttpStatus.OK);

	}

	public int addNr(int a, int b) {
		return a + b;
	}

}
