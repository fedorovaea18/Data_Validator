### Hexlet tests and linter status:
[![Actions Status](https://github.com/fedorovaea18/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/fedorovaea18/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/f98370da14866d304cd0/maintainability)](https://codeclimate.com/github/fedorovaea18/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/f98370da14866d304cd0/test_coverage)](https://codeclimate.com/github/fedorovaea18/java-project-78/test_coverage)
[![GitHub Actions Status](https://github.com/fedorovaea18/java-project-78/actions/workflows/github-actions.yml/badge.svg)](https://github.com/fedorovaea18/java-project-78/actions)

# **Валидатор данных**

Данный проект реализует функцию создания собственной библиотеки для проверки валидации данных в зависимости от их типа. Проверяемые типы данных: String, Number и Map.

## **Валидация строк**

В проекте реализованы следующие методы проверки строк:

- _required()_ — строка должна быть заполненной(непустой);

- _minLength()_ — строка должна быть равна или длиннее указанного числа;

- _contains()_ — cтрока должна содержать определённую подстроку.
  
После настройки схемы валидации необходимо вызвать метод _isValid()_ для проверки данных.

Пример использования:
```java
Validator v = new Validator();
StringSchema schema = v.string();

schema.contains("what").isValid("what does the fox say"); // true
schema.required().isValid(""); //false
```
## **Валидация чисел**

В проекте реализованы следующие методы проверки чисел:

- _required()_ — любое число;

- _positive()_ — положительное число;

- _range()_ — диапазон, в который попадает значение числа, включая границы.

После настройки схемы валидации необходимо вызвать метод _isValid()_ для проверки данных.

Пример использования:
```java
Validator v = new Validator();
NumberSchema schema = v.number();

schema.positive().isValid(5); // true
schema.range(5, 10).isValid(4); //false
```
## **Валидация объектов типа Map**

В проекте реализованы следующие валидаторы проверки объектов Map:

- _required()_ — требуется тип данных Map;

- _sizeof()_ — количество пар ключ-значений в объекте Map равно заданному.

## **Вложенная валидация**
В проекте реализована проверка данных внутри объектов Map с помощью валидатора _shape()_.
