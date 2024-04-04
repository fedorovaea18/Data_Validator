package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> requiredString = str -> (str != null && !str.equals(""));
        addValidityCheck("required", requiredString);
        return this;
    }

    public StringSchema minLength(Integer minLength) {
        Predicate<String> minLengthString = str -> str != null && ((String) str).length() >= minLength;
        addValidityCheck("minLength", minLengthString);
        return this;
    }

    public StringSchema contains(String substring) {
        Predicate<String> containsString = str -> substring != null && str != null
                && str.toString().contains(substring);
        addValidityCheck("contains", containsString);
        return this;
    }
}
