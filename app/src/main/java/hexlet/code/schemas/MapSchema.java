package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public void required() {
        this.addValidator(map -> map != null);
    }

    public void sizeof(int size) {
        this.addValidator(map -> map.size() == size);
    }

    public void shape(Map<String, BaseSchema> schemas) {
        this.addValidator(map -> checkMap(map, schemas));
    }

    boolean checkMap(Map<?, ?> map, Map<String, BaseSchema> schemas) {
        return map != null && schemas != null && schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema schema = entry.getValue();
                    Object value = map.get(key);
                    return value != null && schema.isValid(value);
                });
    }
}
