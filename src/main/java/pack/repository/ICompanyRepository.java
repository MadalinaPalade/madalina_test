package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pack.model.Company;

@Repository
public interface ICompanyRepository extends CrudRepository<Company, Integer>{}