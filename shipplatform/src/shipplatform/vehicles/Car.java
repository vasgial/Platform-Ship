package shipplatform.vehicles;

import java.util.ArrayList;

import shipplatform.passengers.Passenger;

public class Car extends Auto {
	
	private static final double CAR_PRICE = 15;	


	public Car() {
		
	}
	
	public Car(ArrayList<Passenger> passenger, String numberPlate, double length,int id) {
		super(CAR_PRICE, 5, passenger, numberPlate, length,1,id);		 
		
	}

	
}
