package com.bestpractice.api.infrastrucuture.cache;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class MyExtension implements org.junit.jupiter.api.extension.Extension {

    @Override
    public void accept(junit.execution.TestExecutionPlan plan) {
        // Implement the required logic here if any.
        // This is a placeholder.
    }
}
