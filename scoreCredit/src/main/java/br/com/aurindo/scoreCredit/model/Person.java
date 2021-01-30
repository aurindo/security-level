package br.com.aurindo.scoreCredit.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Document
public class Person implements Serializable {

    @Id
    private String id;

    @Getter
    @NonNull
    private String cpf;

    @Getter
    @NonNull
    private LocalDate birthDate;

    @Getter
    @NonNull
    private String sourceIncome;

    @Getter
    @NonNull
    private Address address;

    public Person(String cpf) {
        this.cpf = cpf;
    }

}
