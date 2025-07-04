package com.bestpractice.api.common.property;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

class CredentialPropertyGeneratedAiTests {

    @ExtendWith(MyExtension.class)
    public class CredentialPropertyGeneratedAiTests extends CredentialProperty {

        @BeforeEach
        void setUp() {
            setKey("testKey");
            setProvider("provider1");
            setSubject("subject1");
            setAlg("HS256");
            setHmacSecret("hmacSecret");
            setExpiresHourStr("1");
        }

        String getKey() {
            return "testKey";
        }

        String getProvider() {
            return "provider1";
        }

        String getSubject() {
            return "subject1";
        }

        String getAlg() {
            return "HS256";
        }

        String getHmacSecret() {
            return "hmacSecret";
        }

        String getExpiresHourStr() {
            return "1";
        }

        String convertToIntExpires() {
            if (setExpiresHourStr("-")) {
                return null;
            }
            if (setExpiresHourStr("abc")) {
                return null;
            }
            return Integer.parseInt(setExpiresHourStr());
        }
    }
}