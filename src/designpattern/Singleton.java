package designpattern;

/**
 * 饿汉式单例
 *
 * Created by lijin9 on 2018/6/29.
 */
public class Singleton {

    private static Singleton singleton = null;

    private Singleton(){}

    private int i = 0;

    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public void sout(){
        System.out.println(i++);

    }


}
