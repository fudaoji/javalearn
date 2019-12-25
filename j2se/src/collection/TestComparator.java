package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import charactor.Hero;

public class TestComparator {

	public static void main(String[] args) {
		//1、生成100个随机数值的hero
		ArrayList<Hero> heros = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			heros.add(new Hero("hero-" + i, r.nextInt(100), r.nextInt(100)));
		}
		
		//2、创建比较器Comparator
		Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                //按照hp进行排序
                if(h1.hp>=h2.hp)
                    return 1;  //正数表示h1比h2要大
                return -1;
            }
        };
		
		//3、调用Collections.sort
        Collections.sort(heros, c); //普通方式
		//Collections.sort(heros, (h1, h2) -> h1.hp>=h2.hp ? 1 : -1); //lambda方式
		
		System.out.println(heros);

	}

}
