package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private static Connection connection;
	
	public static List<Book> getBooks(String bookCategory){
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
	public static ResultSet executeQuery(String query){
		ResultSet ret = null;
		try{
			ret = getConnection().createStatement().executeQuery(query);
		} catch (SQLException e) {e.printStackTrace();}
		
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
	public static void recordNewCustomer(String[] data){
		String command = "INSERT INTO kunde VALUES(null";
		
		for(String string : data)
			command += ",'" +string+ "'";
		
		command += ");";
		
		Database.execute(command);
	}
	
	
	public static String getCustomerID(String customerLogin){
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
				connection = DriverManager.getConnection("jdbc:mysql://localhost/amazon_light","root","java");
			} catch (SQLException e) {e.printStackTrace();}
		}
		
		return connection;
	}
	
	public static String getCustomerAttribute(String customerID, String attriName){
		return (String) getAttribute("kunde", customerID, attriName);
	}
	
	public static Object getBookAttribute(String bookID, String attriName){
		return getAttribute("buch", bookID, attriName);
	}
	
	public static void setCustomerAttribute(String customerID, String attriName, String attriValue){
		execute("UPDATE kunde SET " +attriName+ " = '" +attriValue+ "' WHERE ID = '" +customerID+ "';");
	}
	
	private static Object getAttribute(String table, String id, String attriName){
		String query = "SELECT " +attriName+ " FROM " +table+ " WHERE ID ='" +id+ "';";
		ResultSet rs = Database.executeQuery(query);
		Object ret = null;
		
		try {
			rs.next();
			ret = rs.getObject(1);
		} catch (SQLException e) { e.printStackTrace(); }

		return ret;
	}
	
	public static void recordNewOrder(Customer customer, ShoppingCart cart){
		execute("INSERT INTO bestellung VALUES(NULL,'" +customer.getCustomerID()+ "');");
		ResultSet rs = Database.executeQuery("SELECT ID FROM bestellung WHERE `Kunden-Nr.` = '" +customer.getCustomerID()+ "';");
		String orderID = null;
		
		try {
			rs.last();
			orderID = rs.getString(1);
		} catch (SQLException e) {e.printStackTrace();	}
		
		for(CartElement elem : cart.getCartContent()){
			String bla = "INSERT INTO position VALUES('" +orderID+ "','" +elem.getBook().getID()+ "','" +elem.getQuantity()+ "');";
			Database.execute(bla);
		}
	}
	
}
