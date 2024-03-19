package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public void required() {
        this.addValidator(map -> map != null);
    }

    public void sizeof(int size) {
        this.addValidator(map -> ((Map<?, ?>) map).size() == size);
    }

    public void shape(Map<String, BaseSchema> schemas) {
        this.addValidator(map -> checkMap((Map<?, ?>) map, schemas));
    }

    boolean checkMap(Map<?, ?> map, Map<String, BaseSchema> schemas) {
        return map != null && schemas != null && schemas.entrySet().stream()
                .allMatch(entry -> {
                    String key = entry.getKey();
                    BaseSchema schema = schemas.get(key);
                    Object value = map.get(key);
                    return value != null && schema != null && schema.isValid(value);
                });
    }
}

