package your.package.name;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CredentialPropertyGeneratedAiTests {

    private CredentialProperty credentialProperty;

    @BeforeEach
    void setUp() {
        credentialProperty = new CredentialProperty();
    }

    @Test
    void testDefaultState() {
        // GIVEN
        // No properties set

        // WHEN
        // Retrieve default values

        // THEN
        assertThat(credentialProperty.getKey()).isNull();
        assertThat(credentialProperty.getProvider()).isNull();
        assertThat(credentialProperty.getSubject()).isNull();
        assertThat(credentialProperty.getAlg()).isNull();
        assertThat(credentialProperty.getHmacSecret()).isNull();
        assertThat(credentialProperty.getExpiresHourStr()).isNull();
    }

    @Test
    void testGettersAndSetters() {
        // GIVEN
        String key = "myKey";
        String provider = "myProvider";
        String subject = "mySubject";
        String alg = "HS256";
        String hmacSecret = "secret";
        String expiresHourStr = "12";

        // WHEN
        credentialProperty.setKey(key);
        credentialProperty.setProvider(provider);
        credentialProperty.setSubject(subject);
        credentialProperty.setAlg(alg);
        credentialProperty.setHmacSecret(hmacSecret);
        credentialProperty.setExpiresHourStr(expiresHourStr);

        // THEN
        assertThat(credentialProperty.getKey()).isEqualTo(key);
        assertThat(credentialProperty.getProvider()).isEqualTo(provider);
        assertThat(credentialProperty.getSubject()).isEqualTo(subject);
        assertThat(credentialProperty.getAlg()).isEqualTo(alg);
        assertThat(credentialProperty.getHmacSecret()).isEqualTo(hmacSecret);
        assertThat(credentialProperty.getExpiresHourStr()).isEqualTo(expiresHourStr);
    }

    @Test
    void testConvertToIntExpiresWithValidString() {
        // GIVEN
        credentialProperty.setExpiresHourStr("24");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(24);
    }

    @Test
    void testConvertToIntExpiresWithDash() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithInvalidString() {
        // GIVEN
        credentialProperty.setExpiresHourStr("invalid");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithEmptyString() {
        // GIVEN
        credentialProperty.setExpiresHourStr("");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithWhitespace() {
        // GIVEN
        credentialProperty.setExpiresHourStr("   ");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithLeadingWhitespace() {
        // GIVEN
        credentialProperty.setExpiresHourStr(" 10");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithTrailingWhitespace() {
        // GIVEN
        credentialProperty.setExpiresHourStr("10 ");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresWithZero() {
        // GIVEN
        credentialProperty.setExpiresHourStr("0");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(0);
    }

    @Test
    void testConvertToIntExpiresWithNegativeNumber() {
        // GIVEN
        credentialProperty.setExpiresHourStr("-5");

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isEqualTo(-5);
    }

    @Test
    void testConvertToIntExpiresWithOverflow() {
        // GIVEN
        credentialProperty.setExpiresHourStr("2147483648"); // one more than Integer.MAX_VALUE

        // WHEN
        Integer result = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(result).isNull();
    }

    @Test
    void testConvertToIntExpiresAfterMultipleUpdates() {
        // GIVEN
        credentialProperty.setExpiresHourStr("10");

        // WHEN
        Integer firstResult = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(firstResult).isEqualTo(10);

        // WHEN
        credentialProperty.setExpiresHourStr("20");
        Integer secondResult = credentialProperty.convertToIntExpires();

        // THEN
        assertThat(secondResult).isEqualTo(20);
    }

    @Test
    void testConvertToIntExpiresWithNullThrowsException() {
        // GIVEN
        credentialProperty.setExpiresHourStr(null);

        // WHEN
        // THEN
        assertThatThrownBy(() -> credentialProperty.convertToIntExpires())
                .isInstanceOf(NullPointerException.class);
    }
}
