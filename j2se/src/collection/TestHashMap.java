package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import charactor.Hero;

public class TestHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> hm = new HashMap<>();
		hm.put("hello", "你好");
		hm.put("world", "世界");
		
		 
		for (Map.Entry<String, String> entry : hm.entrySet()) {
		 
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		 
		}

		
		//System.out.println(hm.get("hello"));
		
		//searchTest();
	}
	
	/**
	 * 性能测试
	 */
	public static void searchTest(){
		int len = 3000000;
		Hero _h;
		ArrayList<Hero> h = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < len; i++) {
			_h = new Hero(r.nextInt(1000));
			_h.name = "Hero-";
			h.add(_h);
		}
		//使用非hashmap的方式
		final long t1 = System.currentTimeMillis();
		for (int i = 0; i < h.size(); i++) {
			if(h.get(i).hp == 5555){
				//目标
			}
		}
		final long t2 = System.currentTimeMillis();
		//使用hashmap的方式
		HashMap<String, Integer> hm = new HashMap<>();
		System.out.println("使用for方式所花时间：" + (t2 - t1));
	}

}
