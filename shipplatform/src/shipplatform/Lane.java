package shipplatform;

import java.text.DecimalFormat;
import java.util.ArrayList;

import shipplatform.vehicles.Auto;

public class Lane {
	private int id;
	private ArrayList<Auto> autos;
	private double length;
	private int index;
	public Lane() {
		autos=new ArrayList<Auto>();
	}
	public Lane(int index, double length,int id) {
		this.index=index;
		this.length=length;
		this.id=id;
		autos=new ArrayList<Auto>();
	}
	
	
	
	public Lane(int index, double length,ArrayList<Auto> autos,int id) {
		this.index=index;
		this.length=length;
		this.autos=autos;
		this.id=id;
		autos=new ArrayList<Auto>();
	}
	
	public double spaceTaken() {
		double sum=0;
		if(autos!=null){
			for(int i=0;i<autos.size();i++) {
				sum+=autos.get(i).getLength();
			}
		}
		return sum;
	}
	
	public boolean fits(Auto auto) {
		double freeSpace=length-spaceTaken();
		if(auto.getLength()>freeSpace) {
			return false;
		}else {
			return true;
		}
		
	}
	
	public double freeSpace() {
		return length-spaceTaken();
	}
	
	public void insert(Auto auto) {
		autos.add(auto);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Auto> getAutos() {
		return autos;
	}
	public void setAutos(ArrayList<Auto> autos) {
		this.autos = autos;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String toString() {
		DecimalFormat df=new DecimalFormat("####.##");
		String lane="\n========================================================"
				+ "\n========================================================\n     LANE:"+index+"\t TOTAL_AUTOS:"+autos.size()+"\t FREE_SPACE:"+df.format(freeSpace())+" m\n";
		        lane+="========================================================\n"
		        		+ "========================================================\n\n";
		for(int i=0; i< autos.size();i++) {
			lane+=autos.get(i).toString();
		}
		return lane;
	}
	
}
