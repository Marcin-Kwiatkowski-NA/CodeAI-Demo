package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestAi.class)
class InfoServiceImplGeneratedAiTests {

    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;
    private Info info;

    @BeforeEach
    void setUp() {
        infoRepository = new InfoPersistentRepository() {};
        info = new Info();
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    void getInfos() {
        // GIVEN: Assume there are some Info entities in the repository
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        infoRepository.newId();
        infoRepository.insert(info);

        // WHEN: Retrieve all Info entities
        List<InfoResponse> responses = infoService.getInfos();

        // THEN: Verify that the responses contain the correct data
        assertEquals(1, responses.size());
        InfoResponse response = responses.get(0);
        assertEquals("1", response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void getInfo() {
        // GIVEN: Assume there is an Info entity in the repository with id "1"
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        infoRepository.newId();
        infoRepository.insert(info);

        // WHEN: Retrieve the Info entity with id "1"
        InfoResponse response = infoService.getInfo("1");

        // THEN: Verify that the response contains the correct data
        assertEquals("1", response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void updateInfo() {
        // GIVEN: Assume there is an Info entity in the repository with id "1"
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        infoRepository.newId();
        infoRepository.insert(info);

        // WHEN: Update the Info entity with id "1" with a new InfoRequest
        InfoRequest req = new InfoRequest("Test Title", "New Description");
        InfoResponse response = infoService.updateInfo("1", req);

        // THEN: Verify that the response contains the correct data
        assertEquals("1", response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("New Description", response.getDescription());
    }

    @Test
    void generateInfo() {
        // GIVEN: Assume there is a new ID
        infoRepository.newId();

        // WHEN: Generate a new Info entity
        InfoResponse response = infoService.generateInfo(new InfoRequest("New Title", "New Description"));

        // THEN: Verify that the response contains the correct data
        assertEquals("1", response.getId());
        assertEquals("New Title", response.getTitle());
        assertEquals("New Description", response.getDescription());
    }

    @Test
    void deleteInfo() {
        // GIVEN: Assume there is an Info entity in the repository with id "1"
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");
        infoRepository.newId();
        infoRepository.insert(info);

        // WHEN: Delete the Info entity with id "1"
        infoService.deleteInfo("1");

        // THEN: Verify that the Info entity with id "1" is removed from the repository
        assertNull(infoRepository.findById("1"));
    }
}
