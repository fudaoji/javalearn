package multiplethread;

import java.util.Random;
import java.util.Stack;

public class ProducerThread implements Runnable {
	 
    // 生产者
    private MyStack stack;
    private String name;
    private int count = 1;
 
    public ProducerThread(MyStack stack, String name) {
        this.stack = stack;
        this.name = name;
    }
 
    @Override
    public void run() {
    	while(count++ <= 20){
    		String str = randomStr();
            stack.push(str);
            //System.out.println(this.name + " 压入: " + str);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    	}
    }
 
    public String randomStr() {
        int minNum = 'A';
        int maxNum = 'Z' + 1;
        char mark = (char) (Math.random() * (maxNum - minNum) + minNum);
 
        return Character.toString(mark);
    }
}