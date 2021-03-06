package com.ethen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * java动态代理
 * 动态代理主要是通过反射机制，在运行时动态生成所需代理的class.
 */
public class DynamicProxy implements InvocationHandler {
    private Target target;

    public DynamicProxy(Target target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.err.println("DynamicProxy pre execute ...");
        Object result = method.invoke(target, args);
        System.err.println("DynamicProxy post execute !!!");
        return result;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
