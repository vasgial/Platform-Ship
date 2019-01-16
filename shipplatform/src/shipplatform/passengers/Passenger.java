package shipplatform.passengers;

public class Passenger {

	private int id;
	private double fee;
	private int type;
	
	public Passenger() {
		// TODO Auto-generated constructor stub
	}
	public Passenger(double fee, int type) {
		this.fee = fee;
		this.type=type;
	}
	
	public Passenger(double fee, int type, int id) {
		this.fee = fee;
		this.type=type;
		this.id=id;
	}
	
	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String toString() {
		String str="";
		switch(type) {
			case 5:
				str="REGULAR";
				break;
			case 4:
				str="STUDENT";
				break;
			case 3:
				str="SPECIAL";
				break;
			case 2:
				str="ELDERLY";
				break;
			case 1:
				str="BABY   ";
				break;
		}
		return str+"\t "+fee+"€";
	}

}
