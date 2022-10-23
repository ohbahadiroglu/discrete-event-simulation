
import java.util.Comparator;

public class PhysioLineComparator implements Comparator<Player>{
	@Override
	public int compare(Player o1, Player o2) {		
		if (o1.getArrivalServiceDuration() > o2.getArrivalServiceDuration() )
			return -1;
		else if ( o1.getArrivalServiceDuration() < o2.getArrivalServiceDuration() )
			return 1;
		else {
			if(Math.abs(o1.getpPQEntranceTime() - o2.getpPQEntranceTime() ) < 0.0000000001 ) {
				return ( o1.getID() - o2.getID() ); 
			}else {
				if(o1.getpPQEntranceTime() > o2.getpPQEntranceTime() )
					return 1;
				else if (o1.getpPQEntranceTime() < o2.getpPQEntranceTime() )
					return -1;
				return 0; // never return from here
			}
		}
	}
}
