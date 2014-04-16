package database;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.sun.rowset.CachedRowSetImpl;

import data.Book;
import data.CartElement;

public class DatabaseMiddleware implements Database {

	private static Connection connection;
	
	public List<Book> getBooks(String bookCategory){
		ResultSet rs = executeQuery("SELECT ID FROM buch WHERE Warengruppe = '" +bookCategory+ "'");
		List<Book> list = new LinkedList<>();
		
		try {
			while(rs.next())
				list.add(new Book(rs.getString("ID")));
		} catch (SQLException e) {e.printStackTrace();}
		
		return list;
	}
	
	/**
	 * Use only for queries. Executes the given database-operation.
	 * @param query - Database-operation to execute
	 * @return ResultSet if there is any else return null
	 */
	public CachedRowSetImpl executeQuery(String query){
		CachedRowSetImpl ret = null;
		try{
			ret = new CachedRowSetImpl();
			ret.populate(getConnection().createStatement().executeQuery(query));
		} catch (SQLException e) {e.printStackTrace();}
		
		System.out.println("done");
		return ret;
	}
	
	/**
	 * Executes any database-operation.
	 * @param sqlCommand - Database-operation to execute
	 */
	private static void execute(String sqlCommand){
		try{
			getConnection().createStatement().execute(sqlCommand);
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	/**
	 * Use only for new users. Writes information to database and execute needed steps to assign values.
	 * @param data
	 */
	public void recordNewCustomer(String[] data){
		String command = "INSERT INTO kunde VALUES(null";
		
		for(String string : data)
			command += ",'" +string+ "'";
		
		command += ");";
		
		execute(command);
	}
	
	public String getCustomerID(String customerLogin){
		ResultSet rs = executeQuery("SELECT ID FROM kunde WHERE Login = '" +customerLogin+ "';");
		String ret = null;
		
		try {
			rs.next();
			ret = rs.getString(1);
		} catch (SQLException e) {e.printStackTrace();}
		
		return ret;
	}
	
	private static Connection getConnection() {
		
		if(connection == null){
			try {
				connection = DriverManager.getConnection("jdbc:mysql://192.168.178.20/amazon_light","root","java");
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		return connection;
	}
	
	public String getCustomerAttribute(String customerID, String attriName){
		return (String) getAttribute("kunde", customerID, attriName);
	}
	
	public Object getBookAttribute(String bookID, String attriName){
		return getAttribute("buch", bookID, attriName);
	}
	
	public void setCustomerAttribute(String customerID, String attriName, String attriValue){
		execute("UPDATE kunde SET " +attriName+ " = '" +attriValue+ "' WHERE ID = '" +customerID+ "';");
	}
	
	private Object getAttribute(String table, String id, String attriName){
		String query = "SELECT " +attriName+ " FROM " +table+ " WHERE ID ='" +id+ "';";
		ResultSet rs = executeQuery(query);
		Object ret = null;
		
		try {
			rs.next();
			ret = rs.getObject(1);
		} catch (SQLException e) { e.printStackTrace(); }

		return ret;
	}
	
	public void recordNewOrder(String customerID, List<CartElement> cartContent){
		execute("INSERT INTO bestellung VALUES(NULL,'" +customerID+ "');");
		ResultSet rs = executeQuery("SELECT ID FROM bestellung WHERE `Kunden-Nr.` = '" +customerID+ "';");
		String orderID = null;
		
		try {
			rs.last();
			orderID = rs.getString(1);
		} catch (SQLException e) {e.printStackTrace();	}
		
		for(CartElement elem : cartContent){
			String bla = "INSERT INTO position VALUES('" +orderID+ "','" +elem.getBook().getID()+ "','" +elem.getQuantity()+ "');";
			execute(bla);
		}
	}
	
	public static void main(String[] args) {
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		
		try{
			String name = "Database";
			Database engine = new DatabaseMiddleware();
			Database stub = (Database) UnicastRemoteObject.exportObject(engine, 0);
			Registry reg = LocateRegistry.getRegistry();
			reg.rebind(name, stub);
			System.out.println("DatabaseEngine bound");
		}catch(Exception e){
			System.err.println("DatabaseEngine exception:");
			e.printStackTrace();
		}
	}
}
