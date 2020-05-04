import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;

public class AudioListFrame extends JFrame {
	private AudioListPanel listPanel = new AudioListPanel();
	public AudioListFrame() {
		super("오디오 연주");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		JLabel title = new JLabel("체크된 곡만 순서대로 한 번 연주합니다.");
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
		private int playIndex = 0; // 오디오가 연주될 인덱스
		private String musicArray [] = { "audio\\wolf.wav","audio\\dhol_drums.wav","audio\\sirenpolice.wav","audio\\hiphop.wav" };
		private JCheckBox checkArray [] = new JCheckBox [musicArray.length];
		private JButton playBtn = new JButton("연주시작");
		private JButton stopBtn = new JButton("연주 끝");
		
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
					playIndex = 0; // 처음부터 시작
					playNextSelected();
				}
			});
			
			stopBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(playIndex >= checkArray.length)
						return;
					checkArray[playIndex].setForeground(Color.BLACK);
					playIndex = checkArray.length; // 끝까지 연주한 것으로 처리하도록 미리 세팅
					clip.stop(); // 오디오 클립의 연주 중단
				}
			});
			
		}
		
		private void playNextSelected() {
			while(true) {
				if(playIndex >= checkArray.length)
					break; // 끝까지 연주하였음
				
				if(checkArray[playIndex].isSelected()) {
					checkArray[playIndex].setForeground(Color.BLUE);
					playAudio(checkArray[playIndex].getText());
					break; // 체크된 곡 발견하고 연주 시작
				}
				playIndex++; // 다음 곡
			}
		}
		
		class MyLineListener implements LineListener {
			public void update(LineEvent e) {
				if (e.getType() == LineEvent.Type.STOP) { // clip.stop()이 호출되거나 재생이 끝났을 때
					try {
						audioStream.close(); // 현재 연주되는 오디오 스트림 닫기
						checkArray[playIndex].setForeground(Color.BLACK); // 연주가 끝난 체크박스를 원래 색으로 바꿈
						
						playIndex++; // 다음 곡
						playNextSelected();
					} catch (IOException e1) {
						e1.printStackTrace();		
					}
                }
			}
		}
		
		private void playAudio(String pathName) {
			try {
				File audioFile = new File(pathName); // 오디오 파일의 경로명
				audioStream = AudioSystem.getAudioInputStream(audioFile); // 오디오 파일로부터
				
				clip = AudioSystem.getClip(); // 비어있는 오디오 클립 만들기
				clip.addLineListener(new MyLineListener());
				clip.open(audioStream); // 재생할 오디오 스트림 열기
				clip.start(); // 재생 시작
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

