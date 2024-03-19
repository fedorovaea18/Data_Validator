package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate> validations = new HashMap<>();

    public final boolean isValid(Object object) {
        return validations.values()
                .stream()
                .allMatch(validation -> validation.test(object));
    }

    protected final void addValidation(Predicate<Object> value, String nameValidation) {
        validations.put(nameValidation, value);
    }
}

