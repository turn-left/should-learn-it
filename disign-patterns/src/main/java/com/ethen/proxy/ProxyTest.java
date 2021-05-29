package com.ethen.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class ProxyTest {
    @Test
    public void testStaticProxy() {
        TargetImpl target = new TargetImpl();
        StaticProxy staticProxy = new StaticProxy(target);
        System.err.println(staticProxy.execute());
    }

    @Test
    public void testDynamicProxy() {
        TargetImpl target = new TargetImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(target);
        Object obj = Proxy.newProxyInstance(TargetImpl.class.getClassLoader(), TargetImpl.class.getInterfaces(), dynamicProxy);
        Target proxyInstance = (Target) obj;
        System.err.println(proxyInstance.execute());
    }

    //    @Before
    public static Object createProxy(Class targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new CglibMethodInterceptor());
        return enhancer.create();
    }

    @Test
    public void cglibProxyTest() {
        TargetImpl target = new TargetImpl();
        TargetImpl proxyTarget = (TargetImpl) createProxy(TargetImpl.class);
        System.err.println(proxyTarget.execute());
    }
}
