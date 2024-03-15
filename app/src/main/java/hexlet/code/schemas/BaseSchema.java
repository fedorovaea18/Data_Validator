package hexlet.code.schemas;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
public class BaseSchema<T> {
    private final List<Predicate<T>> validators = new ArrayList<>();

    public BaseSchema<T> addValidator(Predicate<T> validator) {
        validators.add(validator);
        return this;
    }

    public boolean isValid(T obj) {
        return validators.stream().allMatch(validator -> validator.test(obj));
    }
}
