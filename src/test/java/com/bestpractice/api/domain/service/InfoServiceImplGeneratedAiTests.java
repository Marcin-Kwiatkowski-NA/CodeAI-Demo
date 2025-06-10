package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    @BeforeEach
    public void setUp() {
        reset(infoRepository);
    }

    @Test
    public void testGetInfos_Success() {
        // GIVEN
        List<Info> mockInfos = Arrays.asList(
                new Info(),
                new Info()
        );
        when(infoRepository.findAll()).thenReturn(mockInfos);

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    public void testGetInfos_InternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> infoService.getInfos());
        assertNotNull(thrown);
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    public void testGetInfo_Success() {
        // GIVEN
        Info mockInfo = new Info();
        when(infoRepository.findById(anyString())).thenReturn(Optional.of(mockInfo));

        // WHEN
        InfoResponse result = infoService.getInfo("1");

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    public void testGetInfo_InternalServerError() {
        // GIVEN
        when(infoRepository.findById(anyString())).thenThrow(new RuntimeException("Database error"));

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> infoService.getInfo("1"));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateInfo_Success() {
        // GIVEN
        Info mockInfo = new Info();
        when(infoRepository.findById(anyString())).thenReturn(Optional.of(mockInfo));
        InfoRequest req = new InfoRequest();
        req.setTitle("New Title");
        req.setDescription("New Description");

        Info updatedInfo = new Info();
        when(infoRepository.insert(updatedInfo)).thenReturn(updatedInfo);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", req);

        // THEN
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("New Title", result.getTitle());
        verify(infoRepository, times(2)).findById(anyString());
        verify(infoRepository, times(1)).insert(updatedInfo);
    }

    @Test
    public void testUpdateInfo_BadRequest() {
        // GIVEN
        when(infoRepository.findById(anyString())).thenReturn(Optional.empty());

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> infoService.updateInfo("1", new InfoRequest()));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    public void testUpdateInfo_InternalServerError() {
        // GIVEN
        when(infoRepository.findById(anyString())).thenReturn(Optional.of(new Info()));
        doThrow(new RuntimeException("Database error")).when(infoRepository).insert(any(Info.class));

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () ->```java
        infoService.updateInfo("1", new InfoRequest()));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    public void testCreateInfo_Success() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("New Title");
        req.setDescription("New Description");

        Info createdInfo = new Info();
        when(infoRepository.insert(any(Info.class))).thenReturn(createdInfo);

        // WHEN
        InfoResponse result = infoService.createInfo(req);

        // THEN
        assertNotNull(result);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    public void testCreateInfo_Conflict() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("New Title");
        req.setDescription("New Description");

        doThrow(new Conflict("Duplicate entry")).when(infoRepository).insert(any(Info.class));

        // WHEN & THEN
        Conflict thrown = assertThrows(Conflict.class, () -> infoService.createInfo(req));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    public void testCreateInfo_InternalServerError() {
        // GIVEN
        InfoRequest req = new InfoRequest();
        req.setTitle("New Title");
        req.setDescription("New Description");

        doThrow(new RuntimeException("Database error")).when(infoRepository).insert(any(Info.class));

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> infoService.createInfo(req));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    public void testDeleteInfo_Success() {
        // GIVEN
        doNothing().when(infoRepository).deleteById("1");

        // WHEN
        infoService.deleteInfo("1");

        // THEN
        verify(infoRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteInfo_BadRequest() {
        // GIVEN
        doThrow(new RuntimeException("Not Found")).when(infoRepository).deleteById("1");

        // WHEN & THEN
        BadRequest thrown = assertThrows(BadRequest.class, () -> infoService.deleteInfo("1"));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteInfo_InternalServerError() {
        // GIVEN
        doThrow(new RuntimeException("Database error")).when(infoRepository).deleteById("1");

        // WHEN & THEN
        InternalServerError thrown = assertThrows(InternalServerError.class, () -> infoService.deleteInfo("1"));
        assertNotNull(thrown);
        verify(infoRepository, times(1)).deleteById("1");
    }
}