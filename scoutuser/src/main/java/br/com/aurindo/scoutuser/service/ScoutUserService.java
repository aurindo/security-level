package br.com.aurindo.scoutuser.service;

import br.com.aurindo.scoutuser.dto.LastSearchCreateDTO;
import br.com.aurindo.scoutuser.dto.LastSearchDTO;
import br.com.aurindo.scoutuser.exception.EntityNotFoundException;
import br.com.aurindo.scoutuser.model.LastSearch;
import br.com.aurindo.scoutuser.repository.LastSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoutUserService {

    @Autowired
    LastSearchRepository lastSearchRepository;

    public LastSearchDTO seachByCpf(String cpf) throws EntityNotFoundException {
        Optional<LastSearch> opt = lastSearchRepository.findById(cpf);
        if (opt.isPresent()) {
            return new LastSearchDTO(opt.get());
        } else {
            throw new EntityNotFoundException(LastSearch.class, "cpf", cpf);
        }
    }

    public LastSearch create(LastSearchCreateDTO lastSearchCreateDTO) {
        LastSearch lastSearch = new LastSearch(lastSearchCreateDTO);
        lastSearch = lastSearchRepository.save(lastSearch);
        return lastSearch;
    }
}
