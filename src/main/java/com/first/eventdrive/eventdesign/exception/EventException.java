package com.first.eventdrive.eventdesign.exception;

public class EventException extends RuntimeException {
    private String msg;
    public EventException(String msg) {
        super();
        this.msg = msg;
    }
}
