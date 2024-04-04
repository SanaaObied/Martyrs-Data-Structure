package application;

//Enum representing the marital status options
public enum MaritalStatus {
	// Enumeration constants with corresponding status values
	SINGLE("Single"), MARRIED("Married"), DIVORCED("Divorced"), WIDOWED("Widowed");

	private final String status;

	// Constructor to initialize the status value for each enum constant
	private MaritalStatus(String status) {
		this.status = status;
	}

}
