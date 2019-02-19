package com.common.exceptions;

/**
 * token错误异常
 *
 * @author wangjie
 */
public class TokenErrorException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TokenErrorException() {
    }

    public TokenErrorException(String message) {
        super(message);
    }

    public TokenErrorException(Throwable cause) {
        super(cause);
    }

    public TokenErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
