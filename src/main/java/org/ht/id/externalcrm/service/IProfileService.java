package org.ht.id.externalcrm.service;

import org.ht.id.externalcrm.model.Profile;
import reactor.core.publisher.Flux;

import java.util.Date;

public interface IProfileService {
    Flux<Profile> findAll();
    Profile generate(Date createdDate);
    Flux<Profile> generateBatch(Long beginDate, Long endDate, int numbersPerDay);
}
