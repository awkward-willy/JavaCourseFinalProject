package JFrameGUI;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Data.AccountAndPasswords;

public class Login {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private JTextField textField;
	private JPasswordField passwordField;
	private ImageIcon icon;

	public Login() {
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 登入");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 300);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel("登入");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(400, 0, 400, 100);
		frame.add(label);

		label = new JLabel();
		ImageIcon pic = new ImageIcon(this.getClass().getResource("/LoginPage.png"));
		label.setIcon(pic);
		label.setBounds(0, 0, 400, 300);
		frame.add(label);

		label = new JLabel("帳號: ");
		label.setFont(new Font("標楷體", Font.BOLD, 24));
		label.setBounds(463, 95, 100, 40);
		frame.add(label);

		// TODO
		textField = new JTextField(20);
		textField.setBounds(533, 100, 202, 30);
		frame.add(textField);

		label = new JLabel("密碼: ");
		label.setFont(new Font("標楷體", Font.BOLD, 24));
		label.setBounds(463, 135, 100, 40);
		frame.add(label);

		// TODO
		passwordField = new JPasswordField(20);
		passwordField.setBounds(533, 140, 202, 30);
		frame.add(passwordField);

		button = new JButton("返回");
		button.setFont(new Font("標楷體", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new WelcomePage();
			}
		});
		button.setBounds(456, 200, 86, 27);
		frame.add(button);

		// TODO - setLocation
		// Clear
		button = new JButton("清空");
		button.setFont(new Font("標楷體", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				textField.setText(null);
				passwordField.setText(null);
			}
		});
		button.setBounds(557, 200, 86, 27);
		frame.add(button);

		button = new JButton("登入");
		button.setFont(new Font("標楷體", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (textField.getText().length() != 0 && passwordField.getPassword().length != 0) {
					try {
						String result = AccountAndPasswords.checkUserInfo(textField.getText(),
								passwordField.getPassword());
						if (result.equals("User")) {
							frame.dispose();
							new MemberPage(textField.getText());
						} else if (result.equals("Admin")) {
							frame.dispose();
							new AdminPage(textField.getText());
						} else {
							JOptionPane.showMessageDialog(null, result);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "登入帳戶時發生錯誤, 請聯絡服務人員", "發生錯誤", 0);
					}

				} else {
					JOptionPane.showMessageDialog(null, "請填完所有空格", "發生錯誤", 0);
				}
			}
		});
		button.setBounds(658, 200, 86, 27);
		frame.add(button);
		frame.setVisible(true);
	}
}