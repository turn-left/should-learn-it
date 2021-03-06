package com.ethen.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib动态代理
 * <p>
 * CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。
 * JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。
 * <p>
 * 代理对象的生成过程由Enhancer类实现，大概步骤如下：
 * <p>
 * 1.生成代理类Class的二进制字节码；
 * <p>
 * 2.通过Class.forName加载二进制字节码，生成Class对象；
 * <p>
 * 3.通过反射机制获取实例构造，并初始化代理类对象。
 */
public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.err.println("====================== CglibMethodInterceptor start =====================");
        Object result = proxy.invokeSuper(obj, args);
        System.err.println("======================= CglibMethodInterceptor end ======================");
        return result;
    }
}
