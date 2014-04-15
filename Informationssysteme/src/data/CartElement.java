package data;

import de.imut.ec.bshop.Buch;

public class CartElement {

	private Buch buch;
	private int quantity;
	
	
	
	public CartElement(Buch buch, int quantity){
		this.buch = buch;
		this.quantity = quantity;
	}
	
	public boolean equals(Object o){
		if(o instanceof CartElement && this.getBuch().getId() == ((CartElement) o).getBuch().getId()){
			return true;
		}else{
			return false;
		}	
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public double getCost(){
		return buch.getPreis() * this.quantity;
	}
	
	
	public Buch getBuch(){
		return buch;
	}
	
}
