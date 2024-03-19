package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
        schema.sizeof(2);

        assertFalse(schema.isValid(data));
        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testMapSchemaValidation2() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        HashMap<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", v.string().required().minLength(4));
        schemas.put("age", v.number().required().positive());

        schema.shape(schemas);

        Map<String, Object> person1 = new HashMap<>();
        person1.put("name", "John");
        person1.put("age", 25);
        assertTrue(schema.isValid(person1));

        Map<String, Object> person2 = new HashMap<>();
        person2.put("name", "Bob");
        person2.put("age", null);
        assertFalse(schema.isValid(person2));

        Map<String, Object> person3 = new HashMap<>();
        person3.put("name", "Anna");
        person3.put("age", -30);
        assertFalse(schema.isValid(person3));

        Map<String, Object> person4 = new HashMap<>();
        person4.put("name", "Diana");
        person4.put("age", 40);
        assertTrue(schema.isValid(person4));
    }
}
