package com.first.eventdrive.domainevent.exception;

import com.first.eventdrive.domainevent.event.DomainEvent;

public class DomainEventException extends RuntimeException {
    private String msd;
    public DomainEventException(String msg){
        super();
        this.msd = msg;
    }

    public String getMsd() {
        return msd;
    }

    public void setMsd(String msd) {
        this.msd = msd;
    }
}
