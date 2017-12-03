package system_connection;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;

public class testConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		// TODO Auto-generated method stub
		
		connectionPool pool = connectionPool.getInstance();
		Set<Connection> myconnection = new HashSet(5);
		for (Connection i :myconnection) {
			i= (Connection) pool.getConnection();
			System.out.println("connected");
			
			
		}
		
	}

}
