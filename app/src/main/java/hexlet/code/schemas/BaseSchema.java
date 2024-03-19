package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final List<Predicate<Object>> validators = new ArrayList<>();

    public void addValidator(Predicate<Object> validator) {
        validators.add(validator);
    }

    public boolean isValid(Object object) {
        return validators.stream().allMatch(validator -> validator.test(object));
    }
}
