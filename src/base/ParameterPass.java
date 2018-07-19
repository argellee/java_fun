package base;

/**
 *
 * 值传递（pass by value）是指在调用函数时将实际参数[复制]一份传递到函数中，这样在函数中如果对[参数]进行修改，将不会影响到实际参数。
 * 引用传递（pass by reference）是指在调用函数时将实际参数的地址[直接]传递到函数中，那么在函数中对[参数]所进行的修改，将影响到实际参数。
 *
 * java参数传递采用的是值传递
 * 参考：http://www.hollischuang.com/archives/2275
 * Created by lijin9 on 2018/5/13.
 */
public class ParameterPass {


    public static void main(String[] args) {
//        int i = 10;
//        new ParameterPass().pass(i);
//        System.out.println("print in main , i is " + i);

//        User user = new User();
//        user.setName("zhangsan");
//        user.setAge(10);
//        new ParameterPass().pass(user);
//        System.out.println("print in main , user is " + user.toString());

        String name = "zhangsan";
        new ParameterPass().pass(name);
        System.out.println("print in main , name is " + name);



    }


    public void pass(int j) {
        j = 20;
        System.out.println("print in pass , j is " + j);
    }

    public void pass(User user1) {
        user1.setName("lisi");
        System.out.println("print in pass , user is " + user1.toString());

//        user1 = new User();
//        user1.setName("lisi");
//        System.out.println("print in pass , user is " + user1.toString());

    }

    public void pass(String name) {
        name = "lisi";
        System.out.println("print in pass , name is " + name);
    }

    static class User{
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
