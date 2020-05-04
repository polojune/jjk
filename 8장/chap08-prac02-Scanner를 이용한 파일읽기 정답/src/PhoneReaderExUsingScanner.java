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
			
			System.out.println(f.getPath() + "�� ����մϴ�.");
			while(scanner.hasNext()) {
				String line = scanner.nextLine(); // ������ �д´�.
				System.out.println(line);
			}
			
			fr.close();
			scanner.close();	
		} 
		catch (IOException e) { // ������ ������ �� ���� ��� ����
			e.printStackTrace();
		}
	}

}
