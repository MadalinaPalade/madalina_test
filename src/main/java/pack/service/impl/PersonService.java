package pack.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Person;
import pack.repository.IPersonRepository;
import pack.service.IPersonService;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRepository repository;
	
	private List<Person> personList;

	@PostConstruct
	public void init(){
		personList=new ArrayList<>();
	}

	@Override
	public List<Person> getPersons() {
		try {
			for (Person person : repository.findAll()) {
				personList.add(person);
			} 
			return personList;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return java.util.Collections.emptyList();
		}
	}

	@Override
	public Person findPersonById(int id) {
		return repository.findOne(id);
	}

//	@Override
	//public Person findPersonByEmail(String email) {	
//	}

	@Override
	public boolean savePersonListFromJsonplaceholder(List<Person> list) {
		try {
			repository.save(list);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
