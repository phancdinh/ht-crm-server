package org.ht.id.externalcrm.model.sub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhoneNumber {
    private String value;
    @JsonProperty("is_primary")
    private boolean is_primary;
}
