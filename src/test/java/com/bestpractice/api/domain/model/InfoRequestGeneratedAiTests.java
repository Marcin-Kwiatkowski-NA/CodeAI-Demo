package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static com.bestpractice.api.infrastrucuture.entity.Info;
import static com.bestpractice.api.infrastrucuture.entity.SharedData;

import org.junit.Assert;

public class InfoRequestTest {

    @Test
    public void testGetTitle() {
        InfoRequest request = new InfoRequest();
        assertEquals("Hello", request.getTitle());
    }

    @Test
    public void testSetTitle() {
        InfoRequest request = new InfoRequest();
        request.setTitle("World");
        Assert.assertEquals("World", request.getTitle());
    }

    @Test
    public void testGetDescription() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello, World!", request.getDescription());
    }

    @Test
    public void testSetDescription() {
        InfoRequest request = new InfoRequest();
        request.setDescription("This is a test description.")
        Assert.assertEquals("This is a test description.", request.getDescription());
    }

    @Test
    public void testConvert() {
        InfoRequest request = new InfoRequest();
        String id = "123";
        String result = request.convert(id);
        Assert.assertEquals("123", result);
    }

    @Test
    public void testGetCreatedAt() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals(new Date(), request.getCreatedAt());
    }

    @Test
    public void testSetCreatedAt() {
        InfoRequest request = new InfoRequest();
        request.setCreatedAt(new Date());
        Assert.assertEquals(new Date(), request.getCreatedAt());
    }

    @Test
    public void testTitleEquals() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello", request.getTitle());
    }

    @Test
    public void testDescriptionEquals() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello, World!", request.getDescription());
    }

    @Test
    public void testConvertEquals() {
        InfoRequest request = new InfoRequest();
        String id = "123";
        String result = request.convert(id);
        Assert.assertEquals("123", result);
    }

    @Test
    public void testTitleEquals() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello", request.getTitle());
    }

    @Test
    public void testDescriptionEquals() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello, World!", request.getDescription());
    }

    @Test
    public void testSetCreatedAt() {
        InfoRequest request = new InfoRequest();
        request.setCreatedAt(new Date());
        Assert.assertEquals(new Date(), request.getCreatedAt());
    }

    @Test
    public void testSetCreatedAt() {
        InfoRequest request = new InfoRequest();
        request.setCreatedAt(new Date());
        Assert.assertEquals(new Date(), request.getCreatedAt());
    }

    @Test
    public void testTitleEquals() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello", request.getTitle());
    }

    @Test
    public void testDescriptionEquals() {
        InfoRequest request = new InfoRequest();
        Assert.assertEquals("Hello, World!", request.getDescription());
    }

    @Test
    public void testConvertEquals() {
        InfoRequest request = new InfoRequest();
        String id = "123";
        String result = request.convert(id);
        Assert.assertEquals("123", result);
    }
}
