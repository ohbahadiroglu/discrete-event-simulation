
import java.util.Comparator;
public class EventInProgressComparator implements Comparator<EventInProgress> {

	@Override
	public int compare(EventInProgress o1, EventInProgress o2) {
		if (Math.abs(o1.getEndingTime() - o2.getEndingTime()) < 0.0000000001 )
			return 0;
		else {
			if (o1.getEndingTime() > o2.getEndingTime() )
				return 1;
			else if (o1.getEndingTime() < o2.getEndingTime() )
				return -1;
			return 0;
		}
	}	
}
