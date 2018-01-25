package pack.controller;

import java.util.List;

import pack.model.Person;


public interface IPersonController {
	public List<Person> getPersons();
	public Person findPersonById(int id);
//	public Person findPersonByEmail(String email);
	
	//https://jsonplaceholder.typicode.com/users
	public boolean savePersonListFromJsonplaceholder();
	public List<Person> getPersonsFromJsonplaceholder();
}
