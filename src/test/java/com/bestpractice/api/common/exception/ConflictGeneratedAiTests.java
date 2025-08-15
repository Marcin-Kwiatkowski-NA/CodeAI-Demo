package com.bestpractice.api.common.exception;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

@ExtendWith(MyTestFactory.class)
public class ConflictGeneratedAiTests {

    private Conflict conflict;

    @BeforeEach
    void setUp() {
        conflict = new Conflict();
    }

    @Test
    void constructor_no_args() {
        // GIVEN: No arguments are passed to the constructor.
        // WHEN: The constructor is called.
        // THEN: A Conflict object is created with no message.
        assertNotNull(conflict);
    }

    @Test
    void constructor_with_message() {
        // GIVEN: A message is provided to the constructor.
        // WHEN: The constructor is called with a message.
        // THEN: A Conflict object is created with the provided message.
        String msg = "Conflict occurred";
        Conflict conflictWithMsg = new Conflict(msg);
        assertEquals(msg, conflictWithMsg.getMessage());
    }

    @Test
    void constructor_with_cause() {
        // GIVEN: A Throwable object is provided as the cause.
        // WHEN: The constructor is called with a cause.
        Throwable cause = new RuntimeException("Something went wrong");
        Conflict conflictWithCause = new Conflict(cause);
        assertEquals(cause, conflictWithCause.getCause());
    }

    @Test
    void constructor_with_message_and_cause() {
        // GIVEN: A message and a Throwable object are provided.
        // WHEN: The constructor is called with a message and a cause.
        String msg = "Conflict occurred";
        Throwable cause = new RuntimeException("Something went wrong");
        Conflict conflictWithMsgAndCause = new Conflict(msg, cause);
        assertEquals(msg, conflictWithMsgAndCause.getMessage());
        assertEquals(cause, conflictWithMsgAndCause.getCause());
    }
}
