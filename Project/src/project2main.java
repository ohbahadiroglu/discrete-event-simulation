import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class project2main {
	
	public static int numberOfPlayers;
	public static double currentTime = 0;
	public static int numberOfArrivals = 0;
	public static int numberOfPhysioterapists = 0;
	public static int numberOfTcoachs = 0;
	public static int numberOfMasseur = 0;
	public static int invalidAttempts = 0;
	public static int canceledAttempts = 0;
	public static int maxTrainingLineLenght=0;
	public static int maxPhysioLineLenght=0;
	public static int maxMassageLineLenght=0;
	public static int idOfPlayerMTSPL = 0;
	public static double mostTimeSpentPline = 0;
	public static int idOfPlayerLTSML = -1 ;
	public static double leastTimeSpentMline = Double.POSITIVE_INFINITY ;
	
	public static ArrayList<Double> allWaitingTimesTLine = new ArrayList<Double>();
	public static ArrayList<Double> allWaitingTimesPLine = new ArrayList<Double>();
	public static ArrayList<Double> allWaitingTimesMLine = new ArrayList<Double>();
	public static ArrayList<Double> allTrainingDuration = new ArrayList<Double>();
	public static ArrayList<Double> allTerapyDuration = new ArrayList<Double>();
	public static ArrayList<Double> allMassageDuration = new ArrayList<Double>();
	public static ArrayList<Double> allTurnarounds = new ArrayList<Double>();


	static HashMap<Integer,Player> players = new HashMap<Integer,Player>();	
	static PriorityQueue<TrainingCoach> coachTeaRoom = new PriorityQueue<TrainingCoach>( 10, new TrainingCoachIDComparator() );
	static PriorityQueue<Physioterapist> terapistTeaRoom = new PriorityQueue<Physioterapist>( 10, new PhysiotIDComparator() );
	static PriorityQueue<Masseur> masseurTeaRoom = new PriorityQueue<Masseur>( 10,new MasseurIDComparator() );
	static PriorityQueue<Player> trainingLine = new PriorityQueue<Player>( 10,new TrainingLineComparator() );
	static PriorityQueue<Player> physioLine = new PriorityQueue<Player>( 10, new PhysioLineComparator() );
	static PriorityQueue<Player> massageLine = new PriorityQueue<Player>( 10, new MassageLineComparator() );	
	static PriorityQueue<Double> eventSchedulePQ = new PriorityQueue<Double>(10, new EventTimeComparator() )	;
	static PriorityQueue<EventInProgress> inProgressEvents = new PriorityQueue<>(10 , new EventInProgressComparator() ); 
	static PriorityQueue<Arrival> arrivals = new PriorityQueue<Arrival>(10, new ArrivalComparator() )	;

	public static void main(String[] args) throws FileNotFoundException {
	

		File inputText = new File(args[0]);
		PrintStream outstream = new PrintStream(args[1]);
		Scanner reader = new Scanner(inputText);
		
		numberOfPlayers = reader.nextInt();
		for (int i = 0; i < numberOfPlayers ; i++ ) {
			int tempId = reader.nextInt();
			int tempSkill = reader.nextInt();
			Player tempPlayer = new Player(tempId,tempSkill);
			players.put(tempId, tempPlayer);
			}
		
		numberOfArrivals = reader.nextInt();

		reader.nextLine();
		for (int i = 0; i < numberOfArrivals; i++ ) {
			
			String line = reader.nextLine();
			String[] lineElements = line.split(" ");
			if (lineElements[0].equals("t") ) {
				int playerId = Integer.parseInt(lineElements[1]);
				double traArrivalTime = Double.parseDouble(lineElements[2]);
				double trainingDuration = Double.parseDouble(lineElements[3]);
				Player tmpPlayer = players.get(playerId);
				Arrival tmpArrival = new TrainingArrival(traArrivalTime,trainingDuration,tmpPlayer);
				arrivals.add(tmpArrival);
				eventSchedulePQ.add(traArrivalTime);
				
			}  
			
			if ( lineElements[0].equals("m") ) {	

				int playerId = Integer.parseInt(lineElements[1]);
				double masArrivalTime = Double.parseDouble(lineElements[2]);
				double massageDuration = Double.parseDouble(lineElements[3]);
				Player tmpPlayer = players.get(playerId);
				Arrival tmpArrival = new MassageArrival(masArrivalTime,massageDuration,tmpPlayer);
				arrivals.add(tmpArrival);
				eventSchedulePQ.add(masArrivalTime);

			}
			
			
		}
		

		numberOfPhysioterapists = reader.nextInt();
		for (int i = 0 ; i < numberOfPhysioterapists ; i++) {
			
			int iD = i; 
			double serviceDuration = reader.nextDouble();
			
			Physioterapist pt = new Physioterapist(iD,serviceDuration);
			terapistTeaRoom.add(pt);
			
		} 
		
		
		numberOfTcoachs = reader.nextInt();
		for (int i = 0 ; i < numberOfTcoachs ; i++) {
			int iD = i;		
			TrainingCoach tc = new TrainingCoach(iD);
			coachTeaRoom.add(tc);
		}
		
			
		numberOfMasseur = reader.nextInt();
		for (int i = 0 ; i < numberOfMasseur ; i++) {
			int iD = i;
			Masseur m = new Masseur(iD);
			masseurTeaRoom.add(m);	
		}
		
		
		
		reader.close();
		
		while ( eventSchedulePQ.peek() != null ) {
			
			currentTime = eventSchedulePQ.poll();
			while (eventSchedulePQ.peek() != null && Math.abs(eventSchedulePQ.peek() - currentTime) < 0.0000000001) {
				eventSchedulePQ.poll();
			}
			while( ( inProgressEvents.peek() != null ) &&( Math.abs(inProgressEvents.peek().getEndingTime()-currentTime) < 0.0000000001 ) ) {
				
				EventInProgress tmpEvent = inProgressEvents.poll();
				Player tmpPlayer = tmpEvent.getPlayer();
				Staff tmpStaff = tmpEvent.getStaff();
				
				if (tmpStaff.getClass().getName() == "TrainingCoach") {
					double timePassedInTraining = tmpPlayer.getArrivalServiceDuration();
					allTrainingDuration.add(timePassedInTraining);
					tmpPlayer.setpPQEntranceTime(currentTime);
					physioLine.add(tmpPlayer);
					coachTeaRoom.add((TrainingCoach) tmpStaff);
					
					
				}else if ( tmpStaff.getClass().getName() == "Masseur") {
					double timePassedInMassage = tmpPlayer.getArrivalServiceDuration();
					allMassageDuration.add(timePassedInMassage);
					tmpPlayer.setAvailable(true);
					masseurTeaRoom.add( (Masseur) tmpStaff);

				}else {
					Physioterapist tmpTerapist = (Physioterapist) tmpStaff;
					double timePassedInTerapy = tmpTerapist.getServiceDuration();
					allTerapyDuration.add(timePassedInTerapy);
					double totalTurnaroundDuration = currentTime - tmpPlayer.getTurnaroundEntranceTime();
					allTurnarounds.add(totalTurnaroundDuration);
					tmpPlayer.setAvailable(true);
					terapistTeaRoom.add(tmpTerapist);
				}
					
			}

			
			
			while ( ( arrivals.peek()!=null ) && ( Math.abs(arrivals.peek().getArrivalTime() - currentTime) < 0.0000000001 ) ){
				
				
				
				Arrival tmpArrival = arrivals.poll();
				Player tmpP= tmpArrival.getRelatedPlayer();
				
				
				
				if (tmpArrival.getClass().getName() == "TrainingArrival") {
					
					if (tmpP.isAvailable() == false ) {
						canceledAttempts++;
						continue;
					}
					double trainingDuration = tmpArrival.getArrivalServiceDuration();
					tmpP.setArrivalServiceDuration(trainingDuration);
					tmpP.setAvailable(false);
					tmpP.settPQEntranceTime(currentTime);
					tmpP.setTurnaroundEntranceTime(currentTime);
					trainingLine.add(tmpP);	
							
				}
				
				
				if (tmpArrival.getClass().getName() == "MassageArrival") {
					if (tmpP.getMassageToken() <= 0) {
						invalidAttempts++;
						continue;
					}
					if (tmpP.isAvailable() == false ) {
						canceledAttempts++;
						continue;
					}
					int token = tmpP.getMassageToken();
					token--;
					tmpP.setMassageToken(token);
					MassageArrival tmpMasArrival = (MassageArrival) tmpArrival;
					double massageDuration = tmpMasArrival.getArrivalServiceDuration();
					tmpP.setArrivalServiceDuration(massageDuration);				
					tmpP.setAvailable(false);
					tmpP.setmPQEntranceTime(currentTime);
					massageLine.add(tmpP);					
					
				}
			}
			while (    ( coachTeaRoom.peek() != null )   &&   ( trainingLine.peek() != null )   ) {
				Player tmpPlayer = trainingLine.poll();
				Staff tmpStaff = coachTeaRoom.poll();
				EventInProgress tmpEvent = new EventInProgress(tmpPlayer,tmpStaff);
				double endingTime = (currentTime + tmpPlayer.getArrivalServiceDuration());
				tmpPlayer.settPQLeavingTime(currentTime);
				double timePassedInLine = tmpPlayer.gettPQLeavingTime() - tmpPlayer.gettPQEntranceTime() ;
				allWaitingTimesTLine.add(timePassedInLine);
				tmpEvent.setEndingTime(endingTime);
				inProgressEvents.add(tmpEvent);
				eventSchedulePQ.add(endingTime);

			}
			while (    ( terapistTeaRoom.peek() != null )   &&   ( physioLine.peek() != null )   ) {
				Player tmpPlayer = physioLine.poll();
				Physioterapist tmpTerapist = terapistTeaRoom.poll();
				EventInProgress tmpEvent = new EventInProgress(tmpPlayer,tmpTerapist);
				double endingTime = ( currentTime + tmpTerapist.getServiceDuration() );
				tmpPlayer.setpPQLeavingTime(currentTime);
				double timePassedInLine = tmpPlayer.getpPQLeavingTime() - tmpPlayer.getpPQEntranceTime() ;	
				tmpPlayer.addTotalTimePassedInPline(timePassedInLine);
				allWaitingTimesPLine.add(timePassedInLine);
				tmpEvent.setEndingTime(endingTime);
				inProgressEvents.add(tmpEvent);
				eventSchedulePQ.add(endingTime);
			}
			while (    ( masseurTeaRoom.peek() != null )   &&   ( massageLine.peek() != null )   ) {
				Player tmpPlayer = massageLine.poll();
				Staff tmpStaff = masseurTeaRoom.poll();
				EventInProgress tmpEvent = new EventInProgress(tmpPlayer,tmpStaff);
				double endingTime = (currentTime + tmpPlayer.getArrivalServiceDuration());
				tmpPlayer.setmPQLeavingTime(currentTime);
				double timePassedInLine = tmpPlayer.getmPQLeavingTime() - tmpPlayer.getmPQEntranceTime() ;
				tmpPlayer.addTotalTimePassedInMline(timePassedInLine);
				allWaitingTimesMLine.add(timePassedInLine);
				tmpEvent.setEndingTime(endingTime);
				inProgressEvents.add(tmpEvent);
				eventSchedulePQ.add(endingTime);
			}
			if (maxPhysioLineLenght < physioLine.size()) {
				maxPhysioLineLenght = physioLine.size() ;
			}
			if (maxTrainingLineLenght < trainingLine.size()) {
				maxTrainingLineLenght = trainingLine.size() ;
			}
			if (maxMassageLineLenght < massageLine.size()) {
				maxMassageLineLenght = massageLine.size() ;
			}
			
		}
		
		
		for (Player tmpPlayer : players.values() ) {
			double tmpTotal=tmpPlayer.getTotalTimePassedInPline();
			if (tmpTotal > mostTimeSpentPline) {
				idOfPlayerMTSPL = tmpPlayer.getID();
				mostTimeSpentPline = tmpPlayer.getTotalTimePassedInPline();
			}else if (tmpTotal == mostTimeSpentPline) { 
				if (tmpPlayer.getID() < idOfPlayerMTSPL ) {
					idOfPlayerMTSPL = tmpPlayer.getID();
				}
			}
		}
		
		boolean checker = false;
		for (Player tmpPlayer : players.values() ) {
			double tmpTotal=tmpPlayer.getTotalTimePassedInMline();
			if(tmpPlayer.getMassageToken() <= 0) {	
				checker = true;
				if (tmpTotal < leastTimeSpentMline) {
					idOfPlayerLTSML = tmpPlayer.getID();
					leastTimeSpentMline = tmpPlayer.getTotalTimePassedInMline();
				}
				else if (tmpTotal == leastTimeSpentMline) { 
					
					if (tmpPlayer.getID() < idOfPlayerLTSML ) {
						idOfPlayerLTSML = tmpPlayer.getID();
					}
					
				}
			}
		}
		if (checker == false) {
			leastTimeSpentMline = -1;
			idOfPlayerLTSML = -1;
		}
			
		outstream.println(maxTrainingLineLenght);
		outstream.println(maxPhysioLineLenght);
		outstream.println(maxMassageLineLenght);
		outstream.printf( "%.3f" , getAverage(allWaitingTimesTLine) );
		outstream.println();
		outstream.printf( "%.3f" , getAverage(allWaitingTimesPLine) );
		outstream.println();
		outstream.printf( "%.3f" , getAverage(allWaitingTimesMLine) );
		outstream.println();
		outstream.printf( "%.3f" , getAverage(allTrainingDuration) );
		outstream.println();
		outstream.printf( "%.3f" , getAverage(allTerapyDuration) );
		outstream.println();
		outstream.printf( "%.3f" , getAverage(allMassageDuration) );
		outstream.println();
		outstream.printf( "%.3f" , getAverage(allTurnarounds) );
		outstream.println();
		outstream.printf( idOfPlayerMTSPL+" "+"%.3f",mostTimeSpentPline );
		outstream.println();
		outstream.printf( idOfPlayerLTSML+" "+"%.3f",leastTimeSpentMline );
		outstream.println();
		outstream.println(invalidAttempts);
		outstream.println(canceledAttempts);
		outstream.printf("%.3f",currentTime);

		

			
		
		outstream.close();
	}
	public static double getAverage(ArrayList<Double> l) {
		if (l.size() == 0)
			return 0;
		double sum=0;
		for (double el : l) 
			sum += el;
		return (sum / l.size()) ;
	}

}
