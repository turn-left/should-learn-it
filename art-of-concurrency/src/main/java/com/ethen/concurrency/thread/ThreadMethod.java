package com.ethen.concurrency.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程的四种方式
 */
public class ThreadMethod {

    /**
     * 继承thread类
     */
    @Test
    public void extendsThread() {
        Thread task = new ThreadTask();
        task.start();
    }

    /**
     * 实现Runnable接口
     */
    @Test
    public void implRunnable() {
        Runnable task = new RunnableTask();
        new Thread(task).start();
    }

    /**
     * 实现Callable接口
     */
    @Test
    public void implCallable() {
        // fixme ...
    }

    /**
     * 通过线程池的方式
     */
    @Test
    public void executors() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            // do some business ...
        });

    }


    static class ThreadTask extends Thread {
        @Override
        public void run() {
            try {
                System.err.println("ThreadTask do sth. business ...");
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class RunnableTask implements Runnable {
        @Override
        public void run() {
            try {
                System.err.println("RunnableTask do sth. business ...");
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class CallableTask implements Callable {
        @Override
        public Object call() throws Exception {
            try {
                System.err.println("RunnableTask do sth. business ...");
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "COMPLETED";
        }
    }

}
