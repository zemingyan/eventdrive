package com.first.eventdrive.eventdesign.listen;

import com.first.eventdrive.eventdesign.SelfEventCenter;
import com.first.eventdrive.eventdesign.event.OrderCancelEvent;
import com.first.eventdrive.eventdesign.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class OrderCancelListener implements EventListener {
    private static Logger log = LoggerFactory.getLogger(OrderCancelListener.class);

    @Autowired
    private SelfEventCenter selfEventCenter;

    @PostConstruct
    private void registry() {
        selfEventCenter.registry(this, OrderCancelEvent.class);
    }

    @Override
    public void trigger(OrderEvent event) {
        log.info("取消订单，订单id={}", event.getOrderId());
    }
}
