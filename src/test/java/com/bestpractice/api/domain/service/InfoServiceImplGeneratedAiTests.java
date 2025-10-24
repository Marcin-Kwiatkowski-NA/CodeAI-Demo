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
import org.mockito.Mockito;

import java.util.Arrays;
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
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1", result.get(0).getId());
        assertEquals("Title", result.get(0).getTitle());
        assertEquals("Description", result.get(0).getDescription());
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
        info.setId("2");
        info.setTitle("Title2");
        info.setDescription("Description2");
        when(infoRepository.findById("2")).thenReturn(info);

        // WHEN
        InfoResponse result = infoService.getInfo("2");

        // THEN
        assertNotNull(result);
        assertEquals("2", result.getId());
        assertEquals("Title2", result.getTitle());
        assertEquals("Description2", result.getDescription());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("x")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("x"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("3");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDescription");
        when(infoRepository.findById("3")).thenReturn(existingInfo);

        InfoRequest request = Mockito.mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("3");
        updatedInfo.setTitle("NewTitle");
        updatedInfo.setDescription("NewDescription");
        when(request.convert("3")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("3", request);

        // THEN
        assertNotNull(result);
        assertEquals("3", result.getId());
        assertEquals("NewTitle", result.getTitle());
        assertEquals("NewDescription", result.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("bad")).thenThrow(new RuntimeException("Not found"));
        InfoRequest request = Mockito.mock(InfoRequest.class);

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("bad", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("4");
        when(infoRepository.findById("4")).thenReturn(existingInfo);

        InfoRequest request = Mockito.mock(InfoRequest.class);
        Info updatedInfo = new Info();
        updatedInfo.setId("4");
        when(request.convert("4")).thenReturn(updatedInfo);
        when(infoRepository.insert(updatedInfo)).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("4", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = Mockito.mock(InfoRequest.class);
       Info info = new Info();
        info.setId("5");
        info.setTitle("Title5");
        info.setDescription("Description5");
        when(infoRepository.newId()).thenReturn("5");
        when(request.convert("5")).thenReturn(info);
        when(infoRepository.insert(info)).thenReturn(info);

        // WHEN
        InfoResponse result = infoService.generateInfo(request);

        // THEN
        assertNotNull(result);
        assertEquals("5", result.getId());
        assertEquals("Title5", result.getTitle());
        assertEquals("Description5", result.getDescription());
    }

    @Test
    public void testGenerateInfoThrowsConflict() {
        // GIVEN
        InfoRequest request = Mockito.mock(InfoRequest.class);
        Info info = new Info();
        info.setId("6");
        when(infoRepository.newId()).thenReturn("6");
        when(request.convert("6")).thenReturn(info);
        when(infoRepository.insert(info)).thenThrow(new Conflict("Conflict"));

        // WHEN & THEN
        assertThrows(Conflict.class, () -> infoService.generateInfo(request));
    }

    @Test
    public void testGenerateInfoThrowsInternalServerError() {
        // GIVEN
        InfoRequest request = Mockito.mock(InfoRequest.class);
        Info info = new Info();
        info.setId("7");
        when(infoRepository.newId()).thenReturn("7");
        when(request.convert("7")).thenReturn(info);
        when(infoRepository.insert(info)).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.generateInfo(request));
    }

    @Test
    public void testDeleteInfoSuccess() {
        // GIVEN
        when(infoRepository.removeById("8")).thenReturn(true);

        // WHEN & THEN
        assertDoesNotThrow(() -> infoService.deleteInfo("8"));
    }

    @Test
    public void testDeleteInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.removeById("9")).thenThrow(new RuntimeException("Delete error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.deleteInfo("9"));
    }
}
