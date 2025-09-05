package com.bestpractice.api.app;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

assertEquals("user123", requestInfo.getUserId());
assertEquals("user@example.com", requestInfo.getUserEmail());
assertTrue(requestInfo.isRefreshToken());
assertEquals("/api/v1/user", requestInfo.getPath());
