package com.first.eventdrive.domainevent;

import com.first.eventdrive.domainevent.event.DomainEvent;
import com.first.eventdrive.domainevent.exception.DomainEventException;
import com.first.eventdrive.domainevent.listen.DomainEventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;


public class DomainEventCenter {
    private final ConcurrentHashMap<Class<?>, List<DomainEventListener>> subscribes = new ConcurrentHashMap<>();
    private Executor executor;

    public DomainEventCenter(Executor executor){
        this.executor = executor;
    }
    public void register(Class<?> clazz, DomainEventListener domainEventListener){
        List<DomainEventListener> listeners = subscribes.get(clazz);
        if (listeners == null){
            listeners = new ArrayList<>();
        }
        listeners.add(domainEventListener);
        subscribes.put(clazz, listeners);
    }

    public void post(DomainEvent domainEvent){
        System.out.println(domainEvent.getClass());
        List<DomainEventListener> listeners = subscribes.get(domainEvent.getClass());
        if (listeners == null || listeners.size() == 0){
            throw  new DomainEventException("不存在该事件的监听器");
            //System.out.println("不存在监听器");
        }
        for (DomainEventListener listener : listeners){
            executor.execute(()->listener.trigger(domainEvent));
            //listener.trigger(domainEvent);
        }
    }
}
