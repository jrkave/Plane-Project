/*
 * Author: Julia Veenkamp
 * Created on: 02/17/2023
 * Filename: Plane.java
 * Class: CSII WI23
 */

package mypack;
import java.text.DecimalFormat;
import java.util.Random;

public class Plane {
    // Declaration of instance variables
    private String airline;
    private String model;
    private int year;
    private double hoursFlown;
    private double hoursNextInspection;
    private double gallonsInTank;
    double gallonsPerHour;
    private static final double TANK_CAPACITY = 50000;
    public static final int HOURS_BETWEEN_INSPECTION = 48;
    private static DecimalFormat df = new DecimalFormat ("###,##0.00");
    private boolean engineStatus = false;
    private boolean planeLanded = true;

    // Default class constructor
    public Plane() {
        airline = "Southwest";
        model = "747";
        year = 2021;
        gallonsPerHour = 3600.0;
        gallonsInTank = 50000.0;
        hoursFlown = 0.0;
        hoursNextInspection = 48.0;
    }
    // Overloaded class constructor
    public Plane(String airline, String model, int y, double gph) {
        this.airline = airline;
        this.model = model;
        year = y;
        gallonsPerHour = gph;
        gallonsInTank = 50000.0;
        hoursFlown = 0.0;
        hoursNextInspection = 48.0;
    }

    // Accessor methods
    public String getAirline() {
        return airline;
    }
    public String getModel() {
        return model;
    }
    public double getGph() {
        return gallonsPerHour;
    }
    public int getYear() {
        return year;
    }
    public double getHoursNextInspection() {
        return hoursNextInspection;
    }
    public double checkFlyingHours() {
        return hoursFlown;
    }
    public double checkGasGauge() {
        return gallonsInTank;
    }
    public String toString() {
        String plane = airline + " " + model + " " + year + " Flight Hours: " + df.format(hoursFlown) + " JetFuel in Tank: " + df.format(gallonsInTank);
        return plane;
    }

    // Mutator methods
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setGph(double value) {
        gallonsPerHour = value;
    }
    public void setYear(int y) {
        year = y;
    }
    
    // Add gas to plane
    public void addGas(double gallons) {
    	if ((engineStatus == true) || (planeLanded == false)) {
    		System.out.println(airline + " " + model + " " + year + " must be landed and OFF to add fuel.");
    	}
    	else {
        	if (gallons < 0) {
        		System.out.println(airline + " " + model + " " + year + " gallons cannot be a negative number - Fuel in Tank after the fill up: " + df.format(gallonsInTank));
        	}
        	else if ((gallons + gallonsInTank) > TANK_CAPACITY) {
        		gallonsInTank = TANK_CAPACITY;
        		System.out.println(airline + " " + model + " " + year + " tank overflowed - Fuel in tank after the fill up: " + df.format(TANK_CAPACITY));
        	}
        	else {
        		gallonsInTank += gallons;
        		System.out.println(airline + " " + model + " " + year + " added fuel: " + df.format(gallons) + " - Fuel in Tank after the fill up: " + df.format(gallonsInTank));
        	}
    	}

    }
    // Fly plane for certain number of hours depending on fuel in tank
    public void fly(double hours) {
    	if (engineStatus == false) {
    		System.out.println(airline + " " + model + " " + year + " must be ON to fly.");
    	}
    	else {
    		planeLanded = false;
    		if (hours < 0) {
        		System.out.println(airline + " " + model + " " + year + " cannot fly negative miles.");
        	}
        	else if (gallonsInTank - (gallonsPerHour * hours) < 0) {
        		hoursFlown += (gallonsInTank / gallonsPerHour);
        		gallonsInTank = 0.0;
        		engineStatus = false;
        		planeLanded = true;
        		System.out.println(airline + " " + model + " " + year + " ran out of fuel after flying " + df.format(hoursFlown) + " hours.");
        	}
        	else {
        		hoursFlown += hours;
        		gallonsInTank -= (gallonsPerHour * hours);
        		System.out.println(airline + " " + model + " " + year + " flew " + df.format(hours) + " hours.");	
        	}
        }
    }
    // Sets inspection reminder to 48 hours from the current reading
    public void inspect() {
    	if ((engineStatus == false) && (planeLanded == true)) {
    		hoursNextInspection += hoursFlown;
        	System.out.println(airline + " " + model + " " + year + " has been inspected, next inspection is: " + df.format(hoursNextInspection));
    	}
    	else {
    		System.out.println(airline + " " + model + " " + year + " must be landed and OFF to inspect.");
    	}
    }
    // Checks for inspection
    public void checkForInspection() {
    	if ((engineStatus == false) && (planeLanded == true)) {
        	if ((hoursFlown == HOURS_BETWEEN_INSPECTION) || (hoursFlown > HOURS_BETWEEN_INSPECTION)) {
        		System.out.println(airline + " " + model + " " + year + " - It is time to inspect.");
        	}
        	else {
        		System.out.println(airline + " " + model + " " + year + " - Plane is OK, no need to inspect.");
        	}	
    	}
    	else {
    		System.out.println(airline + " " + model + " " + year + " must be landed and OFF to check for inspection.");
    	}
    }
    // Return true if airline, model, and year of plane are same as airline, model, and year of other plane passed as input parameter
    public boolean equals(Plane other) {
    	if ((other.airline == airline) && (other.model == model) && (other.year == year)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    // Starts engine
    public void startEngine() {
    	engineStatus = true;
    	System.out.println(airline + " " + model + " " + year + " - engine started.");
    }
    // Lands plane and stops engine
    public void landAndStopEngine() {
    	engineStatus = false;
    	planeLanded = true;
    	System.out.println(airline + " " + model + " " + year + " - was landed and stopped.");
    }
    // Returns true if engine is on, false otherwise
    public boolean isEngineOn() {
    	if (engineStatus == true) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    // Calculate total range (max hours to fly) based on fuel in tank and gph
    private double calcRange() {
    	double maxHours = 0;
    	maxHours = (gallonsInTank / gallonsPerHour);
    	return maxHours;
    }
    // Calculate fuel needed to fill tank
    private double calcGasNeededToFillTank() {
    	double gallonsToFill = 0;
    	gallonsToFill = TANK_CAPACITY - gallonsInTank;
    	return gallonsToFill;
    }
    // Simulate plane flying to number of cities
    public void simulateMultiCityTrip(int numberCities) {
    	for (int i = 0; i < numberCities; i++) {
    		Random rand = new Random();
    		int randNum = rand.nextInt((int)Math.ceil(calcRange())) + 1;
    		startEngine();
    		fly((double)randNum);
    		landAndStopEngine();
    		inspect();
    		addGas(calcGasNeededToFillTank());
    	}
    }
    
    public static void main(String[] args) {
    	System.out.println("All testing performed in TestPlane.java.");
    }
}