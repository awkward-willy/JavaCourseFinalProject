package JFrameGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Data.Book;
import Data.Member;

public class Record {
	private Member member;
	private JFrame frame;
	private ImageIcon icon;
	private JPanel panel;
	private JTable table;
	private String[] columnNames = { "書名", "作者", "出版商", "狀態", "到期日", "歸還日", "罰金" };
	private JScrollPane scrollPane;
	private JButton button;
	private static DefaultTableModel tablemodel;

	@SuppressWarnings("serial")
	public Record(Member member) {
		this.member = member;
		frame = new JFrame();
		frame.setTitle("圖書館書籍借還系統 - 借閱紀錄");
		icon = new ImageIcon(this.getClass().getResource("/Icon.png"));
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 350);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		panel = new JPanel();
		panel.setBounds(0, 0, 800, 260);
		frame.add(panel);
		tablemodel = new DefaultTableModel(updateBooklist(), columnNames);
		table = new JTable(tablemodel) {
			public String getToolTipText(MouseEvent e) {
				int row = rowAtPoint(e.getPoint());
				String value = (String) "<html>書名: " + getValueAt(row, 0) + "<br>作者: " + getValueAt(row, 1)
						+ "<br>出版商: " + getValueAt(row, 2) + "<br>狀態: " + getValueAt(row, 3) + "<br>到期日: "
						+ getValueAt(row, 4) + "<br>歸還日: " + getValueAt(row, 5) + "<br>罰金: " + getValueAt(row, 6)
						+ "</html>";
				return value;
			}
		};
		table.setDefaultEditor(Object.class, null);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(730, 230));
		table.setFont(new Font("標楷體", Font.PLAIN, 15));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		button = new JButton("付清罰款");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				int fine = 0;
				int booksUnreturned = 0;
				int unreturnedFine = 0;
				for (Book book : member.getLentBook()) {
					if (book.getStatus().equals("已歸還")) {
						fine += book.getFine();
						book.setFine(0);
					} else {
						booksUnreturned++;
						unreturnedFine += book.getFine();
					}
				}
				JOptionPane.showMessageDialog(null, "共需付款" + fine + "元，請繳納給管理員。" + "\n另有未歸還書" + booksUnreturned
						+ "本\n已累積罰金" + unreturnedFine + "元。（請歸還後再繳費）");
				panel.setVisible(false);
				panel = new JPanel();
				panel.setBounds(0, 0, 800, 260);
				frame.add(panel);
				tablemodel = new DefaultTableModel(updateBooklist(), columnNames);
				table = new JTable(tablemodel) {
					public String getToolTipText(MouseEvent e) {
						int row = rowAtPoint(e.getPoint());
						String value = (String) "<html>書名: " + getValueAt(row, 0) + "<br>作者: " + getValueAt(row, 1)
								+ "<br>出版商: " + getValueAt(row, 2) + "<br>狀態: " + getValueAt(row, 3) + "<br>到期日: "
								+ getValueAt(row, 4) + "<br>歸還日: " + getValueAt(row, 5) + "<br>罰金: "
								+ getValueAt(row, 6) + "</html>";
						return value;
					}
				};
				table.setDefaultEditor(Object.class, null);
				table.setRowSelectionAllowed(true);
				table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				table.setPreferredScrollableViewportSize(new Dimension(730, 230));
				table.setFont(new Font("標楷體", Font.PLAIN, 15));
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				scrollPane = new JScrollPane(table);
				panel.add(scrollPane, BorderLayout.CENTER);
			}
		});
		button.setBounds(650, 270, 100, 30);
		frame.add(button);

		frame.setVisible(true);
	}

	private Object[][] updateBooklist() {
		ArrayList<Book> tempBookList = member.getLentBook();
		Object[][] lentBookList = new Object[member.getLentBook().size()][7];
		long diff = 0;
		int before = 0;
		for (Book book : tempBookList) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date firstDate = sdf.parse(dtf.format(now));
				Date secondDate = sdf.parse(book.getDate());
				before = firstDate.compareTo(secondDate);
				long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
				diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			} catch (ParseException e) {
				diff = 0;
			}
			lentBookList[tempBookList.indexOf(book)][0] = book.getName();
			lentBookList[tempBookList.indexOf(book)][1] = book.getNarrator();
			lentBookList[tempBookList.indexOf(book)][2] = book.getPublisher();
			lentBookList[tempBookList.indexOf(book)][3] = book.getStatus();
			lentBookList[tempBookList.indexOf(book)][4] = book.getDate();
			lentBookList[tempBookList.indexOf(book)][5] = book.getReturnDate();
			if (book.getStatus().equals("借閱中")) {
				if (before > 0) {
					book.setFine((int) diff * 30);
					lentBookList[tempBookList.indexOf(book)][6] = book.getFine();
				} else {
					book.setFine(0);
					lentBookList[tempBookList.indexOf(book)][6] = book.getFine();
				}
			} else {
				lentBookList[tempBookList.indexOf(book)][6] = book.getFine();
			}

		}
		return lentBookList;
	}
}
