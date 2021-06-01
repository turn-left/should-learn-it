package com.ethen.concurrency.cas;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 模拟网站点击数的统计-非线程安全
 * 100 个用户 每个用户访问10次网站
 */
public class SyncDemo {
    private static int count = 0;
    private static final int USER_COUNT = 100;

    // 模拟客户点击事件
    public static synchronized void doRequest() throws InterruptedException {
        // 模拟耗时操作
        TimeUnit.NANOSECONDS.sleep(2000L);

        /*
         * count++ 非原子操作分三步
         * 1.主存读取count
         * 2.执行运算
         * 3.刷新回主存
         */
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(USER_COUNT);
        for (int i = 0; i < USER_COUNT; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        doRequest();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        System.err.println("cost:" + (System.currentTimeMillis() - start) + ",count=" + count);
    }
}
