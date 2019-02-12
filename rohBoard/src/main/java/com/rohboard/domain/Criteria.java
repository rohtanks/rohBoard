package com.rohboard.domain;

// 검색 기준 클래스
public class Criteria {

	private int page;
	private int perPageNum;
	
	public Criteria() {
		page = 1;
		perPageNum = 10;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	// method for MyBatis SQL Mapper
	// limit 구문의 시작 위치
	// 시작 데이터 번호 = (페이지 번호 - 1) * 페이지 당 보여지는 갯수
	public int getPageStart() {
		return (page - 1) * perPageNum;
	}
	
	// method for MyBatis SQL Mapper
	// limit 구문의 보여지는 갯수
	public int getPerPageNum() {
		return perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
}
