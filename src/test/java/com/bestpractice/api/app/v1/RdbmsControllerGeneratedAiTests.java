package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.service.InfoServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RdbmsControllerGeneratedAiTests {

    private InfoServiceImpl infoService;
    private RdbmsController rdbmsController;

    @Test
    void createInfo() {
        InfoRequest request = new InfoRequest();
        request.setName("Test Info");

        InfoResponse response = rdbmsController.createInfo(request);

        assertNotNull(response);
        assertEquals("Test Info", response.getName());
    }

    @Test
    void updateInfo() {
        InfoRequest request = new InfoRequest();
        request.setName("Updated Info");

        InfoResponse response = rdbmsController.updateInfo("testId", request);

        assertNotNull(response);
        assertEquals("Updated Info", response.getName());
    }

    @Test
    void deleteInfo() {
        rdbmsController.deleteInfo("testId");
    }
}
