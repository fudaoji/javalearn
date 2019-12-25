package character;

public class TestString {

	public static void main(String[] args) {
		 
		/*String str1 = "the";
		 
        StringBuffer sb = new StringBuffer(str1);
         
        System.out.println(sb.length()); //内容长度
         
        System.out.println(sb.capacity());//总空间
*/         
		/**
		 * 生成10位长度的随机字符串
然后,先使用String的+,连接10000个随机字符串,计算消耗的时间
然后,再使用StringBuffer连接10000个随机字符串,计算消耗的时间

提示: 使用System.currentTimeMillis() 获取当前时间(毫秒)
		 */
		int limit = 10;
		String randomStr = MyString.getRandomString(limit);
		
		System.out.println(randomStr);
		
		int task = 10000;
		System.out.println("使用string+连接10000个随机字符串:");
		long time1_start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			randomStr += MyString.getRandomString(limit);
		}
		long time1_stop = System.currentTimeMillis();
		System.out.println("使用string+连接10000个随机字符串的时间花费： " + (time1_stop - time1_start));
		
		StringBuffer str1 = new StringBuffer(randomStr);
		System.out.println("使用StringBuffer连接10000个随机字符串:");
		long time2_start = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++){
			str1.append(MyString.getRandomString(limit));
		}
		long time2_stop = System.currentTimeMillis();
		System.out.println("使用string+连接10000个随机字符串的时间花费： " + (time2_stop - time2_start));
    }

}
