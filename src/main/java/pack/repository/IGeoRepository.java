package pack.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pack.model.Geo;

@Repository
public interface IGeoRepository extends CrudRepository<Geo, Integer>{}
