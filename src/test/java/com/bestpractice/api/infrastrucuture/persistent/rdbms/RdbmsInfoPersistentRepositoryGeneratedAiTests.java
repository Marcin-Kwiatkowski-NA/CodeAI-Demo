package com.bestpractice.api.infrastrucuture.persistent.rdbms;

        // THEN: The Info object with ID "1" is removed from the database.

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
        boolean result = repository.removeById("1");
        assert result == true;
    }
}
