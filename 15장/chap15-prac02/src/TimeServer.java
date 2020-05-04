import java.io.*;
import java.net.*;

public class TimeServer {
	public static void main(String[] args) {
		BufferedWriter out = null;
		ServerSocket listener = null;
		Socket socket = null;
		System.out.println("타임서버입니다.");
		try {
			listener = new ServerSocket(9999); // 서버 소켓 생성
			socket = listener.accept(); // 클라이언트로부터 연결 요청 대기
			System.out.println("연결되었습니다.\n500ms마다 수를 보냅니다.");
			
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 클라이언트로의 출력 스트림
			TimerThread th = new TimerThread(out); // 스레드 생성자에 스트림 out 전달
			th.start();
			try {
				th.join(); // 스레드 th가 종료하기를 기다린다.
			} catch (InterruptedException e) {	}
			socket.close(); // 클라이언트와 통신용 소켓 닫기
			listener.close(); // 서버 소켓 닫기
			System.out.println("종료합니다.");
		} catch (IOException e) {
			System.out.println("입출력 오류가 발생했습니다.");
		}
	}
}
class TimerThread extends Thread {
	private BufferedWriter out;
	public TimerThread(BufferedWriter out) { // 스레드 생성자에서 소켓에 쓸 수 있는 스트림 out을 전달받음
		this.out = out;		
	}
	
	@Override
	public void run() {
		try {
			for(int i=0; i<20; i++) { // 20번 루프를 돌아 10초간 실행
				sleep(500); // 500ms 잠자기
				out.write(Integer.toString(i) + "\n");
				out.flush();
			}
			out.write("끝\n"); // 클라이언트 소켓으로 전송
			out.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}