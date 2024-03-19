package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema required() {
        addValidation(str -> str != null && !((String) str).isEmpty(), "required");
        return this;
    }

    public StringSchema minLength(Integer length) {
        addValidation(str -> str == null || ((String) str).length() >= length, "minLength");
        return this;
    }

    public StringSchema contains(String substring) {
        addValidation(str -> str == null || ((String) str).contains(substring), "contains");
        return this;
    }
}
