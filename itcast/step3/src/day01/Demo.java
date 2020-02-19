package day01;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

@MyAnno(className = "day01.Teacher", methodName = "teach")
public class Demo {
    public static void main(String[] args) throws Exception {
        autoTest();
        System.out.println();
        System.out.println();
        System.out.print("sss");
        //reflect();
        //annotationTest();
    }

    public static void autoTest() throws Exception {
        //1.实力化Calculator
        Calculator c = new Calculator();
        //2.获取类字节码
        Class cls = c.getClass();
        //3.获取所有方法
        Method[] methods = cls.getMethods();

        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));
        int bugNum = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Check.class)) {
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    bugNum++;
                    bw.write(method.getName() + "方法出现异常");
                    bw.newLine();
                    bw.write("异常类名称：" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常原因：" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("===============================================");
                    bw.newLine();
                }
            }
        }
        bw.write("此次测试出现" + bugNum + "个异常");
        bw.flush();
        bw.close();
    }

    /**
     * 注解测试
     *
     * @throws Exception
     */
    public static void annotationTest() throws Exception {
        Class cls = Demo.class;
        MyAnno ao = (MyAnno) cls.getAnnotation(MyAnno.class);

        //2、get class name & method name
        String className = ao.className();
        String methodName = ao.methodName();

        //3、get class resource by reflection tech
        Class c = Class.forName(className);
        //Object obj = c.getDeclaredConstructor().newInstance();
        Object obj = c.newInstance();
        //4. get method
        Method m = c.getMethod(methodName);
        //5.invoke method by obj
        m.invoke(obj);
    }

    /**
     * commend: 设计一个"框架类"，在不改变代码的情况下支持创建任何类的对象，以及调用该对象的方法
     * step：1、load config file (pro.properties)
     * 2、get class name & method name
     * 3、get class resource by reflection tech
     */
    public static void reflect() throws Exception {
        //1、load config file (pro.properties)
        Properties pro = new Properties();
        ClassLoader cl = Demo.class.getClassLoader();
        InputStream is = cl.getResourceAsStream("pro.properties");
        pro.load(is);

        //2、get class name & method name
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3、get class resource by reflection tech
        Class c = Class.forName(className);
        //Object obj = c.getDeclaredConstructor().newInstance();
        Object obj = c.newInstance();
        //4. get method
        Method m = c.getMethod(methodName);
        //5.invoke method by obj
        m.invoke(obj);
    }

    /**
     * test add method
     */
    @Test
    public void testAdd() {
        Calculator c = new Calculator();
        int res = c.add(1, 2);

        Assert.assertEquals(3, res);
    }

    /**
     * test sub method
     */
    @Test
    public void testSub() {
        Calculator c = new Calculator();
        int res = c.sub(10, 2);

        Assert.assertEquals(8, res);
    }
}
