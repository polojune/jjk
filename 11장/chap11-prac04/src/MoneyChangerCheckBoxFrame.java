import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MoneyChangerCheckBoxFrame extends JFrame {
	public MoneyChangerCheckBoxFrame() {
		setTitle("Money Changer with CheckBox");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new MyPanel());
		setSize(300,300);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		private int [] unit = {50000, 10000, 1000, 500, 100, 50, 10, 1}; // 환산할 돈의 종류
		private String [] text = {"오만원", "만원", "천원", "500원", "100원", "50원", "10원", "1원"}; // 환산할 돈의 이름. 레이블로 출력됨
		private JTextField source; // 사용자로부터 금액이 입력될 텍스트 필드
		private JTextField [] tf = new JTextField [8]; // 환산된 돈의 개수가 출력될 텍스트필드
		private JCheckBox [] cb = new JCheckBox [7]; // 체크박스
		
		public MyPanel() {
			setBackground(Color.PINK);
			setLayout(null);
			
			// 금액 레이블
			JLabel la = new JLabel("금액");
			la.setHorizontalAlignment(JLabel.RIGHT);
			la.setSize(50, 20);
			la.setLocation(20, 20);
			add(la);
			
			// 금액을 입력하는 JTextField
			source = new JTextField(30);
			source.setSize(100, 20);
			source.setLocation(100, 20);
			add(source);
			
			// 계산 버튼
			JButton calcBtn = new JButton("계산");
			calcBtn.setSize(70, 20);
			calcBtn.setLocation(210, 20);
			add(calcBtn);
			
			// 계산된 액수별 돈의 갯수
			for(int i=0; i<text.length; i++) {
				la = new JLabel(text[i]);
				la.setHorizontalAlignment(JLabel.RIGHT);
				la.setSize(50, 20);
				la.setLocation(50, 50+i*20);
				add(la);
				tf[i] = new JTextField(30);
				tf[i].setHorizontalAlignment(JTextField.CENTER);
				tf[i].setSize(70, 20);
				tf[i].setLocation(120, 50+i*20);
				
				add(tf[i]);
			}

			// 체크 박스
			for(int i=0; i<cb.length; i++) {
				cb[i] = new JCheckBox("", true); // 초기에 모두 선택된 상태로 설정
				cb[i].setOpaque(false); // 체크박스의 배경을 투명하게 설정하여 콘텐트 팬의 배경색이 나타나도록 함
				cb[i].setHorizontalAlignment(JTextField.CENTER);
				cb[i].setSize(30, 20);
				cb[i].setLocation(200, 50+i*20);
				
				add(cb[i]);
			}
			
			// 계산 버튼의 Action 리스너
			calcBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String str = source.getText(); // 입력된 금액 문자열
					if(str.length() == 0) return; // 입력된 금액이 없음
					
					int money = Integer.parseInt(str); // 입력된 금액을 정수로 변환
					int res;
					for(int i=0; i<unit.length; i++) {
						if(i==unit.length-1) { // 1원 짜리 계산하는 경우
							res = money/unit[i]; // unit[i]의 개수 계산
							tf[i].setText(Integer.toString(res));
							break;
						}
						if(!cb[i].isSelected()) {// 체크박스가 해제된 경우에는 해당 단위의 개수를 세지 않음
							tf[i].setText("0");
							continue;
						}
						
						res = money/unit[i]; // unit[i]의 개수 계산
						tf[i].setText(Integer.toString(res));
						if(res > 0) 
							money = money%unit[i]; // money 갱신
					}
				}
			});
		}
	}
	
	public static void main(String[] args) {
		new MoneyChangerCheckBoxFrame();
	}
}
