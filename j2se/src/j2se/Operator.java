package j2se;
import java.util.Scanner;

public class Operator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		/*System.out.println("����������a: ");
		int a = scanner.nextInt();
		System.out.println("����������b: ");
		int b = scanner.nextInt();
		System.out.println("a + b = " + (a + b));*/
		//�ַ���
		/*System.out.println("�������˵ʲô��: ");
		String msg = scanner.nextLine();
		System.out.println(msg);
		if(msg == "age"){
			System.out.println("�ҽ���29����");
		}else if(msg == "name"){
			System.out.println("�ҽй���");
		}*/
		
		/*byte a = 1;
        byte b= 2;
        byte c = (byte) (a+b); //��Ȼa b����byte���ͣ�������������int���ͣ���Ҫ����ǿ��ת��
        int d = a+b;
        
        System.out.println(c);
        System.out.println(d);
        
        int i = 1;
        int j = ++i + i++ + ++i + ++i + i++;
        System.out.println(j);*/
		
		/**
		 * �߼������
		 */
		//��·��  ���۵�һ�����ʽ��ֵ��true����false,�ڶ�����ֵ�����ᱻ����
        /*int i = 2;
        System.out.println( i== 1 & i++ ==2  ); //�������i++���ᱻִ�У�����i��ֵ�����3
        System.out.println(i);*/
         
        //��·�� ֻҪ��һ�����ʽ��ֵ��false�ģ��ڶ������ʽ��ֵ���Ͳ���Ҫ����������
        /*int j = 2;
        System.out.println( j== 1 && j++ ==2  );  //��Ϊj==1����false,�����ұߵ�j++��û��ִ���ˣ�����j��ֵ������2
        System.out.println(j);     */
		
		int i = 1;
		boolean b = !(i++ == 3) ^ (i++ ==2) && (i++==3);
		System.out.println(b);
		System.out.println(i);
	}

}
