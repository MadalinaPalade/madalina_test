package pack.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.model.Address;
import pack.model.Company;
import pack.model.Person;
import pack.repository.ICompanyRepository;
import pack.service.ICompanyService;
import pack.util.CacheCompany;
import pack.util.CustomException;

@Service
public class CompanyService implements ICompanyService {

	@Autowired
	private ICompanyRepository repository;

	
	CacheCompany cache;

	@Autowired
	Company saveCompany;

	@PostConstruct
	public void init() {
		cache = new CacheCompany(300L, 100);
	}

	@Override
	public List<Person> getPersonsForACompany(int companyId) throws CustomException {
		try {
			return repository.getPersonsForACompany(companyId);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Company findCompanyById(int id) throws CustomException {
		try {

			if (cache.get(id) != null) {
				return cache.get(id);
			}
			
			Company company = repository.findOne(id);
			cache.put(id, company);
			return company;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public Company getCompanyByName(String name) throws CustomException {
		try {
			if (cache.get(name) != null) {
				return cache.get(name);
			}
			Company company = repository.getCompanyByName(name);
			cache.put(name, company);
			return company;

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Company createOrUpdateCompany(Company company) throws CustomException {
		try {
			Company newCompany = repository.getCompanyByName(company.getName());
			List<Person> persons = company.getPersons();

			for (Person person : persons) {
				for (Address address : person.getAddress()) {
					address.getGeo().setGeoAddresses(address);
					address.setPersons(person);
					if (newCompany != null) {
						person.setCompany(newCompany);
						saveCompany = repository.save(newCompany);
					} else {
						person.setCompany(company);
						saveCompany = repository.save(company);
					}
				}
			}
			cache.put(saveCompany.getCompanyId(), saveCompany);
			return saveCompany;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

	@Override
	public String deleteCompany(String name) throws CustomException {
		try {
			Company deletedCompany = getCompanyByName(name);
			if (deletedCompany != null) {
				repository.delete(deletedCompany);
				return deletedCompany.getName() + " deleted";
			}
			return "Company doesn't exist";

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}

}
