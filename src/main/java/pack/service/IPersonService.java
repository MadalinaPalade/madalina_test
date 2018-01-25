package pack.service;

import java.util.List;

import pack.model.Person;

public interface IPersonService {
	public List<Person> getPersons();
	public Person findPersonById(int id);
	//public Person findPersonByEmail(String email);
	
	//https://jsonplaceholder.typicode.com/users
	public boolean savePersonListFromJsonplaceholder(List<Person> list);
	}
