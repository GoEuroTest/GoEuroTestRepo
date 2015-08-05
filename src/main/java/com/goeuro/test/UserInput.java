package com.goeuro.test;

public class UserInput {

	private final String searchText;

	public UserInput(String searchText) {
		super();
		this.searchText = searchText;
	}

	public String getSearchText() {
		return searchText;
	}

	@Override
	public String toString() {
		return "UserInput [searchText=" + searchText + "]";
	}
}
