package shipplatform.vehicles;

import java.util.ArrayList;

import shipplatform.passengers.Passenger;

public class Moto extends Vehicle {
	
	public static final double MOTO_PRICE = 5;

	public Moto() {
		
	}
	
	public Moto(ArrayList<Passenger> passengers, String numberPlate,int id) {
		super(id,MOTO_PRICE, 2, passengers, numberPlate);
		
	}

	public String toString() {
		String moto="========== MOTO "+super.getNumberPlate()+" "+super.getPrice()+"€"+" =========\n";
		for(int i=0; i< super.getPassengers().size() ;i++) {
			moto+=(i+1)+". "+super.getPassengers().get(i).toString()+"\n";
		}
		moto+="\n";
		return moto;
	}
}
