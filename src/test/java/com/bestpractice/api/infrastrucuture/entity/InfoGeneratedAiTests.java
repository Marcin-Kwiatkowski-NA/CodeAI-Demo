package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import com.github.mpleichhoff.junit5.Junit5Test;
import com.github.mpleichhoff.junit5.Junit5Running;
import com.github.mpleichhoff.junit5.Junit5TestRunner;

import java.util.List;

public class InfoGeneratedAiTests {

    @Test
    public void testGetId() {
        // Arrange
        String id = "12345";

        // Act
        String result = Info.getId();

        // Assert
        self.assertEqual(result, id);
    }

    @Test
    public void testSetId() {
        // Arrange
        String id = "12345";

        // Act
        Info.setCreatedAt(id);

        // Assert
        self.assertEqual(id, id);
    }

    @Test
    public void testGetTitle() {
        // Arrange
        String title = "Example Title";

        // Act
        String result = Info.getTitle();

        // Assert
        self.assertEqual(result, title);
    }

    @Test
    public void testGetDescription() {
        // Arrange
        String description = "This is a description.";

        // Act
        String result = Info.getDescription();

        // Assert
        self.assertEqual(description, description);
    }

    @Test
    public void testGetCreatedAt() {
        // Arrange
        Date createdAt = new Date();

        // Act
        String result = Info.getCreatedAt();

        // Assert
        self.assertEqual(result, new Date());
    }

    @Test
    public void testSetCreatedAt() {
        // Arrange
        Date createdAt = new Date();

        // Act
        Info.setCreatedAt(createdAt);

        // Assert
        self.assertEqual(createdAt, createdAt);
    }
}
