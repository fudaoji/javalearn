package j2se;
import java.util.Scanner;

public class Bmi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("�������������/m:");
		float height = scan.nextFloat();
		System.out.println("");
		
		System.out.print("��������������/kg:");
		float weight = scan.nextFloat();
		System.out.println("");
		
		float bmi =  weight / (height * height);
		System.out.println("����BMIֵ�ǣ�" + bmi);
	}

}
