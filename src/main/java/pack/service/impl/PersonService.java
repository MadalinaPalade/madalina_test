package pack.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Person;
import pack.repository.IPersonRepository;
import pack.service.IPersonService;
import pack.util.CustomException;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepository repository;

	@Override
	public List<Person> getPersons() throws CustomException {
		try {
			return (List<Person>) repository.findAll();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Person findPersonById(int id) throws CustomException {
		try {
			return repository.findOne(id);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Person createPerson(Person person) throws CustomException {
		try {
			repository.save(person);
			return person;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}
}
