import java.util.*;
/**
 * The chart manager adds and removes individuals and groups from the seating chart.
 * @author Bill Li
 *
 */
public class ChartManager {
	
	ArrayList<ArrayList<Seat>> chart;
	
	/**
	 * Constructs a chart manager with the seating chart data structure.
	 * @param chart - Seating Chart data structure
	 */
	public ChartManager(ArrayList<ArrayList<Seat>> chart) {
		this.chart = chart;
	}
	
	/**
	 * Adds a single passenger to the seating chart.
	 * @param name - name of passenger
	 * @param service - type of class service (First, Economy)
	 * @param seat - type of seat ((W)indow, (A)isle, (C)enter)
	 */
	public void addSingle(String name, String service, String seat) {
		
		//boolean canChooseAnother = false; //if can choose another seating type of the same class

		//goes through to check if can fill
		boolean done = false;
		for(int i = 0; i < chart.size(); i++) {
			if(done)
				break;
			for(int j = 0; j < chart.get(i).size(); j++) {
				
				Seat tempSeat = chart.get(i).get(j);
				
				if((service.equals(tempSeat.classType) && tempSeat.isFull == false)) {
					//canChooseAnother = true;
				}
				//System.out.print(tempSeat.seatType); //debug code
				//System.out.println(tempSeat.classType); //debug code

				if((service.equals(tempSeat.getClassType()) &&  //must be same class, seat type and open
						seat.equals(tempSeat.getSeatType()) && tempSeat.isFull == false)){
					tempSeat.fill(name);
					System.out.println(IJToNumber(i,j));
					done = true; 
					break;
					
				}
			}
		}
		if(done == false)
			System.out.println("No seat of your preference is available, please choose another seat preference");
	}
	
	/**
	 * Adds a group to the seating chart.
	 * @param groupName - name of group
	 * @param names - names of passengers
	 * @param serviceClass - type of class service (First, Economy)
	 * Precondition: passenger names are separated by a comma ", "
	 */
	public void addGroup(String groupName, String names, String serviceClass) {
		//parse names and push into stack
		String[] nameList = names.split("\\, ");
		
		Stack<String> nameStack = new Stack();
		for(int i = nameList.length-1; i >= 0; i--) {
			nameStack.push(nameList[i]);
		}
		
		//Request Failed if not enough spots
		if(SeatingChartReader.availabilityCount(chart, serviceClass) < nameList.length) {
			System.out.println("Request Failed");
			return;
		}
		
		//goes through to check if can fill
		int max = 0;
		int maxI = 0;
		int maxJ = 0;
		int counter = 0;
		int startI = 0;
		int startJ = 0;
		boolean done = false;
		while(!nameStack.isEmpty()) {
			
			//find row with largest adjacent empty seats
			for(int i = 0; i < chart.size(); i++) {
				
				if(counter > max) {
					max = counter;
					maxI = startI;
					maxJ = startJ;
				}
				counter = 0;
				
				if(done)
					break;
				
				for(int j = 0; j < chart.get(i).size(); j++) {
					Seat tempSeat = chart.get(i).get(j);
					if(!tempSeat.isFull() && tempSeat.getClassType().equals(serviceClass)) {					//add 1 for another adjacency
						counter++;
						if(counter == 1) {
							startI = i;
							startJ = j;
						}
					}
					else if(tempSeat.isFull()) {				//only keep if adjacency is bigger than previous rows
						if(counter > max) {
							max = counter;
							maxI = startI;
							maxJ = startJ;
						}
						counter = 0;
						startI = 0;
						startJ = 0;
					}			
					if(counter == nameStack.size()+1) {		//break if row can already fit group
						max = counter;
						maxI = startI;
						maxJ = startJ;
						done = true;
						break;
					}
				}				
			}
			
			//fill row
			for(int j = maxJ; j < maxJ+max; j++) {
				if(!nameStack.isEmpty()) {
					String name = nameStack.pop();
					chart.get(maxI).get(j).fill(name, groupName);
					System.out.print(IJToNumber(maxI,j) + " ");
				}
			}
			max = 0;
		}
		System.out.println();
	}
			
	
	/**
	 * Cancels individuals from the flight.
	 * @param name - name of individual(s)
	 * Precondition: names are separated by a comma ", " there are more than one names
	 */
	public void cancelIndividual(String names) {
		String[] nameList = names.split("\\, ");
		for(String name: nameList) {
			for(ArrayList<Seat> row: chart) {
				for(Seat seat: row) {
					if(seat.getName().equals(name))
						seat.empty();
				}
			}
		}
	}
	
	
	/**
	 * Cancels group from the flight.
	 * @param groupName - name of group
	 */
	public void cancelGroup(String groupName) {
		for(ArrayList<Seat> row: chart) {
			for(Seat seat: row) {
				if(seat.getGroup().equals(groupName))
					seat.empty();
			}
		}
	}
	
	
	/**
	 * Converts the row and column (i and j) into a seat number.
	 * @param i - row
	 * @param j - column
	 */
	public static String IJToNumber(int i, int j) {
		int newI;
		
		//turns i into appropriate row
		if(i == 0 || i == 1){
			newI = i+1;
		}
		else {
			newI = 10 + i - 2;
		}

		return (Integer.toString(newI) + (char)(65 + j));
	}
}
