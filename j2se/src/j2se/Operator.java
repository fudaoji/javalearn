package j2se;
import java.util.Scanner;

public class Operator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		/*System.out.println("请输入整数a: ");
		int a = scanner.nextInt();
		System.out.println("请输入整数b: ");
		int b = scanner.nextInt();
		System.out.println("a + b = " + (a + b));*/
		//字符串
		/*System.out.println("您想对我说什么？: ");
		String msg = scanner.nextLine();
		System.out.println(msg);
		if(msg == "age"){
			System.out.println("我今年29岁了");
		}else if(msg == "name"){
			System.out.println("我叫苟哥");
		}*/
		
		/*byte a = 1;
        byte b= 2;
        byte c = (byte) (a+b); //虽然a b都是byte类型，但是运算结果是int类型，需要进行强制转换
        int d = a+b;
        
        System.out.println(c);
        System.out.println(d);
        
        int i = 1;
        int j = ++i + i++ + ++i + ++i + i++;
        System.out.println(j);*/
		
		/**
		 * 逻辑运算符
		 */
		//长路与  无论第一个表达式的值是true或者false,第二个的值，都会被运算
        /*int i = 2;
        System.out.println( i== 1 & i++ ==2  ); //无论如何i++都会被执行，所以i的值变成了3
        System.out.println(i);*/
         
        //短路与 只要第一个表达式的值是false的，第二个表达式的值，就不需要进行运算了
        /*int j = 2;
        System.out.println( j== 1 && j++ ==2  );  //因为j==1返回false,所以右边的j++就没有执行了，所以j的值，还是2
        System.out.println(j);     */
		
		int i = 1;
		boolean b = !(i++ == 3) ^ (i++ ==2) && (i++==3);
		System.out.println(b);
		System.out.println(i);
	}

}
