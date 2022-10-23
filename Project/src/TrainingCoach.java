
public class TrainingCoach extends Staff {
	
	private int iD;
	
	public TrainingCoach(int iD) {
		this.setID(iD);
	}
	public int getID() {
		return this.iD;
	}
	public void setID(int iD) {
		this.iD = iD;
	}
	public String toString() {
		return "TrainingCoach"+this.getID();
	}

}
