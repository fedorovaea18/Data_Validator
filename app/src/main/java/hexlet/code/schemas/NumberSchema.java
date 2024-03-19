package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        this.addValidator(value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        this.addValidator(value -> value == null || (((Number) value).doubleValue() > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.addValidator(value -> {
            if (value == null) {
                return false;
            }
            Number number = (Number) value;
            double doubleValue = number.doubleValue();
            return doubleValue >= min && doubleValue <= max;
        });
        return this;
    }
}

