package system_connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.sql.Connection;
/**
 * 
 * @author MatrixComp
 * @version 1.0v
 * @see
 * Connection Pool Class 
 * this  class connect to database tables 
 * and manage the connections for  5 connection in the same time 
 * java jdbc drive 
 * @category - singleton class
 * no body can create new connection pool form out side the system
 */
public class connectionPool {
	/**
	 * Connection class member
	 */
	private  static connectionPool instance ;
	private static int MAX_CONNECTION = 5;
	private Set<Connection> myConnection;
	private Object key = new Object();
	
	/**
	 * Constructor of Connection pool while  its private  and initializing connection member
	 * @jdbc driver 
	 * @url - database server location
	 * @connection set list
	 * @userName to access the database
	 * @password - MYSQL server required to enter to tables
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private connectionPool() throws ClassNotFoundException,SQLException
	{
		myConnection =new HashSet<>();
		//"com.mysql.fabric.jdbc.FabricMySQLDriver"
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/coupon_system";
		String userName ="root";
		String password="password";
		
		for (int i = 0; i < MAX_CONNECTION; i++) {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, userName, password);
			myConnection.add(conn);
			System.out.println("connected to data base");
			
		}
	}
	/**
	 * @sychronized managing the connection coming and create new instance to connect to system
	 * @return -instance - new connectionPool()
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public synchronized static connectionPool getInstance() throws ClassNotFoundException, SQLException
	{
		if (instance == null )
		{
			instance=new connectionPool();
		}
		return instance;
	}
	/**
	 * 
	 * @return connection -next
	 * @throws InterruptedException
	 */
	public Connection getConnection() throws InterruptedException
	{
		synchronized(key)
		{
			while(myConnection.size() == 0)
			{
				key.wait();
			}
			Connection res = myConnection.iterator().next();
			myConnection.remove(res);
			return res;
		}
	}
	/**
	 * return the connection every time  not closing connection
	 * @param conn - Connection
	 */
	public void returnConnection(Connection conn)
	{
		synchronized(key)
		{
			myConnection.add(conn);
			key.notifyAll();
		}
	}
	
	

}
