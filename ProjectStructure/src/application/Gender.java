package application;

public enum Gender {
	MALE('M'), // Male gender with code 'M'
	FEMALE('F'); // Female gender with code 'F'

	private final char code;

	// Constructor
	Gender(char code) {
		this.code = code;
	}

	// Getter for the gender code
	public char getCode() {
		return code;
	}

	// Static method to get the Gender enum from a given code
	public static Gender fromCode(char code) {
		for (Gender gender : Gender.values()) {
			if (gender.getCode() == code) {
				return gender;
			}
		}
		throw new IllegalArgumentException("Invalid gender code: " + code);
	}

}





