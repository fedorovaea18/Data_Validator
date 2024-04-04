package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema<String, T> extends BaseSchema<Map<String, T>> {

    public MapSchema<String, T> required() {
        Predicate<Map<String, T>> requiredMap = map -> map != null && !map.isEmpty();
        addValidityCheck("required", requiredMap);
        return this;
    }

    public MapSchema<String, T> sizeof(Integer size) {
        Predicate<Map<String, T>> sizeofMap = map -> map != null && map.size() == size;
        addValidityCheck("sizeof", sizeofMap);
        return this;
    }

    public MapSchema<String, T> shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<Map<String, T>> shapeMap = map -> map != null && schemas.entrySet().stream()
                .allMatch(schema -> schema.getValue().isValid(map.get(schema.getKey())));
        addValidityCheck("shape", shapeMap);
        return this;
    }
}
