package com.bestpractice.api.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.bestpractice.api.infrastructure.entity.Info;

public class InfoRequestGeneratedAiTests {

    @InjectMocks
    private InfoRequest infoRequest;

    @Mock
    private Info info;

    @BeforeEach
    void setUp() {
        openMocks(this);
        infoRequest = new InfoRequest();
    }

    @Test
    void testSetTitle() {
        infoRequest.setTitle("Test Title");
        assertEquals("Test Title", infoRequest.getTitle());
    }

    @Test
    void testSetDescription() {
        infoRequest.setDescription("Test Description");
        assertEquals("Test Description", infoRequest.getDescription());
    }

    @Test
    void testConvertToInfo() {
        infoRequest.setTitle("Test Title");
        infoRequest.setDescription("Test Description");
        Info info = infoRequest.convertToInfo();
        assertNotNull(info);
        assertEquals("Test Title", info.getTitle());
        assertEquals("Test Description", info.getDescription());
    }
}

package com.bestpractice.api.domain.model;

public class InfoRequest {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Info convertToInfo() {
        Info info = new Info();
        info.setTitle(this.title);
        info.setDescription(this.description);
        return info;
    }
}

package com.bestpractice.api.infrastructure.entity;

public class Info {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
