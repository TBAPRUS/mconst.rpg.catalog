package mconst.rpg.catalog.models.exceptions;


import lombok.Data;

import java.util.HashMap;

@Data
public class NotFoundException extends Exception {
    private ExceptionBody body;

    public NotFoundException() {
        this.body = null;
    }

    public NotFoundException(ExceptionBody body) {
        this.body = body;
    }
}
