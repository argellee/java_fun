package designpattern.proxy.staticproxy;

/**
 * 代理对象内部含有目标对象的引用，从而可以在任何时候操作目标对象；
 * 代理对象提供一个与目标对象相同的接口，以便可以在任何时候替代目标对象。
 * 代理对象通常在客户端调用传递给目标对象之前或之后，执行某个操作，而不是单纯地将调用传递给目标对象。
 *
 * 代理模式又分为静态代理和动态代理。
 * 静态代理是由程序猿创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。
 * 动态代理是在程序运行时，通过运用[反射]机制动态的创建而成。
 *
 *
 */
public class DoorProxy implements Door {

    private Door door;


    public DoorProxy(){
        door = new DoorImpl();
    }



    @Override
    public void open() {

        before();

        door.open();

        after();

    }

    public void before(){
        System.out.println("before open the door");
    }


    public void after(){
        System.out.println("after open the door");
    }


    public static void main(String[] args) {
        new DoorProxy().open();
    }
}
