import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TenLabelsFrame extends JFrame {
	private JLabel [] gameLabels = new JLabel [10]; // 0~9������ ���̺� ��ġ
	private int nextPressed = 0;
	public TenLabelsFrame() {
		super("Ten ���̺� Ŭ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		setSize(300,300);
		setVisible(true);
		
		for(int i=0; i<gameLabels.length; i++) {
			gameLabels[i] = new JLabel(Integer.toString(i)); // ���̺� ����
			gameLabels[i].setFont(new Font("Gothic", Font.PLAIN, 15));
			gameLabels[i].setForeground(Color.MAGENTA);
			c.add(gameLabels[i]); // ���̺��� ����Ʈ�ҿ� ��Ź
			
			// 0,1,2 ������ �����ϸ�, 2,1,0 ������ ȭ�鿡 ��µȴ�. 
			// �׷����� 2�� 1�� ���� ��ġ�� ��ġ�� �Ǵ��� 1�� ���� ���̱� �ǹǷ�
			// ������� Ŭ���� �� �ִ�.
			
			gameLabels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JLabel la = (JLabel)e.getSource();
					if(Integer.parseInt(la.getText()) == nextPressed) { 
						nextPressed++;
						if(nextPressed == 10) {
							nextPressed = 0;
							configure();
						}
						else 
							la.setVisible(false);
					}
				}
			});
		}
		configure(); // ����Ʈ ��c�� 1,2,3.. ���̺� ��ġ
	}
	
	private void configure() {
		Container c = getContentPane();
		for(int i=0; i<gameLabels.length; i++) {
			gameLabels[i].setSize(15, 15); // ũ�⸦ ���� �����Ͽ��� �ڿ��� getWidth(), getHeight()�� ���� ��Ȯ����
			
			int xBound = c.getWidth() - gameLabels[i].getWidth(); // ���̺��� �� ��ŭ ����
			int yBound = c.getHeight() - gameLabels[i].getHeight(); // ���̺��� ���� ��ŭ ����				
			int x = (int)(Math.random()*xBound);
			int y = (int)(Math.random()*yBound);				
			gameLabels[i].setLocation(x, y);
			gameLabels[i].setVisible(true);
		}
	}
	static public void main(String [] args) {
		new TenLabelsFrame();
	}
}
