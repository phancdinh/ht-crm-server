package org.ht.id.externalcrm.shared;

import com.github.javafaker.Faker;
import org.ht.id.externalcrm.model.sub.CitizenId;
import org.ht.id.externalcrm.model.sub.EmailAddress;
import org.ht.id.externalcrm.model.sub.Gender;
import org.ht.id.externalcrm.model.sub.NationalId;
import org.ht.id.externalcrm.model.sub.Nationality;
import org.ht.id.externalcrm.model.sub.PhoneNumber;
import org.ht.id.externalcrm.util.DateTimeUtility;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class ProfileGenerator implements IProfileGenerator {
    private static final Faker FAKER = new Faker(new Locale("vi"));
    private static final int SIZE_OF_PHONE_NUMBERS = 5;
    private static final int SIZE_OF_PHONE_EMAILS = 5;


    @Override
    public Faker getFaker() {
        return FAKER;
    }

    @Override
    public long generateBirthdayTicks(int fromAge, int toAge) {
        return FAKER.date().birthday(fromAge, toAge).getTime() / 1000;
    }

    @Override
    public NationalId generateNationalId(String fullName, long dob) {

        return NationalId.builder()
                .dob(dob)
                .full_name(fullName)
                .number(FAKER.idNumber().valid())
                .hometown(FAKER.address().city())
                .issued_place(FAKER.address().city())
                .issued_date(DateTimeUtility.toUnixTimestamp(FAKER.date().past(1000, 100, TimeUnit.DAYS)))
                .build();
    }

    @Override
    public CitizenId generateCitizenId(String fullName, long dob) {
        return CitizenId.builder()
                .dob(dob)
                .full_name(fullName)
                .number(FAKER.idNumber().valid())
                .hometown(FAKER.address().city())
                .issued_place(FAKER.address().city())
                .issued_date(DateTimeUtility.toUnixTimestamp(FAKER.date().past(1000, 100, TimeUnit.DAYS)))
                .build();
    }

    @Override
    public Nationality generateNationality() {
        return Nationality.builder()
                .code("VN")
                .value("").build();
    }

    @Override
    public List<EmailAddress> generateEmailAddresses() {
        int size = FAKER.number().numberBetween(0, SIZE_OF_PHONE_EMAILS);
        return generateEmailAddresses(size);
    }

    @Override
    public List<EmailAddress> generateEmailAddresses(int size) {
        List<EmailAddress> emailAddresses = new ArrayList<>();
        if (size == 0) {
            return emailAddresses;
        }

        for (int i = 0; i < size; i++) {
            emailAddresses.add(generateEmailAddress(false));
        }

        int primaryIndex = FAKER.number().numberBetween(-1, size - 1);
        if (primaryIndex > -1 && primaryIndex < size) {
            emailAddresses.get(primaryIndex).set_primary(true);
        }

        return emailAddresses;
    }

    @Override
    public EmailAddress generateEmailAddress(boolean primary) {
        String localPart = Integer.toString(Math.abs(UUID.randomUUID().hashCode()));
        String email = FAKER.internet().emailAddress();
        email = email.replaceAll("[\\uD83D\\uFFFD\\uFE0F\\u203C\\u3010\\u3011\\u300A\\u166D\\u200C\\u202A\\u202C\\u2049\\u20E3\\u300B\\u300C\\u3030\\u065F\\u0099\\u0F3A\\u0F3B\\uF610\\uFFFC]", "");
        email = email.replace("@", String.format("%s@", localPart));

        return EmailAddress.builder()
                .value(email)
                .is_primary(primary)
                .build();
    }

    @Override
    public PhoneNumber generatePhoneNumber(boolean primary) {
        return PhoneNumber.builder()
                .value(FAKER.phoneNumber().phoneNumber())
                .is_primary(primary)
                .build();
    }

    @Override
    public Gender generateGender() {
        String[] genders = {"Male", "Female"};
        int atIndex = FAKER.random().nextInt(0, genders.length - 1);
        return Gender.builder()
                .code(genders[atIndex])
                .description("")
                .build();
    }

    @Override
    public List<PhoneNumber> generatePhoneNumbers(int size) {
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        if (size == 0) {
            return phoneNumbers;
        }

        for (int i = 0; i < size; i++) {
            phoneNumbers.add(generatePhoneNumber(false));
        }

        int primaryIndex = FAKER.number().numberBetween(-1, size - 1);
        if (primaryIndex > -1 && primaryIndex < size) {
             phoneNumbers.get(primaryIndex).set_primary(true);
        }

        return phoneNumbers;
    }

    @Override
    public List<PhoneNumber> generatePhoneNumbers() {
        int size = FAKER.number().numberBetween(0, SIZE_OF_PHONE_NUMBERS);
        return generatePhoneNumbers(size);
    }
}
