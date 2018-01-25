package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pack.model.Address;

@Repository
public interface IAddressRepository extends CrudRepository<Address, Integer>{}
