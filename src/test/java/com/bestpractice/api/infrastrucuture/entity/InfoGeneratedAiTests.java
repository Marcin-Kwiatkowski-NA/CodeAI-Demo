package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    public void setUp() {
        info = new Info();
        info.setId(null);
        info.setTitle(null);
        info.setDescription(null);
        info.setCreatedAt(null);
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN: an Info object and a sample id
        String sampleId = "12345";

        // WHEN: setting the id
        info.setId(sampleId);

        // THEN: the id should be correctly retrieved
        assertEquals(sampleId, info.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN: an Info object and a sample title
        String sampleTitle = "Sample Title";

        // WHEN: setting the title
        info.setTitle(sampleTitle);

        // THEN: the title should be correctly retrieved
        assertEquals(sampleTitle, info.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN: an Info object and a sample description
        String sampleDescription = "Sample Description";

        // WHEN: setting the description
        info.setDescription(sampleDescription);

        // THEN: the description should be correctly retrieved
        assertEquals(sampleDescription, info.getDescription());
    }

    @Test
    public void testSetAndGetCreatedAt() {
        // GIVEN: an Info object and a sample date
        Date now = new Date();

        // WHEN: setting the createdAt date
        info.setCreatedAt(now);

        // THEN: the createdAt date should be correctly retrieved
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN: an Info object with no createdAt date
        assertNull(info.getCreatedAt());

        // WHEN: calling onPrePersist
        info.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(info.getCreatedAt());
    }

    @Test
    public void testSetTitleWithNullValueAllowed() {
        // GIVEN: an Info object

        // WHEN: setting title to null
        info.setTitle(null);

        // THEN: title should be null (no exception thrown)
        assertNull(info.getTitle());
    }

    @Test
    public void testSetDescriptionWithNullValueAllowed() {
        // GIVEN: an Info object

        // WHEN: setting description to null
        info.setDescription(null);

        // THEN: description should be null (no exception thrown)
        assertNull(info.getDescription());
    }

    @Test
    public void testSetIdWithNullValueAllowed() {
        // GIVEN: an Info object

        // WHEN: setting id to null
        info.setId(null);

        // THEN: id should be null (no exception thrown)
        assertNull(info.getId());
    }

    @Test
    public void testMultipleFieldSettersAndGetters() {
        // GIVEN: an Info object and sample values
        String sampleId = "id-001";
        String sampleTitle = "Title Example";
        String sampleDescription = "Description Example";
        Date now = new Date();

        // WHEN: setting all fields
        info.setId(sampleId);
        info.setTitle(sampleTitle);
        info.setDescription(sampleDescription);
        info.setCreatedAt(now);

        // THEN: all getters should return the correct values
        assertEquals(sampleId, info.getId());
        assertEquals(sampleTitle, info.getTitle());
        assertEquals(sampleDescription, info.getDescription());
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    public void testSetTitleNullDoesNotThrowException() {
        // GIVEN: an Info object

        // WHEN & THEN: setting title to null should not throw exception
        assertDoesNotThrow(() -> info.setTitle(null));
    }

    @Test
    public void testSetDescriptionNullDoesNotThrowException() {
        // GIVEN: an Info object

        // WHEN & THEN: setting description to null should not throw exception
        assertDoesNotThrow(() -> info.setDescription(null));
    }

    @Test
    public void testSetIdNullDoesNotThrowException() {
        // GIVEN: an Info object

        // WHEN & THEN: setting id to null should not throw exception
        assertDoesNotThrow(() -> info.setId(null));
    }
}
