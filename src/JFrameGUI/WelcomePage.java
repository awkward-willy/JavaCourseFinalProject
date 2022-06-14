package JFrameGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomePage {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private ImageIcon icon;

	protected WelcomePage() {
		// frame basic info
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel();
		icon = new ImageIcon(this.getClass().getResource("/Cover2.png"));
		label.setIcon(icon);
		label.setBounds(0, 0, 500, 400);
		frame.add(label);

		label = new JLabel("<html>　 第11組<br>圖書管理系統</html>", SwingConstants.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 30));
		label.setBounds(500, 0, 300, 200);
		frame.add(label);

		button = new JButton("登入");
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(600, 200, 100, 50);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Login();
			}
		});
		frame.add(button);

		button = new JButton("註冊");
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(600, 270, 100, 50);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Register();
			}
		});
		frame.add(button);

		frame.setVisible(true);
	}
}