package multiplethread;

import java.util.Stack;

public class ConsumerThread implements Runnable {
	// 消费者
    private MyStack stack;
    private String name;
    private int count = 1;
 
    public ConsumerThread(MyStack stack, String name) {
        this.stack = stack;
        this.name = name;
    }
 
    @Override
    public void run() {
    	while(count++ <= 20){

    		Object o = stack.pop();
            //System.out.println(this.name + " 弹出: " + o);
            
    		try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    		
    	}
    	
    }
    
}
