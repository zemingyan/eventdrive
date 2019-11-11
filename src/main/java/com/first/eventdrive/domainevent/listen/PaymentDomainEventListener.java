package com.first.eventdrive.domainevent.listen;

import com.first.eventdrive.domainevent.DomainEventCenter;
import com.first.eventdrive.domainevent.event.DomainEvent;
import com.first.eventdrive.domainevent.event.PaymentDomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PaymentDomainEventListener implements DomainEventListener{
    private static Logger logger = LoggerFactory.getLogger(PaymentDomainEventListener.class);

    @Autowired
    DomainEventCenter domainEventCenter;


    @PostConstruct
    public void register(){
        domainEventCenter.register(PaymentDomainEvent.class, this);
    }


    @Override
    public void trigger(DomainEvent domainEvent) {
        logger.info("订单类型={}, 订单id={}, 支付订单完毕",domainEvent.getEventType().getMsg(), domainEvent.getEventId());
    }
}
