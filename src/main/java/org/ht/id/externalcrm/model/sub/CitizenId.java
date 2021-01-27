package org.ht.id.externalcrm.model.sub;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class CitizenId implements Serializable {
    private String number;
    private String issued_place;
    private long issued_date;
    private String full_name;
    private long dob;
    private String hometown;
    private String permanent_address;
    private String file_scan;
    private Gender gender;
}
