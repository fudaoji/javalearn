package j2se;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a;  //声明一个引用
		a = new int[5]; //创建一个长度是5的数组，并且使用引用a指向该数组
		
		int[] b = new int[6];  //声明的同时，指向一个数组
		
		/**
		 * task1:  给数组a赋予5个随机值，并找出这5个值中最小的
		 */
		a[0] = (int)(Math.random() * 100);
		a[1] = (int)(Math.random() * 100);
		a[2] = (int)(Math.random() * 100);
		a[3] = (int)(Math.random() * 100);
		a[4] = (int)(Math.random() * 100);
		
		System.out.println("随机生成的5个数是：");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
		
		/*System.out.println("其中最小值是：");
		int min = a[0];
		for(int i = 1; i < a.length; i++){
			if(a[i] < min){
				min = a[i];
			}
		}
		System.out.println(min);*/
		
		/**
		 * task2:  将数组a进行反转
		 */
		/*int temp;
		reverseTask:
		for(int i = 0, j = a.length - 1; i <= j; i++, j--){
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		
		System.out.println("逆转后的数组是：");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}*/
		
		/**
		 * task3: 选择法排序
		 * 外循环： i=0循环直到i=a.length-1
		 * 	内循环：j=i+1循环直到 j=a.length-1
		 * 	 当a[j] < a[i] 时进行位置调换
		 */
		/*int temp;
		for(int i = 0; i < a.length; i++){
			for(int j = i + 1; j < a.length; j++){
				if (a[j] < a[i]) {
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		System.out.println("选择排序后的数组是：");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}*/
		
		/**
		 * task4: 冒泡法排序
		 * 外循环： i=0循环直到i=a.length-1
		 * 	内循环：j=i+1循环直到 j=a.length-1
		 * 	 当a[j] < a[i] 时进行位置调换
		 */
		int temp;
		for(int i = 0; i < a.length; i++){
			for(int j = a.length - 1; j > i; j--){
				if (a[j] > a[j - 1]) {
					temp = a[j];
					a[j] = a[j - 1];
					a[j - 1] = temp;
				}
			}
		}
		System.out.println("冒泡排序后的数组是：");
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
		
	}

}
