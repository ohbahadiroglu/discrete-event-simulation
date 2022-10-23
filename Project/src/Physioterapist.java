
public class Physioterapist extends Staff {
	
	private int iD;
	private double serviceDuration;
	
	public Physioterapist(int iD, double serviceDuration) {
		
		this.setiD(iD);
		this.setServiceDuration(serviceDuration);
	}

	public int getID() {
		return this.iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public double getServiceDuration() {
		return this.serviceDuration;
	}

	public void setServiceDuration(double serviceDuration) {
		this.serviceDuration = serviceDuration;
	}
	public String toString() {
		return "Physioterapist"+this.getID();
	}


}
