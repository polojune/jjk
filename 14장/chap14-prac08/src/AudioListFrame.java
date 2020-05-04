import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;

public class AudioListFrame extends JFrame {
	private AudioListPanel listPanel = new AudioListPanel();
	public AudioListFrame() {
		super("����� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		JLabel title = new JLabel("üũ�� � ������� �� �� �����մϴ�.");
		title.setBackground(Color.LIGHT_GRAY);
		title.setOpaque(true);
		title.setHorizontalAlignment(JLabel.CENTER);
		c.add(title, BorderLayout.NORTH);
		c.add(listPanel, BorderLayout.CENTER);
		
		setSize(400,300);
		setVisible(true);
	}
	
	class AudioListPanel extends JPanel {
		private Clip clip = null;
		private AudioInputStream audioStream = null;
		private int playIndex = 0; // ������� ���ֵ� �ε���
		private String musicArray [] = { "audio\\wolf.wav","audio\\dhol_drums.wav","audio\\sirenpolice.wav","audio\\hiphop.wav" };
		private JCheckBox checkArray [] = new JCheckBox [musicArray.length];
		private JButton playBtn = new JButton("���ֽ���");
		private JButton stopBtn = new JButton("���� ��");
		
		public AudioListPanel() {
			setLayout(new GridLayout(musicArray.length+2,1));
			for(int i=0; i<musicArray.length; i++) {
				checkArray[i] = new JCheckBox(musicArray[i], true);
				checkArray[i].setHorizontalAlignment(JCheckBox.CENTER);
				add(checkArray[i]);
			}
			JPanel btnPanel = new JPanel();
			btnPanel.add(playBtn);
			btnPanel.add(stopBtn);
			
			add(btnPanel);
			
			playBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					playIndex = 0; // ó������ ����
					playNextSelected();
				}
			});
			
			stopBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(playIndex >= checkArray.length)
						return;
					checkArray[playIndex].setForeground(Color.BLACK);
					playIndex = checkArray.length; // ������ ������ ������ ó���ϵ��� �̸� ����
					clip.stop(); // ����� Ŭ���� ���� �ߴ�
				}
			});
			
		}
		
		private void playNextSelected() {
			while(true) {
				if(playIndex >= checkArray.length)
					break; // ������ �����Ͽ���
				
				if(checkArray[playIndex].isSelected()) {
					checkArray[playIndex].setForeground(Color.BLUE);
					playAudio(checkArray[playIndex].getText());
					break; // üũ�� �� �߰��ϰ� ���� ����
				}
				playIndex++; // ���� ��
			}
		}
		
		class MyLineListener implements LineListener {
			public void update(LineEvent e) {
				if (e.getType() == LineEvent.Type.STOP) { // clip.stop()�� ȣ��ǰų� ����� ������ ��
					try {
						audioStream.close(); // ���� ���ֵǴ� ����� ��Ʈ�� �ݱ�
						checkArray[playIndex].setForeground(Color.BLACK); // ���ְ� ���� üũ�ڽ��� ���� ������ �ٲ�
						
						playIndex++; // ���� ��
						playNextSelected();
					} catch (IOException e1) {
						e1.printStackTrace();		
					}
                }
			}
		}
		
		private void playAudio(String pathName) {
			try {
				File audioFile = new File(pathName); // ����� ������ ��θ�
				audioStream = AudioSystem.getAudioInputStream(audioFile); // ����� ���Ϸκ���
				
				clip = AudioSystem.getClip(); // ����ִ� ����� Ŭ�� �����
				clip.addLineListener(new MyLineListener());
				clip.open(audioStream); // ����� ����� ��Ʈ�� ����
				clip.start(); // ��� ����
			}
			catch (LineUnavailableException e) { e.printStackTrace(); }
			catch (UnsupportedAudioFileException e) { e.printStackTrace(); }
			catch (IOException e) { e.printStackTrace(); }
		}

	}

	static public void main(String[] arg) {
		new AudioListFrame();
	}
}

