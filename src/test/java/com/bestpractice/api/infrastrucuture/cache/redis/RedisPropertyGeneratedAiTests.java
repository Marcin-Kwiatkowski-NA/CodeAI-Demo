package com.bestpractice.api.infrastrucuture.cache.redis;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.extension.ExtendWith.withFactory;

@ExtendWith(withFactory(() -> new RedisPropertyExtension()))
class RedisPropertyGeneratedAiTests {

    private RedisProperty redisProperty;

    @BeforeEach
    void setUp() {
        redisProperty = new RedisProperty();
    }

    @Test
    void getSetHost_shouldReturnAndSetHost() {
        // GIVEN: A new RedisProperty instance is created.
        // WHEN: The host property is set to "localhost".
        redisProperty.setHost("localhost");
        // THEN: The host property should be "localhost".
        assertEquals("localhost", redisProperty.getHost());
    }

    @Test
    void getSetPort_shouldReturnAndSetPort() {
        // GIVEN: A new RedisProperty instance is created.
        // WHEN: The port property is set to 6379.
        redisProperty.setPort(6379);
        // THEN: The port property should be 6379.
        assertEquals(6379, redisProperty.getPort());
    }

    @Test
    void getPassword_shouldReturnPassword() {
        // GIVEN: A new RedisProperty instance is created.
        // WHEN: The password property is set to "secret".
        redisProperty.setPassword("secret");
        // THEN: The password property should be "secret".
        assertEquals("secret", redisProperty.getPassword());
    }

    @Test
    void setPassword_shouldSetPassword() {
        // GIVEN: A new RedisProperty instance is created.
        // WHEN: The password property is set to "newSecret".
        redisProperty.setPassword("newSecret");
        // THEN: The password property should be "newSecret".
        assertEquals("newSecret", redisProperty.getPassword());
    }
}

// Extension to handle @ExtendWith
class RedisPropertyExtension implements org.junit.jupiter.api.extension.ExtensionContext.TestRunnerFactory {
    @Override
    public org.junit.jupiter.api.extension.TestContextRunner createTestContextRunner(org.junit.jupiter.api.TestContext context) {
        return new RedisPropertyTestRunner(context);
    }
}

static class RedisPropertyTestRunner extends org.junit.jupiter.api.TestContextRunner {
    RedisPropertyTestRunner(org.junit.jupiter.api.TestContext context) {
        super(context);
    }
}
