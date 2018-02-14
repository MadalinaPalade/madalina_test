package pack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pack.model.Company;
import pack.model.Person;

@Repository
public interface ICompanyRepository extends CrudRepository<Company, Integer> {

	@Query("Select p from Person p,Company c where c.companyId=?1")
	public List<Person> getPersonsForACompany(int id);

	@Query("Select c from Company c where c.name=?1")
	public Company getCompanyByName(String name);;

}
