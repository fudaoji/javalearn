package j2se;

/**
 * 水仙花数定义： 1. 一定是3位数;  2. 每一位的立方，加起来恰好是这个数本身，比如153=1*1*1+5*5*5+3*3*3
 * 求： 寻找所有的水仙花数
 * @author fudaoji<461960962@qq.com>
 *
 */
public class Daffodil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 100; i < 999; i++){
			int single = i % 10;
			int ten = (i / 10) % 10;
			int hundred = i / 100;
			
			if(i == single * single * single + ten * ten * ten + hundred * hundred * hundred){
				System.out.println(i);	
			}
		}
	}

}
