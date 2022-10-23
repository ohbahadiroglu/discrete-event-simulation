
import java.util.Comparator;

public class MasseurIDComparator implements Comparator<Masseur> {

	@Override
	public int compare(Masseur o1, Masseur o2) {
		if (o1.getID() > o2.getID()) 
			return 1 ;
		else if (o1.getID() < o2.getID() ) 
			return -1 ;	
		return 0;
	}
}
