package com.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Course {
	@Id
	@Column(name="p_id")
	private long id;
	@Column(name="p_title")
	private String title;
	@Column(name="p_isbn")
	private long isbn;
	@Column(name="p_count")
	private long pageCount;
	@Column(name="p_desc")
	private String shortDescription;
	@Column(name="p_status")
	private String status;
	
	public Course() {
		super();
	}
	public Course(long id, String title, long isbn, long pageCount, String shortDescription, String status) {
		super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.pageCount = pageCount;
		this.shortDescription = shortDescription;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", isbn=" + isbn + ", pageCount=" + pageCount
				+ ", shortDescription=" + shortDescription + ", status=" + status + "]";
	}
}
