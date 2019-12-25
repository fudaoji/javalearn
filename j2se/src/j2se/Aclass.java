package j2se;

public class Aclass {

	// 方法名和类名一样（包括大小写）
    // 没有返回类型
    public Aclass() {
        System.out.println("实例化一个对象的时候，必然调用构造方法");
    }
     
    public static void main(String[] args) {
        //实例化一个对象的时候，必然调用构造方法
        Aclass c = new Aclass();
    }

}
