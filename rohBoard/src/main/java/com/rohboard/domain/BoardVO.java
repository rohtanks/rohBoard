package com.rohboard.domain;

import java.util.Date;

public class BoardVO {

	// DB에서 자료형이 정수형이지만 null값이 필요한 경우
	// Wrapper 클래스(Integer) 로 사용할 수 있다
	private Integer bno;

	private int category;
	private String title;
	private String content;
	private String writer;
	private String photo;
	private String secret;
	private Date regdate;
	private Date upddate;
	private int viewcnt;
	private int likecnt;

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpddate() {
		return upddate;
	}

	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", writer=" + writer + ", photo=" + photo + ", secret=" + secret + ", regdate=" + regdate
				+ ", upddate=" + upddate + ", viewcnt=" + viewcnt + ", likecnt=" + likecnt + "]";
	}

}
