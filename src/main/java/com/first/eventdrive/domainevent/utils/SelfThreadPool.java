package com.first.eventdrive.domainevent.utils;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Component;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class SelfThreadPool {
    private Executor executor = null;
    SelfThreadPool(){
        int coreThread = Runtime.getRuntime().availableProcessors();
        int maxThread = coreThread * 2;
        executor = new ThreadPoolExecutor(coreThread, maxThread, 10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024));
    }
    public Executor getExecutor(){
        return executor;
    }
}
