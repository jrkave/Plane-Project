/*
 * Author: Julia Veenkamp
 * Created on: 02/17/2023
 * Filename: TestPlane.java
 * Class: CSII WI23
 */

package mypack;

public class TestPlane {

    public static void main(String[] args) {
        Plane plane1 = new Plane("Delta", "747", 2021, 3600.0);
        Plane plane2 = new Plane("Delta", "747", 2021, 4700.0);
        Plane plane3 = new Plane();
        // Phase 1 Testing
        System.out.println("===== Testing Phase 1 =====");
        System.out.println(" * Testing constructors * ");
        System.out.println(plane1);
        System.out.println(plane3);
        System.out.println(" * Testing Setter/Getters * ");
        plane1.setAirline("Allegiant");
        System.out.println(plane1.getAirline());
        plane1.setModel("601");
        System.out.println(plane1.getModel()); 
        plane1.setYear(2012);
        System.out.println(plane1.getYear()); 
        plane1.setGph(4250.0);
        System.out.println(plane1.getGph());
        System.out.println(plane1.checkFlyingHours());
        System.out.println(plane1.checkGasGauge());
        System.out.println();
        // Phase 2 Testing
        System.out.println("===== Testing Phase 2 =====");
        System.out.println(" * Testing addGas() function * ");
        plane1.addGas(-200);
        plane1.addGas(100);
        System.out.println(plane1.checkGasGauge());
        plane1.fly(2);
        plane1.addGas(50);
        System.out.println(" * Testing fly() function * ");
        plane1.fly(-2);
        plane1.fly(15);
        System.out.println(plane1.checkFlyingHours());
        plane1.addGas(10000);
        plane1.fly(1);
        System.out.println(" * Testing inspection() functions * ");
        plane1.inspect();
        plane1.checkForInspection();
        System.out.println(" * Testing equals() function *");
        Plane plane4 = new Plane("Delta", "747", 2021, 5000.0);
        System.out.println("Expected value: false, Return value: " + plane1.equals(plane2));
        System.out.println("Expected value: true, Return value: " + plane2.equals(plane4));
        System.out.println();
        // Phase 3 Testing
        System.out.println("===== Testing Phase 3 =====");
        System.out.println(" * Testing startEngine() * ");
        System.out.println("Expected value: false, Return value: " + plane1.isEngineOn());
        plane1.startEngine();
        System.out.println("Expected value: true, Return true: " + plane1.isEngineOn());
        System.out.println(" Testing correct status of plane to perform methods *");
        plane1.fly(1);
        plane1.addGas(1000);
        System.out.println(plane1.checkFlyingHours());;
        plane1.inspect();
        plane1.checkForInspection();
        plane1.landAndStopEngine();
        plane1.addGas(1000);
        System.out.println(" * Testing simulation of multi-city trip * ");
        plane2.simulateMultiCityTrip(4);
        plane1.simulateMultiCityTrip(2);  
    }   
}