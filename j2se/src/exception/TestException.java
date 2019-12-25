package exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestException {

	
	public static void main(String[] args) {
	    //TestException.errorTest();
		TestException.runtimeTest();
    }
	
	/**
	 * 内存耗尽错误
	 * @param args
	 */
	public static void errorTest(){
		StringBuffer sb = new StringBuffer();  //分配一块空间用于存储字符串
    	
    	try{
    		for (int i = 0; i < Integer.MAX_VALUE; i++) {   //不断加入
    			sb.append('a');
    		}
    	}catch(OutOfMemoryError e){
    		System.out.println("出现异常");
    		e.printStackTrace();
    	}	
	}

	
	/**
	 * 运行时异常演示  RuntimeException
	 * @param args
	 */
	public static void runtimeTest() {
        
        //任何除数不能为0:ArithmeticException
		try{
			int k = 5/0;
		}catch(RuntimeException e){
			System.out.println("异常出现了");
			e.printStackTrace();
		}
        
         
        //下标越界异常：ArrayIndexOutOfBoundsException
        int j[] = new int[5];
        j[10] = 10;
         
        //空指针异常：NullPointerException
        String str = null;
        str.length();
   }
	
	/**
	 * 多异常捕捉2
	 * @param args
	 */
	public static void main03(String[] args){
		File f = new File("d:/LOL.exe");
		 
		try {
            System.out.println("试图打开 d:/LOL.exe");
            //new FileInputStream(f);
            System.out.println("成功打开");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse("2016-06-03");

        } catch (ParseException e) {
            /*if (e instanceof FileNotFoundException)
                System.out.println("d:/LOL.exe不存在");*/
            if (e instanceof ParseException)
                System.out.println("日期格式解析错误");
 
            e.printStackTrace();
        }
		
	}
	
	/**
	 * 多异常捕捉1
	 * @param args
	 */
	public static void main02(String[] args){
		File f = new File("d:/LOL.exe");
		 
        try {
            System.out.println("试图打开 d:/LOL.exe");
            new FileInputStream(f);
            System.out.println("成功打开");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = sdf.parse("2016-06-03");
        } catch (FileNotFoundException e) {
            System.out.println("d:/LOL.exe不存在");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("日期格式解析错误");
            e.printStackTrace();
        }
		
	}
	
	/**
	 * 文件打开异常
	 * @param args
	 */
	public static void main01(String[] args) {
		// TODO Auto-generated method stub
		File f= new File("d:/lol.exe");
		try{
			new FileInputStream(f);
		}catch(FileNotFoundException e){  //也可以
			System.out.println("文件不存在");
			e.printStackTrace();
		}
		
	}

}
