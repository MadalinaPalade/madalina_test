package pack.service;

import java.util.List;

import pack.model.Person;
import pack.util.CustomException;

public interface IPersonService {
	public List<Person> getPersons() throws CustomException;

	public Person findPersonById(int id) throws CustomException;

	public void createPerson(Person person) throws CustomException;

}
