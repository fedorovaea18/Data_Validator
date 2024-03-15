package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        this.addValidator(map -> map != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.addValidator(map -> map.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.addValidator(map -> {
            if (map == null || schemas == null) {
                return false;
            }

            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<String> schema = entry.getValue();

                String value = (String) map.get(key);
                if (value == null || !schema.isValid(value)) {
                    return false;
                }
            }

            return true;
        });

        return this;
    }
}
