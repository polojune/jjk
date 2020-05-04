import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TextFieldAndComboBoxFrame extends JFrame {
	private JTextField tf = new JTextField(10);
	private JComboBox<String> combo = new JComboBox<String>();

	public TextFieldAndComboBoxFrame() {
		super("JTextField and JComboBox Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(tf);
		c.add(combo);		
		
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField t = (JTextField)e.getSource();
				combo.addItem(t.getText());
			}
		});

		setSize(250,200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new TextFieldAndComboBoxFrame();
	}
}
