import java.util.Scanner;

public class DivAndRemains {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("2�ڸ��� ���� �Է�(10~99)>>");
		int n = scanner.nextInt();
		if(n < 10 || n > 99) {
			System.out.println("10~99 ������ ������ �Է��ϼ���.");
			scanner.close();
			return;
		}
		int ten = n/10;
		int one = n%10;
		
		if(ten == one)
			System.out.println("Yes! 10�� �ڸ��� 1�� �ڸ��� �����ϴ�.");
		else
			System.out.println("No! 10�� �ڸ��� 1�� �ڸ��� �ٸ��ϴ�.");			
		scanner.close();
	}

}
