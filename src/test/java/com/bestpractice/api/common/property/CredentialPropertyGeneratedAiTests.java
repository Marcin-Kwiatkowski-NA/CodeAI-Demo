package com.bestpractice.api.common.property;

"newExpiresHourStr", credentialProperty.getExpiresHourStr());

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
    }

    @Test
    void convertToIntExpires_ValidHourStr() {
        // GIVEN a CredentialProperty instance with "123" as expiresHourStr
        // WHEN we call convertToIntExpires()
        // THEN it returns 123
        int result = credentialProperty.convertToIntExpires();
        assertEquals(123, result);
    }

    @Test
    void convertToIntExpires_InvalidHourStr() {
        // GIVEN a CredentialProperty instance with "-" as expiresHourStr
        // WHEN we call convertToIntExpires()
        // THEN it returns null
        assertNull(credentialProperty.convertToIntExpires());
    }

    @Test
    void convertToIntExpires_EmptyHourStr() {
        // GIVEN a CredentialProperty instance with "" as expiresHourStr
        // WHEN we call convertToIntExpires()
        // THEN it returns null
        assertNull(credentialProperty.convertToIntExpires());
    }

    @Test
    void convertToIntExpires_ZeroHourStr() {
        // GIVEN a CredentialProperty instance with "0" as expiresHourStr
        // WHEN we call convertToIntExpires()
        // THEN it returns 0
        int result = credentialProperty.convertToIntExpires();
        assertEquals(0, result);
    }
}
