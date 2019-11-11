package com.first.eventdrive.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution {
    static ExecutorService es = Executors.newFixedThreadPool(2);
    static int[] array = {1, 3, 4, 5, 2};

    public static class shellSort implements Runnable {
        static int d;
        static int start;
        static CountDownLatch latch;

        public shellSort(int d, int start, CountDownLatch latch) {
            this.d = d;
            this.start = start;
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int i = start; i < array.length; i++) {
                int number = array[i];
                int j = i - d;
                while (j >= 0 && array[j] > number) {
                    array[j + d] = array[j];
                    j -= d;
                }
                array[j + d] = number;
                latch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        int d = array.length;
        while (d > 0) {
            d /= 2;
            CountDownLatch latch = new CountDownLatch(d);
            for (int i = 0; i < d; i++) {
                es.submit(new shellSort(d, i, latch));
            }
            latch.await();
        }
    }
}