package com.bestpractice.api.app.v1;
import io.github.junyvr.junit5.MockExtension;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;

import org.springframework.web.bind.annotation.*;

import io.github.junyvr.junit5.MockExtension;

@RestController
@RequestMapping("/api/v1/infos")
public class RdbmsControllerGeneratedAiTests {

    private final InfoServiceImpl infoService;

    public RdbmsControllerGeneratedAiTests(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public List<InfoResponse> getInfos() {
        return this.infoService.getInfos();
    }

    @GetMapping(value="/{id}")
    public InfoResponse getInfo(@PathVariable("id") String id) {
        return this.infoService.getInfo(id);
    }

    @PostMapping
    public ResponseEntity<InfoResponse> postInfo(
        @RequestBody InfoRequest req)
        throws URISyntaxException {

        InfoResponse res = this.infoService.generateInfo(req);
        return ResponseEntity
            .created(new URI("/api/v1/infos/" + res.getId()))
            .body(res);
    }

    @PutMapping(value="/{id}")
    public InfoResponse putInfo(
        @PathVariable("id") String id,
        @RequestBody InfoRequest req) {

        return this.infoService.updateInfo(id, req);
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteInfo(@PathVariable("id") String id) {
        this.infoService.deleteInfo(id);
        return Collections.singletonMap("message", "ok");
    }
}