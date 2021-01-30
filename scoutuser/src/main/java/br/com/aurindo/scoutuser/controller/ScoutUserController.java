package br.com.aurindo.scoutuser.controller;

import br.com.aurindo.scoutuser.dto.LastSearchCreateDTO;
import br.com.aurindo.scoutuser.dto.LastSearchDTO;
import br.com.aurindo.scoutuser.model.LastSearch;
import br.com.aurindo.scoutuser.service.ScoutUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/")
public class ScoutUserController {

    @Autowired
    private ScoutUserService scoutUserService;

    @GetMapping(
            path = "/{cpf}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LastSearchDTO> listAll(@PathVariable("cpf")String cpf) {
        LastSearchDTO lastSearchDTO = scoutUserService.seachByCpf(cpf);
        return new ResponseEntity<>(lastSearchDTO, HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity create(@RequestBody final LastSearchCreateDTO lastSearchCreate) {
        LastSearch lastSearch = scoutUserService.create(lastSearchCreate);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(lastSearch.getCpf())
                .toUri();

        return ResponseEntity.created(uri).body(lastSearch);
    }

}
