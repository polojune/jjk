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
		System.out.print("���� �?");
		int n = scanner.nextInt();
		
		if(n <= 0 || n > 100) {
			System.out.print("1~100���̷� �Է��ϼ���!");			
			scanner.close();
			return; // ���α׷� ����
		}
		int array [] = new int [n]; // n���� ���� �迭 ����
		
		for(int i=0; i<array.length; i++) {
			int r = (int)(Math.random()*100 + 1); // 1~100 ������ ���� ����
			// ���� r�� �̹� �迭 array[0]~array[i-1]�� �ִ��� �˻�
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
				if(i%10 == 0) System.out.println(); // ���� �ٷ� �Ѿ��
				System.out.print(array[i] + " ");
			}
		}
		
		scanner.close();
	}
}
