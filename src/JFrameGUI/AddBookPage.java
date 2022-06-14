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

public class AddBookPage {
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private Book book;
	private ImageIcon icon;

	public AddBookPage() {
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 新增書籍");
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

		// Register label
		label = new JLabel("書 名 :");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 70, 140, 50);
		frame.add(label);

		// Register label
		label = new JLabel("作 者 :");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 120, 140, 50);
		frame.add(label);

		// Register label
		label = new JLabel("出版商:");
		label.setFont(new Font("標楷體", Font.BOLD, 22));
		label.setBounds(35, 170, 140, 50);
		frame.add(label);

		// Book name
		JTextField nameTextField = new JTextField(20);
		nameTextField.setBounds(140, 80, 250, 30);
		frame.add(nameTextField);

		// Book name
		JTextField narratorTextField = new JTextField(20);
		narratorTextField.setBounds(140, 130, 250, 30);
		frame.add(narratorTextField);

		// Book name
		JTextField publisherTextField = new JTextField(20);
		publisherTextField.setBounds(140, 180, 250, 30);
		frame.add(publisherTextField);

		// Back
		button = new JButton("新增");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				BookList.bookList.add(book = new Book(nameTextField.getText(), narratorTextField.getText(),
						publisherTextField.getText()));
				AdminPage.addBook(book);
				frame.dispose();
			}
		});
		button.setFont(new Font("標楷體", Font.BOLD, 22));
		button.setBounds(175, 220, 100, 35);
		frame.add(button);

		frame.setVisible(true);
	}

}
