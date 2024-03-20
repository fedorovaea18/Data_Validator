package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public MapSchema required() {
        Predicate<Object> requiredMap = map -> map instanceof Map<?, ?>;
        addValidityCheck("required", requiredMap);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        Predicate<Object> sizeofMap = map -> map == null || map instanceof Map && ((Map) map).size() == size;
        addValidityCheck("sizeof", sizeofMap);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        Predicate<Object> shapeMap = map -> map == null || map instanceof Map && schemas.entrySet().stream()
                .allMatch(schema -> schema.getValue()
                        .isValid(((Map<String, ?>) map).get(schema.getKey())));
        addValidityCheck("shape", shapeMap);
        return this;
    }
}

