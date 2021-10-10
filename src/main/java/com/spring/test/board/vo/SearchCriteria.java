package com.spring.test.board.vo;

public class SearchCriteria extends Criteria{
	private String search;
	private String optionContent;
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
}
