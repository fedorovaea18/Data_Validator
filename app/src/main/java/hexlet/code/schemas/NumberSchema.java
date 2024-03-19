package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        addValidator(value -> value != null, "required");
        return this;
    }

    public NumberSchema positive() {
        addValidator(value -> value == null || value.doubleValue() > 0, "positive");
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidator(value -> {
            if (value == null) {
                return false;
            }
            double doubleValue = value.doubleValue();
            return doubleValue >= min && doubleValue <= max;
        }, "range");
        return this;
    }
}
