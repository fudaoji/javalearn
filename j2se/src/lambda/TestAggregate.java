package lambda;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import charactor.Hero;
 
public class TestAggregate {
 
    public static void main(String[] args) {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 10; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
 
        System.out.println("初始化后的集合：");
        System.out.println(heros);
        System.out.println("查询条件：hp>100 && damage<50");
        System.out.println("通过传统操作方式找出满足条件的数据：");
 
        for (Hero h : heros) {
            if (h.hp > 100 && h.damage < 50)
                System.out.println(h.name);
        }
 
        System.out.println("通过聚合操作方式找出满足条件的数据：");
        heros
            .stream()
            .filter(h -> h.hp > 100 && h.damage < 50)
            .forEach(h -> System.out.println(h.name));
 
        System.out.println("通过聚合操作方式把hp第三高的英雄名称打印出来：");
        System.out.println(heros
        .stream()
        .sorted((h1, h2)->h1.hp <= h2.hp ? 1 : -1)
        .skip(2)
        .findFirst());
    }
}