package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

package com.bestpractice.api.infrastrucuture.persistent.mongo.property;

    void setPassword() {
        // GIVEN: A new MongoProperty instance is created.
        // WHEN: The setPassword() method is called with a non-null value.
        // THEN: The password property is set to the provided value.
        mongoProperty.setPassword("password");
        assertEquals("password", mongoProperty.getPassword(), "Password should be set correctly");
    }
}
