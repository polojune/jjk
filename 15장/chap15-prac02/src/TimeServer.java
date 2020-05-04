import java.io.*;
import java.net.*;

public class TimeServer {
	public static void main(String[] args) {
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		System.out.println("Ÿ�Ӽ����Դϴ�.");
		try {
			listener = new ServerSocket(9999); // ���� ���� ����
			socket = listener.accept(); // Ŭ���̾�Ʈ�κ��� ���� ��û ���
			System.out.println("����Ǿ����ϴ�.\n500ms���� ���� �����ϴ�.");
			
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Ŭ���̾�Ʈ���� ��� ��Ʈ��
			TimerThread th = new TimerThread(out); // ������ �����ڿ� ��Ʈ�� out ����
			th.start();
			try {
				th.join(); // ������ th�� �����ϱ⸦ ��ٸ���.
			} catch (InterruptedException e) {	}
			socket.close(); // Ŭ���̾�Ʈ�� ��ſ� ���� �ݱ�
			listener.close(); // ���� ���� �ݱ�
			System.out.println("�����մϴ�.");
		} catch (IOException e) {
			System.out.println("����� ������ �߻��߽��ϴ�.");
		}
	}
}
class TimerThread extends Thread {
	private BufferedWriter out;
	public TimerThread(BufferedWriter out) { // ������ �����ڿ��� ���Ͽ� �� �� �ִ� ��Ʈ�� out�� ���޹���
		this.out = out;		
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i<20; i++) { // 20�� ������ ���� 10�ʰ� ����
				sleep(500); // 500ms ���ڱ�
				out.write(Integer.toString(i) + "\n");
				out.flush();
			}
			out.write("��\n"); // Ŭ���̾�Ʈ �������� ����
			out.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}