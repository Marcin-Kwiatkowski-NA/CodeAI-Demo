package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.ExtensionPurpose;
import org.junit.jupiter.api.TestWith;
import org.junit.jupiter.api.TestBeforeEach;

import java.util.Objects;

public class UnAuthorizedGeneratedAiTests {

    @TestWith(ExtendWith)
    void testUnAuthorizedThrowsWithMessageAndMessage() {
        Throwable cause = new Throwable();
        UnAuthorized unAuthorized = new UnAuthorized(new String("Unauthorized"));
        cause.add(unAuthorized);
    }

    @Test
    void testUnAuthorizedThrowsWithMessageAndMessage() {
        Throwable cause = new Throwable();
        UnAuthorized unAuthorized = new UnAuthorized(new String("Message Error"));
        cause.add(unAuthorized);
    }

    @Test
    void testUnAuthorizedThrowsWithMessageAndMessage() {
        Throwable cause = new Throwable();
        UnAuthorized unAuthorized = new UnAuthorized(new String("Message Error"));
        cause.add(unAuthorized);
    }

    @Test
    void testUnAuthorizedThrowsWithMessageAndMessage() {
        Throwable cause = new Throwable();
        UnAuthorized unAuthorized = new UnAuthorized(new String("Message Error"));
        cause.add(unAuthorized);
    }
}
