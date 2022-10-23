
public class MassageArrival extends Arrival{
	MassageArrival(double arrivalTime,double massageDuration , Player p){
		super.setArrivalTime(arrivalTime);
		super.setArrivalServiceDuration(massageDuration);
		super.setRelatedPlayer(p);
	}
	
}
