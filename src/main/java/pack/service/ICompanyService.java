package pack.service;

import java.util.List;

import pack.model.Company;
import pack.model.Person;
import pack.util.CustomException;

public interface ICompanyService {
	
	public List<Person> getPersonsForACompany(int companyId) throws CustomException;

	public Company findCompanyById(int id) throws CustomException;

	public Company getCompanyByName(String name) throws CustomException;

	public Company createOrUpdateCompany(Company company) throws CustomException;

	public String deleteCompany(String name) throws CustomException;


}
