package com.bestpractice.api.common.property;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

@org.junit.jupiter.api.Test
class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @org.junit.jupiter.api.Test
    void convertToIntExpires_validInput() {
        credentialProperty.setExpiresHourStr("123");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(123, result);
    }

    @org.junit.jupiter.api.Test
    void convertToIntExpires_invalidInput() {
        credentialProperty.setExpiresHourStr("-");
        Integer result = credentialProperty.convertToIntExpires();
        assertNull(result);
    }

    @org.junit.jupiter.api.Test
    void convertToIntExpires_invalidInputFormatException() {
        credentialProperty.setExpiresHourStr("abc");
        Integer result = credentialProperty.convertToIntExpires();
        assertNull(result);
    }

    @org.junit.jupiter.api.Test
    void convertToIntExpires_zeroInput() {
        credentialProperty.setExpiresHourStr("0");
        Integer result = credentialProperty.convertToIntExpires();
        assertEquals(0, result);
    }

    @org.junit.jupiter.api.Test
    void convertToIntExpires_validInput_null() {
        credentialProperty.setExpiresHourStr(null);
        Integer result = credentialProperty.convertToIntExpires();
        assertNull(result);
    }
}
