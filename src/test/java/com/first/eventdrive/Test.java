/*
package com.first.eventdrive;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
class Utils{
    public static  Integer count = 0;

    public static final Integer FULL = 10;
    public static String LOCK = "lock";
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
            synchronized (Utils.LOCK) {
                while (Utils.count == Utils.FULL) {
                    try {
                        Utils.LOCK.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Utils.count++;
                System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + Utils.count);
                Utils.LOCK.notifyAll();
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Utils.LOCK) {
                while (Utils.count == 0) {
                    try {
                        Utils.LOCK.wait();
                    } catch (Exception e) {
                    }
                }
                Utils.count--;
                System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + Utils.count);
                Utils.LOCK.notifyAll();
            }
        }
    }
}*/
