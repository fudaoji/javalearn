package reflection;


public class TestReflection {

	public static void main(String[] args) {
		testSynchronized();
	}
	/**
	 * 多线程synchronized演示
	 */
	public static void testSynchronized() {
		//创建线程1
		Thread t1 = new Thread() {
			public void run() {
				method1();
			}
		};
		t1.setName("t1");
		t1.start();
		
		//主线程睡眠1秒，给线程1足够时间运行，清除干扰
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建线程2
		Thread t2 = new Thread() {
			public void run() {
				method1();
			}
		};
		t2.setName("t2");
		t2.start();
	}
	/**
	 * 模拟线程执行某个任务
	 */
	public static void method1(){
		String tName = Thread.currentThread().getName();
        System.out.println(tName + "占用资源");
        synchronized(TestReflection.class) {
        	try {
                System.out.println(tName + "休眠5秒");
                Thread.sleep(5000); //假设任务执行5秒钟，增强实验效果
                System.out.println(tName + "休眠完毕");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
	
	public synchronized void method2(){
        //同步方法必须要有一个同步对象
        System.out.println(Thread.currentThread().getName() + "占用资源");
        try {
            System.out.println("method2休眠5秒");
            Thread.sleep(5000);
            System.out.println("method2休眠完毕");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
    
    public static void test1() {
		String className = "reflection.TestClass";
		try {
			Class pClass1 = Class.forName(className);
			Class pClass2 = TestClass.class;
			Class pClass3 = (new TestClass()).getClass();
			System.out.println(pClass1 == pClass2);
			System.out.println(pClass1 == pClass3);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
