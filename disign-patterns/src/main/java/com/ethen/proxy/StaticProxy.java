package com.ethen.proxy;

/**
 * 静态代理
 * 静态代理其实就是在程序运行之前，提前写好被代理方法的代理类，编译后运行。在程序运行之前，class已经存在。
 */
public class StaticProxy implements Target {
    private Target target;

    public StaticProxy() {
    }

    public StaticProxy(Target target) {
        this.target = target;
    }

    @Override
    public String execute() {
        System.err.println("pre execute...");
        String result = target.execute();
        System.err.println("post execute !!!");
        return result;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
