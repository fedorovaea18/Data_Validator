package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    public StringSchema required() {
        addValidator(str -> str != null && !((String) str).isEmpty(), "required");
        return this;
    }

    public StringSchema minLength(int length) {
        addValidator(str -> ((String) str).length() >= length, "minLength");
        return this;
    }

    public StringSchema contains(String substring) {
        addValidator(str -> ((String) str).contains(substring), "contains");
        return this;
    }
}
