package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testConvertCreatesInfoObjectWithCorrectValues() {
        // GIVEN: an InfoRequest with title and description set
        String title = "Test Title";
        String description = "Test Description";
        String id = "12345";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info object
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have the correct id, title, and description
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertWithNullValues() {
        // GIVEN: an InfoRequest with null title and description
        String id = "67890";

        // WHEN: converting to Info object
        Info info = infoRequest.convert(id);

        // THEN: the Info object should have the correct id and null title/description
        assertNotNull(info);
        assertEquals(id, info.getId());
        assertNull(info.getTitle());
        assertNull(info.getDescription());
    }

    @Test
    public void testConvertWithNullIdDoesNotThrowException() {
        // GIVEN: an InfoRequest with title and description set
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN: converting with null id
        Info info = infoRequest.convert(null);

        // THEN: the Info object should be created with null id
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals("Title", info.getTitle());
        assertEquals("Description", info.getDescription());
    }

    @Test
    public void testConvertWithNullIdAccessingIdThrowsException() {
        // GIVEN: an InfoRequest with title and description set
        infoRequest.setTitle("Title");
        infoRequest.setDescription("Description");

        // WHEN: converting with null id
        Info info = infoRequest.convert(null);

        // THEN: accessing length of null id should throw NullPointerException
        assertThrows(NullPointerException.class, () -> info.getId().length());
    }

    @Test
    public void testConvertWithNullTitleThrowsExceptionWhenAccessingTitleLength() {
        // GIVEN: an InfoRequest with null title and description set
        infoRequest.setTitle(null);
        infoRequest.setDescription("Description");

        // WHEN: converting to Info object
        Info info = infoRequest.convert("999");

        // THEN: accessing length of null title should throw NullPointerException
        assertThrows(NullPointerException.class, () -> info.getTitle().length());
    }

    @Test
    public void testConvertWithNullDescriptionThrowsExceptionWhenAccessingDescriptionLength() {
        // GIVEN: an InfoRequest with title set and null description
        infoRequest.setTitle("Title");
        infoRequest.setDescription(null);

        // WHEN: converting to Info object
        Info info = infoRequest.convert("888");

        // THEN: accessing length of null description should throw NullPointerException
        assertThrows(NullPointerException.class, () -> info.getDescription().length());
    }
}
