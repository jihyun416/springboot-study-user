package com.jessy.user.exception;

public class NoDataException extends RuntimeException {
    public NoDataException(String msg, Throwable t) {
        super(msg, t);
    }
    public NoDataException(String msg) {
        super(msg);
    }
    public NoDataException() {
        super();
    }
}
