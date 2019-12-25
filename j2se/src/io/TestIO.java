package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

public class TestIO {
	private static File f;
	
	public TestIO(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		TestIO.f = new File(dir, "05_new.log");
	}

	public static void main(String[] args) {
		scanner();
		//inputStreamTest();
		//write();
		//read();
		//TestIO.fileWriter();
		//TestIO.fileReader();
		//TestIO.path();
		//TestIO.inputStream();
		//TestIO.outputStream();
		//TestIO.splitV2();
		//TestIO.mergeV2();
		
	}
	
	/**
	 * 按行读取
	 */
	private static void scanner(){
		Scanner s = new Scanner(System.in);
        
        while(true){
            String line = s.nextLine();
            System.out.println(line);
        }
	}
	
	/**
	 * 使用system.in读取键盘输入数据
	 */
	private static void inputStreamTest(){
		byte[] str = new byte[100000];
		int j = 0;
		
		// 控制台输入
        try (InputStream is = System.in;) {
            while (true) {
                // 敲入a,然后敲回车可以看到
                // 97 13 10
                // 97是a的ASCII码
                // 13 10分别对应回车换行
                int i = is.read();
                str[j++] = (byte)i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	private static void read() {
		File dir = new File("E:/workspace/java/j2se/src/temps");
        File f = new File(dir, "datastream.txt");
        try (
                FileInputStream fis  = new FileInputStream(f);
                DataInputStream dis =new DataInputStream(fis);
        ){
            boolean b= dis.readBoolean();
            int i = dis.readInt();
            String str = dis.readUTF();
             
            System.out.println("读取到布尔值:"+b);
            System.out.println("读取到整数:"+i);
            System.out.println("读取到字符串:"+str);
 
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
 
    private static void write() {
    	File dir = new File("E:/workspace/java/j2se/src/temps");
        File f = new File(dir, "datastream.txt");
        try (
                FileOutputStream fos  = new FileOutputStream(f);
                DataOutputStream dos =new DataOutputStream(fos);
        ){
            dos.writeBoolean(true);
            dos.writeInt(300);
            dos.writeUTF("123 this is gareen");
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }
    
	
	/**
	 * 文件写入器
	 */
	public static void fileWriter(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		File f = new File(dir, "06.log");
		
		String txt = "hello world!";
		char[] c = txt.toCharArray();
		try(FileWriter fw = new FileWriter(f)){
			fw.write(c);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 文件阅读器
	 */
	public static void fileReader(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		File f = new File(dir, "05.log");
		
		char[] c = new char[1];
		int n;
		try(FileReader fr = new FileReader(f)){
			while((n = fr.read(c)) != -1){
				System.out.println(c);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 合并文件v2
	 */
	public static void mergeV2(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		File destF = new File(dir, "05_new.log");
		int step = 10000;
		
		File tempF;
		FileInputStream tempFis;
		byte[] tempC = new byte[step];
		
		try{
			FileOutputStream fos = new FileOutputStream(destF);
			for (int i = 1; i <= 15; i++) {
				//读取分文件的长度
				tempF = new File(dir, "log-" + i + ".txt");
			
				tempFis = new FileInputStream(tempF);
				tempFis.read(tempC);
				fos.write(tempC);
				tempFis.close();
			}
			fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 合并文件
	 */
	public static void mergeV1(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		File destF = new File(dir, "05_new.log");
		int step = 10000;
		byte[] all = new byte[step * 15];
		
		int len = 0;
		File tempF;
		FileInputStream tempFis;
		byte[] tempC = new byte[step];
		
		for (int i = 1; i <= 15; i++) {
			//读取分文件的长度
			tempF = new File(dir, "log-" + i + ".txt");
			try{
				tempFis = new FileInputStream(tempF);
				//fos.write(b, off, len);
				tempFis.read(tempC);
				for (byte b : tempC) {
					all[len++] = b;
				}
				tempFis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		try{
			FileOutputStream fos = new FileOutputStream(destF);
			fos.write(all);
			fos.flush();
			fos.close();
			System.out.println("合并完成");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件拆分v1
	 */
	public static void splitV1(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		File f = new File(dir, "05.log");
		if(! f.exists()){
			System.out.println("不存在！");
		}
		try{
			int step = 10000;
			FileInputStream fis = new FileInputStream(f);
			int len = (int) f.length();
			byte[] all = new byte[len];
			byte[] temp = new byte[step];
			
			fis.read(all);
			for (int i = 0; i < len; i++) { //10k一个文件
				temp[i % step] = all[i];
				
				if(i % step == 0 && i > 0){
					if(i / step == 1){
						System.out.println(Arrays.toString(temp));
					}
					TestIO.writeContent(dir + "/log-" + (i / step) + ".txt", temp);
					temp = new byte[step]; //清空数组
				}
			}
			if(temp.length > 0){ //最后一组
				TestIO.writeContent(dir + "/log-"+(len/step + 1)+".txt", temp);
				temp = new byte[step]; //清空数组
			}
			//每次流操作完记得close
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 文件拆分v2
	 */
	public static void splitV2(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		File f = new File(dir, "05.log");
		if(! f.exists()){
			System.out.println(f.getAbsolutePath() + "不存在！");
		}
		try{
			int step = 10000;
			FileInputStream fis = new FileInputStream(f);
			byte[] temp = new byte[step];
			int n;
			int i = 1;
			while((n = fis.read(temp)) != -1){
				TestIO.writeContent(dir + "/log-" + (i++) + ".txt", temp);
				temp = new byte[step]; //清空数组
			}
			
			//每次流操作完记得close
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 往文件写入内容
	 * @param filename
	 * @param content
	 */
	private static void writeContent(String filename, byte[] content){
		File f = new File(filename);
		try{
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(content);
			fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 输出文件流
	 */
	public static void outputStream(){
		File f = new File("E:/workspace/java/j2se/src/temps/f2.txt");
		if(! f.exists()){
			System.out.println("不存在！");
		}
		try{
			FileOutputStream fos = new FileOutputStream(f);
			byte[] data = {-28, -67, -96};
			fos.write(data);
			fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 输入文件流
	 */
	public static void inputStream(){
		File f = new File("E:/workspace/java/j2se/src/temps/f2.txt");
		if(! f.exists()){
			System.out.println("不存在！");
		}
		try{
			FileInputStream fis = new FileInputStream(f);
			//创建字节数组，其长度就是文件长度
			byte[] all = new byte[(int) f.length()];
			//以字节流的形式读取文件流
			fis.read(all);
			for (byte b : all) {
                //打印出来是65 66
                System.out.println(b);
            }
			//每次流操作完记得close
			fis.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文件句柄
	 */
	public static void path(){
		File dir = new File("E:/workspace/java/j2se/src/temps");
		if(! dir.exists()){
			System.out.println("不存在！");
		}
		
		String [] fileList = dir.list();
		for (int i = 0; i < fileList.length; i++) {
			System.out.println(fileList[i]);
		}
	
		//新建文件
		File f2 = new File(dir, "f2.txt");
		try{
			f2.createNewFile();
		}catch(IOException e){
			System.out.println("f2文件创建失败");
			e.printStackTrace();
		}
		File f2New = new File(dir, "f2_rename.txt");
		f2.renameTo(f2New);
	}
}
