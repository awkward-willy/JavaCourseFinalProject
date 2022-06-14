package Data;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Member {
	private String name;
	private String account;
	private String identity;
	private int fine;
	private ArrayList<Book> lentBook = new ArrayList<Book>();
	private int lentBookAmount;
	private int maxBookAmount;
	private int lendDay;

	public Member(String name, String account, String identity) {
		setName(name);
		setAccount(account);
		setIdentity(identity);
		setLentBookAmount(0);
		setFine(0);
		lentBook = new ArrayList<Book>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount() {
		return account;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	public void setLentBookAmount(int lentBookAmount) {
		this.lentBookAmount = lentBookAmount;
	}

	public int getLentBookAmount() {
		return lentBookAmount;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public int getfine() {
		return fine;
	}

	public void setMaxBookAmount(int maxBookAmount) {
		this.maxBookAmount = maxBookAmount;
	}

	public int getMaxBookAmount() {
		return maxBookAmount;
	}

	public void lendBook(Book book) {
		book.setStatus("\u501f\u95b1\u4e2d"); // 借閱中
		this.lentBookAmount += 1;
		lentBook.add(book);
	}

	public void returnBook(Book book) {
		int before = 0;
		int diff = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date firstDate = sdf.parse(dtf.format(now));
			Date secondDate = sdf.parse(book.getDate());
			before = firstDate.compareTo(secondDate);
			long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			diff = 0;
		}
		if (before > 0) {
			book.setFine((int) diff * 30);
		} else {
			book.setFine(0);
		}
		book.setStatus("已歸還");
		book.setReturnDate(dtf.format(now));
		this.lentBookAmount -= 1;
	}

	public ArrayList<Book> getLentBook() {
		return lentBook;
	}

	public void setLendDay(int lendDay) {
		this.lendDay = lendDay;
	}

	public int getLendDay() {
		return lendDay;
	}
}