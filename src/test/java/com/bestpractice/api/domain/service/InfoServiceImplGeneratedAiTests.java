package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(InfoServiceImplGeneratedAiTests.class)
class InfoServiceImplGeneratedAiTests {

    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;
    private Info info;

    @BeforeEach
    void setUp() {
        infoRepository = new InfoPersistentRepository() {
            @Override
            public String newId() {
                return "testId";
            }

            @Override
            public List<Info> findAll() {
                return null;
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
            public Info replace(String id, Info info) {
                return null;
            }

            @Override
            public boolean removeById(String id) {
                return false;
            }
        };
        infoService = new InfoServiceImpl(infoRepository);
        info = new Info();
        info.setId("testId");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
    }

    @Test
    void getInfos() {
        // GIVEN: Setup the context
        // WHEN: Call the getInfos method
        // THEN: Verify the returned list contains the correct InfoResponses
        List<InfoResponse> responses = infoService.getInfos();
        assertEquals(1, responses.size());
        InfoResponse response = responses.get(0);
        assertEquals("testId", response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void getInfo() {
        // GIVEN: Setup the context
        // WHEN: Call the getInfo method with the ID
        // THEN: Verify the returned InfoResponse matches the Info object
        InfoResponse response = infoService.getInfo("testId");
        assertEquals("testId", response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void updateInfo() {
        // GIVEN: Setup the context
        // WHEN: Call the updateInfo method with the ID and a request
        // THEN: Verify the returned InfoResponse matches the updated Info object
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse response = infoService.updateInfo("testId", request);
        assertEquals("testId", response.getId());
        assertEquals("Updated Title", response.getTitle());
        assertEquals("Updated Description", response.getDescription());
    }

    @Test
    void generateInfo() {
        // GIVEN: Setup the context
        // WHEN: Call the generateInfo method with a request
        // THEN: Verify the returned InfoResponse matches the generated Info object
        InfoRequest request = new InfoRequest();
        request.setTitle("Generated Title");
        request.setDescription("Generated Description");
        InfoResponse response = infoService.generateInfo(request);
        assertEquals("testId", response.getId());
        assertEquals("Generated Title", response.getTitle());
        assertEquals("Generated Description", response.getDescription());
    }
}
