package br.com.aurindo.scoreCredit.repository;

import br.com.aurindo.scoreCredit.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    Person findByCpf(String cpf);

}
