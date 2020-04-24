package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Person;
import org.junit.Test;

import java.util.Date;

public class JsonJsonTest {
    /**
     * jason json测试
     *
     * @throws JsonProcessingException
     */
    @Test
    public void test() throws Exception {
        Person p = new Person("张三", 23, "男");
        //1、创建ObjectMap对象
        ObjectMapper om = new ObjectMapper();
        //2、转化
        String s = om.writeValueAsString(p); //转为json字符串
        //om.writeValue(new File("d:/DevTools/BtSoft/a.txt"), p);//写入文件
        System.out.println(s);
    }

    /**
     * jason json测试
     *
     * @throws JsonProcessingException
     */
    @Test
    public void test2() throws Exception {
        Person p = new Person("张三", 23, "男", new Date());
        //1、创建ObjectMap对象
        ObjectMapper om = new ObjectMapper();
        //2、转化
        String s = om.writeValueAsString(p); //转为json字符串
        //om.writeValue(new File("d:/DevTools/BtSoft/a.txt"), p);//写入文件
        System.out.println(s);
    }
}
