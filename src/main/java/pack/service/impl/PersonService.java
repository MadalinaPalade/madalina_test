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
		return (List<Person>) repository.findAll();
	}

	@Override
	public Person findPersonById(int id) throws CustomException {
		return repository.findOne(id);
	}

	// @Override
	// public void savePersonListFromJsonplaceholder(List<Person> list) throws
	// CustomException {
	// repository.save(list);
	// }

	@Override
	public void createPerson(Person person) throws CustomException {
		repository.save(person);
	}
}
