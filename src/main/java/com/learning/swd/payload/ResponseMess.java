package com.learning.swd.payload;

import lombok.Data;

@Data
public class ResponseMess {

    private long code;
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMess(long code, String message) {
        this.code = code;
        this.message = message;
    }
}
