import java.util.Scanner;

public class PrintAlphabet {
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.print("�ҹ��� ���ĺ� �ϳ��� �Է��Ͻÿ�>>");
		String s = scanner.next();
		if(s.length() != 1) {
			System.out.print("���ĺ� �ϳ��� �Է��ؾ� �մϴ�!");
			scanner.close();
			return;
		}
		
		char c = s.charAt(0);
		if (c < 'a' || c > 'z') {
			System.out.println("�ҹ��� ���ĺ��� �ƴմϴ�.");
			scanner.close();
			return;
		}
		
		for (char i=c; i>='a'; i--) {
			for (char j='a'; j<=i; j++)
				System.out.print(j);
			System.out.println();
		}
		
		scanner.close();
	}
}
