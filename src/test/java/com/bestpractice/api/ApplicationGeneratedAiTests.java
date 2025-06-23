package com.bestpractice.api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static com.bestpractice.api.MyClass.myMethod;
import static com.bestpractice.api.MyClass.myMethodTest;

@Test
public class ApplicationGeneratedAiTests {

    @Test
    public void testMyMethod_positiveNumber() {
        MyClass myClass = new MyClass();
        myClass.myMethod(5);
        assertEquals(5, myClass.myMethod());
    }

    @Test
    public void testMyMethod_negativeNumber() {
        MyClass myClass = new MyClass();
        myClass.myMethod(-5);
        assertEquals(-5, myClass.myMethod());
    }

    @Test
    public void testMyMethod_zero() {
        MyClass myClass = new MyClass();
        myClass.myMethod(0);
        assertEquals(0, myClass.myMethod());
    }

    @Test
    public void testMyMethod_largeNumber() {
        MyClass myClass = new MyClass();
        myClass.myMethod(1000000);
        assertEquals(1000000, myClass.myMethod());
    }

    @Test
    public void testMyMethod_stringInput() {
        MyClass myClass = new MyClass();
        myClass.myMethod("hello");
        assertEquals("hello", myClass.myMethod());
    }
}
