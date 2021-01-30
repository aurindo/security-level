package br.com.aurindo.scoutuser.model;

import br.com.aurindo.scoutuser.dto.LastSearchCreateDTO;
import br.com.aurindo.scoutuser.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.ZonedDateTime;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@RedisHash(LastSearch.PREFIX)
public class LastSearch implements Serializable {

    public static final String PREFIX = "LAST_SEARCH";

    @NonNull
    @Getter
    @Id
    private String cpf;

    @NonNull
    @Getter
    @JsonFormat(pattern = DateUtil.DATE_TIME_PATTERN)
    private ZonedDateTime lastCheck;

    @NonNull
    @Getter
    private String eventType;

    @NonNull
    @Getter
    private String lastCredCardUse;

    public LastSearch(LastSearchCreateDTO lastSearchCreateDTO) {
        this.cpf = lastSearchCreateDTO.getCpf();
        this.lastCheck = lastSearchCreateDTO.getLastCheck();
        this.eventType = lastSearchCreateDTO.getEventType();
        this.lastCredCardUse = lastSearchCreateDTO.getLastCredCardUse();
    }
}
