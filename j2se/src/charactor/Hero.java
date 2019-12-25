package charactor;
  
public class Hero  implements Comparable<Hero>
{
    public String name;
    public float hp;
  
    public int damage;
  
    public Hero() {
  
    }
  
    public Hero(String name) {
 
        this.name = name;
    }
    
    public Hero(int hp) {
    	 
        this.hp = hp;
    }
  
    public String toString() {
        return "Hero [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }
 
    public Hero(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public int compareTo(Hero anotherHero) {
        if(damage >= anotherHero.damage)
            return 1; 
        return -1;
    }
  
}