
import java.util.Comparator;

public class EventTimeComparator implements Comparator<Double>{

	@Override
	public int compare(Double o1, Double o2) {
		if ( Math.abs(o1-o2) < 0.0000000001)
			return 0;
		else {
			if ( o1 > o2 )
				return 1;
			else if ( o1 < o2)
				return -1;
			return 0;
		}
	}

}
