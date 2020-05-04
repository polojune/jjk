import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileExplorer2 {
	private File currentDir = null;
	private File subFiles [] = null;
	
	public FileExplorer2(String dirName) {
		currentDir = new File(dirName);
	}

	private void showSubDirtories() {
		System.out.println("\t[" + currentDir.getPath() + "]");
		subFiles = currentDir.listFiles();
		for(File f : subFiles) {
			System.out.print(((f.isFile())?"file":"dir"));
			System.out.printf("%-15s", "\t\t" + f.length() + "바이트");
			System.out.println("\t\t" + f.getName());
		}		
	}
	
	private void mkdir(String dirName) {
		File newDir = new File(currentDir, dirName); // 만들고자 하는 dirName의 전체 경로명 만들기 
		if(newDir.exists()) // 이미 존재한다면
			System.out.println("이미 존재하는 파일이나 디레터리 이름입니다.");
		else {
			System.out.println(dirName + " 디렉터리를 생성하였습니다.");
			newDir.mkdir(); // 디렉터리 생성
			showSubDirtories();
		}
	}
	private void rename(String src, String dest) {
		File srcFile = new File(currentDir, src); // 소스 파일 경로명 
		File destFile = new File(currentDir, dest); // 목적 파일 경로명 
		if(destFile.exists()) // 이미 존재한다면, 이름 바꿀 수없음
			System.out.println("이미 존재하는 파일이나 디레터리 이름입니다. 이름 바꿀 수 없습니다.");
		else {
			System.out.println(src + "를 " + dest + " 이름 변경하였습니다.");
			srcFile.renameTo(destFile); // 이름 변경
			showSubDirtories();
		}
	}
	
	private boolean contains(String filename) {
		for(File f : subFiles) {
			if(f.getName().equalsIgnoreCase(filename))
				return true;
		}
		return false;
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("***** 파일 탐색기입니다. *****");
		showSubDirtories();
		
		while(true) {
			System.out.print(">>");
			String line = scanner.nextLine().trim(); // 한 라인을 읽고 앞뒤에 입력한 빈칸 지우기
			StringTokenizer st = new StringTokenizer(line, " "); // 빈 칸으로 분리
			String command = st.nextToken(); //첫번째 문자열
			if(command.equals("그만"))
				break; // 프로그램 종료
			if(command.equals("..")) {
				String s = currentDir.getParent();
				if(s == null) // s가 null이면, currentDir가 현재 최상위 디렉터리라서 부모 디렉터리가 없음
					continue;
				else { 					
					currentDir = new File(currentDir.getParent());
					showSubDirtories();
				}

			}
			if(command.equalsIgnoreCase("mkdir")) {
				try {
					String dirName = st.nextToken(); //두번째 문자열
					mkdir(dirName);
				} catch(NoSuchElementException e) {
					System.out.println("생성할 디렉터리명이 주어지지 않았습니다!");
				}
			}
			else if(command.equalsIgnoreCase("rename")) {
				try { 
					String src = st.nextToken(); //두번째 문자열
					String dest = st.nextToken(); //두번째 문자열
					rename(src, dest);
				} catch(NoSuchElementException e) {
					System.out.println("두 개의 파일명이 주어지지 않았습니다.!");
				}
			}
			else { // command는 서브디렉토리이름
				if(contains(command)) { // 현재 디렉터리의 파일이나 서브디렉토리라면
					if(new File(currentDir, command).isDirectory()) { // 디렉터리라면
						currentDir = new File(currentDir, command);
						showSubDirtories();
					}
					else { // 파일인 경우
						System.out.println("\t디렉터리가 아닙니다.!");
					}
				}
			}
		}
		
		scanner.close();
	}
	
	public static void main(String[] args) {
		FileExplorer2 fe = new FileExplorer2("c:\\");
		fe.run();
	}

}
