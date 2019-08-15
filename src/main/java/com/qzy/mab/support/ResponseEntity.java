package com.qzy.mab.support;

import lombok.Data;

/**
 * 响应实体
 *
 * @author qzy
 */
@Data
public class ResponseEntity<T> {

    private int code = CustomHttpStatus.OK.value();

    private String msg = CustomHttpStatus.OK.getReasonPhrase();

    private T result;

    public ResponseEntity(T result) {
        this.result = result;
    }

    public ResponseEntity(T result, CustomHttpStatus code) {
        this.result = result;
        this.code = code.value();
    }

    public ResponseEntity(){
        super();
    }

    public ResponseEntity(CustomHttpStatus code, String message) {
        this.code = code.value();
        this.msg = message;
    }

    public ResponseEntity(T result, CustomHttpStatus code, String message) {
        this.code = code.value();
        this.msg = message;
        this.result = result;
    }

}
