package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InfoServiceImplGeneratedAiTests {

    private InfoPersistentRepository infoRepository;
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        infoRepository = Mockito.mock(InfoPersistentRepository.class);
        infoService = new InfoServiceImpl(infoRepository);
    }

    @Test
    public void testGetInfosReturnsList() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("1", responses.get(0).getId());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoReturnsInfoResponse() {
        // GIVEN
        Info info = new Info();
        info.setId("1");
        info.setTitle("Title");
        info.setDescription("Description");
        when(infoRepository.findById("1")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("1");

        // THEN
        assertNotNull(response);
        assertEquals("1", response.getId());
        assertEquals("Title", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("1"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Description");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertNotNull(response);
        assertEquals("New Title", response.getTitle());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("Old Title");
        existingInfo.setDescription("Old Description");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("New Title");
        request.setDescription("New Description");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        when(infoRepository.newId()).thenReturn("1");
        Info info = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.generateInfo(request);

        //// THEN
        assertNotNull(response);
        assertEquals("1", response.getId());
        assertEquals("Title", response.getTitle());
    }

    @Test
    public void testGenerateInfoThrowsConflict() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(any(Info.class))).thenThrow(new Conflict());

        // WHEN & THEN
        assertThrows(Conflict.class, () -> infoService.generateInfo(request));
    }

    @Test
    public void testGenerateInfoThrowsInternalServerError() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Description");

        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.generateInfo(request));
    }

    @Test
    public void testDeleteInfoSuccess() {
        // GIVEN
        when(infoRepository.removeById("1")).thenReturn(true);

        // WHEN
        assertDoesNotThrow(() -> infoService.deleteInfo("1"));

        // THEN
        verify(infoRepository, times(1)).removeById("1");
    }

    @Test
    public void testDeleteInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.removeById("1")).thenThrow(new RuntimeException("Delete error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.deleteInfo("1"));
    }
}
