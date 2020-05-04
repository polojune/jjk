import java.util.Scanner;
public class RectangleCollision {
	public static boolean inRect(int x, int y, int rectx1, int recty1, int rectx2, int recty2) {
		if ((x >= rectx1 && x <= rectx2) && (y >= recty1 && y <= recty2))
			return true;
		else
			return false;
	}
	public static void main (String args[])  {
		Scanner scanner = new Scanner(System.in);
		System.out.print("�� �� (x1,y1), (x2, y2)�� ��ǥ�� �Է��Ͻÿ�>>");
		int x1 = scanner.nextInt();
		int y1 = scanner.nextInt();
		int x2 = scanner.nextInt();
		int y2 = scanner.nextInt();
		
		if(inRect(x1, y1, 100, 100, 200, 200) || 
				inRect(x2, y2, 100, 100, 200, 200) || 
				inRect(x1, y2, 100, 100, 200, 200) || 
				inRect(x2, y1, 100, 100, 200, 200)) // �� �������̶� 4���� �ȿ� ���ԵǴ� ���
			System.out.println("�簢���� ��Ĩ�ϴ�.");
		else if(inRect(x1, y1, 100, 100, 200, 200) &&
				inRect(x2, y2, 100, 100, 200, 200) && 
				inRect(x1, y2, 100, 100, 200, 200) && 
				inRect(x2, y1, 100, 100, 200, 200)) // ��� �������� 4���� �ȿ� �ִ� ���
			System.out.println("�簢���� ��Ĩ�ϴ�.");
		else if(inRect(100, 100, x1, y1, x2, y2) &&
				inRect(100, 200, x1, y1, x2, y2) && 
				inRect(200, 100, x1, y1, x2, y2) && 
				inRect(200, 200, x1, y1, x2, y2)) // (100,100), (200,200) �簢���� ��� �������� ����ڰ� ���� �簢�� �ȿ� �ִ� ���
			System.out.println("�簢���� ��Ĩ�ϴ�.");
		else
			System.out.println("�簢���� ��ġ�� �ʽ��ϴ�.");
		
		scanner.close();
	}
}





