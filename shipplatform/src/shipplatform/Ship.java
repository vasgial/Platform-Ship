package shipplatform;

import java.util.ArrayList;
import shipplatform.passengers.Passenger;
import shipplatform.vehicles.Auto;
import shipplatform.vehicles.Moto;

public class Ship {
	private int id;
	private String name;
	private int totalLanes;
	private int motoCapacity;
	private int passengerCapacity;
	private Lane[] lanes;
	private ArrayList<Moto> motos;
	private ArrayList<Passenger> passengers;
	private boolean checkedOut;
	
	public Ship() {}
	
	public Ship(int id,String name,double laneLength,int totalLanes,int motoCapacity,int passengerCapacity,Lane[] lanes,ArrayList<Moto> motos,ArrayList<Passenger> passengers) {
		this.name=name;
		this.totalLanes=totalLanes;
		this.motoCapacity=motoCapacity;
		this.passengerCapacity=passengerCapacity;
		this.id=id;
		this.lanes=lanes;
		this.motos=motos;
		this.passengers=passengers;
		
	}
	
	
	

	public void insertAuto(Auto auto) {
		double[] freeSpace=new double[totalLanes];
		
		for(int i=0; i < lanes.length; i++) {
			freeSpace[i]=lanes[i].freeSpace();
		}
		
		double max=freeSpace[0];
		int maxIndex=0;
		for(int i=0; i < freeSpace.length;i++) {
			if (max<freeSpace[i]) {
				max=freeSpace[i];
				maxIndex=i;
			}
		}
		

		if(lanes[maxIndex].fits(auto)) {
			if(totalPassengers()+auto.getPassengers().size()>passengerCapacity) {
				if(!checkedOut) {
					checkOut();
				}
			}else {
				lanes[maxIndex].insert(auto);
				DatabaseManager.insertAuto(auto, this, maxIndex);
			}
		}else {
			if(!checkedOut) {
				checkOut();
			}
		}
		
		
			
		
		
	}
	
	public void insertMoto(Moto moto) {
		if( motos.size() < motoCapacity ) {
			if(totalPassengers()+moto.getPassengers().size()<=passengerCapacity) {
				DatabaseManager.insertMoto(moto, this);
				motos.add(moto);
			}else {
				if(!checkedOut) {
					checkOut();
				}
			}
		}else {
			if(!checkedOut) {
				checkOut();
			}
		}
	}
	
	public void insertPassenger(Passenger passenger) {
		if( totalPassengers() <passengerCapacity) {
			passengers.add(passenger);
			DatabaseManager.insertPassenger(passenger,this);
		}else if(passengers.size()==passengerCapacity) {
			if(!checkedOut) {
				checkOut();
			}
		}
	}
	
	public int totalPassengers() {
		int sum=0;
		for(int i=0; i< lanes.length;i++) {
			
			for(int j=0; j < lanes[i].getAutos().size() ; j++) {
				sum+=lanes[i].getAutos().get(j).getPassengers().size();
			}
			
		}
		for(int i=0; i<motos.size();i++) {
			sum+=motos.get(i).getPassengers().size();
		}
		sum+=passengers.size();
		
		
		return sum;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalLanes() {
		return totalLanes;
	}

	public void setTotalLanes(int totalLanes) {
		this.totalLanes = totalLanes;
	}

	public int getMotoCapacity() {
		return motoCapacity;
	}

	public void setMotoCapacity(int motoCapacity) {
		this.motoCapacity = motoCapacity;
	}

	public int getPassengerCapacity() {
		return passengerCapacity;
	}

	public void setPassengerCapacity(int passengerCapacity) {
		this.passengerCapacity = passengerCapacity;
	}

	public Lane[] getLanes() {
		return lanes;
	}

	public void setLanes(Lane[] lanes) {
		this.lanes = lanes;
	}

	public ArrayList<Moto> getMotos() {
		return motos;
	}

	public void setMotos(ArrayList<Moto> motos) {
		this.motos = motos;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public boolean isCheckedOut() {
		return checkedOut;
	}

	
	public void checkOut() {
		double money=0;
		checkedOut=true;
		System.out.println(" The ship is full.  ");
		
		for(int i=0; i< lanes.length;i++) {
			
			for(int j=0; j < lanes[i].getAutos().size() ; j++) {
				
				money+=lanes[i].getAutos().get(j).getPrice();
				for(int k=0; k< lanes[i].getAutos().get(j).getPassengers().size() ;k++) {
					money+=lanes[i].getAutos().get(j).getPassengers().get(k).getFee();
				}
				
			}
			
		}
		for(int i=0; i<motos.size();i++) {
			money+=motos.get(i).getPrice();
			for(int j=0; j< motos.get(i).getPassengers().size();j++) {
				money+=motos.get(i).getPassengers().get(j).getFee();
			}
		}
		for(int i=0; i< passengers.size() ;i++) {
			money+=passengers.get(i).getFee();
		}
		System.out.println(" Total earnings "+ money+"\n");
		Main.printShip(this);
	}
}
