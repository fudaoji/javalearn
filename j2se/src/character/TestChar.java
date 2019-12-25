package character;

public class TestChar {

	public static void main(String[] args) {
		String str = "abc123";
		char[] cs = str.toCharArray();
		
		for(char each: cs){
			System.out.println(each);
		}
	}

}
