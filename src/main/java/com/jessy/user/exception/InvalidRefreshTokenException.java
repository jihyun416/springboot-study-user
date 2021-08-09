package com.jessy.user.exception;
public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(String msg, Throwable t) {
        super(msg, t);
    }
    public InvalidRefreshTokenException(String msg) {
        super(msg);
    }
    public InvalidRefreshTokenException() {
        super();
    }
}
