package com.first.eventdrive.domainevent.event;


import com.first.eventdrive.domainevent.EventType;

public class DeliveryDomainEvent implements  DomainEvent{
    private Long deliveryId;

    @Override
    public Long getEventId() {
        return deliveryId;
    }

    @Override
    public EventType getEventType() {
        return EventType.DELIVERY;
    }

    public DeliveryDomainEvent(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }
}
