import java.util.Scanner;

public class RandomArray {
	public static boolean exists(int a[], int from, int r) {
		for(int j=0; j<from; j++) {
			if(a[j] == r) {
				return true; // exists 
			}
		}		
		return false; // not exists
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("정수 몇개?");
		int n = scanner.nextInt();
		
		if(n <= 0 || n > 100) {
			System.out.print("1~100사이로 입력하세요!");			
			scanner.close();
			return; // 프로그램 종료
		}
		int array [] = new int [n]; // n개의 정수 배열 생성
		
		for(int i=0; i<array.length; i++) {
			int r = (int)(Math.random()*100 + 1); // 1~100 범위의 랜덤 정수
			// 정수 r의 이미 배열 array[0]~array[i-1]에 있는지 검사
			if(exists(array, i, r)) {
				i--; // not to increase index i in for.
				continue;
			}
			array[i] = r;			
		}
		
		for(int i=0; i<array.length; i++) {
			if(i==0)
				System.out.print(array[i] + " ");
			else {
				if(i%10 == 0) System.out.println(); // 다음 줄로 넘어가기
				System.out.print(array[i] + " ");
			}
		}
		
		scanner.close();
	}
}
