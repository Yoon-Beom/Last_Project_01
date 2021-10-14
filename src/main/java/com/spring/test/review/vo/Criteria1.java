package com.spring.test.review.vo;

import org.springframework.stereotype.Component;


@Component("criteria1")
public class Criteria1 {		
		private int page;
		private int perPageNum;
		private int rowStart;
		private int rowEnd;
		private String board_code;
		private int shop_NO;
		private int member_NO;
		
		public int getMember_NO() {
			return member_NO;
		}

		public void setMember_NO(int member_NO) {
			this.member_NO = member_NO;
		}
		
		public int getShop_NO() {
			return shop_NO;
		}

		public void setShop_NO(int shop_NO) {
			this.shop_NO = shop_NO;
		}

		public String getBoard_code() {
			return board_code;
		}

		public void setBoard_code(String board_code) {
			this.board_code = board_code;
		}

		public Criteria1() {
			this.page = 1;
			this.perPageNum = 10;
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
		
		public int getPageStart() {
			return (this.page - 1) * perPageNum;
		}
		
		public int getPerPageNum() {
			return this.perPageNum;
		}
		
		public int getRowStart() {
			rowStart = ((page - 1) * perPageNum) + 1;
			return rowStart;
		}
		
		public int getRowEnd() {
			rowEnd = rowStart + perPageNum - 1;
			return rowEnd;
		}
		
	
		@Override
		public String toString() {
			return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart + ", rowEnd=" + rowEnd
					+ "]";
		}
		
	}

