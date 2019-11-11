package com.first.eventdrive.domainevent.listen;

import com.first.eventdrive.domainevent.event.DomainEvent;

public interface DomainEventListener {
    public void trigger(DomainEvent domainEvent);
}
