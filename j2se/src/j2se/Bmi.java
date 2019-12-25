package j2se;
import java.util.Scanner;

public class Bmi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入您的身高/m:");
		float height = scan.nextFloat();
		System.out.println("");
		
		System.out.print("请输入您的体重/kg:");
		float weight = scan.nextFloat();
		System.out.println("");
		
		float bmi =  weight / (height * height);
		System.out.println("您的BMI值是：" + bmi);
	}

}
