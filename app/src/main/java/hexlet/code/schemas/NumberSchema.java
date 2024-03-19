package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addValidation(value -> value != null, "required");
        return this;
    }

    public NumberSchema positive() {
        addValidation(value -> value == null || (((Number) value).doubleValue() > 0), "positive");
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidation(value -> {
            if (value == null) {
                return false;
            }
            Number number = (Number) value;
            double doubleValue = number.doubleValue();
            return doubleValue >= min && doubleValue <= max;
        }, "range");
        return this;
    }
}

