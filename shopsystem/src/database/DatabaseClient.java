package database;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DatabaseClient{
	public final static String RMI_URI = "localhost";
	public final static String RMI_DATABASE_LOOKUPNAME = "Database";
	
	public static Database getStub(){
		Database database = null;
		try{
			Registry registry = LocateRegistry.getRegistry(RMI_URI);
			database = (Database) registry.lookup(RMI_DATABASE_LOOKUPNAME);
		}catch(Exception e){
			System.err.println("Book exception:");
			e.printStackTrace();
		}
		
		return database;
	}
}
