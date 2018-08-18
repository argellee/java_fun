package designpattern.proxy.staticproxy;

public class DoorService{

    public void open() {
        System.out.println("open the door !");
    }


    public static void main(String[] args) {

        new DoorService().open();
    }
}
