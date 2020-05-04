import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TimeClient {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BufferedReader in = null;
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9999); // Ŭ���̾�Ʈ ���� ����. ������ �ٷ� ����
			System.out.println("������ �����Ͽ����ϴ�...");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // �����κ��� �Է� ��Ʈ��
			String inputMessage;
			System.out.print("������ �ð� : ");
			while (true) {
				inputMessage = in.readLine(); // �����κ��� �� ������ �ؽ�Ʈ ����
				// inputMessage == null�̸� ��Ʈ���� ���� ������. �� ��밡 ������ ������
				if (inputMessage == null || inputMessage.equalsIgnoreCase("��")) { // "��"�� �ԷµǸ� ������ ���� ����
					System.out.println("\n������ �����մϴ�.");
					break;
				}
				else {
					int n = Integer.parseInt(inputMessage); // ���� ���ڿ��� ���ڷ� ��ȯ
					System.out.print(n + " ");
				}
			}
			socket.close(); //  ���� ����. Ŭ���̾�Ʈ ���� �ݱ�
			scanner.close();
		} catch (IOException e) {
			System.out.println("����� ������ �߻��߽��ϴ�.");
		}
	}

}
