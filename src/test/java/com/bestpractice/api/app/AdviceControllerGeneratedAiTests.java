package com.bestpractice.api.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@Test
public class AdviceControllerGeneratedAiTests {

    private AdviceController controller;

    @BeforeEach
    void setUp() {
        controller = new AdviceController();
    }

    @Test
    public void badRequestTest() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    public void unAuthorizedTest() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    public void forbiddenTest() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    public void notFound01Test() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    public void notFound02Test() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    public void conflictTest() {
        // GIVEN
        // WHEN
        // THEN
    }

    @Test
    public void serverErrorTest() {
        // GIVEN
        // WHEN
        // THEN
    }

    private ErrorResponse shareNotFound() {
        ErrorResponse res = new ErrorResponse();
        res.setStatus(404);
        res.setError("Not found");
        res.setMessage("Not found path");
        return res;
    }
}
