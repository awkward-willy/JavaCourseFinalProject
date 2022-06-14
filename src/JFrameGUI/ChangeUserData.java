package JFrameGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Data.AccountAndPasswords;
import Data.Admin;
import Data.Member;

public class ChangeUserData {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private ImageIcon icon;

	public ChangeUserData(Member m) {
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 修改帳戶資料");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(450, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel("修改帳戶資料");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 450, 80);
		frame.add(label);

		// TODO - setLocation
		// Register label
		label = new JLabel("帳戶名:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 70, 140, 50);
		frame.add(label);

		label = new JLabel("帳號:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 120, 140, 50);
		frame.add(label);

		label = new JLabel("原密碼:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 170, 140, 50);
		frame.add(label);

		label = new JLabel("新密碼:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 220, 140, 50);
		frame.add(label);

		JTextField nameTextField = new JTextField(20);
		nameTextField.setText(m.getName());
		nameTextField.setBounds(140, 80, 250, 30);
		frame.add(nameTextField);

		JTextField accountTextField = new JTextField(20);
		accountTextField.setText(m.getAccount());
		accountTextField.setBounds(140, 130, 250, 30);
		frame.add(accountTextField);

		JPasswordField oriPasswordField = new JPasswordField(20);
		oriPasswordField.setBounds(140, 180, 250, 30);
		frame.add(oriPasswordField);

		JPasswordField newPasswordField = new JPasswordField(20);
		newPasswordField.setBounds(140, 230, 250, 30);
		frame.add(newPasswordField);

		button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if ((nameTextField.getText().length() != 0) && (accountTextField.getText().length() != 0)
						&& (oriPasswordField.getPassword().length != 0)
						&& (newPasswordField.getPassword().length != 0)) {
					String result = AccountAndPasswords.changeUserInfo(m.getAccount(), oriPasswordField.getPassword(),
							accountTextField.getText(), newPasswordField.getPassword());
					JOptionPane.showMessageDialog(null, result);
					m.setName(nameTextField.getText());
					m.setAccount(accountTextField.getText());
					if (result.equals("修改成功，請重新登入以套用修改後的資料")) {
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "請填完所有空格", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(175, 300, 100, 35);
		frame.add(button);

		frame.setVisible(true);
	}

	public ChangeUserData(Admin a) {
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 修改帳戶資料");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(450, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel("修改帳戶資料");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 450, 80);
		frame.add(label);

		// TODO - setLocation
		// Register label
		label = new JLabel("帳戶名:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 70, 140, 50);
		frame.add(label);

		label = new JLabel("帳號:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 120, 140, 50);
		frame.add(label);

		label = new JLabel("原密碼:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 170, 140, 50);
		frame.add(label);

		label = new JLabel("新密碼:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 220, 140, 50);
		frame.add(label);

		JTextField nameTextField = new JTextField(20);
		nameTextField.setText(a.getName());
		nameTextField.setBounds(140, 80, 250, 30);
		frame.add(nameTextField);

		JTextField accountTextField = new JTextField(20);
		accountTextField.setText(a.getAccount());
		accountTextField.setBounds(140, 130, 250, 30);
		frame.add(accountTextField);

		JPasswordField oriPasswordField = new JPasswordField(20);
		oriPasswordField.setBounds(140, 180, 250, 30);
		frame.add(oriPasswordField);

		JPasswordField newPasswordField = new JPasswordField(20);
		newPasswordField.setBounds(140, 230, 250, 30);
		frame.add(newPasswordField);

		button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if ((nameTextField.getText().length() != 0) && (accountTextField.getText().length() != 0)
						&& (oriPasswordField.getPassword().length != 0)
						&& (newPasswordField.getPassword().length != 0)) {
					String result = AccountAndPasswords.changeAdminInfo(a.getAccount(), oriPasswordField.getPassword(),
							accountTextField.getText(), newPasswordField.getPassword());
					JOptionPane.showMessageDialog(null, result);
					a.setName(nameTextField.getText());
					a.setAccount(accountTextField.getText());
					if (result.equals("修改成功，請重新登入以套用修改後的資料")) {
						frame.dispose();
					}
				} else {
					JOptionPane.showMessageDialog(null, "請填完所有空格", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(175, 300, 100, 35);
		frame.add(button);

		frame.setVisible(true);
	}

}
