package com.first.eventdrive.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();

    }
}
class Utils2 {
    public static Integer count = 0;
    public static final Integer FULL = 10;
    public static Lock lock = new ReentrantLock();
    public static final Condition notFull = lock.newCondition();
    public static final Condition notEmpty = lock.newCondition();
}
class Producer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Utils2.lock.lock();
            try {
                while (Utils2.count == Utils2.FULL) {
                    try {
                        Utils2.notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Utils2.count++;
                System.out.println(Thread.currentThread().getName()
                        + "生产者生产，目前总共有" + Utils2.count);
                //唤醒消费者
                Utils2.notEmpty.signal();
            } finally {
                //释放锁
                Utils2.lock.unlock();
            }
        }
    }
}


class Consumer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Utils2.lock.lock();
            try {
                while (Utils2.count == 0) {
                    try {
                        Utils2.notEmpty.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Utils2.count--;
                System.out.println(Thread.currentThread().getName()
                        + "消费者消费，目前总共有" + Utils2.count);
                Utils2.notFull.signal();
            } finally {
                Utils2.lock.unlock();
            }
        }
    }
}