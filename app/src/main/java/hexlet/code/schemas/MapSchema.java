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

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        addValidation(map -> {
            Map<?, ?> contentMap = (Map<?, ?>) map;
            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                String parameterName = entry.getKey();
                BaseSchema currentSchema = entry.getValue();
                Object parameterValue = contentMap.get(parameterName);

                if (parameterValue == null || !currentSchema.isValid(parameterValue)) {
                    return false;
                }
            }
            return true;
        }, "shape");
        return this;
    }

}
