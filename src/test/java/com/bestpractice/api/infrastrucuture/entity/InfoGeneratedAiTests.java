package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        info = new Info();
        info.setId(null);
        info.setTitle(null);
        info.setDescription(null);
        info.setCreatedAt(null);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testSetAndGetId() {
        // GIVEN: an Info object and a sample id
        String sampleId = "12345";

        // WHEN: setting the id
        info.setId(sampleId);

        // THEN: the id should be retrievable and match the set value
        assertEquals(sampleId, info.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        // GIVEN: an Info object and a sample title
        String sampleTitle = "Sample Title";

        // WHEN: setting the title
        info.setTitle(sampleTitle);

        // THEN: the title should be retrievable and match the set value
        assertEquals(sampleTitle, info.getTitle());
    }

    @Test
    public void testSetAndGetDescription() {
        // GIVEN: an Info object and a sample description
        String sampleDescription = "Sample Description";

        // WHEN: setting the description
        info.setDescription(sampleDescription);

        // THEN: the description should be retrievable and match the set value
        assertEquals(sampleDescription, info.getDescription());
    }

    @Test
    public void testSetAndGetCreatedAtFromSharedData() {
        // GIVEN: an Info object and a sample date
        Date now = new Date();

        // WHEN: setting the createdAt date
        info.setCreatedAt(now);

        // THEN: the createdAt date should be retrievable and match the set value
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    public void testOnPrePersistSetsCreatedAt() {
        // GIVEN: an Info object with no createdAt date set
        assertNull(info.getCreatedAt());

        // WHEN: calling onPrePersist
        info.onPrePersist();

        // THEN: createdAt should be set to a non-null value
        assertNotNull(info.getCreatedAt());
    }

    @Test
    public void testSetTitleToNullThrowsConstraintViolation() {
        // GIVEN: an Info object with title set to null
        info.setTitle(null);
        info.setDescription("Valid Description");

        // WHEN & THEN: validating should throw ConstraintViolationException
        assertThrows(ConstraintViolationException.class, () -> {
            var violations = validator.validate(info);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    public void testSetDescriptionToNullThrowsConstraintViolation() {
        // GIVEN: an Info object with description set to null
        info.setTitle("Valid Title");
        info.setDescription(null);

        // WHEN & THEN: validating should throw ConstraintViolationException
        assertThrows(ConstraintViolationException.class, () -> {
            var violations = validator.validate(info);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    public void testBothTitleAndDescriptionNullThrowsConstraintViolation() {
        // GIVEN: an Info object with both title and description null
        info.setTitle(null);
        info.setDescription(null);

        // WHEN & THEN: validating should throw ConstraintViolationException
        assertThrows(ConstraintViolationException.class, () -> {
            var violations = validator.validate(info);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }

    @Test
    public void testValidInfoDoesNotThrowConstraintViolation() {
        // GIVEN: an Info object with valid title and description
        info.setTitle("Valid Title");
        info.setDescription("Valid Description");

        // WHEN: validating the object
        var violations = validator.validate(info);

        // THEN: there should be no violations
        assertEquals(0, violations.size());
    }
}
