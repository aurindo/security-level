package br.com.aurindo.scoutuser.dto;

import lombok.*;

@EqualsAndHashCode
@Setter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class LastSearchCreateDTO extends LastSearchDTO {

    @NonNull
    @Getter
    private String cpf;

}
