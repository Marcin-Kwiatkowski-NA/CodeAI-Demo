package com.bestpractice.api.app.v2;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

class MyClass {
    private String myString;

    @Test
    void testStringInitialization() {
        myString = null;
        
        assertThrows(NullPointerException.class, () -> {
            myString.length();
        });
    }
}
