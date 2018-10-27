package base.serializable;

import java.io.*;

/**
 * java序列化、反序列化
 *
 * 对象需实现Serializable接口
 */
public class SerializableTest
{
    public static void main(String[] args) throws IOException, IOException,
            ClassNotFoundException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "/Users/argel/data"));

        Person.Education education = new Person.Education("qh",3);
        Person p = new Person(25, "China", 180,education);
        oos.writeObject(p);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                "/Users/argel/data"));
        Person p1 = (Person) ois.readObject();
        System.out.println("age=" + p1.age + ";address=" + p1.address
                + ";height=" + p1.height);
        ois.close();
    }
}