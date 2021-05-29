package com.ethen.proxy;

import org.junit.Test;

public class ProxyTest {
    @Test
    public void testStaticProxy() {
        TargetImpl target = new TargetImpl();
        StaticProxy staticProxy = new StaticProxy(target);
        System.err.println(staticProxy.execute());
    }
}
