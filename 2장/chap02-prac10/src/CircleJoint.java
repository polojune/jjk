import java.util.Scanner;

public class CircleJoint {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("ù��° ���� �߽ɰ� ������ �Է�>>");
		double x1 = scanner.nextDouble();
		double y1 = scanner.nextDouble();
		double radius1 = scanner.nextDouble();

		System.out.print("�ι�° ���� �߽ɰ� ������ �Է�>>");
		double x2 = scanner.nextDouble();
		double y2 = scanner.nextDouble();
		double radius2 = scanner.nextDouble();

		double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		double radiusSum = radius1+radius2;
		
		if(distance < radiusSum)
			System.out.print("�� ���� ���� ��ģ��.");
		else
			System.out.print("�� ���� ���� ��ġ�� �ʴ´�.");

		scanner.close();
	}

}
