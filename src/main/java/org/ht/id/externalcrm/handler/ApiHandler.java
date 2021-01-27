package org.ht.id.externalcrm.handler;

import lombok.RequiredArgsConstructor;
import org.ht.id.externalcrm.dto.CreationRequest;
import org.ht.id.externalcrm.model.Profile;
import org.ht.id.externalcrm.service.IProfileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ApiHandler {
    private final IProfileService profileService;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileService.findAll(), Profile.class);
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Flux<CreationRequest> creationRequest = request.bodyToFlux(CreationRequest.class);
        Flux<Profile> creationResult = creationRequest
                .flatMap(t -> profileService.generateBatch(t.getBeginDate(), t.getEndDate(), t.getNumbersPerDay()))
                .take(5);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(creationResult, Profile.class);
    }
}
