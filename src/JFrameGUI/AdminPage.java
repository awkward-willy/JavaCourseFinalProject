package JFrameGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;

import Data.Admin;
import Data.Book;
import Data.BookList;
import Data.User;

public class AdminPage {

	private Admin admin;
	private JFrame frame;
	private JLabel label;
	private JButton button;
	private JTextField textField;
	private JTable table;
	private JPanel panel;
	private String[] columnNames = { "書名", "作者", "出版商", "狀態", "到期日" };
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private static DefaultTableModel tablemodel;

	@SuppressWarnings("serial")

	public AdminPage(String account) {
		for (Admin a : User.admin) {
			if (a.getAccount().equals(account)) {
				admin = a;
			}
		}

		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 管理員");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// Register label
		label = new JLabel("管理員");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("標楷體", Font.BOLD, 40));
		label.setBounds(0, 0, 267, 133);
		frame.add(label);

		// Name label
		label = new JLabel("用戶名: " + admin.getName());
		label.setToolTipText("用戶名: " + admin.getName());
		label.setFont(new Font("標楷體", Font.PLAIN, 22));
		label.setBounds(50, 100, 200, 50);
		frame.add(label);

		// Account label
		label = new JLabel("帳　號: " + admin.getAccount());
		label.setToolTipText("帳　號: " + admin.getAccount());
		label.setFont(new Font("標楷體", Font.PLAIN, 22));
		label.setBounds(50, 153, 200, 50);
		frame.add(label);

		button = new JButton("匯入／匯出");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new ImportExportChoicePage();
			}
		});
		button.setBounds(60, 212, 149, 36);
		frame.add(button);

		button = new JButton("修改帳戶資料");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new ChangeUserData(admin);
			}
		});
		button.setBounds(60, 260, 149, 36);
		frame.add(button);

		button = new JButton("登出");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				frame.dispose();
				new WelcomePage();
			}
		});
		button.setBounds(89, 308, 89, 36);
		frame.add(button);

		button = new JButton("刪除書籍");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "請選擇欲刪除之書籍", "錯誤", 0);
				} else {
					BookList tempBookList = new BookList();
					tempBookList.deleteBookFromIndex(table.getSelectedRow());
					tablemodel.removeRow(table.getSelectedRow());
				}
			}
		});
		button.setBounds(630, 310, 100, 40);
		frame.add(button);

		button = new JButton("修改書籍");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "請選擇欲修改之書籍", "錯誤", 0);
				} else {
					int bookIndex = table.getSelectedRow();
					new ChangeBookPage(bookIndex, BookList.bookList.get(bookIndex));
				}
			}
		});
		button.setBounds(410, 310, 100, 40);
		frame.add(button);

		button = new JButton("新增書籍");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				new AddBookPage();
			}
		});
		button.setBounds(520, 310, 100, 40);
		frame.add(button);

		button = new JButton("重新整理");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				panel.setVisible(false);
				panel = new JPanel();
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
				table.setPreferredScrollableViewportSize(new Dimension(450, 220));
				table.setFont(new Font("標楷體", Font.PLAIN, 15));
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				scrollPane = new JScrollPane(table);
				panel.add(scrollPane, BorderLayout.CENTER);
			}
		});
		button.setBounds(300, 310, 100, 40);
		frame.add(button);

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

		panel = new JPanel();
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

	public static void addBook(Book book) {
		Object[] row = { book.getName(), book.getNarrator(), book.getPublisher(), book.getStatus(), book.getDate() };
		tablemodel.addRow(row);
	}

	public static void changeBook(int index, Book book) {
		tablemodel.setValueAt(book.getName(), index, 0);
		tablemodel.setValueAt(book.getNarrator(), index, 1);
		tablemodel.setValueAt(book.getPublisher(), index, 2);
	}

}
