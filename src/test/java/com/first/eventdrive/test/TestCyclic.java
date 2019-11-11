package com.first.eventdrive.test;

import java.util.Random;
import java.util.concurrent.*;

public class TestCyclic {
    public static void main(String[] args){
        cyclicBarrierTest();
    }



    private static void future(){

        Callable<Integer> callable = new Callable<Integer>(){
            public Integer call() throws Exception {
                System.out.println("任务开始");
                Thread.sleep(1000);
                int result = 0;
                for(int i=0;i<=100;i++){
                    result += i;
                }
                System.out.println("任务结束并且返回结果");
                return result;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();


        try {
            System.out.println("主线程调用 futureTask.get()之前");
            System.out.println("Result:" + futureTask.get());
            System.out.println("主线程调用 futureTask.get()之后");
            //也可以用线程池来获得Future
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static void cyclicBarrierTest(){
        int runner = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        final Random random = new Random();

        for(char runnerName = 'A'; runnerName <= 'C'; runnerName++){
            final String rName = String.valueOf(runnerName);

            new Thread(new Runnable(){

                public void run() {
                    long prepareTime = random.nextInt(10000) + 100;
                    System.out.println(rName + ":预处理的时间：" + prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(rName + "预处理完毕，等待其他");
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    //所有线程预处理完毕
                    System.out.println(rName + "开始执行后续操作");
                }
            }).start();
        }
    }
}
