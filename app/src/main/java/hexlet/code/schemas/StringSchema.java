package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema<T> extends BaseSchema<T> {
    public StringSchema() {
        Predicate<T> instanceofString = str -> str == null || str instanceof String;
        addValidityCheck("instanceof", instanceofString);
    }

    public StringSchema required() {
        Predicate<T> requiredString = str -> (str != null && !str.equals(""));
        addValidityCheck("required", requiredString);
        return this;
    }

    public StringSchema minLength(Integer minLength) {
        Predicate<T> minLengthString = str -> str != null && ((String) str).length() >= minLength;
        addValidityCheck("minLength", minLengthString);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<T> containsString = str -> substring != null && str != null
                && str.toString().contains(substring);
        addValidityCheck("contains", containsString);
        return this;
    }
}
