public abstract class Arrival {
	
	private double arrivalTime;
	private Player relatedPlayer;
	private double arrivalServiceDuration;
	

	public Player getRelatedPlayer() {
		return this.relatedPlayer;
	}

	public void setRelatedPlayer(Player relatedPlayer) {
		this.relatedPlayer = relatedPlayer;
	}


	public double getArrivalTime() {
		return this.arrivalTime;
	}


	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public String toString() {
		return this.getClass().getName()+"-->arrivalTime " +this.getArrivalTime() + " "+this.getRelatedPlayer()  ;
	}

	public double getArrivalServiceDuration() {
		return arrivalServiceDuration;
	}

	public void setArrivalServiceDuration(double arrivalServiceDuration) {
		this.arrivalServiceDuration = arrivalServiceDuration;
	}
	

}



