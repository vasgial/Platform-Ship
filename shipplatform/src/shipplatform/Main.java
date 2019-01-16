package shipplatform;

import java.util.ArrayList;
import java.util.Random;

import shipplatform.passengers.Baby;
import shipplatform.passengers.Passenger;
import shipplatform.passengers.Regular;
import shipplatform.passengers.Special;
import shipplatform.passengers.Student;
import shipplatform.vehicles.Auto;
import shipplatform.vehicles.Car;
import shipplatform.vehicles.Moto;

public class Main {
	public static void main(String[] args) {
		DatabaseManager.dbManager();
		Ship myrto=DatabaseManager.getShip(1);
		Ship artemis=DatabaseManager.getShip(2);
		Ship eirini=DatabaseManager.getShip(3);
		
		for(int i=0;i<5;i++) {
			if(!myrto.isCheckedOut()) {
				myrto.insertAuto(randomAuto());
				myrto.insertMoto(randomMoto());
				myrto.insertPassenger(randomPassenger());
			}
			
		}
		if(!myrto.isCheckedOut()) {
			printShip(myrto);
		}
		
	}
	
	public static void printShip(Ship ship) {

		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("===================LANE INFORMATION=====================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		for(int i=0;i<ship.getLanes().length;i++) {
			System.out.println(ship.getLanes()[i]);
		}
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("===================MOTO INFORMATION=====================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		for(int i=0; i< ship.getMotos().size(); i++) {
			System.out.println(ship.getMotos().get(i));
		}
		System.out.println("========================================================");
		System.out.println("========================================================");
		System.out.println("===============PASSENGER INFORMATION====================");
		System.out.println("========================================================");
		System.out.println("========================================================");
		for(int i=0; i<ship.getPassengers().size();i++) {
			System.out.println((i+1)+". "+ship.getPassengers().get(i));
		}
	}
	
	public static ArrayList<Passenger> randomAutoPassengers(){
		Random rnd=new Random();
		ArrayList<Passenger> randomPassengers=new ArrayList<Passenger>();
		int number=rnd.nextInt(5)+1;
		double[][] fees= {{0.0,1},{2.0,2},{2.0,3},{2.5,4},{5.0,5}};
		
		
		
		for(int i=0; i < number; i++) {
			int rndmElement=rnd.nextInt(fees.length);
			double fee=fees[rndmElement][0];
			int type=(int)fees[rndmElement][1];
			
			randomPassengers.add(new Passenger(fee,type));
		}
		
		return randomPassengers;
	}
	
	public static ArrayList<Passenger> randomMotoTruckPassengers(){
		Random rnd=new Random();
		ArrayList<Passenger> randomPassengers=new ArrayList<Passenger>();
		int number=rnd.nextInt(2)+1;
		double[][] fees= {{0.0,1},{2.0,2},{2.0,3},{2.5,4},{5.0,5}};
		
		
		
		for(int i=0; i < number; i++) {
			int rndmElement=rnd.nextInt(fees.length);
			double fee=fees[rndmElement][0];
			int type=(int)fees[rndmElement][1];
			
			randomPassengers.add(new Passenger(fee,type));
		}
		
		return randomPassengers;
	}
	
	public static Passenger randomPassenger() {
		Random rnd=new Random();
		double[][] fees= {{0.0,1},{2.0,2},{2.0,3},{2.5,4},{5.0,5}};
		int rndmElement=rnd.nextInt(fees.length);
		double fee=fees[rndmElement][0];
		int type=(int)fees[rndmElement][1];
		return new Passenger(fee,type);
	}
	
	public static Auto randomAuto() {
		Random rnd=new Random();

		int auto_type=rnd.nextInt(10)<7?1:2;
		
		String numberPlate=randomLetters(2).toUpperCase()+(rnd.nextInt(8999)+1000);
		
		
		int passengerCapacity;
		double length;
		double price;

		ArrayList<Passenger> passengers;
		
		
		if(auto_type==1) {
			price=15;
			passengerCapacity=5;
			length= 4 + (5.5 - 4) * rnd.nextDouble();
			passengers=randomAutoPassengers();
		}else {
			price=45;
			passengerCapacity=2;
			length=7 + (15 - 7) * rnd.nextDouble();
			passengers=randomMotoTruckPassengers();
		}
		
		
		
		
		int id=0;
		return new Auto(price, passengerCapacity, passengers,numberPlate, length,auto_type,id);
	}
	
	public static Moto randomMoto() {
		Random rnd=new Random();		
		String numberPlate=randomLetters(2).toUpperCase()+(rnd.nextInt(8999)+1000);
		return new Moto(randomMotoTruckPassengers(),numberPlate, 0);
	}
	
	public static String randomLetters(int size) {
		  
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = size;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}
	
	
	
}
