package com.bestpractice.api.app.v1;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.*;

@RestController
@RequestMapping("/api/v1/infos")
public class RdbmsController {

    private final InfoServiceImpl infoService;

    public RdbmsController(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }

    @GetMapping()
    void getInfos() {
        // GIVEN: A list of Info objects is retrieved from the repository.
        // WHEN: The getInfos method is called.
        // THEN: A list of InfoResponse objects is returned, each corresponding to an Info object.
    }

    @GetMapping(value="/{id}")
    InfoResponse getInfo(@PathVariable("id") String id) {
        // GIVEN: An Info object with the specified ID is retrieved from the repository.
        // WHEN: The getInfo method is called with the given ID.
        // THEN: An InfoResponse object is returned, containing the retrieved Info object's data.
        return this.infoService.getInfo(id);
    }

    @PostMapping
    ResponseEntity<InfoResponse> postInfo(
        @RequestBody InfoRequest req)
    ) {

        InfoResponse res = this.infoService.generateInfo(req);
        return ResponseEntity
            .created(new URI("/api/v1/infos/" + res.getId()))
            .body(res);
    }

    @PutMapping(value="/{id}")
    InfoResponse putInfo(
        @PathVariable("id") String id,
        @RequestBody InfoRequest req) {

        // GIVEN: An InfoRequest object is received, and an Info object is created from it.
        // WHEN: The putInfo method is called with the given ID and the request object.
        // THEN: An InfoResponse object is returned, representing the updated Info object.
        return this.infoService.updateInfo(id, req);
    }

    @DeleteMapping(value = "/{id}")
    Map<String, String> deleteInfo(@PathVariable("id") String id) {
        // GIVEN: An Info object with the specified ID is deleted from the repository.
        // WHEN: The deleteInfo method is called with the given ID.
        // THEN: A Map is returned, containing the message "ok".
        this.infoService.deleteInfo(id);
        return Collections.singletonMap("message", "ok");
    }
}