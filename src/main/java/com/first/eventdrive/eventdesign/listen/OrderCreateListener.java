package com.first.eventdrive.eventdesign.listen;

import com.first.eventdrive.eventdesign.SelfEventCenter;
import com.first.eventdrive.eventdesign.event.OrderCreateEvent;
import com.first.eventdrive.eventdesign.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class OrderCreateListener implements EventListener {

    private static Logger log = LoggerFactory.getLogger(OrderCreateListener.class);
    @Autowired
    private SelfEventCenter selfEventCenter;

    @PostConstruct
    private void registry() {
        selfEventCenter.registry(this, OrderCreateEvent.class);
    }

    @Override
    public void trigger(OrderEvent event) {
        log.info("创建订单，订单id={}", event.getOrderId());
    }
}
