package mconst.rpg.catalog.models.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ExceptionBody {
    Map<String, Object> errors;

    public ExceptionBody() {
        errors = new HashMap<>();
    }

    public void addError(Object value, String ... fields) {
        Map<String, Object> ptr = errors;
        for (int i = 0; i < fields.length - 1; i++) {
            var field = fields[i];
            if (!ptr.containsKey(field)) {
                ptr.put(field, new HashMap<String, Object>());
            }
            ptr = (Map<String, Object>) ptr.get(field);
        }
        ptr.put(fields[fields.length - 1], value);
    }
}