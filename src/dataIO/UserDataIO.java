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

import Data.AccountAndPasswords;
import Data.Admin;
import Data.Member;
import Data.Staff;
import Data.Student;
import Data.Teacher;
import Data.User;

public class UserDataIO {

	public static void WriteAdminCSV() {
		try {
			String path = "UserData";
			String path1 = "UserData\\admin";
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String fileName = dateFormat.format(date);
			String path2 = path1 + "\\" + fileName + ".csv";

			// Folder
			File file = new File(path);
			if (!file.isDirectory()) {
				file.mkdir();
			}
			File file1 = new File(path1);
			if (!file1.isDirectory()) {
				file1.mkdir();
			}

			// CSV
			File csv = new File(path2);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(csv), "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write('\ufeff'); // ByteOrder Mark
			bw.write("管理員資料");
			bw.newLine();
			bw.write("用戶名,帳號,密碼");
			bw.newLine();
			for (Admin admin : User.admin) {
				bw.write(admin.getName() + "," + admin.getAccount() + ","
						+ AccountAndPasswords.getAdminInfo(admin.getAccount()));
				bw.newLine();
				bw.flush();
			}
			bw.close();
			osw.close();
			JOptionPane.showMessageDialog(null, "匯出成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "匯出失敗，請確認檔案是否正常，或洽程式設計人員", "發生錯誤", 0);
		}
	}

	public static void WriteMemberCSV() {
		try {
			String path = "UserData";
			String path1 = "UserData\\Member";
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String fileName = dateFormat.format(date);
			String path2 = path1 + "\\" + fileName + ".csv";

			// Folder
			File file = new File(path);
			if (!file.isDirectory()) {
				file.mkdir();
			}
			File file1 = new File(path1);
			if (!file1.isDirectory()) {
				file1.mkdir();
			}

			// CSV
			File csv = new File(path2);
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(csv), "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write('\ufeff'); // ByteOrder Mark
			bw.write("用戶資料");
			bw.newLine();
			bw.write("用戶名,身分,帳號,密碼");
			bw.newLine();
			for (Member member : User.member) {
				bw.write(member.getName() + "," + member.getIdentity() + "," + member.getAccount() + ","
						+ AccountAndPasswords.getMemberInfo(member.getAccount()));
				bw.newLine();
				bw.flush();
			}
			bw.close();
			osw.close();
			JOptionPane.showMessageDialog(null, "匯出成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "匯出失敗，請確認檔案是否正常，或洽程式設計人員", "發生錯誤", 0);
		}
	}

	public static void ReadAdminCSV(String path) throws Exception {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
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
			JOptionPane.showMessageDialog(null, "匯入成功");
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "匯入失敗，請確認檔案是否正常，或洽程式設計人員", "發生錯誤", 0);
		}
	}

	public static void ReadMemberCSV(String path) throws Exception {
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
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
			JOptionPane.showMessageDialog(null, "匯入成功");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "匯入失敗，請確認檔案是否正常，或洽程式設計人員", "發生錯誤", 0);
		}
	}
}
