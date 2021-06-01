package com.ethen.concurrency.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 使用CAS提升性能 减小锁的粒度
 * CAS=>Compare And Swap
 */
public class CASDemo {
    // 内存可见
    private static volatile int count = 0;
    private static final int USER_COUNT = 100;

    // 模拟客户点击事件
    public static void doRequest() throws InterruptedException {
        // 模拟耗时操作
        TimeUnit.NANOSECONDS.sleep(2000L);

        /*
         * count++ 非原子操作分三步
         * 1.主存读取count
         * 2.执行运算
         * 3.刷新回主存 (在此步骤加锁)
         * 当前使用CAS原理，减小锁粒度,仅在第三步加锁
         */
//        count++;
        int expectCount;
        while (!compareAndSwap(expectCount = getCount(), count + 1)) ;
    }

    public static int getCount() {
        return count;
    }

    /**
     * 比较替换
     *
     * @param expectCount 期望值
     * @param newCount    新值
     */
    public synchronized static boolean compareAndSwap(int expectCount, int newCount) {
        if (getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
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
