package com.amdocs.whs.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.amdocs.whs.util.InputValidator;

public class InputValidatorTest {

    @Test
    public void testIsEmpty() {
        assertTrue(InputValidator.isEmpty(null));
        assertTrue(InputValidator.isEmpty(""));
        assertTrue(InputValidator.isEmpty("   "));
        assertFalse(InputValidator.isEmpty("hello"));
    }

    @Test
    public void testIsValidInteger() {
        assertTrue(InputValidator.isValidInteger("123"));
        assertFalse(InputValidator.isValidInteger("abc"));
        assertFalse(InputValidator.isValidInteger(""));
    }

    @Test
    public void testIsValidNumber() {
        assertTrue(InputValidator.isValidNumber("10.5"));
        assertTrue(InputValidator.isValidNumber("100"));
        assertFalse(InputValidator.isValidNumber("ten"));
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(InputValidator.isValidEmail("test@example.com"));
        assertFalse(InputValidator.isValidEmail("not-an-email"));
        assertFalse(InputValidator.isValidEmail(""));
    }
}
