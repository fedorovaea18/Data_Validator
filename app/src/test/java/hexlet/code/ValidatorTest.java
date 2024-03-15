package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void testNumberSchemaValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(5));

        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void testMapSchemaValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null)); // Должно быть true

        schema.required();

        assertFalse(schema.isValid(null)); // Должно быть false
        assertTrue(schema.isValid(new HashMap<>())); // Должно быть true

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // Должно быть true

        schema.sizeof(2);

        assertFalse(schema.isValid(data)); // Должно быть false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // Должно быть true
    }
}
