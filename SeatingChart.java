import java.util.*;
/**
 * A 2D ArrayList class which holds the seating chart info.
 * 
 * @author Bill Li
 * @version 1.0 7/09/20
 *
 */
public class SeatingChart extends ArrayList<ArrayList<Seat>> { 
	
	int seatsPerRow = 6;
	String seatType = null;
	String classType;

	/**
	 * Constructs an unfilled seating chart in accordance with homework diagram. 
	 */
	public SeatingChart() {
		
		for(int i = 0; i < 22; i ++) {
			
			ArrayList<Seat> temp = new ArrayList<Seat>();
			
			if(i == 0 || i == 1) { 	//sets the first two rows (first class) to 4 seats per row
				seatsPerRow = 4;
			}
			else {
				seatsPerRow = 6;
			}
			
			for(int j = 0; j < seatsPerRow; j++) {
				
				//setting seatTypes (center, aisle, etc) based on position in the array and assigns class
				if(i == 0 || i == 1) {
					if(j == 0 || j == 3)
						seatType = "W";
					if(j == 1 || j == 2)
						seatType = "A";
					classType  = "First";
				}
				if(i > 1) {
					if(j == 0 || j == 5)
						seatType = "W";
					if(j == 1 || j == 4)
						seatType = "C";
					if(j == 2 || j == 3)
						seatType = "A";
					classType = "Economy";
				}
						
				temp.add(new Seat(seatType, classType));
			}	
			this.add(temp);
		}	
	}
}
