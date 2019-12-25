package j2se;

/**
 * 寻找某两个数相除，其结果 离黄金分割点 0.618最近
 * 要求：1、分母和分子不能同时为偶数； 2、分母和分子 取值范围在[1-20]
 * @author fudaoji<461960962@qq.com>
 */
public class GoldenSection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int min_num = 1;
		int max_num = 20;
		
		float cha = 20f;
		float shang = 0.0f;
		final float RATE = 0.618f;
		float temp_cha;
		
		int a = 1;  //分子
		int b = 1;  //分母
		
		for(int i = min_num; i <= max_num; i++){
			for(int j = min_num; j <= max_num; j++){
				if(i > j || (i % 2 == 0 && j % 2 == 0)){
					continue; //条件过滤
				}
				shang = (float)i / j;
				temp_cha = (RATE - shang) > 0 ? (RATE - shang) : (shang - RATE);
				
				if(temp_cha < cha){//不断替换满足条件的值
					cha = temp_cha; 
					a = i;
					b = j;
				}
			}
		}
		
		System.out.println("分子： " + a);
		System.out.println("分母： " + b);
	}

}
