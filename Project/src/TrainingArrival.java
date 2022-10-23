
public class TrainingArrival extends Arrival {
	TrainingArrival(double arrivalTime,double trainingDuration , Player p){
		super.setArrivalTime(arrivalTime);
		super.setArrivalServiceDuration(trainingDuration);
		super.setRelatedPlayer(p);
	}
	
}
