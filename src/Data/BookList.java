package Data;

import java.util.ArrayList;

public class BookList {
	public static ArrayList<Book> bookList = new ArrayList<Book>();

	public void addBook(Book book) {
		bookList.add(book);
	}

	public void deleteBook(Book book) {
		bookList.remove(book);
	}

	public void deleteBookFromIndex(int index) {
		bookList.remove(index);
	}

}
