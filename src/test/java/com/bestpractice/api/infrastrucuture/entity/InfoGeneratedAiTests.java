package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InfoGeneratedAiTests {

    private Info info;

    @BeforeEach
    void setUp() {
        info = new Info();
    }

    @Test
    void testSetAndGetId() {
        // GIVEN
        String expectedId = "12345";

        // WHEN
        info.setId(expectedId);

        // THEN
        assertEquals(expectedId, info.getId());
    }

    @Test
    void testSetAndGetTitle() {
        // GIVEN
        String expectedTitle = "Sample Title";

        // WHEN
        info.setTitle(expectedTitle);

        // THEN
        assertEquals(expectedTitle, info.getTitle());
    }

    @Test
    void testSetAndGetDescription() {
        // GIVEN
        String expectedDescription = "Sample Description";

        // WHEN
        info.setDescription(expectedDescription);

        // THEN
        assertEquals(expectedDescription, info.getDescription());
    }

    @Test
    void testInheritedSetAndGetCreatedAt() {
        // GIVEN
        Date now = new Date();

        // WHEN
        info.setCreatedAt(now);

        // THEN
        assertEquals(now, info.getCreatedAt());
    }

    @Test
    void testInheritedOnPrePersistSetsCreatedAt() {
        // GIVEN
        info.setCreatedAt(null);

        // WHEN
        info.onPrePersist();

        // THEN
        assertThat(info.getCreatedAt()).isNotNull();
        assertThat(info.getCreatedAt()).isInstanceOf(Date.class);
    }

    // EDGE CASE TESTS

    @Test
    void testSetIdWithWhitespaceOnlyString() {
        // GIVEN
        String whitespaceId = "   ";

        // WHEN
        info.setId(whitespaceId);

        // THEN
        assertEquals(whitespaceId, info.getId());
    }

    @Test
    void testSetTitleWithWhitespaceOnlyString() {
        // GIVEN
        String whitespaceTitle = "   ";

        // WHEN
        info.setTitle(whitespaceTitle);

        // THEN
        assertEquals(whitespaceTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithWhitespaceOnlyString() {
        // GIVEN
        String whitespaceDescription = "   ";

        // WHEN
        info.setDescription(whitespaceDescription);

        // THEN
        assertEquals(whitespaceDescription, info.getDescription());
    }

    @Test
    void testSetIdWithSingleCharacter() {
        // GIVEN
        String singleCharId = "A";

        // WHEN
        info.setId(singleCharId);

        // THEN
        assertEquals(singleCharId, info.getId());
    }

    @Test
    void testSetTitleWithSingleCharacter() {
        // GIVEN
        String singleCharTitle = "T";

        // WHEN
        info.setTitle(singleCharTitle);

        // THEN
        assertEquals(singleCharTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithSingleCharacter() {
        // GIVEN
        String singleCharDescription = "D";

        // WHEN
        info.setDescription(singleCharDescription);

        // THEN
        assertEquals(singleCharDescription, info.getDescription());
    }

    @Test
    void testSetIdWithLongStringBoundary() {
        // GIVEN
        String longId = "A".repeat(1000);

        // WHEN
        info.setId(longId);

        // THEN
        assertEquals(longId, info.getId());
    }

    @Test
    void testSetTitleWithLongStringBoundary() {
        // GIVEN
        String longTitle = "T".repeat(1000);

        // WHEN
        info.setTitle(longTitle);

        // THEN
        assertEquals(longTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithLongStringBoundary() {
        // GIVEN
        String longDescription = "D".repeat(1000);

        // WHEN
        info.setDescription(longDescription);

        // THEN
        assertEquals(longDescription, info.getDescription());
    }

    @Test
    void testSetCreatedAtWithFutureDateBoundary() {
        // GIVEN
        Date futureDate = new Date(System.currentTimeMillis() + 1000000000L);

        // WHEN
        info.setCreatedAt(futureDate);

        // THEN
        assertEquals(futureDate, info.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithPastDateBoundary() {
        // GIVEN
        Date pastDate = new Date(System.currentTimeMillis() - 1000000000L);

        // WHEN
        info.setCreatedAt(pastDate);

        // THEN
        assertEquals(pastDate, info.getCreatedAt());
    }

    @Test
    void testSetTitleWithLeadingAndTrailingSpaces() {
        // GIVEN
        String spacedTitle = "  Leading and trailing spaces  ";

        // WHEN
        info.setTitle(spacedTitle);

        // THEN
        assertEquals(spacedTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithSpecialCharacters() {
        // GIVEN
        String specialDescription = "!@#$%^&*()_+{}|:\"<>?";

        // WHEN
        info.setDescription(specialDescription);

        // THEN
        assertEquals(specialDescription, info.getDescription());
    }

    @Test
    void testSetTitleWithUnicodeCharacters() {
        // GIVEN
        String unicodeTitle = "タイトル🌟";

        // WHEN
        info.setTitle(unicodeTitle);

        // THEN
        assertEquals(unicodeTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithUnicodeCharacters() {
        // GIVEN
        String unicodeDescription = "説明💡";

        // WHEN
        info.setDescription(unicodeDescription);

        // THEN
        assertEquals(unicodeDescription, info.getDescription());
    }

    @Test
    void testSetIdWithEmptyStringBoundary() {
        // GIVEN
        String emptyId = "";

        // WHEN
        info.setId(emptyId);

        // THEN
        assertEquals(emptyId, info.getId());
    }

    @Test
    void testSetTitleWithEmptyStringBoundary() {
        // GIVEN
        String emptyTitle = "";

        // WHEN
        info.setTitle(emptyTitle);

        // THEN
        assertEquals(emptyTitle, info.getTitle());
    }

    @Test
    void testSetDescriptionWithEmptyStringBoundary() {
        // GIVEN
        String emptyDescription = "";

        // WHEN
        info.setDescription(emptyDescription);

        // THEN
        assertEquals(emptyDescription, info.getDescription());
    }

    @Test
    void testSetCreatedAtWithEpochDateBoundary() {
        // GIVEN
        Date epochDate = new Date(0);

        // WHEN
        info.setCreatedAt(epochDate);

        // THEN
        assertEquals(epochDate, info.getCreatedAt());
    }

    @Test
    void testSetCreatedAtWithCurrentDateBoundary() {
        // GIVEN
        Date currentDate = new Date(System.currentTimeMillis());

        // WHEN
        info.setCreatedAt(currentDate);

        // THEN
        assertEquals(currentDate, info.getCreatedAt());
    }

    @Test
    void testSettingTitleDoesNotAffectDescriptionOrId() {
        // GIVEN
        info.setId("ID1");
        info.setDescription("Desc1");

        // WHEN
        info.setTitle("Title1");

        // THEN
        assertEquals("ID1", info.getId());
        assertEquals("Desc1", info.getDescription());
        assertEquals("Title1", info.getTitle());
    }

    @Test
    void testSettingDescriptionDoesNotAffectTitleOrId() {
        // GIVEN
        info.setId("ID2");
        info.setTitle("Title2");

        // WHEN
        info.setDescription("Desc2");

        // THEN
        assertEquals("ID2", info.getId());
        assertEquals("Title2", info.getTitle());
        assertEquals("Desc2", info.getDescription());
    }

    // IMPROVEMENT: Verify that default values are null before setting
    @Test
    void testDefaultValuesAreNullBeforeSetting() {
        // GIVEN WHEN THEN
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }

    // IMPROVEMENT: Verify that setting null values is allowed (since no validation logic exists)
    @Test
    void testSetNullValuesAreStoredAsNull() {
        // GIVEN
        info.setId(null);
        info.setTitle(null);
        info.setDescription(null);

        // WHEN THEN
        assertThat(info.getId()).isNull();
        assertThat(info.getTitle()).isNull();
        assertThat(info.getDescription()).isNull();
    }
}
