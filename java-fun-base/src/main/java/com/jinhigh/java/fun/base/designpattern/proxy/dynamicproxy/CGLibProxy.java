package com.jinhigh.java.fun.base.designpattern.proxy.dynamicproxy;

import designpattern.proxy.staticproxy.Door;
import designpattern.proxy.staticproxy.DoorImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    /**
     * 饿汉模式单例
     */
    private static final CGLibProxy CGLIB_PROXY = new CGLibProxy();

    private CGLibProxy(){}

    public static CGLibProxy getInstance(){
        return CGLIB_PROXY;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects,
                            MethodProxy methodProxy) throws Throwable {

        before();
        Object result = methodProxy.invokeSuper(o,objects);
        after();
        return result;
    }


    public static void main(String[] args) {

        ///每次都需要new 一下这个对象，建议采用单例
        //CGLibProxy cgLibProxy = new CGLibProxy();

        ///此处代码封装到getProxy()里
        ///Door doorProxy = (Door) Enhancer.create(DoorImpl.class,cgLibProxy);
        Door doorProxy = CGLibProxy.getInstance().getProxy(DoorImpl.class);

        doorProxy.open();

    }

    public <T> T getProxy(Class<T> clazz){

        return (T) Enhancer.create(clazz,this);
    }



    public void before() {
        System.out.println("before open the door");
    }

    public void after() {
        System.out.println("after open the door");
    }
}
