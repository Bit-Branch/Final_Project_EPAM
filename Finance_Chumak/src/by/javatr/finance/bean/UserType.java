package by.javatr.finance.bean;

public enum UserType {

	USER("U"),
	ADMIN("A");

	private String shortName;

	private UserType(String shortName) {
		this.shortName = shortName;
	}

	public static UserType getTypeByShortName(String shortName) {
		switch (shortName) {
		case "C":
			return UserType.USER;
		case "A":
			return UserType.ADMIN;
		default:
			throw new IllegalArgumentException();
		}
	}

	public String getShortName() {
		return this.shortName;
	}
}
