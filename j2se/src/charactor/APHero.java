package charactor;

public class APHero extends Hero implements AP {
	
	public APHero() {
		
	}
	
	public APHero(int hp) {
		super(hp);
		// TODO Auto-generated constructor stub
	}

	public APHero(String name) {
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		APHero aph = new APHero(1);
		aph.magicAttack();
	}

	public void magicAttack(){
		System.out.println("输出魔法攻击");
	}
}
