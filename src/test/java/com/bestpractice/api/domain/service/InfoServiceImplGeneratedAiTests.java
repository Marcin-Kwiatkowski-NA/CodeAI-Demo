package com.bestpractice.api.domain.service;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastructure.entity.Info;
import com.bestpractice.api.infrastructure.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class InfoServiceImplGeneratedAiTests {

    @Mock
    private InfoPersistentRepository infoRepository;

    @InjectMocks
    private InfoServiceImpl infoService;

    private Info info;
    private InfoRequest infoRequest;
    private InfoResponse infoResponse;

    @BeforeEach
    void setUp() {
        info = new Info();
        info.setId("1");
        info.setTitle("Test Title");
        info.setDescription("Test Description");

        infoRequest = new InfoRequest();
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");

        infoResponse = new InfoResponse("1", "Test Title", "Test Description");
    }

    @Test
    void getInfos_ShouldReturnListOfInfoResponses() {
        // GIVEN
        when(infoRepository.findAll()).thenReturn(Collections.singletonList(info));

        // WHEN
        List<InfoResponse> result = infoService.getInfos();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        verify(infoRepository, times(1)).findAll();
    }

    @Test
    void getInfo_ShouldReturnInfoResponse() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(Optional.of(info));

        // WHEN
        InfoResponse result = infoService.getInfo("1");

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        verify(infoRepository, times(1)).findById("1");
    }

    @Test
    void updateInfo_ShouldReturnUpdatedInfoResponse() {
        // GIVEN
        when(infoRepository.findById("1")).thenReturn(Optional.of(info));
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHEN
        InfoResponse result = infoService.updateInfo("1", infoRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        verify(infoRepository, times(1)).findById("1");
        verify(infoRepository, times(1)).insert(any(Info.class));
    }

    @Test
    void generateInfo_ShouldReturnGeneratedInfoResponse() {
        // GIVEN
        when(infoRepository.newId()).thenReturn("1");
        when(infoRepository.insert(any(Info.class))).thenReturn(info);

        // WHEN
        InfoResponse result = infoService.generateInfo(infoRequest);

        // THEN
        assertThat(result.getId()).isEqualTo("1");
        verify(infoRepository, times(1)).newId();
        verify(infoRepository, times(1)).insert(any(Info.class));
    }
}

This corrected version includes the missing closing brace `}