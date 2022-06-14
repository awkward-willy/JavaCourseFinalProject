package JFrameGUI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import Data.AccountAndPasswords;
import Data.Admin;
import Data.Book;
import Data.BookList;
import Data.Staff;
import Data.Student;
import Data.Teacher;
import Data.User;

public class Launcher {
	public static void main(String[] args) {
		// try to load initial value
		try {
			InputStream is = Launcher.class.getResourceAsStream("/BookDemo.csv");
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
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
			is = Launcher.class.getResourceAsStream("/MemberDemo.csv");
			isr = new InputStreamReader(is, "UTF-8");
			reader = new BufferedReader(isr);
			line = null;
			reader.skip(1); // skip ByteOrder Mark
			reader.readLine();
			reader.readLine(); // skip header
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String name = item[0].trim();
				String identity = item[1].trim();
				String account = item[2].trim();
				String password = item[3].trim();
				AccountAndPasswords.addUserInfo(account, password);
				if (identity.equals("學生")) {
					User.member.add(new Student(name, account, identity));
				} else if (identity.equals("職員")) {
					User.member.add(new Staff(name, account, identity));
				} else {
					User.member.add(new Teacher(name, account, identity));
				}
			}
			reader.close();
			is = Launcher.class.getResourceAsStream("/AdminDemo.csv");
			isr = new InputStreamReader(is, "UTF-8");
			reader = new BufferedReader(isr);
			line = null;
			reader.skip(1); // skip ByteOrder Mark
			reader.readLine();
			reader.readLine(); // skip header
			while ((line = reader.readLine()) != null) {
				String item[] = line.split(",");
				String name = item[0].trim();
				String account = item[1].trim();
				String password = item[2].trim();
				AccountAndPasswords.addAdminInfo(account, password);
				User.admin.add(new Admin(name, account));
			}
			reader.close();
		} catch (Exception e) {
		}
		new WelcomePage();
	}
}