
import java.util.Comparator;

public class ArrivalComparator implements Comparator<Arrival>{

	@Override
	public int compare(Arrival o1, Arrival o2) {
		if (o1.getArrivalTime() > o2.getArrivalTime())
			return 1;
		else if ( o1.getArrivalTime() < o2.getArrivalTime() )
			return -1;
		else {
			if ( o1.getRelatedPlayer().getID() > o2.getRelatedPlayer().getID() )
				return 1;
			else if (o1.getRelatedPlayer().getID() < o2.getRelatedPlayer().getID() )
				return -1;
			return 0;

		}
		
	}

}
