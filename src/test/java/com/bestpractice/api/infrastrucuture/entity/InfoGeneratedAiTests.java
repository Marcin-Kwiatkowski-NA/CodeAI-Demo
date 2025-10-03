package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    public void setUp() {
        info = new Info();
    }

    @Test
    public void testGetAndSetId() {
        // GIVEN: an Info object and a sample id
        String sampleId = "12345";

        // WHEN: setting the id
        info.setId(sampleId);

        // THEN: the id should be correctly retrieved
        assertEquals(sampleId, info.getId());
    }

    @Test
    public void testGetAndSetTitle() {
        // GIVEN: an Info object and a sample title
        String sampleTitle = "Sample Title";

        // WHEN: setting the title
        info.setTitle(sampleTitle);

        // THEN: the title should be correctly retrieved
        assertEquals(sampleTitle, info.getTitle());
    }

    @Test
    public void testGetAndSetDescription() {
        // GIVEN: an Info object and a sample description
        String sampleDescription = "Sample Description";

        // WHEN: setting the description
        info.setDescription(sampleDescription);

        // THEN: the description should be correctly retrieved
        assertEquals(sampleDescription, info.getDescription());
    }

    @Test
    public void testGetAndSetCreatedAtFromSharedData() {
        // GIVEN: an Info object and a sample date
        Date now = new Date();

        // WHEN: setting the createdAt date
        info.setCreatedAt(now);

        // THEN: the createdAt date should be correctly retrieved
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN: an Info object with no createdAt set
        assertNull(info.getCreatedAt());

        // WHEN: calling onPrePersist
        info.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(info.getCreatedAt());
    }

    @Test
    public void testSetTitleWithNullValue() {
        // GIVEN: an Info object

        // WHEN: setting the title to null
        info.setTitle(null);

        // THEN: title should be null
        assertNull(info.getTitle());
    }

    @Test
    public void testSetDescriptionWithNullValue() {
        // GIVEN: an Info object

        // WHEN: setting the description to null
        info.setDescription(null);

        // THEN: description should be null
        assertNull(info.getDescription());
    }

    @Test
    public void testSetIdWithNullValue() {
        // GIVEN: an Info object

        // WHEN: setting the id to null
        info.setId(null);

        // THEN: id should be null
        assertNull(info.getId());
    }

    @Test
    public void testMultipleSetAndGetTitle() {
        // GIVEN: an Info object and multiple titles
        String firstTitle = "First Title";
        String secondTitle = "Second Title";

        // WHEN: setting the title twice
        info.setTitle(firstTitle);
        assertEquals(firstTitle, info.getTitle());
        info.setTitle(secondTitle);

        // THEN: the title should be updated to the second value
        assertEquals(secondTitle, info.getTitle());
    }

    @Test
    public void testMultipleSetAndGetDescription() {
        // GIVEN: an Info object and multiple descriptions
        String firstDescription = "First Description";
        String secondDescription = "Second Description";

        // WHEN: setting the description twice
        info.setDescription(firstDescription);
        assertEquals(firstDescription, info.getDescription());
        info.setDescription(secondDescription);

        // THEN: the description should be updated to the second value
        assertEquals(secondDescription, info.getDescription());
    }

    @Test
    public void testMultipleSetAndGetId() {
        // GIVEN: an Info object and multiple ids
        String firstId = "ID1";
        String secondId = "ID2";

        // WHEN: setting the id twice
        info.setId(firstId);
        assertEquals(firstId, info.getId());
        info.setId(secondId);

        // THEN: the id should be updated to the second value
        assertEquals(secondId, info.getId());
    }
}
