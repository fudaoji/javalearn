package character;


public class MyString {
	//length用户要求产生字符串的长度
	 public static String getRandomString(int length){
		 char[] c = new char[length];
		 for (int i = 0; i < length; i++) {
			 int num = (int) (Math.random() * 75 + 48);
			 if ((num <= 57) || ((num >= 65) && (num <= 90)) || (num >= 97)) {
				 c[i] = (char) num;
			 } else {
				 i--;
			 }
		 }
		 return new String(c);
	
	 }
}
