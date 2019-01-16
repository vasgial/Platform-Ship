package shipplatform.vehicles;

import java.text.DecimalFormat;
import java.util.ArrayList;

import shipplatform.passengers.Passenger;

public class Auto extends Vehicle {
	
	private int auto_type;
	
	private double length;
	

	public Auto() {
		
	}
	
	public Auto(double price, int passengerCapacity, ArrayList<Passenger> passengers, String numberPlate, double length,int auto_type,int id) {
		super(id,price, passengerCapacity, passengers, numberPlate);		
		this.length = length;
		this.auto_type=auto_type;
	}



	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getAuto_type() {
		return auto_type;
	}

	public void setAuto_type(int auto_type) {
		this.auto_type = auto_type;
	}
	
	public String toString() {
		DecimalFormat df=new DecimalFormat("###.##");
		String str=auto_type==1?"CAR  ":"TRUCK";
		
		String auto="========== "+str+" "+super.getNumberPlate()+" "+super.getPrice()+"€ "+df.format(length)+"m"+" =========";
		for(int i=0; i< super.getPassengers().size() ;i++) {
			auto+="\n"+(i+1)+". "+super.getPassengers().get(i).toString();
		}
		auto+="\n\n";
		return auto;
	}

}
