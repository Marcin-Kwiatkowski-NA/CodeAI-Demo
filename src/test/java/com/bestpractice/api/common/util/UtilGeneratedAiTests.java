package com.bestpractice.api.common.util;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Util {

    public static <T> T deepClone(T object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    public static <T> String getSpringProfileActive() {
        return System.getenv("SPRING_PROFILES_ACTIVE");
    }

    @ExtendWith<String>
    public static String deepClone(T object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (T) ois.readObject();
    }

    @ExtendWith<String>
    public static String getSpringProfileActive() {
        return System.getenv("SPRING_PROFILES_ACTIVE");
    }

    @Test
    public static void testDeepClone() {
        // Test case 1: Basic clone
        T obj = new T();
        String expected = "new T";
        assertDeepClone(obj);
        assertEquals(expected, obj);

        // Test case 2: Clone with null
        T obj2 = null;
        String expected2 = "null";
        assertDeepClone(obj2);
        assertEquals(expected2, obj2);

        // Test case 3: Clone with a simple object
        T obj3 = new T();
        assertDeepClone(obj3);
        assertEquals(expected, obj3);
    }

    @Test
    public static void testDeepCloneGeneratedAiTests() {
        // Test case 1: Basic clone
        T obj = new T();
        String expected = "new T";
        assertDeepClone(obj);
        assertEquals(expected, obj);
    }
}
