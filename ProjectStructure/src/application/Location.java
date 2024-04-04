package application;

import java.awt.TextArea;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Location implements Comparable<Location> {
	private String name; // Name of the location
	private int count = 0, single = 0, male = 0, Fmale = 0;
	private int above40 = 0;
	private int age30to70 = 0;
	 private int below50 = 0;
	private SingleLLSort st; // List of martyrs
	static StringBuilder sb = new StringBuilder(); // StringBuilder for string concatenation
	String dateWithMaxMartyrs = null;
	int maxMartyrsOnDate = 0;
	static int getNumMartyrsByAge=0;
    static  int totalAge = 0;
    private int currentLocationIndex = 0;
    

	Map<String, Integer> dateToMartyrsMap = new HashMap<>(); // Map to store date-to-martyrs count

	// Getter and Setter for SingleLLSort
	public SingleLLSort getSt() {
		return st;
	}public void incremgetNumMartyrsByAge() {
		getNumMartyrsByAge++;
    }

	public int getMaxMartyrsOnDate() {
		return maxMartyrsOnDate;
	}

	public void setMaxMartyrsOnDate(int maxMartyrsOnDate) {
		this.maxMartyrsOnDate = maxMartyrsOnDate;
	}

	public int getGetNumMartyrsByAge() {
		return getNumMartyrsByAge;
	}

	public void setGetNumMartyrsByAge(int getNumMartyrsByAge) {
		this.getNumMartyrsByAge = getNumMartyrsByAge;
	}

	public int getTotalAge() {
		return totalAge;
	}

	public void setTotalAge(int totalAge) {
		this.totalAge = totalAge;
	}

	public void setDateWithMaxMartyrs(String dateWithMaxMartyrs) {
		this.dateWithMaxMartyrs = dateWithMaxMartyrs;
	}

	public void setSt(SingleLLSort st) {
		this.st = st;
	}

	public Location(String name) {
		super();
		this.name = name;
		this.st = new SingleLLSort();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + "," + st;
	}

	@Override
	public int compareTo(Location other) {
		return this.name.compareTo(other.getName());
	}

	// Getter and Setter for count
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// Increment count by 1
	public void incrementCount() {
		count++;
	}// Increment single by 1

	public void incrementSingles() {
		single++;
	}

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}

	public void incrementMale() {
		male++;
	}public void incrementFmale() {
		Fmale++;
	}

	
	
	// Get the number of martyrs with a specific gender
	public void updateAgeCounts() {
        above40 = 0;
        age30to70 = 0;
        below50 = 0;

        for (int i = 0; i < Main.locationsList.getFirst().getLocation().getSt().getCount(); i++) {
            int age = Main.locationsList.getFirst().getLocation().getSt().get(i).getAge();
            if (age > 40) {
                above40++;
            }
            if (age >= 30 && age <= 70) {
                age30to70++;
            }
            if (age < 50) {
                below50++;
            }
        }
    }

	
	

	public int getFmale() {
		return Fmale;
	}

	public void setFmale(int fmale) {
		Fmale = fmale;
	}

	public int getAbove40() {
		return above40;
	}

	public void setAbove40(int above40) {
		this.above40 = above40;
	}

	public int getAge30to70() {
		return age30to70;
	}

	public void setAge30to70(int age30to70) {
		this.age30to70 = age30to70;
	}

	public int getBelow50() {
		return below50;
	}

	public void setBelow50(int below50) {
		this.below50 = below50;
	}
	
	
	

	
	public void setMartyr(Martyr martyr, boolean updateMap) {
		// Sets the martyr and updates the map if required
		if (updateMap) {
			LocalDate date = martyr.getDateOfDeath();
			int numMartyrsOnDate = dateToMartyrsMap.getOrDefault(date.toString(), 0);
			dateToMartyrsMap.put(date.toString(), numMartyrsOnDate + 1);
		}
	}
	// Get the number of martyrs with a specific age
	public int getNumMartyrsByAge(int age) {
		for (int i = 0; i < Main.locationsList.getFirst().getLocation().getSt().getCount(); i++) {
			if (Main.locationsList.getFirst().getLocation().getSt().get(i).getAge() == age) {
				getNumMartyrsByAge++;
			}
		}
		return getNumMartyrsByAge;
	}

	public int getNumMarriedMartyrs() {
		// Returns the number of married martyrs

		for (int i = 0; i < Main.locationsList.getFirst().getLocation().getSt().getCount(); i++) {
			if (Main.locationsList.getFirst().getLocation().getSt().get(i)
					.getMaritalStatus() == MaritalStatus.MARRIED) {
				count++;
			}
		}
		return count;
	}

	public int getNumFemaleMartyrs() {
		// Returns the number of married martyrs
		for (int i = 0; i < Main.locationsList.getFirst().getLocation().getSt().getCount(); i++) {
			if (Main.locationsList.getFirst().getLocation().getSt().get(i).getGender() == Gender.FEMALE) {
				count++;
			}
		}
		return count;
	}void updateTextAreaWithSummaryReport(TextArea textArea) {
	    if (currentLocationIndex >= 0 && currentLocationIndex < Main.locationsList.size()) {
	        currentLocationIndex++; // Move to the next location
	        if (currentLocationIndex < Main.locationsList.size()) {
	            Location currentLocation = Main.locationsList.getLocation(currentLocationIndex);
	            if (currentLocation != null) {
	                String summaryReport = currentLocation.generateSummaryReport2();
	                textArea.setText(summaryReport); // Update the TextArea with the summary report
	            }
	        }
	    }
	}	        
	      
	    
	void navigateToNextLocation(TextArea textArea) {
	    if (currentLocationIndex < Main.locationsList.size() - 1) {
	        currentLocationIndex++;
	        updateTextAreaWithSummaryReport(textArea);
	    }} void navigateToPreviousLocation(TextArea textArea) {
	        if (currentLocationIndex > 0) {
	            currentLocationIndex--;
	            updateTextAreaWithSummaryReport(textArea);
	        }}

	public int getSingle() {
		// Returns the number of single martyrs
		return single;
	}

	public int setSingle(int single) {
		// Sets the number of single martyrs
		return single;
	}

	public void incrementAbove40() {
		above40++;
	}

	public void incrementAge30to70() {
		age30to70++;
	}

	public void incrementBelow50() {
		below50++;
	}

	public List<String> getNameList() {
		List<String> locationNames = new ArrayList<>();
		for (int i = 0; i < Main.locationsList.getFirst().getLocation().getSt().getCount(); i++) {
			String locationName = Main.locationsList.getLocation(i).name;
			if (!locationNames.contains(locationName)) {
				locationNames.add(locationName);
			}
		}
		return locationNames;
	}

	public Martyr getMartyrLocat(int i) {
		// Returns the martyr at the specified index
		if (i < 0 || i >= Main.locationsList.getFirst().getLocation().getSt().getCount()) {
			throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");
		}
		return st.get(i);
	}

	public String generateSummaryReport2() {
		sb.setLength(0); // Clear the StringBuilder
		sb.append("Summary Report for District: ").append(name).append("\n");
		sb.append("=====================================").append("\n");

		// Number of martyrs by age
		sb.append("Number of Martyrs by Age:\n");
		sb.append("Age Above 40 :").append(above40).append("\n");
		sb.append("Age Between 30->70:").append(age30to70).append("\n");
		sb.append("Age Below 50 :").append(below50).append("\n");
	
		
		// Number of martyrs by gender
		sb.append("Number of Martyrs by Gender:\n");
		sb.append("  Male: ").append(getMale()).append("\n");
		sb.append("  Female: ").append(getFmale()).append("\n");

		
		sb.append("Average Age of Martyrs: ").append(Martyr.getAverageAge(st)).append("\n");
         
		sb.append("Number of Martyrs by Marital Status:\n");
		sb.append("  Single: ").append(getSingle()).append("\n");
		sb.append("  Married: ").append(getFmale()).append("\n");
		
		// Date with the maximum number of martyrs
		
		sb.append("Date with the maximum number of martyrs: ").append(Martyr.getDateWithMaxMartyrs(st)).append("\n");

		return sb.toString();
	}

}
