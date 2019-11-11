package com.first.eventdrive.domainevent.event;

import com.first.eventdrive.domainevent.EventType;

public interface DomainEvent {
    Long getEventId();

    EventType getEventType();

}
