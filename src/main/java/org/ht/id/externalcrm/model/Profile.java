package org.ht.id.externalcrm.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.ht.id.externalcrm.model.sub.CitizenId;
import org.ht.id.externalcrm.model.sub.EmailAddress;
import org.ht.id.externalcrm.model.sub.Gender;
import org.ht.id.externalcrm.model.sub.NationalId;
import org.ht.id.externalcrm.model.sub.Nationality;
import org.ht.id.externalcrm.model.sub.PhoneNumber;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@Document(collection = "Profiles")
public class Profile implements Serializable {
    private String full_name;
    private String first_name;
    private String last_name;
    private long dob;
    private String pob;
    private String hometown;
    private List<Nationality> nationalities;
    private NationalId national_id;
    private CitizenId citizen_id;

    private Date created_date;
    private List<EmailAddress> email_addresses;
    private List<PhoneNumber> phone_numbers;
    private Gender gender;
}
