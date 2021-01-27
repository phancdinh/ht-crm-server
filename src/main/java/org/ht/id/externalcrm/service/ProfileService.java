package org.ht.id.externalcrm.service;

import lombok.RequiredArgsConstructor;
import org.ht.id.externalcrm.model.Profile;
import org.ht.id.externalcrm.repository.IProfileRepository;
import org.ht.id.externalcrm.shared.IProfileGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProfileService implements IProfileService {

    private final IProfileRepository profileRepository;
    private final IProfileGenerator profileGenerator;

    @Override
    public Flux<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile generate(Date createdDate) {
        var name = profileGenerator.getFaker().name();
        var fullName = name.fullName();
        var nameElements = fullName.split(" ");
        var address = profileGenerator.getFaker().address();
        long dob = profileGenerator.generateBirthdayTicks(19, 65);

        return Profile.builder()
                .full_name(fullName)
                .first_name(nameElements[nameElements.length - 1])
                .last_name(nameElements[0])
                .pob(address.city())
                .hometown(address.city())
                .dob(dob)
                .nationalities(Collections.singletonList(profileGenerator.generateNationality()))
                .national_id(profileGenerator.generateNationalId(fullName, dob))
                .citizen_id(profileGenerator.generateCitizenId(fullName, dob))
                .email_addresses(profileGenerator.generateEmailAddresses())
                .phone_numbers(profileGenerator.generatePhoneNumbers())
                .gender(profileGenerator.generateGender())
                .created_date(createdDate)
                .build();
    }

    @Override
    public Flux<Profile> generateBatch(Long beginDate, Long endDate, int numbersPerDay) {
        long timestampBegin = beginDate * 1000;
        long timestampEnd = endDate == null ? System.currentTimeMillis() : endDate * 1000 ;
        Date begin = new Date(timestampBegin);
        Date end = new Date(timestampEnd);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(begin);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(end);

        List<Profile> newProfiles = new ArrayList<>();
        while (calendar.before(endCalendar)) {
            calendar.add(Calendar.DATE, 1);

            IntStream.range(0, numbersPerDay).forEach($ -> {
                Profile item = generate(calendar.getTime());
                newProfiles.add(item);
            });
        }

        return profileRepository.bulkInsert(Flux.fromIterable(newProfiles));
    }
}
