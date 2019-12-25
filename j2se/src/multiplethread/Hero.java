package multiplethread;

import java.util.Random;

public class Hero {
	public String name;
	public float hp;
	public int damage;
	public int aDuGen = 3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void attackHero(Hero h){
        if(! h.isDead()){
        	if(aDuGen <= 0){
        		recharge();
        	}else{
        		try {
            		Random r = new Random();
                    //为了表示攻击需要时间，每次攻击暂停1000毫秒内
                    Thread.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                h.hp -= damage;
                aDuGen -= 1;
                System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n", name, h.name, h.name, h.hp);
                
                if(h.isDead())
                	System.out.println(h.name +"死了！");
        	}
        }
	}
	
	public boolean isDead(){
		return 0 >= hp ? true : false;
	}
	
	//充能
	public void recharge(){
		try {
			System.out.println("充能5秒钟...");
            Thread.sleep(5000);
            aDuGen = 3;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
