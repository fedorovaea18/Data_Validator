# **Data Validator**
[![Actions Status](https://github.com/fedorovaea18/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/fedorovaea18/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/f98370da14866d304cd0/maintainability)](https://codeclimate.com/github/fedorovaea18/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f98370da14866d304cd0/test_coverage)](https://codeclimate.com/github/fedorovaea18/java-project-78/test_coverage)
[![GitHub Actions Status](https://github.com/fedorovaea18/java-project-78/actions/workflows/github-actions.yml/badge.svg)](https://github.com/fedorovaea18/java-project-78/actions)

This project implements the function of creating a custom library for data validation depending on their type. The library supports three data types: Strings, Integers and Maps. Three schemas are available: StringSchema, NumberSchema and MapSchema.

## **Examples of usage:**
**StringSchema**

StringSchema has three validation methods:

- _required()_ — makes the fields required and limits the possibility to use null or empty String;

- _minLength()_ — adds a minimum length constraint for the String. The String must be equal or longer than a specified number. Requires an integer parameter of minimum length;

- _contains()_ — adds a String content constraint. The String must contain a substring passed in the method parameter.

```java
Validator v = new Validator();
StringSchema schema = v.string();

schema.required().isValid(""); //false
schema.minLength(6).isValid("fox"); //false
schema.contains("what").isValid("what does the fox say"); // true
```
**NumberSchema**

NumberSchema has three validation methods:

- _required()_ — makes the fileds required and limits the possibility to use null;

- _positive()_ — adds a constraint to use negative numbers;

- _range()_ — adds a range constraint (inclusive). Requires two integer parameters of the first and the last numbers of range.

```java
Validator v = new Validator();
NumberSchema schema = v.number();

schema.required().isValid(-10); //false
schema.positive().isValid(5); // true
schema.range(5, 10).isValid(4); //false
```
**MapSchema**

MapSchema has three validation methods:

- _required()_ — makes the fields required and limits the possibility to use null.

- _sizeof()_ — adds a map size constraint. The K-V count must be equal to the number passed in the method parameter.

- _shape()_ — adds constraints to map values. Accepts as a parameter a map of keys whose values need to be validated and schemas that would validate the values.

```java
Validator v = new Validator();
MapSchema schema = v.map();

schema.required().isValid(new HashMap<>()); //true

Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));
schemas.put("age", v.number().required().positive());

schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
human1.put("age", 30);
schema.isValid(human1); //true

Map<String, Object> human2 = new HashMap<>();
human2.put("firstName", "Anna");
human2.put("lastName", "B");
human2.put("age", -5);
schema.isValid(human2); //false

Map<String, Object> human3 = new HashMap<>();
human3.put("firstName", "Bob");
human3.put("lastName", null);
human3.put("age", 45);
schema.isValid(human3); //false
```
