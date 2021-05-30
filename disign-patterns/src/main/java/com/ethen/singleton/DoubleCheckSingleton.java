package com.ethen.singleton;

/**
 * 双重校验锁 单例模式
 */
public class DoubleCheckSingleton {
    private DoubleCheckSingleton() {
    }

    private static volatile DoubleCheckSingleton instance = null;

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
