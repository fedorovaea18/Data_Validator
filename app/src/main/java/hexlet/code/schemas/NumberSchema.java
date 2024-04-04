package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        Predicate<Integer> requiredNumber = Objects::nonNull;
        addValidityCheck("required", requiredNumber);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> positiveNumber = num -> num == null || num > 0;
        addValidityCheck("positive", positiveNumber);
        return this;
    }

    public NumberSchema range(Integer min, Integer max) {
        Predicate<Integer> rangeNumber = num -> num == null || (num >= min && num <= max);
        addValidityCheck("range", rangeNumber);
        return this;
    }
}
