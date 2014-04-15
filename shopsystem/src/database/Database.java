package database;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.List;

import data.CartElement;

/**
 * Interface for RMI
 */
public interface Database extends Remote{
	
	public List getBooks(String bookCategory) throws RemoteException;
	
	/**
	 * Use only for queries. Executes the given database-operation.
	 * @param query - Database-operation to execute
	 * @return ResultSet if there is any else return null
	 */
	public ResultSet executeQuery(String query) throws RemoteException;
	
	/**
	 * Use only for new users. Writes information to database and execute needed steps to assign values.
	 * @param data
	 */
	public void recordNewCustomer(String[] data) throws RemoteException;
	
	public String getCustomerID(String customerLogin) throws RemoteException;
	
	public String getCustomerAttribute(String customerID, String attriName) throws RemoteException;
	
	public Object getBookAttribute(String bookID, String attriName) throws RemoteException;
	
	public void setCustomerAttribute(String customerID, String attriName, String attriValue) throws RemoteException;
	
	public void recordNewOrder(String customerID, List<CartElement> cartContent) throws RemoteException;
}
