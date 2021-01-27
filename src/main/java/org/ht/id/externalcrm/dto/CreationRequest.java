package org.ht.id.externalcrm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreationRequest {
    @JsonProperty("begin_date")
    private Long beginDate;
    @JsonProperty("end_date")
    private Long endDate;
    @JsonProperty("numbers_per_day")
    private int numbersPerDay;
}
