package j2se;

import java.util.Arrays;

public class ArrayCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] a;  //声明一个引用
		a = new int[5]; //创建一个长度是5的数组，并且使用引用a指向该数组
		
		int[] b = new int[4];  //分配了长度是4的数组
		
		/**
		 * task1:  给数组a赋予5个随机值，并找出这5个值中最小的
		 */
		a[0] = (int)(Math.random() * 100);
		a[1] = (int)(Math.random() * 100);
		a[2] = (int)(Math.random() * 100);
		a[3] = (int)(Math.random() * 100);
		a[4] = (int)(Math.random() * 100);
		
		System.out.println("数组a：");
		for(int each: a){
			System.out.println(each);
		}
		
		/**
		 * 1、常规复制
		 */
		/*for(int i = 0; i < b.length; i++){
			b[i] = a[i];
		}*/
		
		/**
		 * 2、系统内置方法： System.arraycopy(src, srcPos, dest, destPos, length)
		 */
		/*System.arraycopy(a, 0, b, 0, b.length);

		System.out.println("数组b：");
		for(int each: b){
			System.out.println(each);
		}*/
		
		/**
		 * 使用java.util.Arrays类中的copyOfRange 进行数组复制;
		 */
		/*int[] c = Arrays.copyOfRange(a, 0, a.length);
		System.out.println("数组c：");
		for(int each: c){
			System.out.println(each);
		}*/
		
		/**
		 * 使用java.util.Arrays类中的toString 将数组字符串化;
		 */
		String a2String = Arrays.toString(a);
		System.out.println("a转为字符串后是：" + a2String);
		
		Arrays.sort(a);
		System.out.println("排序后：" + Arrays.toString(a));
		
	}

}
