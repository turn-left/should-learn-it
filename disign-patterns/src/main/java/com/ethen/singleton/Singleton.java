package com.ethen.singleton;

/**
 * 最基础的单例模式
 * 饿汉式
 */
public class Singleton {
    // 私有实例
    private static final Singleton INSTANCE = new Singleton();

    // 私有化构造
    private Singleton() {

    }

    // 公开获取单一实例
    public static Singleton getInstance() {
        return INSTANCE;
    }

}
