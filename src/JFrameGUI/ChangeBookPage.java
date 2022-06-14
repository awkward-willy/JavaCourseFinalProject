package JFrameGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Data.Book;
import Data.BookList;

public class ChangeBookPage {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private ImageIcon icon;

	public ChangeBookPage(int index, Book b) {
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 修改書籍");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(450, 300);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		label = new JLabel("新增書籍");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 450, 80);
		frame.add(label);

		label = new JLabel("書 名 :");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 70, 140, 50);
		frame.add(label);

		label = new JLabel("作 者 :");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 120, 140, 50);
		frame.add(label);

		label = new JLabel("出版商:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 170, 140, 50);
		frame.add(label);

		JTextField nameTextField = new JTextField(20);
		nameTextField.setText(b.getName());
		nameTextField.setBounds(140, 80, 250, 30);
		frame.add(nameTextField);

		JTextField narratorTextField = new JTextField(20);
		narratorTextField.setText(b.getNarrator());
		narratorTextField.setBounds(140, 130, 250, 30);
		frame.add(narratorTextField);

		JTextField publisherTextField = new JTextField(20);
		publisherTextField.setText(b.getPublisher());
		publisherTextField.setBounds(140, 180, 250, 30);
		frame.add(publisherTextField);

		button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				b.setName(nameTextField.getText());
				b.setNarrator(narratorTextField.getText());
				b.setPublisher(publisherTextField.getText());
				BookList.bookList.set(index, b);
				AdminPage.changeBook(index, b);
				frame.dispose();
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(175, 220, 100, 35);
		frame.add(button);

		frame.setVisible(true);
	}
}
