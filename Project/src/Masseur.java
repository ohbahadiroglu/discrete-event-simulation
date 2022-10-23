
public class Masseur extends Staff {
	
	private int iD;
	
	public Masseur(int iD) {
		this.setID(iD);
	}
	public int getID() {
		return this.iD;
	}
	public void setID(int iD) {
		this.iD = iD;
	}
	public String toString() {
		return "Masseur"+this.getID();
	}

}
