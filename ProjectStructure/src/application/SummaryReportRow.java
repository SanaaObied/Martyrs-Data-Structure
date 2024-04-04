package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SummaryReportRow {
	private  String Location2="";
	private SimpleIntegerProperty above40=new SimpleIntegerProperty();
	private SimpleIntegerProperty below50=new SimpleIntegerProperty();
	private SimpleIntegerProperty rangeBrtween37 =new SimpleIntegerProperty();
	
	private SimpleIntegerProperty numbersOfMartyrsByAge=new SimpleIntegerProperty();
	private  SimpleIntegerProperty numMarriedMartyrs=new SimpleIntegerProperty();;
	private  SimpleIntegerProperty numSingleMartyrs=new SimpleIntegerProperty();;
	private  SimpleIntegerProperty numFemaleMartyrs=new SimpleIntegerProperty();;
	private  SimpleIntegerProperty numMaleMartyrs=new SimpleIntegerProperty();;
	private  SimpleDoubleProperty averageAgeOfMartyrs=new SimpleDoubleProperty();
	private  SimpleStringProperty dateThatHasTheMaximumNumberOfMartyrs= new SimpleStringProperty();

	public SummaryReportRow(Location locat) {
		super();
		System.out.println(locat.getSingle());
		this.above40.setValue(locat.getAbove40());
        this.below50.setValue(locat.getBelow50());
        this.rangeBrtween37.setValue(locat.getAge30to70());
		this.numbersOfMartyrsByAge.setValue( locat.getNumMartyrsByAge(0));;
		this.numFemaleMartyrs.setValue(locat.getCount()-locat.getMale());
		
		
		this.numMaleMartyrs.setValue(locat.getMale());
		this.averageAgeOfMartyrs.setValue(Martyr.getAverageAge(Main.locationsList.getFirst().getLocation().getSt()));;
		this.dateThatHasTheMaximumNumberOfMartyrs.setValue(Martyr.getDateWithMaxMartyrs(Main.locationsList.getFirst().getLocation().getSt())+"");;
		this.Location2=locat.getName();
		this.numMarriedMartyrs.setValue(locat.getCount()-locat.getSingle());
		this.numSingleMartyrs.setValue(locat.getSingle());
		
	
	}

	

	   public double getAbove40() {
	        return above40.getValue();
	    }
	    
	   
	    public void setAbove40(int above40) {
	        this.above40.set(above40);
	    }
	    
	    public double getRangeBetween37() {
	        return rangeBrtween37.getValue();
	    }
	    
	   
	    public void setRangeBetween37(double rangeBetween37) {
	        this.rangeBrtween37.set(getNumMarriedMartyrs());
	    }

	public String getLocation2() {
		return Location2;
	}

	public void setAbove40(SimpleIntegerProperty above40) {
		this.above40 = above40;
	}



	public int getNumFemaleMartyrs() {
		return numFemaleMartyrs.getValue();
	}

	public void setNumFemaleMartyrs(SimpleIntegerProperty numFemaleMartyrs) {
		this.numFemaleMartyrs = numFemaleMartyrs;
	}

	public int getNumMaleMartyrs() {
		return numMaleMartyrs.getValue();
	}

	public void setNumMaleMartyrs(SimpleIntegerProperty numMaleMartyrs) {
		this.numMaleMartyrs = numMaleMartyrs;
	}

	public void setLocation2(String location2) {
		Location2 = location2;
	}

	public int getNumbersOfMartyrsByAge() {
		return numbersOfMartyrsByAge.getValue();
	}

	public void setNumbersOfMartyrsByAge(int numbersOfMartyrsByAge) {
		this.numbersOfMartyrsByAge.setValue(numbersOfMartyrsByAge);
	}


	public int getNumMarriedMartyrs() {
		return numMarriedMartyrs.getValue();
	}

	public void setNumMarriedMartyrs(SimpleIntegerProperty numMarriedMartyrs) {
		this.numMarriedMartyrs = numMarriedMartyrs;
	}

	public int getNumSingleMartyrs() {
		return numSingleMartyrs.getValue();
	}

	public void setNumSingleMartyrs(SimpleIntegerProperty numSingleMartyrs) {
		this.numSingleMartyrs = numSingleMartyrs;
	}

	

	

	public Integer getBelow50() {
		return below50.getValue();
	}



	public void setBelow50(SimpleIntegerProperty below50) {
		this.below50 = below50;
	}



	public Integer getRangeBrtween37() {
		return rangeBrtween37.getValue();
	}



	public void setRangeBrtween37(SimpleIntegerProperty rangeBrtween37) {
		this.rangeBrtween37 = rangeBrtween37;
	}



	public Double getAverageAgeOfMartyrs() {
		return averageAgeOfMartyrs.getValue();
	}



	public void setAverageAgeOfMartyrs(SimpleDoubleProperty averageAgeOfMartyrs) {
		this.averageAgeOfMartyrs = averageAgeOfMartyrs;
	}



	public void setNumbersOfMartyrsByAge(SimpleIntegerProperty numbersOfMartyrsByAge) {
		this.numbersOfMartyrsByAge = numbersOfMartyrsByAge;
	}



	public String getDateThatHasTheMaximumNumberOfMartyrs() {
		return dateThatHasTheMaximumNumberOfMartyrs.getValue();
	}

	public void setDateThatHasTheMaximumNumberOfMartyrs(SimpleStringProperty dateThatHasTheMaximumNumberOfMartyrs) {
		this.dateThatHasTheMaximumNumberOfMartyrs = dateThatHasTheMaximumNumberOfMartyrs;
	}

	
	}
	
	