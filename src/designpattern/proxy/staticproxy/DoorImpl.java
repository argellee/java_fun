package designpattern.proxy.staticproxy;

public class DoorImpl implements Door {

    @Override
    public void open() {
        System.out.println("open the door !");
    }


    public static void main(String[] args) {
        Door door = new DoorImpl();
        door.open();
    }
}
