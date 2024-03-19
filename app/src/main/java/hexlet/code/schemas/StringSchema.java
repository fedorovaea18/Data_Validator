package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidator(str -> str != null && !str.isEmpty(), "required");
        return this;
    }

    public StringSchema minLength(Integer length) {
        addValidator(str -> str == null || str.length() >= length, "minLength");
        return this;
    }

    public StringSchema contains(String substring) {
        addValidator(str -> str == null || str.contains(substring), "contains");
        return this;
    }
}

