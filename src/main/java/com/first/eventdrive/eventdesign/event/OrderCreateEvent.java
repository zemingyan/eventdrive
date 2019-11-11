package com.first.eventdrive.eventdesign.event;

import com.first.eventdrive.eventdesign.OrderEventType;
import com.first.eventdrive.eventdesign.event.OrderEvent;

public class OrderCreateEvent implements OrderEvent {

    private Long orderId;

    public OrderCreateEvent(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public Long getOrderId() {
        return this.orderId;
    }

    @Override
    public OrderEventType getEventType() {
        return OrderEventType.CREATE;
    }
}
