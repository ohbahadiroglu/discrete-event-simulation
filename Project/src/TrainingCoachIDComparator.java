
import java.util.Comparator;

public class TrainingCoachIDComparator implements Comparator<TrainingCoach> {

	@Override
	public int compare(TrainingCoach o1, TrainingCoach o2) {
		if (o1.getID() > o2.getID()) 
			return 1 ;
		else if (o1.getID() < o2.getID() ) 
			return -1 ;		
		return 0;
	}
}
