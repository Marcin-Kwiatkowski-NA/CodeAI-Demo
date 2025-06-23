package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import static org.junit.Assert.assertEquals;

package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

public class MongoUserEntityTest {

    @Test
    public void testCreateUser() {
        MongoUserEntity entity = new MongoUserEntity();
        entity.setId(123);
        assertEquals(123, entity.getId());
        assertEquals("testuser", entity.getUsername());
        assertEquals("test@example.com", entity.getEmail());
        assertEquals("testpassword", entity.password);
    }

    @Test
    public void testSetUsername() {
        MongoUserEntity entity = new MongoUserEntity();
        entity.setUsername("newuser");
        assertEquals("newuser", entity.getUsername());
    }

    @Test
    public void testSetPassword() {
        MongoUserEntity entity = new MongoUserEntity();
        entity.setPassword("securepassword");
        assertEquals("securepassword", entity.getPassword());
    }

    @Test
    public void testGetUser() {
        MongoUserEntity entity = new MongoUserEntity();
        assertEquals("testuser", entity.getUsername());
        assertEquals("test@example.com", entity.getEmail());
        assertEquals("testpassword", entity.password);
    }

    @Test
    public void testGetUserFromEntity() {
        MongoUserEntity entity = new MongoUserEntity();
        assertEquals("testuser", entity.getUsername());
        assertEquals("test@example.com", entity.getEmail());
        assertEquals("testpassword", entity.password);
    }
}
