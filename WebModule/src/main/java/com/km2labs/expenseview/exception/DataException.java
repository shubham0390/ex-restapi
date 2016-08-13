package com.km2labs.expenseview.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by suze on 07/08/16.
 */
@Getter
@Setter
public class DataException extends ClientException {

    public DataException(List<DataError> message) {
        super(message);
    }

    @Getter
    @Setter
    public static class DataError {
        private int code;
        private String message;

        public DataError(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
