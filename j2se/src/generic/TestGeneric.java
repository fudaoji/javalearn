package generic;
   
import java.util.ArrayList;
  
import charactor.ADHero;
import charactor.APHero;
import charactor.Hero;
   
public class TestGeneric {
   
    public static void main(String[] args) {
          
        ArrayList<APHero> apHeroList = new ArrayList<APHero>();
        apHeroList.add(new APHero(0));
         
        ArrayList<? extends Hero> heroList = apHeroList;
          
        //? extends Hero 表示这是一个Hero泛型的子类泛型
          
        //heroList 的泛型可以是Hero
        //heroList 的泛型可以使APHero
        //heroList 的泛型可以使ADHero
          
        //可以确凿的是，从heroList取出来的对象，一定是可以转型成Hero的
          
        Hero h= heroList.get(0);
          
        //但是，不能往里面放东西
        //heroList.add(new ADHero(null)); //编译错误，因为heroList的泛型 有可能是APHero
        
        ArrayList<ADHero> adhs = new ArrayList<>();
        ArrayList<APHero> aphs = new ArrayList<>();
        adhs.add(new ADHero("liu"));
        aphs.add(new APHero("zhou"));
        //iterate(adhero);
        //iterate(aphero);
        
        ArrayList<Hero> hs = new ArrayList<>();
        
        //hs = adhs; //错误，读的时候没问题，因为adhs是hs的子类，但是存的时候，如果存aphs的对象，那就出问题了。
        //aphs = hs; //错误， 存的时候没问题，但是取的时候可能取到adhs的，这样就与aphs的冲突了。
    }
    
    public static void iterate(ArrayList<?extends Hero> HERO){
        for (Hero h:HERO){
            System.out.println(h.name);
        }
    }
      
}