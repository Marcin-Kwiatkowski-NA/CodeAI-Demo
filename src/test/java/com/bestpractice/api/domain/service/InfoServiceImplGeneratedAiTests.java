package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class InfoGeneratedAiTests {

    @Test
    void getInfos() {
        // GIVEN: An empty list of Info entities
        // WHEN: The getInfos method is called
        // THEN: A list of InfoResponse objects is returned, each representing an empty Info entity
        InfoPersistentRepository infoRepository = mock(InfoPersistentRepository.class);
        List<Info> infoEntities = new ArrayList<>();
        when(infoRepository.findAll()).thenReturn(infoEntities);
        InfoServiceImpl infoService = new InfoServiceImpl(infoRepository);
        List<InfoResponse> result = infoService.getInfos();
        assertEquals(0, result.size());
    }

    @Test
    void getInfo() {
        // GIVEN: An Info entity with specific id, title, and description
        // WHEN: The getInfo method is called with the given id
        // THEN: An InfoResponse object is returned, containing the same id, title, and description as the Info entity
        InfoPersistentRepository infoRepository = mock(InfoPersistentRepository.class);
        Info info = new Info();
        info.setId("testId");
        info.setTitle("testTitle");
        info.setDescription("testDescription");
        when(infoRepository.findById("testId")).thenReturn(info);
        InfoServiceImpl infoService = new InfoServiceImpl(infoRepository);
        InfoResponse result = infoService.getInfo("testId");
        assertEquals("testId", result.getId());
        assertEquals("testTitle", result.getTitle());
        assertEquals("testDescription", result.getDescription());
    }

    @Test
    void updateInfo() {
        // GIVEN: An existing Info entity with specific id, title, and description
        // WHEN: The updateInfo method is called with the given id and InfoRequest
        // THEN: The Info entity is updated with the new title and description from the InfoRequest
        // AND: An InfoResponse object is returned, containing the updated title and description
        InfoPersistentRepository infoRepository = mock(InfoPersistentRepository.class);
        Info info = new Info();
        info.setId("testId");
        info.setTitle("testTitle");
        info.setDescription("testDescription");
        when(infoRepository.findById("testId")).thenReturn(info);
        InfoRequest req = new InfoRequest();
        req.setTitle("newTitle");
        req.setDescription("newDescription");
        InfoServiceImpl infoService = new InfoServiceImpl(infoRepository);
        InfoResponse result = infoService.updateInfo("testId", req);
        assertEquals("testId", result.getId());
        assertEquals("newTitle", result.getTitle());
        assertEquals("newDescription", result.getDescription());
    }

    @Test
    void generateInfo() {
        // GIVEN: A new InfoRequest
        // WHEN: The generateInfo method is called with the InfoRequest
        // THEN: A new Info entity is created with the title and description from the InfoRequest
        // AND: An InfoResponse object is returned, containing the id, title, and description of the new Info entity
        InfoPersistentRepository infoRepository = mock(InfoPersistentRepository.class);
        InfoRequest req = new InfoRequest();
        req.setTitle("newTitle");
        req.setDescription("newDescription");
        when(infoRepository.newId()).thenReturn("newId");
        when(infoRepository.insert(any())).thenThrow(new Conflict(null));
        InfoServiceImpl infoService = new InfoServiceImpl(infoRepository);
        InfoResponse result = infoService.generateInfo(req);
        assertEquals("newId", result.getId());
        assertEquals("newTitle", result.getTitle());
        assertEquals("newDescription", result.getDescription());
    }

    @Test
    void deleteInfo() {
        // GIVEN: An existing Info entity with specific id
        // WHEN: The deleteInfo method is called with the given id
        //        assertEquals(1, infoRepository.removeById("testId"));
    }
}