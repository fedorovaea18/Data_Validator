package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema required() {
        addValidator(map -> map != null, "required");
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidator(map -> map == null ||((Map<?, ?>) map).size() == size, "sizeof");
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addValidator(map -> {
            Map<?, ?> contentMap = (Map<?, ?>) map;
            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                String parameterName = entry.getKey();
                BaseSchema currentSchema = entry.getValue();
                if (!currentSchema.isValid(contentMap.get(parameterName))) {
                    return false;
                }
            }
            return true;
        }, "shape");
        return this;
    }
}
