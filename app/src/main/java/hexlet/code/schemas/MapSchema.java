package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    public MapSchema required() {
        this.addValidator(map -> map != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.addValidator(map -> ((Map<?, ?>) map).size() == size);
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
        });
        return this;
    }
}
