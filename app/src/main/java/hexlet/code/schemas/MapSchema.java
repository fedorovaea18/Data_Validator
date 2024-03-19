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

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addValidator(map -> {
            for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
                String parameterName = entry.getKey();
                BaseSchema<String> currentSchema = entry.getValue();
                Object parameterValue = ((Map<?, ?>) map).get(parameterName);

                if (parameterValue == null || !currentSchema.isValid((String) parameterValue)) {
                    return false;
                }
            }
            return true;
        }, "shape");
        return this;
    }
}

