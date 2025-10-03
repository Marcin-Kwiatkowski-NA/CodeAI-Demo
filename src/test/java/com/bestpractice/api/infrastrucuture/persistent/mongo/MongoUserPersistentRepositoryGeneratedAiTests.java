package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// THEN: The user is removed from the database and the result is true.
        assertTrue(removed, "User should have been removed");

        // Verify that the user is no longer in the database
        List<MongoUserEntity> users = mongoDatabase.getCollection("users").find(Filters.eq("_id", new ObjectId("6543a8e9b1f2a3b4c5d6e7f8"))).intoList();
        assertEquals(0, users.size());
    }
}
