package com.jessy.user.exception;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }
    public AccessDeniedException(String msg) {
        super(msg);
    }
    public AccessDeniedException() {
        super();
    }
}
