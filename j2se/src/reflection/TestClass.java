package reflection;

public class TestClass {
	static String copyright = "";
	
	static {
		System.out.println("此处执行静态变量初始化");
		copyright = "版权由厦门酷云网络科技有限公司所有";
	}
	
}
