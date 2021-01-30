package br.com.aurindo.scoreCredit.service;

import br.com.aurindo.scoreCredit.model.Person;
import br.com.aurindo.scoreCredit.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private PersonRepository personRepository;

    public Person getDataForScore(String cpf) {
        Person person = personRepository.findByCpf(cpf);
        return person;
    }

}
