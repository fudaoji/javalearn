package day01;

/**
 * 教师类
 * @author fudaoji
 * @version 1.0
 * @since 1.5
 */
public class Teacher {
    private String name = "name";
    private int age = 22;

    public void teach(){
        System.out.println("Teacher: I am " + this.name + "," + this.age + " years old now!");
    }

    /**
     * 获取姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher() {
    }

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
