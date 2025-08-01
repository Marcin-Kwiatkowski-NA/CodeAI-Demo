package com.bestpractice.api.common.property;

        assertEquals(123, expiresHour);

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

        // GIVEN a new CredentialProperty instance with 'expiresHourStr' set to "-"
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return null.
        Integer expiresHourNull = credentialProperty.convertToIntExpires();
        assertNull(expiresHourNull);

        // GIVEN a new CredentialProperty instance with 'expiresHourStr' set to "abc"
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return null.
        Integer expiresHourException = credentialProperty.convertToIntExpires();
        assertNull(expiresHourException);
    }
}
