package JFrameGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dataIO.BookDataIO;
import dataIO.UserDataIO;

public class ImportExportChoicePage {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private ImageIcon icon;

	public ImportExportChoicePage() {
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 匯入／匯出");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel("匯入／匯出");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 400, 80);
		frame.add(label);

		button = new JButton("匯入管理員資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					new ImportExportData(1);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "匯入失敗，請洽程式設計人員", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(100, 60, 200, 35);
		frame.add(button);

		button = new JButton("匯入會員資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					new ImportExportData(2);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "匯入失敗，請洽程式設計人員", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(100, 110, 200, 35);
		frame.add(button);

		button = new JButton("匯入書籍資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					new ImportExportData(3);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "匯入失敗，請洽程式設計人員", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(100, 160, 200, 35);
		frame.add(button);

		button = new JButton("匯出管理員資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					UserDataIO.WriteAdminCSV();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "匯出失敗，請洽程式設計人員", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(100, 210, 200, 35);
		frame.add(button);
		frame.setVisible(true);

		button = new JButton("匯出會員資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					UserDataIO.WriteMemberCSV();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "匯出失敗，請洽程式設計人員", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(100, 260, 200, 35);
		frame.add(button);
		frame.setVisible(true);

		button = new JButton("匯出書籍資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					BookDataIO.WriteCSV();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "匯出失敗，請洽程式設計人員", "發生錯誤", 0);
				}
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(100, 310, 200, 35);
		frame.add(button);
		frame.setVisible(true);
	}
}
