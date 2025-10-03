package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testGenerateInfoSuccess() {
        // GIVEN
        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.newId()).thenReturn("newId");
        Info info = request.convert("newId");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHENpackage com.bestpractice.api.domain.service;

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
    public void testGetInfosSuccess() {
        // GIVEN
        Info info1 = new Info();
        info1.setId("1");
        info1.setTitle("Title1");
        info1.setDescription("Desc1");
        Info info2 = new Info();
        info2.setId("2");
        info2.setTitle("Title2");
        info2.setDescription("Desc2");
        when(infoRepository.findAll()).thenReturn(Arrays.asList(info1, info2));

        // WHEN
        List<InfoResponse> responses = infoService.getInfos();

        // THEN
        assertEquals(2, responses.size());
        assertEquals("Title1", responses.get(0).getTitle());
    }

    @Test
    public void testGetInfosThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfos());
    }

    @Test
    public void testGetInfoSuccess() {
        // GIVEN
        Info info = new Info();
        info.setId("123");
        info.setTitle("TestTitle");
        info.setDescription("TestDesc");
        when(infoRepository.findById("123")).thenReturn(info);

        // WHEN
        InfoResponse response = infoService.getInfo("123");

        // THEN
        assertEquals("123", response.getId());
        assertEquals("TestTitle", response.getTitle());
    }

    @Test
    public void testGetInfoThrowsInternalServerError() {
        // GIVEN
        when(infoRepository.findById("123")).thenThrow(new RuntimeException("DB error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.getInfo("123"));
    }

    @Test
    public void testUpdateInfoSuccess() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        existingInfo.setTitle("OldTitle");
        existingInfo.setDescription("OldDesc");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("NewTitle");
        request.setDescription("NewDesc");

        Info updatedInfo = request.convert("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(updatedInfo);

        // WHEN
        InfoResponse response = infoService.updateInfo("1", request);

        // THEN
        assertEquals("NewTitle", response.getTitle());
        assertEquals("NewDesc", response.getDescription());
    }

    @Test
    public void testUpdateInfoThrowsBadRequest() {
        // GIVEN
        when(infoRepository.findById("1")).thenThrow(new RuntimeException("Not found"));

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        // WHEN & THEN
        assertThrows(BadRequest.class, () -> infoService.updateInfo("1", request));
    }

    @Test
    public void testUpdateInfoThrowsInternalServerErrorOnInsert() {
        // GIVEN
        Info existingInfo = new Info();
        existingInfo.setId("1");
        when(infoRepository.findById("1")).thenReturn(existingInfo);

        InfoRequest request = new InfoRequest();
        request.setTitle("Title");
        request.setDescription("Desc");

        when(infoRepository.insert(any(Info.class))).thenThrow(new RuntimeException("Insert error"));

        // WHEN & THEN
        assertThrows(InternalServerError.class, () -> infoService.updateInfo("1", request));
    }
