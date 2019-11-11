package com.first.eventdrive.domainevent.event;

import com.first.eventdrive.domainevent.EventType;
import org.omg.CORBA.INTERNAL;

public class OrderDomainEvent implements DomainEvent {
    private Long orderId;
    @Override
    public Long getEventId() {
        return orderId;
    }

    @Override
    public EventType getEventType() {
        return EventType.ORDER;
    }

    public OrderDomainEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
