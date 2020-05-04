import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PhoneReaderExUsingScanner {

	public static void main(String[] args) {
		FileReader fr = null;
		File f = new File("c:\\temp\\phone.txt");
		try {
			fr = new FileReader(f);
			Scanner scanner = new Scanner(fr);
			
			System.out.println(f.getPath() + "를 출력합니다.");
			while(scanner.hasNext()) {
				String line = scanner.nextLine(); // 한줄을 읽는다.
				System.out.println(line);
			}
			
			fr.close();
			scanner.close();	
		} 
		catch (IOException e) { // 파일을 저장할 수 없는 경우 예외
			e.printStackTrace();
		}
	}

}
