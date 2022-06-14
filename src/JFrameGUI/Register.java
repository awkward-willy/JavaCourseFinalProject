package JFrameGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Data.Staff;
import Data.Student;
import Data.Teacher;
import Data.User;
import Data.AccountAndPasswords;
import Data.Admin;

public class Register {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private JPasswordField passwordField;
	private ImageIcon icon;
	private JComboBox<String> comboBox;

	public Register() {
		// frame basic info
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 註冊");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel("註冊");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 430, 80);
		frame.add(label);

		label = new JLabel();
		icon = new ImageIcon(this.getClass().getResource("/RegisterPage.png"));
		label.setIcon(icon);
		label.setBounds(0, 70, 430, 292);
		frame.add(label);

		label = new JLabel("用戶名: ");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(463, 11, 100, 50);
		frame.add(label);

		label = new JLabel("帳 號 : ");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(463, 88, 100, 50);
		frame.add(label);

		label = new JLabel("密 碼 : ");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(463, 165, 100, 50);
		frame.add(label);

		label = new JLabel("身分別: ");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(463, 242, 100, 50);
		frame.add(label);

		// Name
		JTextField nameTextField = new JTextField(20);
		nameTextField.setFont(new Font("標楷體", Font.PLAIN, 18));
		nameTextField.setBounds(560, 21, 202, 30);
		frame.add(nameTextField);

		// Account
		JTextField accountTextField = new JTextField(20);
		accountTextField.setFont(new Font("標楷體", Font.PLAIN, 18));
		accountTextField.setBounds(560, 98, 202, 30);
		frame.add(accountTextField);

		// Password
		passwordField = new JPasswordField(20);
		passwordField.setFont(new Font("標楷體", Font.PLAIN, 18));
		passwordField.setBounds(560, 175, 202, 30);
		frame.add(passwordField);

		// Identity
		comboBox = new JComboBox<String>();
		comboBox.addItem("");
		comboBox.addItem("管理員");
		comboBox.addItem("學生");
		comboBox.addItem("教師");
		comboBox.addItem("職員");
		comboBox.setFont(new Font("標楷體", Font.BOLD, 18));
		comboBox.setBounds(560, 252, 202, 30);
		frame.add(comboBox);

		// Back
		button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new WelcomePage();
			}
		});
		button.setBounds(464, 305, 86, 27);
		button.setFont(new Font("標楷體", Font.PLAIN, 18));
		frame.add(button);

		// Clear
		button = new JButton("清空");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				nameTextField.setText(null);
				accountTextField.setText(null);
				passwordField.setText(null);
				comboBox.setSelectedItem(null);
			}
		});
		button.setBounds(565, 305, 86, 27);
		button.setFont(new Font("標楷體", Font.PLAIN, 18));
		frame.add(button);

		// Register
		button = new JButton("註冊");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if ((nameTextField.getText().length() != 0) && (accountTextField.getText().length() != 0)
						&& (passwordField.getPassword().length != 0) && comboBox.getSelectedIndex() != 0) {
					try {
						if (AccountAndPasswords.checkUserExist(accountTextField.getText()) == 0) {
							if (comboBox.getSelectedIndex() == 1) {
								User.addAdmin(new Admin(nameTextField.getText(), accountTextField.getText()));
								AccountAndPasswords.addAdminInfo(accountTextField.getText(),
										passwordField.getPassword());
							} else if (comboBox.getSelectedIndex() == 2) {
								User.addMember(new Student(nameTextField.getText(), accountTextField.getText(), "學生"));
								AccountAndPasswords.addUserInfo(accountTextField.getText(),
										passwordField.getPassword());
							} else if (comboBox.getSelectedIndex() == 3) {
								User.addMember(new Teacher(nameTextField.getText(), accountTextField.getText(), "教師"));
								AccountAndPasswords.addUserInfo(accountTextField.getText(),
										passwordField.getPassword());
							} else {
								User.addMember(new Staff(nameTextField.getText(), accountTextField.getText(), "職員"));
								AccountAndPasswords.addUserInfo(accountTextField.getText(),
										passwordField.getPassword());
							}
							JOptionPane.showMessageDialog(null, nameTextField.getText() + ", 歡迎!\n" + "您的帳號: "
									+ accountTextField.getText() + "\n您的身分別: " + comboBox.getSelectedItem(), "創建成功", 1);
							frame.dispose();
							new WelcomePage();
						} else {
							JOptionPane.showMessageDialog(null, "此帳號已有人使用，請更換帳號", "發生錯誤", 0);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "創建帳戶時發生錯誤, 請聯絡服務人員", "發生錯誤", 0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "請填完所有空格", "發生錯誤", 0);
				}
			}
		});
		button.setBounds(666, 305, 86, 27);
		button.setFont(new Font("標楷體", Font.PLAIN, 18));
		frame.add(button);

		frame.setVisible(true);
	}
}
