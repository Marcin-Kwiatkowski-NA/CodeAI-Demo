package com.bestpractice.api;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.bestpractice.api.MyClass.myMethod;
import static com.bestpractice.api.MyClass.MyClass.main;

@Test
public void testMyMethod() {
    Test.assertEquals(10, myMethod);
}
