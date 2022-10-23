
public class EventInProgress {
	private Player player;
	private Staff staff;
	private double endingTime;
	EventInProgress(Player p , Staff s){
		this.setPlayer(p);
		this.setStaff(s);
		
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public double getEndingTime() {
		return endingTime;
	}
	public void setEndingTime(double endingTime2) {
		this.endingTime = endingTime2;
	}
}
