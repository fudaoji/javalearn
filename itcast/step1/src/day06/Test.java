package day06;

public class Test {
    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(11);
        System.out.println(student1);

        Student student2 = new Student("Lisi", 14);
        System.out.println(student2);
    }
}
