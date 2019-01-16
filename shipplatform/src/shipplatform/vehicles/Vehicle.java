package shipplatform.vehicles;

import java.util.ArrayList;

import shipplatform.passengers.Passenger;

public abstract class Vehicle {
	
	private int id;
	private double price;
	private int passengerCapacity;
	private ArrayList<Passenger> passengers;
	private String numberPlate;

	public Vehicle() {
		
	}
	
	public Vehicle(int id,double price, int passengerCapacity, ArrayList<Passenger> passengers, String numberPlate) {
		this.id=id;
		this.price = price;
		this.passengerCapacity = passengerCapacity;
		this.passengers = passengers;
		this.numberPlate = numberPlate;
	}
	
	public double getPrice() {
		return price;
	}
	public String getNumberPlate() {
		return numberPlate;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}