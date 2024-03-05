package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTest {

    Validator v = new Validator();
    StringSchema schema = v.string();

    @Test
    void testIsValidWithRequiredEmptyString() {
        assertTrue(schema.isValid(""));
    }

    @Test
    void testIsValidWithRequiredNull() {
        assertFalse(schema.isValid(null));
    }

    @Test
    void testIsValidWithRequiredValidString() {
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    public void testIsValidWithMinLength() {
        assertFalse(schema.minLength(5).isValid("hi"));
    }

    @Test
    void testIsValidWithContains() {
        assertTrue(schema.contains("fox").isValid("what does the fox say"));
    }

    @Test
    void testIsValidWithChainedContains() {
        assertTrue(schema.contains("wh").contains("what").isValid("what does the fox say"));
    }

    @Test
    void testIsValidWithChainedContainsInvalid() {
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
    }
}
