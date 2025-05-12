package com.bestpractice.api.infrastrucuture.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bestpractice.api.infrastrucuture.entity.SharedDataGeneratedAiTests;

@ExtensionPoint
public class SharedDataGeneratedAiTests {

    @Test
    void testCreateAndGetCreatedAt() {
        SharedData sharedData = new SharedData();
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAt() {
        SharedData sharedData = new SharedData();
        sharedData.setCreatedAt(new Date());
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void testOnPrePersist() {
        SharedData sharedData = new SharedData();
        sharedData.onPrePersist();
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void testGetCreatedAt() {
        SharedData sharedData = new SharedData();
        assertEquals(new Date(), sharedData.getCreatedAt());
    }

    @Test
    void testSetCreatedAt() {
        SharedData sharedData = new SharedData();
        sharedData.setCreatedAt(new Date());
        assertEquals(new Date(), sharedData.getCreatedAt());
    }
}
