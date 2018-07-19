package generic;

/**
 * http://docs.oracle.com/javase/tutorial/java/generics/types.html
 * @author lijin9
 *
 * @param <T>
 */
public class Box<T> {

	private T t;
	public void set(T t) {
		this.t = t;
	}


	public static void main(String[] args) {
		Box box1 = new Box();
		box1.set(1);
		Box<Integer> box2 = new Box<Integer>();
		box2.set(1);

		Integer i = box2.t;
		Integer j = (Integer)box1.t;
	}
}