package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        Predicate<Object> instanceofString = num -> num == null || num instanceof Integer;
        addValidityCheck("instanceof", instanceofString);
    }
    public NumberSchema required() {
        Predicate<Object> requiredNumber =  Objects::nonNull;
        addValidityCheck("required", requiredNumber);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Object> positiveNumber = num -> num instanceof Integer && (Integer) num > 0 || num == null;
        addValidityCheck("positive", positiveNumber);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        Predicate<Object> rangeNumber = num -> num == null || num instanceof Integer
                && ((Integer) num >= min && (Integer) num <= max);
        addValidityCheck("range", rangeNumber);
        return this;
    }
}

