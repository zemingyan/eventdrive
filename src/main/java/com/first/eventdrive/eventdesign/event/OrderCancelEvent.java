package com.first.eventdrive.eventdesign.event;

import com.first.eventdrive.eventdesign.OrderEventType;
import com.first.eventdrive.eventdesign.event.OrderEvent;

public class OrderCancelEvent implements OrderEvent {

    private Long orderId;

    public OrderCancelEvent(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public Long getOrderId() {
        return this.orderId;
    }

    @Override
    public OrderEventType getEventType() {
        return OrderEventType.CANCEL;
    }
}

