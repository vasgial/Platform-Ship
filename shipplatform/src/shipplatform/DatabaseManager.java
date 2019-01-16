package shipplatform;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shipplatform.passengers.Passenger;
import shipplatform.vehicles.Auto;
import shipplatform.vehicles.Moto;


public class DatabaseManager {
	private static DatabaseManager dbManager;
	private static Connection conn;
	
	private DatabaseManager() {
		conn=conn();
	}
	
	private static Connection conn() {
		if(conn==null) {
			try {
				conn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ship_platform?autoReconnect=true&useSSL=false","root","1234");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static DatabaseManager dbManager() {
		if(dbManager==null) {
			dbManager=new DatabaseManager();
		}
		return dbManager;
	}
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Ship getShip(int id) {

		Ship ship=null;
		String query= "select * from ship where ship_id=?";
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
				int ship_id=rs.getInt("ship_id");
				String name=rs.getString("name");
				int moto_capacity=rs.getInt("moto_capacity");
				double length=rs.getDouble("length");
				int total_lanes=rs.getInt("total_lanes");
				int passenger_capacity=rs.getInt("passenger_capacity");
				
				ship=new Ship(ship_id,name,length,total_lanes,moto_capacity,passenger_capacity, getShipLanes(ship_id,length), getShipMotos(ship_id), getShipPassengers(ship_id));
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ship;
		
	}
	
	public static Lane[] getShipLanes(int ship_id,double length) {
		ArrayList<Lane>  lanes=new ArrayList<Lane>();
		
		String query= "select * from lanes where ship_id=?";
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, ship_id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
				int lane_id=rs.getInt("lane_id");
				int lane_index=rs.getInt("lane_index");
				lanes.add(new Lane(lane_index,length,getLaneAutos(ship_id,lane_id),lane_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Lane[] lanes1=new Lane[lanes.size()];
		for(int i=0;i<lanes1.length;i++) {
			lanes1[i]=lanes.get(i);
		}
		
		return lanes1;
		
	}
	
	public static ArrayList<Auto> getLaneAutos(int ship_id,int lane_id) {
		String query= "select * from auto where lane_id=?;";
		ArrayList<Auto> autos=new ArrayList<Auto>();
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, lane_id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
				double price=rs.getDouble("price");
				int auto_type=rs.getInt("auto_type");
				int passengerCapacity=(auto_type==1)? 5 : 2;
				String numberPlate=rs.getString("number_plate");
				double length=rs.getDouble("length");
				int id=rs.getInt("auto_id");
				autos.add(new Auto(price,passengerCapacity,getAutoPassengers(id),numberPlate,length,auto_type,id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autos;
	}
	
	public static ArrayList<Passenger> getAutoPassengers(int id){
		String query= "select * from auto_passengers where auto_id=?";
		ArrayList<Passenger> passengers=new ArrayList<Passenger>();
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
					double fee=0;
					int passenger_id= rs.getInt("passenger_id");
					int passenger_type=rs.getInt("passenger_type");
					switch(passenger_type) {
						case 5:
							fee=5;
							break;
						case 4:
							fee=2.5;
							break;
						case 3:
							fee=2;
							break;
						case 2:
							fee=2;
							break;
						case 1:
							fee=0;
							break;
					}
					passengers.add(new Passenger(fee,passenger_type,passenger_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengers;
	}
	
	public static ArrayList<Moto> getShipMotos(int ship_id) {
		String query= "select * from moto where ship_id=?;";
		ArrayList<Moto> motos=new ArrayList<Moto>();
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, ship_id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
				String numberPlate=rs.getString("number_plate");
				int id=rs.getInt("moto_id");
				motos.add(new Moto( getMotoPassengers(id), numberPlate, id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return motos;
	}
	public static ArrayList<Passenger> getShipPassengers(int ship_id) {
		String query= "select * from passengers where ship_id=?";
		ArrayList<Passenger> passengers=new ArrayList<Passenger>();
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, ship_id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
					double fee=0;
					int passenger_id= rs.getInt("passenger_id");
					int passenger_type=rs.getInt("passenger_type");
					switch(passenger_type) {
						case 5:
							fee=5;
							break;
						case 4:
							fee=2.5;
							break;
						case 3:
							fee=2;
							break;
						case 2:
							fee=2;
							break;
						case 1:
							fee=0;
							break;
					}
					passengers.add(new Passenger(fee,passenger_type,passenger_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengers;
	}
	
	public static ArrayList<Passenger> getMotoPassengers(int id){
		String query= "select * from moto_passengers where moto_id=?";
		ArrayList<Passenger> passengers=new ArrayList<Passenger>();
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setInt(1, id);
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
					double fee=0;
					int passenger_id= rs.getInt("passenger_id");
					int passenger_type=rs.getInt("passenger_type");
					switch(passenger_type) {
						case 5:
							fee=5;
							break;
						case 4:
							fee=2.5;
							break;
						case 3:
							fee=2;
							break;
						case 2:
							fee=2;
							break;
						case 1:
							fee=0;
							break;
					}
					passengers.add(new Passenger(fee,passenger_type,passenger_id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passengers;
	}
	
	public static void insertAuto(Auto auto,Ship ship, int laneIndex) {
		String sql = "INSERT INTO auto VALUES(null,?,?,?,?,?)";
		
        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, ship.getLanes()[laneIndex].getId()  );
            pstmt.setDouble(2, auto.getLength());
			pstmt.setString(3, auto.getNumberPlate());
			pstmt.setInt(4,auto.getAuto_type());
			pstmt.setDouble(5, auto.getPrice());
			pstmt.executeUpdate();
			
			
			insertAutoPassengers(auto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void insertAutoPassengers(Auto auto) {
		for(int i=0; i< auto.getPassengers().size();i++) {
			String sql="insert into auto_passengers values(null,?,?)";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
	    		pstmt.setInt(1,  getAutoId(auto));
	    		pstmt.setInt(2, auto.getPassengers().get(i).getType());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static int getAutoId(Auto auto) {
		String query= "select auto_id from auto where number_plate=?";
		int id=-1;
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setString(1, auto.getNumberPlate());
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("auto_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	public static void insertMoto(Moto moto,Ship ship) {
		String sql = "INSERT INTO moto VALUES(null,?,?,?,?)";
		
        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, ship.getId()  );
            pstmt.setDouble(2,moto.getPrice());
			pstmt.setString(3, moto.getNumberPlate());
			pstmt.setInt(4,moto.getPassengerCapacity());
			pstmt.executeUpdate();
			insertMotoPassengers(moto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void insertMotoPassengers(Moto moto) {
		for(int i=0; i< moto.getPassengers().size();i++) {
			String sql="insert into moto_passengers values(null,?,?)";
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
	    		pstmt.setInt(1,  getMotoId(moto));
	    		pstmt.setInt(2, moto.getPassengers().get(i).getType());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static int getMotoId(Moto moto) {
		String query= "select moto_id from moto where number_plate=?";
		int id=-1;
		try {
			PreparedStatement stmnt=conn.prepareStatement(query);
			stmnt.setString(1, moto.getNumberPlate());
			ResultSet rs=stmnt.executeQuery();
			while(rs.next()) {
				id=rs.getInt("moto_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static void insertPassenger(Passenger passenger,Ship ship) {
		String sql = "INSERT INTO passengers VALUES(null,?,?)";
		
        try {
        	PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, ship.getId()  );
            pstmt.setInt(2,passenger.getType());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
