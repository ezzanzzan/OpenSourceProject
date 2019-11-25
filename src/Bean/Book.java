package Bean;

import java.util.ArrayList;

public class Book {
	private String title;		// 제목
	private String writer;		// 저자
	private String publisher;	// 출판사
	private String code;		// 코드
	private String forrent;		// 대여 상태
	
	public static ArrayList<Book> list = new ArrayList<Book>();
	
	public Book (String title, String writer, String publisher, String code, String forrent)  
	{
		this.title = title;			
		this.writer = writer;		
		this.publisher = publisher;	
		this.code = code;			
		this.forrent = forrent;		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForrent() {
		return forrent;
	}

	public void setForrent(String forrent) {
		this.forrent = forrent;
	}



}
