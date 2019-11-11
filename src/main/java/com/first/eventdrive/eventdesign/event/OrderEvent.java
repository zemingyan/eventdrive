package com.first.eventdrive.eventdesign.event;

import com.first.eventdrive.eventdesign.OrderEventType;

/**
 * 这种事件驱动的方式适合原生的java里面使用
 * 如果引入kafka或者redis  可以使用他们的事件订阅模型
 */
public interface OrderEvent {

    Long getOrderId();

    OrderEventType getEventType();
}