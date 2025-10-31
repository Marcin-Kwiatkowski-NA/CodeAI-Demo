package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class InfoRequestGeneratedAiTests {

    private InfoRequest infoRequest;

    @BeforeEach
    void setUp() {
        infoRequest = new InfoRequest();
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN: a title value
        String title = "Sample Title";

        // WHEN: setting the title
        infoRequest.setTitle(title);

        // THEN: the retrieved title should match the set value
        assertEquals(title, infoRequest.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN: a description value
        String description = "Sample Description";

        // WHEN: setting the description
        infoRequest.setDescription(description);

        // THEN: the retrieved description should match the set value
        assertEquals(description, infoRequest.getDescription());
    }

    @Test
    void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN: an InfoRequest with title and description
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have the correct values
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    void testConvertWithNullValues() {
        // GIVEN: an InfoRequest with null title and description
        String id = "456";

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have the correct id and null title/description
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    void testConvertWithNullId() {
        // GIVEN: an InfoRequest with title and description
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info with null id
        Info info = infoRequest.convert(null);

        // THEN: the Info object should have null id but correct title and description
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    void testConvertWithEmptyId() {
        // GIVEN: an InfoRequest with title and description
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info with empty id
        Info info = infoRequest.convert("");

        // THEN: the Info object should have empty id but correct title and description
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    void testConvertDoesNotThrowExceptionWhenAllFieldsAreNull() {
        // GIVEN: an InfoRequest with null title and description
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN: converting to Info with null id
        Info info = infoRequest.convert(null);

        // THEN: the Info object should be created successfully with all fields null
        assertNotNull(info);
        assertNull(info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    void testConvertWithSpecialCharactersInFields() {
        // GIVEN: an InfoRequest with special characters in title and description
        String title = "!@#$%^&*()_+";
        String description = "<script>alert('xss')</script>";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info
        Info info = infoRequest.convert("specialId");

        // THEN: the Info object should preserve special characters
        assertNotNull(info);
        assertEquals("specialId", info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }
}
