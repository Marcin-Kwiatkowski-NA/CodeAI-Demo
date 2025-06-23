package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.ExtensionTest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.best prática.api.domain.model.InfoRequest;
import com.best prática.api.domain.model.InfoResponse;
import com.best prática.api.domain.service.InfoServiceImpl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.best prática.api.common.exception.BadRequest;
import com.best prática.api.common.exception.Conflict;
import com.best prática.api.common.exception.InternalServerError;
import com.best prática.api.infrastrucuture.entity.Info;
import com.best prática.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import com.best prática.api.app.v1.RdbmsController;

class RdbmsController {

    private final InfoServiceImpl infoService;

    public RdbmsController(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }

    @RestController
    @RequestMapping("/api/1/infos")
    public class RdbmsController {

        @GetMapping()
        public List<InfoResponse> getInfos() {
            return this.infoService.getInfos();
        }

        @GetMapping(value="/{id}")
        public InfoResponse getInfo(@PathVariable("id") String id) {
            return this.infoService.getInfo(id);
        }

        @PostMapping(
            @RequestBody
            InfoRequest req)
            throws URISyntaxException {
            InfoResponse res = this.infoService.generateInfo(req);
            return ResponseEntity.created(new URI("/api/1/infos/" + res.getId()))
                    .body(res);
        }

        @PutMapping(
            value="/{id}")
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
}
