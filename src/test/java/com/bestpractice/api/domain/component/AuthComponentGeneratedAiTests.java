package com.bestpractice.api.domain.component;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class StringUtilTest {

    @BeforeAll
    static void setUpBeforeAllTests() {
    }

    @BeforeEach
    void setUpBeforeEachTest() {
    }

    @AfterAll
    static void tearDownAfterAllTests() {
    }

    @AfterEach
    void tearDownAfterEachTest() {
    }

    @Test
    void removeTrailingNewline_emptyString() {
        StringUtil stringUtil = new StringUtil();
        String result = stringUtil.removeTrailingNewline("");
        assert result.isEmpty();
    }

    @Test
    void removeTrailingNewline_stringWithNewline() {
        StringUtil stringUtil = new StringUtil();
        String input = "Hello\n";
        String result = stringUtil.removeTrailingNewline(input);
        assert result.equals("Hello");
    }

    @Test
    void removeTrailingNewline_stringWithoutNewline() {
        StringUtil stringUtil = new StringUtil();
        String input = "Hello";
        String result = stringUtil.removeTrailingNewline(input);
        assert result.equals(input);
    }
}