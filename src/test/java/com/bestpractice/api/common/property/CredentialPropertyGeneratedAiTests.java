package com.bestpractice.api.common.property;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.Test;
import static org.junit.Assert.*;

@Test
public class CredentialPropertyTest {

    @Test
    public void testGetProvider() {
        CredentialProperty property = new CredentialProperty();
        assertEquals("Provider", property.getProvider());
    }

    @Test
    public void testSetProvider() {
        CredentialProperty property = new CredentialProperty();
        property.setProvider("MyProvider");
        assertEquals("MyProvider", property.getProvider());
    }

    @Test
    public void testGetSubject() {
        CredentialProperty property = new CredentialProperty();
        assertEquals("Subject", property.getSubject());
    }

    @Test
    public void testSetSubject() {
        CredentialProperty property = new CredentialProperty();
        property.setSubject("MySubject");
        assertEquals("MySubject", property.getSubject());
    }

    @Test
    public void testGetAlg() {
        CredentialProperty property = new CredentialProperty();
        assertEquals("Alg", property.getAlg());
    }

    @Test
    public void testSetAlg() {
        CredentialProperty property = new CredentialProperty();
        property.setAlg("MyAlg");
        assertEquals("MyAlg", property.getAlg());
    }

    @Test
    public void testConvertToIntExpires() {
        CredentialProperty property = new CredentialProperty();
        Integer result = property.convertToIntExpires();
        assertNotNull(result);
        assertNull(result.intValue());
    }

    @Test
    public void testGetExpiresHourStr() {
        CredentialProperty property = new CredentialProperty();
        assertEquals("ExpiresHourStr", property.getExpiresHourStr());
    }

    @Test
    public void testSetExpiresHourStr() {
        CredentialProperty property = new CredentialProperty();
        property.setExpiresHourStr("-1");
        assertEquals("-1", property.getExpiresHourStr());
    }

    @Test
    public void testassertEquals() {
        CredentialProperty property = new CredentialProperty();
        assertEquals(null, property.convertToIntExpires());
    }
}
