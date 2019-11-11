package com.first.eventdrive.eventdesign.listen;

import com.first.eventdrive.eventdesign.event.OrderEvent;

public interface EventListener{


    void trigger(OrderEvent event);
}
