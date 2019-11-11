package com.first.eventdrive.domainevent.listen;

import com.first.eventdrive.domainevent.DomainEventCenter;
import com.first.eventdrive.domainevent.event.DomainEvent;
import com.first.eventdrive.domainevent.event.StocDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StocDomainEventListener implements DomainEventListener {
    private Logger logger = LoggerFactory.getLogger(StocDomainEventListener.class);

    @Autowired
    private DomainEventCenter domainEventCenter;

    @PostConstruct
    public void register(){
        domainEventCenter.register(StocDomainEvent.class, this);
    }

    @Override
    public void trigger(DomainEvent domainEvent) {
        logger.info("处理的事件类型是 {},事件id = {}" + domainEvent.getEventType().getMsg(), domainEvent.getEventId());
    }
}
