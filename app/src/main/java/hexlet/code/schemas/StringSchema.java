package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        Predicate<Object> instanceofString = str -> str == null || str instanceof String;
        addValidityCheck("instanceof", instanceofString);
    }

    public StringSchema required() {
        Predicate<Object> requiredString = str -> (str != null && !str.equals(""));
        addValidityCheck("required", requiredString);
        return this;
    }

    public StringSchema minLength(Integer minLength) {
        Predicate<Object> minLengthString = str -> str != null && ((String) str).length() >= minLength;
        addValidityCheck("minLength", minLengthString);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<Object> containsString = str -> substring != null && str != null
                && str.toString().contains(substring);
        addValidityCheck("contains", containsString);
        return this;
    }
}
