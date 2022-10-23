
import java.util.Comparator;

public class TrainingLineComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if (Math.abs(o1.gettPQEntranceTime() - o2.gettPQEntranceTime()) < 0.0000000001 )
			return ( o1.getID() - o2.getID() ) ;
		else {
			if (o1.gettPQEntranceTime() > o2.gettPQEntranceTime()) 
				return 1 ;
			else if (o1.gettPQEntranceTime()  < o2.gettPQEntranceTime() ) 
				return -1 ;		
			return 0;
		}
	}
}

