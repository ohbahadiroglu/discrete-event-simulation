
import java.util.Comparator;

public class PhysiotIDComparator implements Comparator<Physioterapist> {

	@Override
	public int compare(Physioterapist o1, Physioterapist o2) {
		if (o1.getID() > o2.getID()) 
			return 1 ;
		else if (o1.getID() < o2.getID() ) 
			return -1 ;		
		return 0;
	}
}
