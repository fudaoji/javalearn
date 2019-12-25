package multiplethread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Date.TestDate;

public class TestThread {
	private static int value = 0;
	static AtomicInteger atomicValue = new AtomicInteger();
 
    public static void main(String[] args) {
         
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;
 
        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;
         
        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;
         
        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;
        
        atomicIntegerTest();
        //lockTest();
        //poolTest();
        //System.out.println(Producer.generateChar());
        //producerConsumer();
        //syncThread();
        //mainThread(teemo, gareen, bh, leesin);
    }
    
    public static void atomicIntegerTest(){
    	int number = 100000;
    	Thread[] ts1 = new Thread[number];
    	
    	for (int i = 0; i < number; i++) {
			Thread t = new Thread(){
				public void run(){
					value++;
				}
			};
			t.start();
			ts1[i] = t;
		}
    	
    	//等待任务结束
    	try{
    		for (Thread t: ts1) {
    			t.join();
    		}
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}
    	System.out.printf("%d个线程进行value++后，value的值变成:%d%n", number,value);
    	
    	System.out.printf("atomicValue初始值:%d%n", atomicValue.intValue());
        Thread[] ts2 = new Thread[number];
        for (int i = 0; i < number; i++) {
            Thread t =new Thread(){
                public void run(){
                    atomicValue.incrementAndGet();
                }
            };
            t.start();
            ts2[i] = t;
        }
         
        //等待这些线程全部结束
        for (Thread t : ts2) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.printf("%d个线程进行atomicValue.incrementAndGet();后，atomicValue的值变成:%d%n", number,atomicValue.intValue());
    }
    
    /**
     * 打印
     * @param msg
     */
    public static void log(String msg) {
        System.out.printf("%s %s %s %n", now() , Thread.currentThread().getName() , msg);
    }
    /*=====================使用lock同步线程=====================*/
    public static void lockTest(){
    	Lock lock = new ReentrantLock();
    	Condition condition = lock.newCondition();
    	
    	Thread t1 = new Thread(){
    		public void run(){
    			try{
    	    		log("线程启动");
    				log("尝试占有对象");
    				lock.lock();
    				log("占有对象");
    				log("进行5秒的业务操作");
    				Thread.sleep(5000);
    				log("临时释放对象 lock， 并等待");
    				//暂停线程
    				condition.await();
    				log("重新占有对象，继续执行5秒业务操作");
    				Thread.sleep(5000);
    	    	}catch(InterruptedException e){
    	    		e.printStackTrace();
    	    	}finally{
    	    		log("释放对象：lock");
    	    		lock.unlock(); //解锁
    	    	}
    		}
    	};
    	t1.setName(" t1");
    	t1.start();
    	
    	try {
            //先让t1飞2秒
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	
    	Thread t2 = new Thread(){
    		public void run(){
    			try{
    	    		log("线程启动");
    				log("尝试占有对象");
    				lock.lock();
    				log("占有对象");
    				log("进行5秒的业务操作");
    				Thread.sleep(5000);
    				//唤醒等待的线程
    				condition.signal();
    	    	}catch(InterruptedException e){
    	    		e.printStackTrace();
    	    	}finally{
    	    		log("释放对象：lock");
    	    		lock.unlock(); //解锁
    	    	}
    		}
    	};
    	t2.setName(" t2");
    	t2.start();
    }
    
    /*=================java内置线程池=================*/
    public static void poolTest(){
    	ThreadPoolExecutor threadPool= new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        
        threadPool.execute(new Runnable(){
   
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("任务1");
            }
               
        });
    }
    
    /*=================自定义线程池======================*/
    public static void customPoolTest(){
    	ThreadPool pool = new ThreadPool();
    	
    	int sleep = 1000;
    	while (true) {
            try {
            	//一秒加一个任务
            	Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("时间播报： " + TestDate.getTodayDate());
                        //任务可能是打印一句话
                        //可能是访问文件
                        //可能是做排序
                    }
                };
            	pool.add(task);
            	
            	Thread.sleep(sleep);
                sleep = sleep>100 ? sleep-100 : sleep;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
		}
    }
    
    /*=================生产者消费者问题===============*/
    public static void producerConsumer(){
    	MyStack stack = new MyStack();
    	 
        for (int i = 0; i < 4; i++) {
            ProducerThread pt = new ProducerThread(stack, "Producer" + (i + 1));
            new Thread(pt).start();
        }
        for (int i = 0; i < 3; i++) {
            ConsumerThread ct = new ConsumerThread(stack, "Consumer" + (i + 1));
            new Thread(ct).start();
        }
    	
    }
    
    public static String now(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
    
    /*==================同步进程演示==================*/
    public static void syncThread(){
    	final Object someObject = new Object();
        
        Thread t1 = new Thread(){
            public void run(){
                try {
                    System.out.println( now()+" t1 线程已经运行");
                    System.out.println( now()+this.getName()+ " 试图占有对象：someObject");
                    synchronized (someObject) {
                          
                        System.out.println( now()+this.getName()+ " 占有对象：someObject");
                        Thread.sleep(5000);
                        System.out.println( now()+this.getName()+ " 释放对象：someObject");
                    }
                    System.out.println(now()+" t1 线程结束");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t1.setName(" t1");
        t1.start();
        Thread t2 = new Thread(){
  
            public void run(){
                try {
                    System.out.println( now()+" t2 线程已经运行");
                    System.out.println( now()+this.getName()+ " 试图占有对象：someObject");
                    synchronized (someObject) {
                        System.out.println( now()+this.getName()+ " 占有对象：someObject");
                        Thread.sleep(5000);
                        System.out.println( now()+this.getName()+ " 释放对象：someObject");
                    }
                    System.out.println(now()+" t2 线程结束");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t2.setName(" t2");
        t2.start();
    }
    
    /*========================主线程演示======================*/
    public static void mainThread(Hero teemo, Hero gareen, Hero bh, Hero leesin){
    	Thread t1 = new Thread(){
    		public void run(){
    			while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }   
    		}
    	};
    	
    	t1.start();
    	//代码执行到这里，一直是main线程在运行
        try {
            //t1线程加入到main线程中来，只有t1线程运行结束，才会继续往下走
            t1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Thread t2 = new Thread(){
    		public void run(){
    			while(!leesin.isDead()){
                    bh.attackHero(leesin);
                }   
    		}
    	};
    	//会观察到盖伦把提莫杀掉后，才运行t2线程
    	t2.start();
    }
    
    /*==============多线程演示============*/
    public static void multiThread(Hero teemo, Hero gareen, Hero bh, Hero leesin){
    	KillThread killThread1 = new KillThread(gareen, teemo);
        killThread1.start();
        
        KillThread killThread2 = new KillThread(bh, leesin);
        killThread2.start();
    }
    
    /*==============单线程演示============*/
    public static void singleThread(Hero teemo, Hero gareen, Hero bh, Hero leesin){
    	//盖伦攻击提莫
        while(!teemo.isDead()){
            gareen.attackHero(teemo);
        }
        
      //赏金猎人攻击盲僧
        while(!leesin.isDead()){
            bh.attackHero(leesin);
        }
    }
     
}