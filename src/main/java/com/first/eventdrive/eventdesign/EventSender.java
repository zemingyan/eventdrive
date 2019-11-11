package com.first.eventdrive.eventdesign;

import com.first.eventdrive.eventdesign.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventSender {

    @Autowired
    private SelfEventCenter selfEventCenter;

    public void post(OrderEvent event) {
        selfEventCenter.post(event);
    }
}
