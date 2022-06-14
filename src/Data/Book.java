package Data;

public class Book {
	private String name;
	private String narrator;
	private String publisher;
	private String status;
	private String date;
	private String returnDate;
	private int fine;

	public Book(String name, String narrator, String publisher) {
		setName(name);
		setNarrator(narrator);
		setPublisher(publisher);
		setStatus("\u67b6\u4e0a");
		setDate("");
		setReturnDate("");
		setFine(0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setNarrator(String narrator) {
		this.narrator = narrator;
	}

	public String getNarrator() {
		return narrator;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setReturnDate(String date) {
		this.returnDate = date;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public int getFine() {
		return fine;
	}

}
