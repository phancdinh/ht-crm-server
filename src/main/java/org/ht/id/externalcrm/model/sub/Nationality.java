package org.ht.id.externalcrm.model.sub;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class Nationality implements Serializable {
    private String code;
    private String value;
}
