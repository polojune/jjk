import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;

public class SpellCheckerServerFrame extends JFrame {
	private SpellChecker spellChecker = null;
	private JTextArea log = new JTextArea();
	public SpellCheckerServerFrame() {
		super("���� ���� üũ ����");
		setSize(250, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ ���� ��ư(X)�� Ŭ���ϸ� ���α׷� ����
		Container c = getContentPane();
		c.add(new JLabel("���� ���� üũ �����Դϴ�"));
		c.add(new JScrollPane(log), BorderLayout.CENTER);
		setVisible(true);
		
		spellChecker = new SpellChecker("words.txt");
		if(spellChecker.isFileRead()) { // �ܾ� ������ �������� ��� ���� ����
			log.setText("words.txt �б� �Ϸ�\n");
			new ServerThread().start(); // ���� ����
		}
	}
	
	class ServerThread extends Thread {
		@Override
		public void run() {
			ServerSocket listener = null;
			Socket socket = null;
			try {
				listener = new ServerSocket(9998);
				while(true) {
					socket = listener.accept();
					log.append("Ŭ���̾�Ʈ �����\n");
					new ServiceThread(socket).start();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				if(listener != null)
					listener.close();
				if(socket != null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class SpellChecker {
		private Vector<String> v = new Vector<String>();
		private boolean fileOn = false;
		
		public SpellChecker(String fileName) {
			try {
				Scanner reader = new Scanner(new FileReader(fileName));
				while(reader.hasNext()) {
					String word = reader.nextLine();
					v.add(word);
				}
				reader.close();
				fileOn = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fileOn = false;
			}
		}
		
		public boolean isFileRead() {
			return fileOn;
		}
		
		public boolean check(String word) { // word�� ���� v�� �ִ��� Ȯ��
			if(v.contains(word))
				return true;
			else
				return false;
		}
	}
	
	class ServiceThread extends Thread {
		private Socket socket = null;
		private BufferedReader in = null;
		private BufferedWriter out = null;
		public ServiceThread(Socket socket) { // Ŭ���̾�Ʈ�� ����� ������ ���޹���
			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					String word = in.readLine(); // ù ��
					boolean res = spellChecker.check(word);
					if(res == true) {
						out.write("YES\n");
						log.append(word + "=YES\n");
					}
					else { 
						out.write("NO\n");
						log.append(word + "=NO\n");						
					}
					out.flush();
				} catch (IOException e) {
					log.append("���� ����\n");
					System.out.println("���� ����");
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					return; // ������ ����
					//e.printStackTrace();
				}

			}
		}
	}
	public static void main(String[] args) {
		new SpellCheckerServerFrame();
	}

}
