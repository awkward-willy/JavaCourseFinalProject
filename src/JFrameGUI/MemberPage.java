package JFrameGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Data.Book;
import Data.BookList;
import Data.Member;
import Data.Staff;
import Data.Student;
import Data.Teacher;
import Data.User;

public class MemberPage {

	private Member member;
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private ImageIcon icon;
	private JTextField textField;
	private JTable table;
	private String[] columnNames = { "書名", "作者", "出版商", "狀態", "到期日" };
	private JScrollPane scrollPane;
	private static DefaultTableModel tablemodel;

	@SuppressWarnings("serial")
	public MemberPage(String account) {
		for (Member m : User.member) {
			if (m.getAccount().equals(account)) {
				if (m instanceof Student) {
					member = (Student) m;
				} else if (m instanceof Teacher) {
					member = (Teacher) m;
				} else if (m instanceof Staff) {
					member = (Staff) m;
				}
			}
		}
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 使用者");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// Register label
		label = new JLabel("使用者");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 267, 100);
		frame.add(label);

		// Name label
		label = new JLabel("用戶名: " + member.getName());
		label.setToolTipText("用戶名: " + member.getName());
		label.setFont(new Font("標楷體", Font.PLAIN, 22));
		label.setBounds(50, 100, 200, 50);
		frame.add(label);

		// Account label
		label = new JLabel("帳　號: " + member.getAccount());
		label.setToolTipText("帳　號: " + member.getAccount());
		label.setFont(new Font("標楷體", Font.PLAIN, 22));
		label.setBounds(50, 153, 200, 50);
		frame.add(label);

		// Search
		textField = new JTextField("請輸入關鍵字（書名、作者、出版社）");
		textField.setBounds(280, 20, 402, 26);
		frame.add(textField);

		button = new JButton("查詢");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				ArrayList<Integer> tempBookIndex = new ArrayList<Integer>();
				for (Book book : BookList.bookList) {
					if (book.getName().contains(textField.getText())) {
						tempBookIndex.add(BookList.bookList.indexOf(book));
					} else if (book.getNarrator().contains(textField.getText())) {
						tempBookIndex.add(BookList.bookList.indexOf(book));
					} else if (book.getPublisher().contains(textField.getText())) {
						tempBookIndex.add(BookList.bookList.indexOf(book));
					}
				}
				if (tempBookIndex.size() == 0) {
					JOptionPane.showMessageDialog(null, "抱歉，查無相關內容");
				}
				table.clearSelection();
				for (Integer index : tempBookIndex) {
					table.addRowSelectionInterval(index, index);
				}
			}
		});
		button.setBounds(680, 20, 70, 25);
		frame.add(button);

		button = new JButton("修改帳戶資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new ChangeUserData(member);
			}
		});
		button.setBounds(60, 238, 149, 36);
		frame.add(button);

		button = new JButton("登出");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new WelcomePage();
			}
		});
		button.setBounds(89, 294, 89, 36);
		frame.add(button);

		button = new JButton("借閱紀錄");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new Record(member);
			}
		});
		button.setBounds(380, 310, 100, 30);
		frame.add(button);

		button = new JButton("借閱");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (member.getLentBookAmount() == member.getMaxBookAmount()) {
					JOptionPane.showMessageDialog(null, "已達借閱數量上限 " + member.getMaxBookAmount() + " 本", "錯誤", 0);
				} else {
					if (table.getSelectedRow() != -1) {
						if (BookList.bookList.get(table.getSelectedRow()).getStatus().equals("架上")) {
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
							LocalDateTime now = LocalDateTime.now();
							Book book = BookList.bookList.get(table.getSelectedRow());
							Book tempBook = new Book(book.getName(), book.getNarrator(), book.getPublisher());
							book.setStatus("借出");
							member.lendBook(tempBook);
							tempBook.setDate(dtf.format(now.plusDays(member.getLendDay())));
							book.setDate(dtf.format(now.plusDays(member.getLendDay())));
							table.getModel().setValueAt("借出", table.getSelectedRow(), 3);
							table.getModel().setValueAt(dtf.format(now.plusDays(member.getLendDay())),
									table.getSelectedRow(), 4);
						} else {
							JOptionPane.showMessageDialog(null, "此書已被借閱", "錯誤", 0);
						}
					} else {
						JOptionPane.showMessageDialog(null, "請選擇欲借閱之書籍", "錯誤", 0);
					}
				}
			}
		});
		button.setBounds(500, 310, 100, 30);
		frame.add(button);

		button = new JButton("還書");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (table.getSelectedRow() != -1) {
					Book book = BookList.bookList.get(table.getSelectedRow());
					boolean findInMember = false;
					for (Book tempBook : member.getLentBook()) {
						if (tempBook.getName().equals(book.getName()) && tempBook.getReturnDate().equals("")) {
							member.returnBook(tempBook);
							book.setStatus("架上");
							book.setDate("");
							table.getModel().setValueAt("架上", table.getSelectedRow(), 3);
							table.getModel().setValueAt("", table.getSelectedRow(), 4);
							findInMember = true;
							break;
						}
					}
					if (findInMember == false) {
						JOptionPane.showMessageDialog(null, "此書籍非您所借，無法歸還", "錯誤", 0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "請選擇欲歸還之書籍", "錯誤", 0);
				}
			}
		});
		button.setBounds(620, 310, 100, 30);
		frame.add(button);

		JPanel panel = new JPanel();
		panel.setBounds(237, 50, 560, 250);
		frame.add(panel);

		tablemodel = new DefaultTableModel(updateBooklist(), columnNames);
		table = new JTable(tablemodel) {
			public String getToolTipText(MouseEvent e) {
				int row = rowAtPoint(e.getPoint());
				String value = (String) "<html>書名: " + getValueAt(row, 0) + "<br>作者: " + getValueAt(row, 1)
						+ "<br>出版商: " + getValueAt(row, 2) + "<br>狀態: " + getValueAt(row, 3) + "<br>到期日: "
						+ getValueAt(row, 4) + "</html>";
				return value;
			}
		};
		table.setDefaultEditor(Object.class, null);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(450, 220));
		table.setFont(new Font("標楷體", Font.PLAIN, 15));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		frame.setVisible(true);

	}

	private Object[][] updateBooklist() {
		Object[][] newBookList = new Object[BookList.bookList.size()][5];
		for (Book book : BookList.bookList) {
			newBookList[BookList.bookList.indexOf(book)][0] = book.getName();
			newBookList[BookList.bookList.indexOf(book)][1] = book.getNarrator();
			newBookList[BookList.bookList.indexOf(book)][2] = book.getPublisher();
			newBookList[BookList.bookList.indexOf(book)][3] = book.getStatus();
			newBookList[BookList.bookList.indexOf(book)][4] = book.getDate();
		}
		return newBookList;
	}

}
