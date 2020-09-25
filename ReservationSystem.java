import java.util.*;
import java.io.*;
/**
 * The reservation system contains the main method for the program.
 * @author Bill Li
 * @version 1.0 9/09/20
 */
public class ReservationSystem {
	
	/**
	 * Main method for ReservationSystem program
	 * @param args - input from console
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		//creation of data structure and scanner
		SeatingChart chart = new SeatingChart(); 
		ChartManager manager = new ChartManager(chart);
		Scanner in = new Scanner(System.in);

		
		//reads existing file or creates new one		
		File data = new File(args[0]); 									// first argument from console
		if(data.exists()) {
			SeatingChartReader.readFromFile(chart, data);				// read from file and add to data structure
		}
		if(!data.exists()) {
			if(data.createNewFile()) {
			//System.out.println("File Created: " + data.getName()); 	// debug code
			}
		}
		
		//program loop 
		String response = "";
		while(!response.equals("Q")) {
			System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
			response = in.next();
			
			//add a single passenger
			if(response.equals("P")){
				System.out.print("Name: ");
				in.nextLine();
				String name = in.nextLine();
				System.out.print("Service Class: ");
				String service = in.next();
				System.out.print("Seat Preference: ");
				String seat = in.next();
				manager.addSingle(name, service, seat);
			}
			
			
			//add a group
			if(response.equals("G")){
				System.out.print("Group Name: ");
				in.nextLine();
				String groupName = in.nextLine();
				System.out.print("Names: ");
				String passengerNames = in.nextLine();
				System.out.print("Service Class: ");
				String serviceClass = in.next();
				manager.addGroup(groupName, passengerNames, serviceClass);
			}
			
			
			//cancel Reservations
			if(response.equals("C")){
				System.out.print("Cancel [I]ndividual or [G]roup? ");
				String input = in.next();
				if(input.equals("I")){
					System.out.print("Names: ");
					in.nextLine();
					input = in.nextLine();
					manager.cancelIndividual(input);
				}
				else if(input.equals("G")){
					System.out.print("Group Name: ");
					input = in.next();
					manager.cancelGroup(input);
				}
			}	
			
			
			//print seating availability
			if(response.equals("A")){
				System.out.print("Service Class: ");
				String serviceClass = in.next();
				SeatingChartReader.getAvailability(chart, serviceClass);

			}
			
			
			//print seating manifest
			if(response.equals("M")){
				System.out.print("Service Class: ");
				String serviceClass = in.next();
				SeatingChartReader.getManifest(chart, serviceClass);
			}
		}
		
		//run on quit, save seating info
		SeatingChartWriter.writeToFile(data, chart);
		System.out.println("Thank you and have a nice day!");
	
	}
	

}
