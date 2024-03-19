package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<Predicate<T>, String> validators = new HashMap<>();

    public void addValidator(Predicate<T> validator, String identifier) {
        validators.put(validator, identifier);
    }

    public boolean isValid(Object object) {
        return validators.keySet().stream().allMatch(validator -> validator.test((T) object));
    }
}

