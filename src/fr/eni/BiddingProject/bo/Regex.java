package fr.eni.BiddingProject.bo;

public class Regex {

	private final static String REGEX_BASIC = "^[a-zA-Z0-9]+$";
	private final static String REGEX_MAIL = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private final static String REGEX_PWD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private final static String REGEX_BASIC_NO_NUMBERS = "^[a-zA-Z]+$";
	private final static String REGEX_PHONE = "^[0-9]{10}$";

	
	public Regex() {
	}

	public static String getREGEX_BASIC() {
		return REGEX_BASIC;
	}

	public static String getREGEX_MAIL() {
		return REGEX_MAIL;
	}

	public static String getREGEX_PWD() {
		return REGEX_PWD;
	}

	public static String getRegexBasicNoNumbers() {
		return REGEX_BASIC_NO_NUMBERS;
	}

	public static String getRegexPhone() {
		return REGEX_PHONE;
	}

}
