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
    public void testSetAndGetTitle() {
        // GIVEN: a title value
        String title = "Sample Title";

        // WHEN: setting the title
        infoRequest.setTitle(title);

        // THEN: the retrieved title should match the set value
        assertEquals(title, infoRequest.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN: a description value
        String description = "Sample Description";

        // WHEN: setting the description
        infoRequest.setDescription(description);

        // THEN: the retrieved description should match the set value
        assertEquals(description, infoRequest.getDescription());
    }

    @Test
    public void testConvertCreatesInfoWithCorrectValues() {
        // GIVEN: an InfoRequest with title and description set
        String id = "123";
        String title = "Test Title";
        String description = "Test Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: Info should have the correct id, title, and description
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN: an InfoRequest with null title and description
        String id = "456";

        // WHEN: converting to Info
        Info info = infoRequest.convert(id);

        // THEN: Info should have the correct id and null title/description
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullId() {
        // GIVEN: an InfoRequest with title and description set
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info with null id
        Info info = infoRequest.convert(null);

        // THEN: Info should have null id but correct title and description
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithAllNullFields() {
        // GIVEN: an InfoRequest with all fields null
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN: converting to Info with null id
        Info info = infoRequest.convert(null);

        // THEN: Info should have all fields null
        assertNull(info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenTitleIsNull() {
        // GIVEN: an InfoRequest with null title and valid description
        infoRequest.setTitle(null);
        infoRequest.setDescription("Valid Description");

        // WHEN: converting to Info
        Info info = infoRequest.convert("789");

        // THEN: Info should have null title and correct description
        assertNull(info.getTitle());
        assertEquals("Valid Description", info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWhenDescriptionIsNull() {
        // GIVEN: an InfoRequest with valid title and null description
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription(null);

        // WHEN: converting to Info
        Info info = infoRequest.convert("987");

        // THEN: Info should have correct title and null description
        assertEquals("Valid Title", info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithEmptyStrings() {
        // GIVEN: an InfoRequest with empty title and description
        infoRequest.setTitle("");
        infoRequest.setDescription("");

        // WHEN: converting to Info
        Info info = infoRequest.convert("111");

        // THEN: Info should have empty title and description
        assertEquals("", info.getTitle());
        assertEquals("", info.getDescription());
        assertEquals("111", info.getId());
    }
}
