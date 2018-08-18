package designpattern.proxy.dynamicproxy;

import designpattern.proxy.staticproxy.Door;
import designpattern.proxy.staticproxy.DoorImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理
 * 实现 InvocationHandler
 *
 *
 *
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();

        Object result = method.invoke(target, args);

        after();

        return result;
    }


    @SuppressWarnings("unchecked")
    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);

    }




    public static void main(String[] args) {
        DynamicProxy dynamicProxy = new DynamicProxy(new DoorImpl());
        ///此处代码应该封装一下，见getProxy()
//        Door doorProxy = (Door) Proxy.newProxyInstance(door.getClass().getClassLoader(),
//                door.getClass().getInterfaces(),
//                dynamicProxy);
        Door doorProxy = dynamicProxy.getProxy();
        doorProxy.open();

    }



    public void before() {
        System.out.println("before open the door");
    }


    public void after() {
        System.out.println("after open the door");
    }


}
