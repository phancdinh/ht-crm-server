package org.ht.id.externalcrm.repository;

import lombok.RequiredArgsConstructor;
import org.ht.id.externalcrm.model.Profile;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Repository
public class ProfileRepository implements IProfileRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Profile> findAll() {
        Query query = new Query();
        return mongoTemplate.find(query, Profile.class);
    }

    @Override
    public Mono<Profile> findById(String id) {
        return mongoTemplate.findById(id, Profile.class);
    }

    @Override
    public Mono<Profile> create(Profile profile) {
        return mongoTemplate.save(profile);
    }

    @Override
    public Flux<Profile> bulkInsert(Flux<Profile> profiles) {
        return profiles.buffer(10000).flatMap(rs -> mongoTemplate.insert(rs, Profile.class));
        //return profiles.flatMap(rs -> mongoTemplate.insert(profiles));
    }
}
