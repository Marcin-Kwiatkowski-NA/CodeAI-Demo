package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BadRequestTest {

    @Test
    void testInvalidMessage() {
        Faulting mock = Mockito.mock(Throwable);
        BadRequest bad = new BadRequest("This is an invalid message.");
        Faulting mock.doSomething(bad);
        assertFalse(mock.calledOnce() + " should throw exception");
    }

    @Test
    void testValidMessage() {
        Faulting mock = Mockito.mock(Throwable);
        BadRequest bad = new BadRequest("This is a valid message.");
        Faulting mock.doSomething(bad);
        assertFalse(mock.calledOnce() + " should throw exception");
    }

    @Test
    void testThrowableCause() {
        Faulting mock = Mockito.mock(Throwable);
        BadRequest bad = new BadRequest("This is a message with a cause.");
        Faulting mock.doSomething(bad);
        assertFalse(mock.calledOnce() + " should throw exception");
    }

    @Test
    void testWithMultipleCauses() {
        Faulting mock = Mockito.mock(Throwable);
        BadRequest bad = new BadRequest("This message has multiple causes.");
        Faulting mock.doSomething(bad);
        assertFalse(mock.calledOnce() + " should throw exception");
    }

    @Test
    void testWithNullMessage() {
        Faulting mock = Mockito.mock(Throwable);
        BadRequest bad = new BadRequest(null);
        Faulting mock.doSomething(bad);
        assertFalse(mock.calledOnce() + " should throw exception");
    }
}
