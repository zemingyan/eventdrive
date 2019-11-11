package com.first.eventdrive.domainevent.event;

import com.first.eventdrive.domainevent.EventType;

public class StocDomainEvent implements  DomainEvent {
    private Long stocId;

    @Override
    public Long getEventId() {
        return stocId;
    }

    @Override
    public EventType getEventType() {
        return EventType.STOC;
    }

    public StocDomainEvent(Long stocId) {
        this.stocId = stocId;
    }

    public Long getStocId() {
        return stocId;
    }

    public void setStocId(Long stocId) {
        this.stocId = stocId;
    }
}
