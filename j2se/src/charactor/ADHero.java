package charactor;

/**
 * 物理攻击性英雄
 * @author fudaoji<461960962@qq.com>
 *
 */
public class ADHero extends Hero implements AD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ADHero ah = new ADHero("德莱文");
		ah.physicAttack();
	}
	
	public ADHero(){
       
    }
	
	public ADHero(String name){
        this.name = name;
    }
	
	@Override
	public void physicAttack(){
		System.out.println("进行物理攻击");
	}
	
	public void equip(){
		System.out.println("重写父类的equip方法");
	}

}
