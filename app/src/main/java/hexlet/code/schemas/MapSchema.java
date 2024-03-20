package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<R, T> extends BaseSchema<T> {

    public MapSchema<R, T> required() {
        Predicate<T> requiredMap = map -> map instanceof Map<?, ?>;
        addValidityCheck("required", requiredMap);
        return this;
    }

    public MapSchema<R, T> sizeof(Integer size) {
        Predicate<T> sizeofMap = map -> map == null || map instanceof Map && ((Map) map).size() == size;
        addValidityCheck("sizeof", sizeofMap);
        return this;
    }

    public MapSchema<R, T> shape(Map<String, BaseSchema<T>> schemas) {
        Predicate<T> shapeMap = map -> map == null || map instanceof Map && schemas.entrySet().stream()
                .allMatch(schema -> schema.getValue()
                        .isValid((T) ((Map<String, ?>) map).get(schema.getKey())));
        addValidityCheck("shape", shapeMap);
        return this;
    }
}

