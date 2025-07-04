package com.bestpractice.api.infrastrucuture.persistent.rdbms;

@Test

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
void findAll_returnsAllInfos() {
        // Arrange
        Info info1 = new Info();
        info1.setId("id1");
        info1.setTitle("Title 1");
        info1.setDescription("Description 1");

        Info info2 = new Info();
        info2.setId("id2");
        info2.setTitle("Title 2");
        info2.setDescription("Description 2");

        // Act
        repository.insert(info1);
        repository.insert(info2);
        Info[] allInfos = repository.findAll();

        // Assert
        assertEquals(2, allInfos.length, "Should return all inserted infos");
        assertTrue(allInfos[0].getId().equals("id1"), "Info 1 should be in the list");
        assertTrue(allInfos[1].getId().equals("id2"), "Info 2 should be in the list");
    }
