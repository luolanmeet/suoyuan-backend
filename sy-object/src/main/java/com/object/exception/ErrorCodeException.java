package com.object.exception;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author cck
 */
@Getter
@Setter
public class ErrorCodeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;

    /**
     *
     * @param code
     */
    public ErrorCodeException(int code) {
        super();
        this.code = code;
    }

    /**
     * @param code
     * @param message
     */
    public ErrorCodeException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param code
     * @param codeText
     * @param message
     * @param cause
     */
    public ErrorCodeException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorCodeException() {
        super();
    }
}
