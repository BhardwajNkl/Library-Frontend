package libraryfrontend.models;

public class Book {
	private int bookId;
	private String bookCode;
	private String bookName;
	private String dateAdded;
	private Author author;
	public Book() {
		super();
	}
	public Book(int bookId, String bookCode, String bookName, String dateAdded, Author author) {
		super();
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.dateAdded = dateAdded;
		this.author = author;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	

}
