package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(InfoServiceGeneratedAiTests.class)
class InfoServiceImplGeneratedAiTests {

    private InfoServiceImpl infoService;
    private InfoPersistentRepository infoRepository;

    @BeforeEach
    void setUp() {
        infoRepository = new InfoPersistentRepository();
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    void getInfos_returnsAllInfos() {
        // GIVEN: Assume there are two Info entities in the repository
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title 1");
        info1.setDescription("Description 1");

        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title 2");
        info2.setDescription("Description 2");

        infoRepository.findAll().add(info1);
        infoRepository.findAll().add(info2);

        // WHEN: Call the getInfos method
        List<InfoResponse> responses = infoService.getInfos();

        // THEN: Verify that the returned list contains the correct number of InfoResponse objects
        assertEquals(2, responses.size());

        // Verify the content of the responses
        InfoResponse response1 = responses.get(0);
        assertEquals("1", response1.getId());
        assertEquals("Title 1", response1.getTitle());
        assertEquals("Description 1", response1.getDescription());

        InfoResponse response2 = responses.get(1);
        assertEquals("2", response2.getId());
        assertEquals("Title 2", response2.getTitle());
        assertEquals("Description 2", response2.getDescription());
    }

    @Test
    void getInfo_returnsInfoById() {
        // GIVEN: Assume there is an Info entity in the repository with id "1"
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title 1");
        info.setDescription("Description 1");

        infoRepository.findById("1").add(info);

        // WHEN: Call the getInfo method with id "1"
        InfoResponse response = infoService.getInfo("1");

        // THEN: Verify that the returned InfoResponse object has the correct data
        assertEquals("1", response.getId());
        assertEquals("Title 1", response.getTitle());
        assertEquals("Description 1", response2.getDescription());
    }

    @Test
    void updateInfo_updatesInfoById() {
        // GIVEN: Assume there is an Info entity in the repository with id "1"
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title 1");
        info.setDescription("Description 1");

        infoRepository.findById("1").add(info);

        // GIVEN: Create an InfoRequest object with updated title and description
        InfoRequest request = new InfoRequest();
        request.setTitle("Updated Title");
        request.setDescription("Updated Description");

        // WHEN: Call the updateInfo method with id "1" and the InfoRequest object
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN: Verify that the returned InfoResponse object has the correct data
        assertEquals("1", response.getId());
        assertEquals("Updated Title", response.getTitle());
        assertEquals("Updated Description", response2.getDescription());
    }
}