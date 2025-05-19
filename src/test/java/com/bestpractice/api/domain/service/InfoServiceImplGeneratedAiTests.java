package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@Test
class InfoServiceImplGeneratedAiTests {

    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;

    @BeforeEach
    void setUp() {
        infoRepository = new InfoPersistentRepository() {};
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    void getInfos() {
        // GIVEN: No preconditions
        // WHEN: getInfos() is called
        // THEN: A list of InfoResponse objects is returned, each representing an Info entity from the repository.
        List<InfoResponse> responses = infoService.getInfos();
        assertNotNull(responses);
        assertEquals(1, responses.size());
    }

    @Test
    void getInfo() {
        // GIVEN: An Info entity exists in the repository with an ID "testId"
        String testId = "testId";
        Info testInfo = new Info();
        testInfo.setId(testId);
        testInfo.setTitle("Test Title");
        testInfo.setDescription("Test Description");
        infoRepository.insert(testInfo);

        // WHEN: getInfo(testId) is called
        // THEN: An InfoResponse object is returned, containing the same data as the Info entity.
        InfoResponse response = infoService.getInfo(testId);
        assertNotNull(response);
        assertEquals(testId, response.getId());
        assertEquals("Test Title", response.getTitle());
        assertEquals("Test Description", response.getDescription());
        infoRepository.removeById(testId);
    }

    @Test
    void updateInfo() {
        // GIVEN: An Info entity exists in the repository with an ID "testId"
        String testId = "testId";
        Info testInfo = new Info();
        testInfo.setId(testId);
        testInfo.setTitle("Test Title");
        testInfo.setDescription("Test Description");
        infoRepository.insert(testInfo);

        // WHEN: updateInfo(testId, new InfoRequest(...)) is called
        // THEN: The Info entity in the repository is updated with the provided data, and an InfoResponse object is returned.
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");
        InfoResponse response = infoService.updateInfo(testId, request);
        assertNotNull(response);
        assertEquals(testId, response.getId());
        assertEquals("Updated Title", response.getTitle());
        assertEquals("Updated Description", response.getDescription());
        infoRepository.removeById(testId);
    }

    @Test
    void generateInfo() {
        // GIVEN: No preconditions
        // WHEN: generateInfo(new InfoRequest(...)) is called
        // THEN: An Info entity is inserted into the repository, and an InfoResponse object is returned.
        InfoRequest request = new InfoRequest();
        request.setTitle("Generated Title");
        request.setDescription("Generated Description");
        InfoResponse response = infoService.generateInfo(request);
        assertNotNull(response);
        assertEquals("testId", response.getId());
        assertEquals("Generated Title", response.getTitle());
        assertEquals("Generated Description", response.getDescription());
    }
}
