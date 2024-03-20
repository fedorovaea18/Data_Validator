package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema<T> extends BaseSchema<T> {
    public NumberSchema() {
        Predicate<T> instanceofString = num -> num == null || num instanceof Integer;
        addValidityCheck("instanceof", instanceofString);
    }
    public NumberSchema<T> required() {
        Predicate<T> requiredNumber =  Objects::nonNull;
        addValidityCheck("required", requiredNumber);
        return this;
    }

    public NumberSchema<T> positive() {
        Predicate<T> positiveNumber = num -> num instanceof Integer && (Integer) num > 0 || num == null;
        addValidityCheck("positive", positiveNumber);
        return this;
    }

    public NumberSchema<T> range(Integer min, Integer max) {
        Predicate<T> rangeNumber = num -> num == null || num instanceof Integer
                && ((Integer) num >= min && (Integer) num <= max);
        addValidityCheck("range", rangeNumber);
        return this;
    }
}

