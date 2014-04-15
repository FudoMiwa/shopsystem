package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import de.imut.ec.bshop.Buch;

public class MyDBUtil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement ps;
	ResultSet result;
	
	//Methode zur Verbindungsherstellung
	private Connection OpenCon() throws Exception{
		
		//Treiber zur Laufzeit geladen - Class.forName(Treibername)
		Class.forName("com.mysql.jdbc.Driver");
	    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bshop", "root","");
	     
	    return connection;	
	}
	
	
	public List<String> findWarengruppen(int gruppennr){
		
		List<String> list = new LinkedList<String>();
		try{	
			con = OpenCon();
			ps = con.prepareStatement("select distinct warengruppe from buch where gruppennr =" +  gruppennr); 
			//Ausfuehrung SQL Abfrage executeQuery(String sql)
			result =  ps.executeQuery();
			
			//Durchlauf - stellt naechsten Datensatz zur Verfuegung
			while(result.next()){		
				list.add(result.getString("warengruppe"));		
			}			
		}catch(Exception e){	
			System.out.println("Fehler beim auslesen der Warengruppe: " + e);		
		} 
		
		finally{		 
			
			try {
				con.close();		
			}catch (SQLException e) {	
				System.out.println("Fehler beim Schlieﬂen der Verbindung: " + e);	
			}
		}
		return list;		
	}
	
	
	public List<Buch> findBuecherByWarengruppe(String warengruppe){
		
		Buch buch;
		List<Buch> list = new LinkedList<Buch>();
		
		try{
			
			con = OpenCon();
			System.out.println("select * from buch where warengruppe =" +  warengruppe);
			ps = con.prepareStatement("select * from buch where warengruppe = '" +  warengruppe + "'"); 
			result =  ps.executeQuery();
			
			while(result.next()){
				
				buch = new Buch();
				
				buch.setWarengruppe(result.getString("warengruppe"));
				buch.setBezeichnung(result.getString("bezeichnung"));
				buch.setPreis(result.getDouble("preis"));
				buch.setVerlag(result.getString("verlag"));
				buch.setAutor(result.getString("autor"));
				buch.setBeschreibung(result.getString("beschreibung"));
				buch.setBild(result.getString("bild"));	
				buch.setIsbn(result.getString("isbn"));
				buch.setGruppennr(result.getInt("gruppennr"));
				
				list.add(buch);		
			}
			
		} catch(Exception e){	
			System.out.println("Fehler beim Finden der Buecher: " + e);		
		}	
		finally{		 
			try {
				con.close();			
			}catch (SQLException e) {				
				System.out.println("Fehler beim Schlieﬂen der Verbindung: " + e);			
			}
		}		
		return list;		
	}
}