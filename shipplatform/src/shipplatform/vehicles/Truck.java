package shipplatform.vehicles;

import java.util.ArrayList;

import shipplatform.passengers.Passenger;

public class Truck extends Auto {
	
	private static final double TRUCK_PRICE = 45;
	

	public Truck() {
		
	}
	
	public Truck(ArrayList<Passenger> passenger, String numberPlate, double length,int id) {
		super(TRUCK_PRICE, 2, passenger, numberPlate, length,2,id);
		
		
	}

	

}
