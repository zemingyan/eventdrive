package com.first.eventdrive.domainevent.event;

import com.first.eventdrive.domainevent.EventType;

public class PaymentDomainEvent implements DomainEvent {
    private Long payId;

    @Override
    public Long getEventId() {
        return payId;
    }

    @Override
    public EventType getEventType() {
        return EventType.PAYMENT;
    }

    public PaymentDomainEvent(Long payId) {
        this.payId = payId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }
}
