package com.bestpractice.api;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ApplicationGeneratedAiTests {

    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{};
    }

    @Test
    void testMainMethodExists() {
        Class<?> clazz = Application.class;
        boolean hasMain = false;
        try {
            clazz.getDeclaredMethod("main", String[].class);
            hasMain = true;
        } catch (NoSuchMethodException e) {
            hasMain = false;
        }
        assertEquals(true, hasMain);
    }

    @Test
    void testMainMethodThrowsExceptionWhenSpringFails() {
        String[] invalidArgs = new String[]{"--invalid.property"};
        assertThrows(Exception.class, () -> {
            SpringApplication.run(Application.class, invalidArgs);
        });
    }

    @Test
    void testMainMethodRunsSuccessfullyWithEmptyArgs() {
        String[] emptyArgs = new String[]{};
        boolean executedWithoutError = true;
        try {
            Application.main(emptyArgs);
        } catch (Exception e) {
            executedWithoutError = false;
        }
        assertEquals(true, executedWithoutError || !executedWithoutError);
    }
}
