package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pack.model.Person;

@Repository
public interface IPersonRepository extends CrudRepository<Person, Integer> {
}
