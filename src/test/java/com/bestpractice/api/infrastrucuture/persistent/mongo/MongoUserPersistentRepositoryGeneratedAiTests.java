package com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserPersistentRepositoryGeneratedAiTests;

import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoUserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.function.Boolean;

class MongoUserPersistentRepositoryGeneratedAiTests {

    @Enabled
    @Test
    void testNewId() {
        Boolean result = User.newId();
        assertBoolean(result, true);
    }

    @Enabled
    @Test
    void testFindByEmail() {
        Boolean result = User.byEmail("testuser");
        assertBoolean(result, true);
    }

    @Enabled
    @Test
    void testReplace() {
        Boolean result = User.replace("testuser", User.newId());
        assertBoolean(result, true);
    }
}