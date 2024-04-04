package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import application.SingleLLSort.Node;

public class Martyr implements Comparable<Martyr> {
	private String name;
	private int age;
	private LocalDate dateOfDeath;
	private Gender gender;
	private MaritalStatus maritalStatus;
	StringBuilder sb = new StringBuilder();

	// Constructor to initialize a Martyr object
	public Martyr(String name, int age, LocalDate dateOfDeath, Gender gender, MaritalStatus maritalStatus,
			String location) {
		super();

		Main.locationsList.searchLocationNode3(location).getLocation().incrementCount();
		// Increment the count and singles count for the corresponding location
		if (maritalStatus.equals(MaritalStatus.SINGLE)) {
			Main.locationsList.searchLocationNode3(location).getLocation().incrementSingles();

		}
		Main.locationsList.searchLocationNode3(location).getLocation().incrementCount();
		if (gender.equals(Gender.MALE)) {
			Main.locationsList.searchLocationNode3(location).getLocation().incrementMale();

		}

		Main.locationsList.searchLocationNode3(location).getLocation().incrementCount();
		if (gender.equals(Gender.FEMALE)) {
			Main.locationsList.searchLocationNode3(location).getLocation().incrementFmale();
			;
			;

		}

		Location martyrLocation = Main.locationsList.searchLocationNode3(location).getLocation();
		updateAgeCounts(martyrLocation);
		SingleLLSort allMartyrs = Main.locationsList.getFirst().getLocation().getSt();
		double averageAge = getAverageAge(allMartyrs);
		LocalDate dateWithMaxMartyrs = getDateWithMaxMartyrs(allMartyrs);

		this.name = name;
		this.age = age;
		this.dateOfDeath = dateOfDeath;
		this.gender = gender;
		this.maritalStatus = maritalStatus;

	}

	public void updateAgeCounts(Location location) {
		if (age > 40) {
			location.incrementAbove40();
		}
		if (age >= 30 && age <= 70) {
			location.incrementAge30to70();
		}
		if (age < 50) {
			location.incrementBelow50();
		}
	}

	// Getter and setter methods
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	// Implementing the Comparable interface to compare Martyr objects based on
	// dateOfDeath
	@Override
	public int compareTo(Martyr o) {
		return this.dateOfDeath.compareTo(o.dateOfDeath);
	}

	@Override
	public String toString() {
		return name + "," + age + "," + dateOfDeath + "," + gender + "," + maritalStatus;
	}

	// Check if two Martyr objects are equal based on dateOfDeath
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Martyr)) {
			return false;
		}
		Martyr otherMartyr = (Martyr) other;
		return this.dateOfDeath.equals(otherMartyr.dateOfDeath);
	} // Additional helper methods

	public boolean isMale() {
		return gender == Gender.MALE;
	}

	public boolean isFemale() {
		return gender == Gender.FEMALE;
	}

	public boolean isBefore(LocalDate date) {
		return dateOfDeath.isBefore(date);
	}

	public boolean isAfter(LocalDate date) {
		return dateOfDeath.isAfter(date);
	}

	public boolean isOn(LocalDate date) {
		return dateOfDeath.isEqual(date);
	}

	public int getDaysSinceDeath() {
		return Period.between(dateOfDeath, LocalDate.now()).getDays();
	}

	public int getYearsSinceDeath() {
		return Period.between(dateOfDeath, LocalDate.now()).getYears();
	}

	public Martyr get(int i) {
		if (i < 0 || i >= Main.locationsList.size()) {
			throw new IndexOutOfBoundsException();
		}
		return Main.locationsList.getMartyr(i);
	}

	public static double getAverageAge(SingleLLSort martyrs) {
		Node current = martyrs.getFirst();
		while (current != null) {
			Location.totalAge += current.getMartyr().getAge();
			Location.getNumMartyrsByAge++;
			current = current.getNext();
		}
		if (Location.getNumMartyrsByAge > 0) {
			return (double) Location.totalAge / Location.getNumMartyrsByAge;
		} else {
			return 0;
		}
	}

	public static LocalDate getDateWithMaxMartyrs(SingleLLSort martyrs) {
		Map<LocalDate, Integer> dateCountMap = new HashMap<>();
		Node current = martyrs.getFirst();
		while (current != null) {
			LocalDate date = current.getMartyr().getDateOfDeath();
			dateCountMap.put(date, dateCountMap.getOrDefault(date, 0) + 1);
			current = current.getNext();
		}

		int maxCount = 0;
		LocalDate dateWithMaxMartyrs = null;
		for (Map.Entry<LocalDate, Integer> entry : dateCountMap.entrySet()) {
			LocalDate date = entry.getKey();
			int count = entry.getValue();
			if (count > maxCount) {
				maxCount = count;
				dateWithMaxMartyrs = date;
			}
		}
		return dateWithMaxMartyrs;
	}
}
