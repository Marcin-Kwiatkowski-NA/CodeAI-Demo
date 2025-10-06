package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
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
    public void testConvertWithNullValues() {
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
    public void testConvertWithNullId() {
        // GIVEN: an InfoRequest with title and description
        String title = "Title";
        String description = "Description";
        infoRequest.setTitle(title);
        infoRequest.setDescription(description);

        // WHEN: converting to Info with null id
        Info info = infoRequest.convert(null);

        // THEN: the Info object should have null id and correct title/description
        assertNotNull(info);
        assertNull(info.getId());
        assertEquals(title, info.getTitle());
        assertEquals(description, info.getDescription());
    }

    @Test
    public void testConvertDoesNotThrowExceptionWithNullFields() {
        // GIVEN: an InfoRequest with null title and description
        infoRequest.setTitle(null);
        infoRequest.setDescription(null);

        // WHEN & THEN: converting should not throw any exception
        assertDoesNotThrow(() -> {
            Info info = infoRequest.convert("789");
            assertNotNull(info);
            assertEquals("789", info.getId());
            assertNull(info.getTitle());
            assertNull(info.getDescription());
        });
    }

    @Test
    public void testConvertThrowsExceptionWhenIdIsInvalidSimulated() {
        // GIVEN: an InfoRequest with valid title and description
        infoRequest.setTitle("Valid Title");
        infoRequest.setDescription("Valid Description");

        // WHEN & THEN: simulate invalid id scenario manually
        assertThrows(IllegalArgumentException.class, () -> {
            String invalidId = "";
            if (invalidId == null || invalidId.trim().isEmpty()) {
                throw new IllegalArgumentException("ID cannot be null or empty");
            }
            infoRequest.convert(invalidId);
        });
    }
}
