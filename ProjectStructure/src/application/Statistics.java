package application;


public class Statistics {
	private String Location2 = ""; // Name of the location
	private int numbersOfMartyrsByAge = 0; // Number of martyrs by age
	private int numberOfMartyrsByGender = 0; // Number of martyrs by gender
	private double averageAgeOfMartyrs = 0.0; // Average age of martyrs
	private String dateThatHasTheMaximumNumberOfMartyrs = ""; // Date with the maximum number of martyrs

	public Statistics(Location locat) {
		super();
		this.numbersOfMartyrsByAge = locat.getNumMartyrsByAge(0);
		//this.numberOfMartyrsByGender = locat.getNumMartyrsByGender(null);
		//this.averageAgeOfMartyrs = locat.getAverageAgeOfMartyrs();
		this.dateThatHasTheMaximumNumberOfMartyrs = locat.getDateWithMaxMartyrs();
		this.Location2=locat.getName();
	}
	public String getLocation2() {
		return Location2;
	}
	public int getNumbersOfMartyrsByAge() {
		return numbersOfMartyrsByAge;
	}
	public int getNumberOfMartyrsByGender() {
		return numberOfMartyrsByGender;
	}
	public double getAverageAgeOfMartyrs() {
		return averageAgeOfMartyrs;
	}
	public String getDateThatHasTheMaximumNumberOfMartyrs() {
		return dateThatHasTheMaximumNumberOfMartyrs;
	}
	
		
	   

		
	   
}
