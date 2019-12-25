package Date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 当前时间
        Date d1 = new Date();
        System.out.println("当前时间:");
        System.out.println(d1);
        System.out.println();
        // 从1970年1月1日 早上8点0分0秒 开始经历的毫秒数
        Date d2 = new Date(5000);
        System.out.println("从1970年1月1日 早上8点0分0秒 开始经历了5秒的时间");
        System.out.println(d2);
        
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS" );
        Date d= new Date();
        String str = sdf.format(d);
        System.out.println("当前时间通过 yyyy-MM-dd HH:mm:ss SSS 格式化后的输出: "+str);
         
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
        Date d3= new Date();
        String str1 = sdf1.format(d3);
        System.out.println("当前时间通过 yyyy-MM-dd 格式化后的输出: "+str1);
	}
	
	/**
	 * 获取当天时间
	 * @return
	 */
	public static String getTodayDate(){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        Date d= new Date();
        String dateStr = sdf.format(d);
        return dateStr;
	}

}
