package com.ethen.proxy;

/**
 * 被代理的目标其中一个实现类
 */
public class TargetImpl implements Target {
    @Override
    public String execute() {
        System.err.println(this.getClass().getCanonicalName() + " executed!");
        return "executed";
    }
}
