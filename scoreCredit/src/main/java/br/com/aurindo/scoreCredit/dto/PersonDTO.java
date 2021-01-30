package br.com.aurindo.scoreCredit.dto;

import br.com.aurindo.scoreCredit.model.Address;
import br.com.aurindo.scoreCredit.model.Person;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
public class PersonDTO {

    private String cpf;

    private LocalDate birthDate;

    private String sourceIncome;

    private Address address;

    public PersonDTO(Person person) {
        this.cpf = person.getCpf();
        this.birthDate = person.getBirthDate();
        this.sourceIncome = person.getSourceIncome();
        this.address = person.getAddress();
    }
}
