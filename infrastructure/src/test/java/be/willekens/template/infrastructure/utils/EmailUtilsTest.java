package be.willekens.template.infrastructure.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailUtilsTest {

    @Test
    void ValidEmail() {
        assertTrue(EmailUtils.isValidEmail("test1._%+-@test123.-.test"));
    }

    @Test
    void ValidEmailInAllUppercase() {
        assertTrue(EmailUtils.isValidEmail("TEST@TEST.TEST"));
    }

    @Test
    void EmailWithNothingBeforeTheAt() {
        assertFalse(EmailUtils.isValidEmail("@test.test"));
    }

    @Test
    void EmailWithNothingAfterTheDot() {
        assertFalse(EmailUtils.isValidEmail("test@test."));
    }

    @Test
    void EmailWithNothingBetweenAtAndDot() {
        assertFalse(EmailUtils.isValidEmail("test@.test"));
    }

    @Test
    void EmailWithAToLongValueAfterTheDot() {
        assertFalse(EmailUtils.isValidEmail("test@test.testtest"));
    }

    @Test
    void EmailWithAToShortValueAfterTheDot() {
        assertFalse(EmailUtils.isValidEmail("test@test.t"));
    }

    @Test
    void EmailWithNumbersAfterTheDot() {
        assertFalse(EmailUtils.isValidEmail("test@test.123"));
    }

    @Test
    void EmailWithNoAtSign() {
        assertFalse(EmailUtils.isValidEmail("testtest.test"));
    }

    @Test
    void EmailWithNoDotAfterTheAtSign() {
        assertFalse(EmailUtils.isValidEmail("test@testtest"));
    }

}