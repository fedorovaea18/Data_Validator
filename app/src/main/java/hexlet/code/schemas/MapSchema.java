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
}
