package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<Predicate<Object>, String> validators = new HashMap<>();

    public void addValidator(Predicate<Object> validator, String identifier) {
        validators.put(validator, identifier);
    }

    public boolean isValid(Object object) {
        return validators.keySet().stream().allMatch(validator -> validator.test(object));
    }
}
