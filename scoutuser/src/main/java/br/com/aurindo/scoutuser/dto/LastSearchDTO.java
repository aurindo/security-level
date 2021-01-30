package br.com.aurindo.scoutuser.dto;

import br.com.aurindo.scoutuser.model.LastSearch;
import br.com.aurindo.scoutuser.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.ZonedDateTime;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Setter
public class LastSearchDTO {

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

    public LastSearchDTO(LastSearch lastSearch) {
        this.lastCheck = lastSearch.getLastCheck();
        this.eventType = lastSearch.getEventType();
        this.lastCredCardUse = lastSearch.getLastCredCardUse();
    }
}
