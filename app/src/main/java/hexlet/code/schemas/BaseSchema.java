package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {
    private final Map<String, Predicate> validityChecks;
    protected BaseSchema() {
        this.validityChecks = new HashMap<>();
    }

    public final void addValidityCheck(String typeValidation, Predicate<Object> method) {
        validityChecks.put(typeValidation, method);
    }

    public final boolean isValid(Object object) {
        return validityChecks.values()
                .stream()
                .allMatch(check -> check.test(object));
    }
}
