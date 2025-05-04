package com.kpit.automation.utilities;

//Enum class
public enum PageOptions {
	OPTION1("5"),
	OPTION2("12"),
	OPTION3("20");

	private final String option;

	PageOptions(String option) {
		this.option = option;
	}

	public String getOption() {
		return option;
	}
}

