package com.first.eventdrive.domainevent;

import com.first.eventdrive.domainevent.event.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DomainEventSender {
    @Autowired
    private DomainEventCenter domainEventCenter;

    public void post(DomainEvent domainEvent){
        domainEventCenter.post(domainEvent);
    }
}
