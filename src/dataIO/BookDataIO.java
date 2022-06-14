package dataIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import Data.Book;
import Data.BookList;

public class BookDataIO {

	public static void WriteCSV() {
		try {
			String path1 = "BookData";
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String fileName = dateFormat.format(date);
			String path2 = path1 + "\\" + fileName + ".csv";

			// Folder
			File file1 = new File(path1);
			if (!file1.isDirectory()) {
				file1.mkdir();
			}

			// CSV
			File csv = new File(path2);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(csv), "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write('\ufeff'); // ByteOrder Mark
			bw.write("書籍資料");
			bw.newLine();
			bw.write("書名,作者,出版商,狀態");
			bw.newLine();
			for (Book book : BookList.bookList) {
				bw.write(
						book.getName() + "," + book.getNarrator() + "," + book.getPublisher() + "," + book.getStatus());
				bw.newLine();
				bw.flush();
			}
			bw.close();
			osw.close();
			JOptionPane.showMessageDialog(null, "匯出成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "匯出失敗，請確認資料是否正常，或洽程式設計人員", "發生錯誤", 0);
		}
	}

	public static void ReadCSV(String path) throws Exception {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
			reader.skip(1); // skip ByteOrder Mark
			reader.readLine();
			reader.readLine(); // skip header
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String bookName = item[0].trim();
				String narrator = item[1].trim();
				String publisher = item[2].trim();
				String status = item[3].trim();
				Book tempBook = new Book(bookName, narrator, publisher);
				tempBook.setStatus(status);
				BookList.bookList.add(tempBook);
			}
			reader.close();
			JOptionPane.showMessageDialog(null, "匯入成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "匯入失敗，請確認檔案是否正常，或洽程式設計人員", "發生錯誤", 0);
		}
	}

}
