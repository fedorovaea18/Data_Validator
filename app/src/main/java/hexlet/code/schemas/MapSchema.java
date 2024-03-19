package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema required() {
        addValidation(map -> map != null, "required");
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation(map -> map == null || ((Map<?, ?>) map).size() == size, "sizeof");
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> mapSchemas) {
        addValidation(map -> mapSchemas.entrySet().stream()
                .allMatch(keyAndValue -> keyAndValue.getValue()
                        .isValid(((Map<?, ?>) map).get(keyAndValue.getKey()))), "shape");
        return this;
    }
}

