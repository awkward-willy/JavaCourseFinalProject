package Data;

public class Admin {
	private String name;
	private String account;
	private Book book;

	public Admin(String name, String account) {
		setName(name);
		setAccount(account);
	}

	BookList bookList = new BookList();

	public void addBook(Book book) {
		bookList.addBook(book);
	}

	public void deleteBook(Book book) {
		bookList.deleteBook(book);
	}

	public void setName(String a) {
		this.name = a;
	}

	public String getName() {
		return name;
	}

	public void setAccount(String a) {
		this.account = a;
	}

	public String getAccount() {
		return account;
	}

	public void setBook(Book a) {
		this.book = a;
	}

	public Book getBook() {
		return book;
	}
}