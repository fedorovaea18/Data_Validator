# **Data Validator**
[![Actions Status](https://github.com/fedorovaea18/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/fedorovaea18/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/f98370da14866d304cd0/maintainability)](https://codeclimate.com/github/fedorovaea18/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f98370da14866d304cd0/test_coverage)](https://codeclimate.com/github/fedorovaea18/java-project-78/test_coverage)
[![GitHub Actions Status](https://github.com/fedorovaea18/java-project-78/actions/workflows/github-actions.yml/badge.svg)](https://github.com/fedorovaea18/java-project-78/actions)

This project implements the function of creating a custom library for data validation depending on their type. The library supports three data types: Strings, Integers and Maps.

## **StringSchema**

StringSchema has three validation methods:

- _required()_ — строка должна быть заполненной(непустой);

- _minLength()_ — строка должна быть равна или длиннее указанного числа;

- _contains()_ — cтрока должна содержать определённую подстроку.

Usage example:
```java
Validator v = new Validator();
StringSchema schema = v.string();

schema.required().isValid(""); //false
schema.minLength(6).isValid("fox"); //false
schema.contains("what").isValid("what does the fox say"); // true
```
## **NumberSchema**

NumberSchema has three validation methods:

- _required()_ — требуется наличие любого числа;

- _positive()_ — число должно быть положительным;

- _range()_ — значение числа должно попадать в диапазон, включая границы.

Usage example:
```java
Validator v = new Validator();
NumberSchema schema = v.number();

schema.required().isValid(-10); //false
schema.positive().isValid(5); // true
schema.range(5, 10).isValid(4); //false
```
## **MapSchema**

MapSchema has three validation methods:

- _required()_ — требуется тип данных Map;

- _sizeof()_ — количество пар ключ-значений в объекте Map должно быть равно заданному.

- _shape()_ - 

Usage example:
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
