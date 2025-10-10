package com.bestpractice.api.common.property;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

void setExpiresHourStr() {
        // GIVEN a CredentialProperty instance
        String expiresHourStr = "123";
        // WHEN the setExpiresHourStr() method is called with "123"
        credentialProperty.setExpiresHourStr(expiresHourStr);
        // THEN the expiresHourStr should be set to "123"
        assertEquals(expiresHourStr, credentialProperty.getExpiresHourStr());
    }

    @Test
    void convertToIntExpires_ValidHourStr() {
        // GIVEN a CredentialProperty instance with expiresHourStr = "123"
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return 123
        assertEquals(123, credentialProperty.convertToIntExpires());
    }

    @Test
    void convertToIntExpires_InvalidHourStr() {
        // GIVEN a CredentialProperty instance with expiresHourStr = "abc"
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return null
        assertNull(credentialProperty.convertToIntExpires());
    }

    @Test
    void convertToIntExpires_MinusHourStr() {
        // GIVEN a CredentialProperty instance with expiresHourStr = "-"
        // WHEN the convertToIntExpires() method is called
        // THEN the method should return null
        assertNull(credentialProperty.convertToIntExpires());
    }
}
