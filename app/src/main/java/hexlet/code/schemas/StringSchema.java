package hexlet.code.schemas;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StringSchema {
    private boolean isRequired;
    private int minLength;
    private String containsString;

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }

    public StringSchema minLength(int min) {
        this.minLength = min;
        return this;
    }

    public StringSchema contains(String str) {
        this.containsString = str;
        return this;
    }

    public boolean isValid(String data) {
        if (isRequired && (data == null || data.isEmpty())) {
            return false;
        }

        if (data == null || data.length() < minLength) {
            return false;
        }

        return containsString == null || data.contains(containsString);
    }
}
