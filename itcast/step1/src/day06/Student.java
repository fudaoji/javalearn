package day06;

/**
 * 一个标准类（Java bean）包含四个特征：
 * 1. 所有的成员变量使用private修饰
 * 2. 为所有成员变量添加setter／getter
 * 3、添加无参构造函数
 * 4、添加全参构造函数
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
