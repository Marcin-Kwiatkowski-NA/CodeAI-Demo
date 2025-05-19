package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;

class RdbmsControllerGeneratedAiTests {

    private RdbmsController rdbmsController;
    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;

    @BeforeEach
    void setUp() {
        InfoServiceImpl infoService = new InfoServiceImpl(new InfoPersistentRepository() {
            @Override
            public List<Info> findAll() {
                List<Info> infos = new java.util.ArrayList<>();
                Info info1 = new Info();
                info1.setId("id1");
                info1.setTitle("Title1");
                info1.setDescription("Description1");
                infos.add(info1);
                return infos;
            }

            @Override
            public Info findById(String id) {
                return null;
            }

            @Override
            public Info insert(Info info) {
                return info;
            }

            @Override
            public Info removeById(String id) {
                return null;
            }

            @Override
            public Info newId() {
                return null;
            }
        });
        this.infoService = infoService;
        this.rdbmsController = new RdbmsController(infoService);
    }

    @Test
    void getInfos() {
        // GIVEN
        // WHEN
        List<InfoResponse> result = rdbmsController.getInfos();

        // THEN
        assertEquals(1, result.size());
        assertTrue(result.get(0).getId().equals("id1"));
        assertEquals("Title1", result.get(0).getTitle());
        assertEquals("Description1", result.get(0).getDescription());
    }

    @Test
    void getInfo() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    void postInfo() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    void putInfo() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    void deleteInfo() {
        // GIVEN
        // WHEN
        // THEN
    }
}
