package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Number> {

    public NumberSchema required() {
        this.addValidator(value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        this.addValidator(value -> value == null || (value.doubleValue() > 0));
        return this;
    }


    public NumberSchema range(int min, int max) {
        this.addValidator(value -> value != null && value.doubleValue() >= min && value.doubleValue() <= max);
        return this;
    }
}
