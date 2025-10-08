package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    public void setUp() {
        infoRequest = new InfoRequest();
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN: a title value
        String title = "Sample Title";

        // WHEN: setting the title
        infoRequest.setTitle(title);

        // THEN: the title should be retrievable and match the set value
        assertEquals(title, infoRequest.getTitle());
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN: a description value
        String description = "Sample Description";

        // WHEN: setting the description
        infoRequest.setDescription(description);

        // THEN: the description should be retrievable and match the set value
        assertEquals(description, infoRequest.getDescription());
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN: an InfoRequest with title and description
        String title = "Test Title";
        String description = "Test Description";
        String id = "12345";
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
    public void testConvertWithNullValues() {
        // GIVEN: an InfoRequest with null title and description
        String id = "67890";

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have null title and description but correct id
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
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
    public void testConvertHandlesNullTitleGracefully() {
        // GIVEN: an InfoRequest with null title and valid description
        infoRequest.setTitle(null);
        infoRequest.setDescription("Valid Description");

        // WHEN: converting to Info
        Info info = infoRequest.convert("id");

        // THEN: the Info object should have null title and correct description
        assertNotNull(info);
        assertNull(info.getTitle());
        assertEquals("Valid Description", info.getDescription());
    }

    @Test
    public void testConvertHandlesNullDescriptionGracefully() {
        // GIVEN: an InfoRequest with valid title and null description
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription(null);

        // WHEN: converting to Info
        Info info = infoRequest.convert("id");

        // THEN: the Info object should have correct title and null description
        assertNotNull(info);
        assertEquals("Valid Title", info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertHandlesEmptyId() {
        // GIVEN: an InfoRequest with valid title and description
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription("Valid Description");

        // WHEN: converting to Info with empty id
        Info info = infoRequest.convert("");

        // THEN: the Info object should have empty id and correct title and description
        assertNotNull(info);
        assertEquals("", info.getId());
        assertEquals("Valid Title", info.getTitle());
        assertEquals("Valid Description", info.getDescription());
    }
}
