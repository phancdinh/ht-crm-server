package org.ht.id.externalcrm.repository;

import org.ht.id.externalcrm.model.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProfileRepository {
    Flux<Profile> findAll();
    Mono<Profile> findById(String id);
    Mono<Profile> create(Profile profile);
    Flux<Profile> bulkInsert(Flux<Profile> profiles);
}
