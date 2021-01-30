package br.com.aurindo.scoreCredit.controller;

import br.com.aurindo.scoreCredit.dto.PersonDTO;
import br.com.aurindo.scoreCredit.model.Person;
import br.com.aurindo.scoreCredit.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping(
            path = "/{cpf}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> listAll(@PathVariable("cpf")String cpf) {
        Person person = scoreService.getDataForScore(cpf);
        PersonDTO personDTO = new PersonDTO(person);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

}
