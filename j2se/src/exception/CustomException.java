/**
 * 自定义异常
 * @author fudaoji<461960962@qq.com>
 */
package exception;

import exception.CustomException.Hero.EnemyHeroIsDeadException;

/**
 * @author fudaoji<461960962@qq.com>
 *
 */
public class CustomException {
	
	static class Hero {
		public String name;
		protected float hp;
		
		public String toString(){
			return name;
		}
		/**
		 * 攻击
		 * @param h
		 * @throws EnemyHeroIsDeadException
		 */
		public void attackHero(Hero h) throws EnemyHeroIsDeadException{
			if(h.hp <= 0){
				throw new EnemyHeroIsDeadException(h.name + "已经挂了，不需要释放技能");
			}
		}
		
		class EnemyHeroIsDeadException extends Exception{
			//无参构造函数
			public EnemyHeroIsDeadException(){
				
			}
			//带参构造函数
			public EnemyHeroIsDeadException(String msg){
				super(msg);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Hero garen = new CustomException.Hero();
		garen.name = "盖伦";
		garen.hp = 616;
		
		Hero teemo = new CustomException.Hero();
		teemo.name = "提莫";
		teemo.hp = 0;
		
		try {
			garen.attackHero(teemo);
		} catch (EnemyHeroIsDeadException e) {
			// TODO Auto-generated catch block
			System.out.println("异常的具体原因："+ e.getMessage());
			//e.printStackTrace();
		}
	}

}
