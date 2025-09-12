package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.bestpractice.api.domain.model.Info;
import com.bestpractice.api.domain.service.InfoService;
import com.bestpractice.api.domain.repository.InfoPersistentRepository;

class InfoServiceTest {

    private InfoPersistentRepository repository;
    private InfoService infoService;

    @BeforeEach
    void setUp() {
        repository = new InfoPersistentRepository();
        infoService = new InfoService(repository);
    }

    @Test
    void getInfo_validId_returnsInfo() {
        // Arrange
        Long validId = 1L;
        Info info = new Info(validId, "Test Info");
        repository.save(info);

        // Act
        Info result = infoService.getInfo(validId);

        // Assert
        assertNotNull(result);
        assertEquals(validId, result.getId());
        assertEquals("Test Info", result.getContent());
    }

    @Test
    void getInfo_invalidId_throwsException() {
        // Arrange
        Long invalidId = 999L;
        InfoService infoService = new InfoService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> infoService.getInfo(invalidId));
    }

    @Test
    void getInfo_nullId_throwsException() {
        // Arrange
        Long nullId = null;
        InfoService infoService = new InfoService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> infoService.getInfo(nullId));
    }
}
