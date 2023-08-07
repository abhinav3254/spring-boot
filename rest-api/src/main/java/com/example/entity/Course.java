package com.example.entity;

public class Course {
	private Long id;
	private String bookName;
	private String bookprice;
	public Course(Long id, String bookName, String bookprice) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookprice = bookprice;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookprice() {
		return bookprice;
	}
	public void setBookprice(String bookprice) {
		this.bookprice = bookprice;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", bookName=" + bookName + ", bookprice=" + bookprice + "]";
	}
}
