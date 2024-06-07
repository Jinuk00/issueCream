package com.clova.issuecream.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException{
    private String code = "";
    private Object data;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, String code) {
        super(message);
        this.code = code;
    }

    public UserException(String message, String code, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }
}
