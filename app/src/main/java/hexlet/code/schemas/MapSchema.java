package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<String, Object>> {

    public MapSchema required() {
        addValidator(map -> map != null, "required");
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidator(map -> map != null && map.size() == size, "sizeof");
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        addValidator(map -> {
            if (map == null) {
                return true;
            }
            return schemas.entrySet().stream()
                    .allMatch(schemaEntry -> {
                        String key = schemaEntry.getKey();
                        BaseSchema<?> schema = schemaEntry.getValue();
                        return map.containsKey(key) && schema.isValid(map.get(key));
                    });
        }, "shape");
        return this;
    }
}
