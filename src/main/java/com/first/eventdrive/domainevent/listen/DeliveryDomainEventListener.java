package com.first.eventdrive.domainevent.listen;

import com.first.eventdrive.domainevent.DomainEventCenter;
import com.first.eventdrive.domainevent.event.DeliveryDomainEvent;
import com.first.eventdrive.domainevent.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeliveryDomainEventListener implements DomainEventListener {
    private Logger logger = LoggerFactory.getLogger(DeliveryDomainEventListener.class);

    @Autowired
    private DomainEventCenter domainEventCenter;

    @PostConstruct
    public void register(){
        domainEventCenter.register(DeliveryDomainEvent.class, this);
    }
    @Override
    public void trigger(DomainEvent domainEvent) {
        logger.info("快递订单，订单编号是 ={}, 订单类型是={}" + domainEvent.getEventId(), domainEvent.getEventType().getMsg());
    }
}
