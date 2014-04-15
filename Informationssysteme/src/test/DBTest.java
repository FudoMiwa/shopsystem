package test;

import java.util.Iterator;
import java.util.List;

import de.imut.ec.bshop.Bestellung;
import de.imut.ec.bshop.Buch;
import de.imut.ec.bshop.DBUtil;
import de.imut.ec.bshop.Kunde;
import de.imut.ec.bshop.Position;

public class DBTest {


	public static void main(String[] args) {
		
		int gruppennr = 7;
		
		DBUtil db = new DBUtil();
		
		// Warengruppen ausgeben
		List<String> lw;
		System.out.println("Warengruppen ausgeben:");
		lw = db.findWarengruppen(gruppennr);		
		for (String wg: lw) {
			System.out.println(wg);
		}		
		//System.out.println();
	
		
		// Buecher einer Warengruppe ausgeben
		System.out.println("Alle Krimis ausgeben:");
		List<Buch> lb;
		lb = db.findBuecherByWarengruppe("Krimi",gruppennr);
		
		for (Buch b : lb) {
			System.out.println(b.getId()+". "+b.getBezeichnung());
		}
		System.out.println();
		
		
		// Buch ausgeben
		System.out.println("Buch mit Id 1 ausgeben:");
		Buch buch = db.findBuchById(1);
		System.out.println(buch.getAutor()+": "+buch.getBezeichnung());
		System.out.println();
		
		
		// Kunde hinzufuegen
		// ID wird automatisch vergeben
		System.out.println("Kunde hinzufuegen:");		
		//public Kunde(String vorname, String name, String plz, String ort,
    	//		String strasse, String zahlungsart, int gruppennr) ...        
		Kunde ku = new Kunde("Max","Mustermann","26723","Emden","Musterweg 3",
        		"Nachnahme",0);      
		ku.setVorname("huso");
		// optional bei Kartenzahlung:
        ku.setKartennr("0123012301230123");
        ku.setGueltigbis("12/10");
     

		int id = db.persistKunde(ku);
		System.out.println("Kunde mit Id "+id+" hinzugefuegt.");
		System.out.println();
		

		// Bestellung hinzufuegen
		// ID wird automatisch vergeben
		// Verwendung der KundenID
		System.out.println("Bestellung hinzufuegen:");
		// public Bestellung(Integer kdnr, int gruppennr) ...
		Bestellung b = new Bestellung(id, 0);
		int bid = db.persistBestellung(b);
		System.out.println("Bestellung mit Id "+bid+" hinzugefuegt.");
		System.out.println();
		
		
		// Position zu einer Bestellung hinzufuegen
		// Verwendung der ID der Bestellung
		System.out.println("Position hinzufuegen:");
		// public Position(int bestnr, int buchnr, int menge, int gruppennr) ...
		Position p = new Position(bid, 1, 2, 0);
		db.persistPosition(p);
		System.out.println("Position hinzugefuegt.");
	}

}