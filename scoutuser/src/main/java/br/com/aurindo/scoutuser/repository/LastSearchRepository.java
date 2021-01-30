package br.com.aurindo.scoutuser.repository;

import br.com.aurindo.scoutuser.model.LastSearch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastSearchRepository extends CrudRepository<LastSearch, String> {
}
