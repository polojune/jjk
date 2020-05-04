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
		System.out.print("두 점 (x1,y1), (x2, y2)의 좌표를 입력하시오>>");
		int x1 = scanner.nextInt();
		int y1 = scanner.nextInt();
		int x2 = scanner.nextInt();
		int y2 = scanner.nextInt();
		
		if(inRect(x1, y1, 100, 100, 200, 200) || 
				inRect(x2, y2, 100, 100, 200, 200) || 
				inRect(x1, y2, 100, 100, 200, 200) || 
				inRect(x2, y1, 100, 100, 200, 200)) // 한 꼭지점이라도 4각형 안에 포함되는 경우
			System.out.println("사각형이 겹칩니다.");
		else if(inRect(x1, y1, 100, 100, 200, 200) &&
				inRect(x2, y2, 100, 100, 200, 200) && 
				inRect(x1, y2, 100, 100, 200, 200) && 
				inRect(x2, y1, 100, 100, 200, 200)) // 모든 꼭지점이 4각형 안에 있는 경우
			System.out.println("사각형이 겹칩니다.");
		else if(inRect(100, 100, x1, y1, x2, y2) &&
				inRect(100, 200, x1, y1, x2, y2) && 
				inRect(200, 100, x1, y1, x2, y2) && 
				inRect(200, 200, x1, y1, x2, y2)) // (100,100), (200,200) 사각형의 모든 꼭지점이 사용자가 만든 사각형 안에 있는 경우
			System.out.println("사각형이 겹칩니다.");
		else
			System.out.println("사각형이 겹치지 않습니다.");
		
		scanner.close();
	}
}





