package org.ht.id.externalcrm.shared;

import com.github.javafaker.Faker;
import org.ht.id.externalcrm.model.sub.CitizenId;
import org.ht.id.externalcrm.model.sub.EmailAddress;
import org.ht.id.externalcrm.model.sub.Gender;
import org.ht.id.externalcrm.model.sub.NationalId;
import org.ht.id.externalcrm.model.sub.Nationality;
import org.ht.id.externalcrm.model.sub.PhoneNumber;
import java.util.List;

public interface IProfileGenerator {
    Faker getFaker();
    long generateBirthdayTicks(int fromAge, int toAge);
    NationalId generateNationalId(String fullName, long dob);
    CitizenId generateCitizenId(String fullName, long dob);

    Nationality generateNationality();

    List<EmailAddress> generateEmailAddresses();
    List<EmailAddress> generateEmailAddresses(int size);
    EmailAddress generateEmailAddress(boolean primary);

    List<PhoneNumber> generatePhoneNumbers();
    List<PhoneNumber> generatePhoneNumbers(int size);
    PhoneNumber generatePhoneNumber(boolean primary);

    Gender generateGender();
}
