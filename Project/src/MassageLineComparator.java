
import java.util.Comparator;

public class MassageLineComparator implements Comparator<Player>{
	@Override
	public int compare(Player o1,Player o2) {
		if (o1.getSkill() > o2.getSkill())
			return -1;
		else if (o1.getSkill() < o2.getSkill() )
			return 1;
		else {
			if(Math.abs(o1.getmPQEntranceTime() - o2.getmPQEntranceTime()) < 0.0000000001 ) {
				return ( o1.getID() - o2.getID() ); 
			}else {
				if(o1.getmPQEntranceTime() > o2.getmPQEntranceTime() )
					return 1;
				else if (o1.getmPQEntranceTime() < o2.getmPQEntranceTime() )
					return -1;
				return 0; // will never return from here
			}	
		}
	}
}
