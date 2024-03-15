package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidator(str -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidator(str -> str.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addValidator(str -> str.contains(substring));
        return this;
    }
}
