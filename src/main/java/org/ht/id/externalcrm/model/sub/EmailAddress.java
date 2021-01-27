package org.ht.id.externalcrm.model.sub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class EmailAddress implements Serializable {
    private String value;
    @JsonProperty("is_primary")
    private boolean is_primary;
}
