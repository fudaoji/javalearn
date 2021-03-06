package charactor;

public class GiantDragon {
	static String name="D1";
	
	//私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){
         
    }
 
    //饿汉式：准备一个类属性，指向一个实例化对象。 因为是类属性，所以只有一个
    private static GiantDragon instance = new GiantDragon();
     
    //public static 方法，提供给调用者获取12行定义的对象
    public static GiantDragon getInstance(){
    	if(instance == null){ //懒汉式
    		instance = new GiantDragon();
    	}
        return instance;
    }
}
