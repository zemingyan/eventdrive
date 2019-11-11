package com.first.eventdrive.domainevent.listen;

import com.first.eventdrive.domainevent.DomainEventCenter;
import com.first.eventdrive.domainevent.event.DomainEvent;
import com.first.eventdrive.domainevent.event.OrderDomainEvent;
import com.first.eventdrive.domainevent.utils.SelfThreadPool;
import com.first.eventdrive.kafka.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OrderDomainEventListener implements DomainEventListener {
    Logger logger = LoggerFactory.getLogger(OrderDomainEventListener.class);
    @Autowired
    private SelfThreadPool selfThreadPool;


    @Autowired
    private DomainEventCenter domainEventCenter;

    @PostConstruct
    public void register(){
        logger.info("注册监听器");
        domainEventCenter.register(OrderDomainEvent.class, this);
    }

    @Override
    public void trigger(DomainEvent domainEvent) {
        logger.info("订单类型是 ={},  订单id是 = {}  执行处理订单的相关操作",domainEvent.getEventType(), domainEvent.getEventId());
        Product product = new Product("create_order_success", domainEvent.getEventId() + "订单处理完毕，输出信息到kafka");
        //new Thread(product).start();
        selfThreadPool.getExecutor().execute(product);
    }
}
