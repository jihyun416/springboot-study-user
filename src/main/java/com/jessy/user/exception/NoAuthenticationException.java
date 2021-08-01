package com.jessy.user.exception;

public class NoAuthenticationException extends Exception {
    public NoAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }
    public NoAuthenticationException(String msg) {
        super(msg);
    }
    public NoAuthenticationException() {
        super();
    }
}
