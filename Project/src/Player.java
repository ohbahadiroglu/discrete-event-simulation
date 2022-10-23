
public class Player {
	private double startingTime;
	private double endingTime;
	private int iD;
	private int skill;
	private double tPQEntranceTime;
	private double tPQLeavingTime;
	private double pPQEntranceTime;
	private double pPQLeavingTime;
	private double mPQEntranceTime;
	private double mPQLeavingTime;
	private double tEntranceTime;
	private double pEntranceTime;
	private double mEntranceTime;
	private double turnaroundEntranceTime;
	private double arrivalServiceDuration;
	private double totalTimePassedInPline = 0;
	private double totalTimePassedInMline = 0 ;
	private int massageToken = 3;
	private boolean available = true;
	
	public Player( int iD, int skill) {
		this.setID(iD);
		this.setSkill(skill);
	}
	
	public int getID() {
		return this.iD;
	}
	public void setID(int iD) {
		this.iD = iD;
	}
	public int getSkill() {
		return skill;
	}
	public void setSkill(int skill) {
		this.skill = skill;
	}

	public boolean isAvailable() {
		return this.available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}



	public int getMassageToken() {
		return massageToken;
	}

	public void setMassageToken(int massageToken) {
		this.massageToken = massageToken;
	}

	public String toString(){
		return "Player"+String.valueOf(this.getID()+"-Skill: " + this.getSkill() + " arrivalSerivceDuration: "+ this.getArrivalServiceDuration());
		
	}

	public double getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(double startingTime) {
		this.startingTime = startingTime;
	}

	public double getEndingTime() {
		return endingTime;
	}

	public void setEndingTime(double endingTime) {
		this.endingTime = endingTime;
	}

	public double gettPQEntranceTime() {
		return tPQEntranceTime;
	}

	public void settPQEntranceTime(double tPQEntranceTime) {
		this.tPQEntranceTime = tPQEntranceTime;
	}

	public double gettPQLeavingTime() {
		return tPQLeavingTime;
	}

	public void settPQLeavingTime(double tPQLeavingTime) {
		this.tPQLeavingTime = tPQLeavingTime;
	}

	public double getpPQEntranceTime() {
		return pPQEntranceTime;
	}

	public void setpPQEntranceTime(double pPQEntranceTime) {
		this.pPQEntranceTime = pPQEntranceTime;
	}

	public double getpPQLeavingTime() {
		return pPQLeavingTime;
	}

	public void setpPQLeavingTime(double pPQLeavingTime) {
		this.pPQLeavingTime = pPQLeavingTime;
	}

	public double getmPQEntranceTime() {
		return mPQEntranceTime;
	}

	public void setmPQEntranceTime(double mPQEntranceTime) {
		this.mPQEntranceTime = mPQEntranceTime;
	}

	public double getmPQLeavingTime() {
		return mPQLeavingTime;
	}

	public void setmPQLeavingTime(double mPQLeavingTime) {
		this.mPQLeavingTime = mPQLeavingTime;
	}

	public double getArrivalServiceDuration() {
		return arrivalServiceDuration;
	}

	public void setArrivalServiceDuration(double arrivalServiceDuration) {
		this.arrivalServiceDuration = arrivalServiceDuration;
	}

	public double gettEntranceTime() {
		return tEntranceTime;
	}

	public void settEntranceTime(double tEntranceTime) {
		this.tEntranceTime = tEntranceTime;
	}

	public double getpEntranceTime() {
		return pEntranceTime;
	}

	public void setpEntranceTime(double pEntranceTime) {
		this.pEntranceTime = pEntranceTime;
	}

	public double getmEntranceTime() {
		return mEntranceTime;
	}

	public void setmEntranceTime(double mEntranceTime) {
		this.mEntranceTime = mEntranceTime;
	}

	public double getTurnaroundEntranceTime() {
		return turnaroundEntranceTime;
	}

	public void setTurnaroundEntranceTime(double turnaroundEntranceTime) {
		this.turnaroundEntranceTime = turnaroundEntranceTime;
	}

	public double getTotalTimePassedInPline() {
		return totalTimePassedInPline;
	}

	public void addTotalTimePassedInPline(double duration) {
		this.totalTimePassedInPline += duration;
	}

	public double getTotalTimePassedInMline() {
		return totalTimePassedInMline;
	}

	public void addTotalTimePassedInMline(double duration) {
		this.totalTimePassedInMline += duration;
	}

}
