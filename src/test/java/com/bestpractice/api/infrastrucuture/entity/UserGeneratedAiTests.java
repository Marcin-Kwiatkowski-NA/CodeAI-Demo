package com.bestpractice.api.infrastrucuture.entity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SharedDataTest {

    private SharedData sharedData;

    @org.junit.jupiter.api.Test
    void createNewSharedData() {
        sharedData = new SharedData();
        sharedData.setData("Test Data");
        sharedData.setTimestamp(new Date());
        sharedData.setIsValid(true);

        assertNotNull(sharedData);
        assertEquals("Test Data", sharedData.getData());
        assertEquals(new Date(), sharedData.getTimestamp());
        assertTrue(sharedData.getIsValid());
    }

    @org.junit.jupiter.api.Test
    void setId() {
        sharedData = new SharedData();
        sharedData.setId(123);
        assertEquals(123, sharedData.getId());
    }

    @org.junit.jupiter.api.Test
    void setAndGetTimestamp() {
        sharedData = new SharedData();
        sharedData.setTimestamp(new Date());
        assertEquals(new Date(), sharedData.getTimestamp());
    }

    @org.junit.jupiter.api.Test
    void setIsValid() {
        sharedData = new SharedData();
        sharedData.setIsValid(true);
        assertTrue(sharedData.getIsValid());
    }
}