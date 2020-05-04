import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TimeClient {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		BufferedReader in = null;
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9999); // 클라이언트 소켓 생성. 서버에 바로 접속
			System.out.println("서버에 접속하였습니다...");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 서버로부터 입력 스트림
			String inputMessage;
			System.out.print("서버의 시간 : ");
			while (true) {
				inputMessage = in.readLine(); // 서버로부터 한 라인의 텍스트 받음
				// inputMessage == null이면 스트림의 끝에 도달함. 즉 상대가 연결을 끊었음
				if (inputMessage == null || inputMessage.equalsIgnoreCase("끝")) { // "끝"이 입력되면 서버와 연결 종료
					System.out.println("\n연결을 종료합니다.");
					break;
				}
				else {
					int n = Integer.parseInt(inputMessage); // 받은 문자열을 숫자로 변환
					System.out.print(n + " ");
				}
			}
			socket.close(); //  연결 종료. 클라이언트 소켓 닫기
			scanner.close();
		} catch (IOException e) {
			System.out.println("입출력 오류가 발생했습니다.");
		}
	}

}
