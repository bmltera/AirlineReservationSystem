import java.util.*;
import java.io.*;
/**
 * The seating chart reader reads data from a file and processes it into the seating chart
 * @author Bill Li
 *
 */
public class SeatingChartReader {
	
	/**
	 * Prints availability of seats for specified service class.
	 * @param chart - seating chart
	 * @param serviceClass - class of service (First, Economy)
	 */
	public static void getAvailability(SeatingChart chart, String serviceClass) {
		
		//return if service class is not specified
		if(!serviceClass.equals("First") && !serviceClass.equals("Economy")){
			return;
		}
		
		int startRow = 0;
		int endRow = chart.size();
		
		//setting start and end rows of where to traverse for class
		if (serviceClass.equals("First")){
			endRow = 2;
		}
		
		if (serviceClass.equals("Economy")){
			startRow = 2;
		}
		
		System.out.println(serviceClass);
		
		//finding availability
		for(int i = startRow; i < endRow; i++) {
			if(serviceClass.equals("First"))
				System.out.print((i+1) + ": ");
			else 
				System.out.print((8+i) + ": ");

			boolean first = true; //boolean to help with commas
			for(int j = 0; j < chart.get(i).size(); j++) {
				Seat tempSeat = chart.get(i).get(j);
				if(tempSeat.isFull() == false){
					if(first == false)
						System.out.print(",");
					System.out.print((char)(65+j));
					first = false;
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints occupied seats and passengers seated in them
	 * @param chart - seating chart
	 * @param serviceClass - class of service (First, Economy)
	 */
	public static void getManifest(ArrayList<ArrayList<Seat>> chart, String serviceClass) {
		
		//return if service class is not specified
		if(!serviceClass.equals("First") && !serviceClass.equals("Economy")){
			return;
		}
		
		System.out.println(serviceClass);
		System.out.println();
		int startRow = 0;
		int endRow = chart.size();
		//setting start and end rows of where to traverse for class
		if (serviceClass.equals("First")){
			endRow = 2;
		}
		
		if (serviceClass.equals("Economy")){
			startRow = 2;
		}
		
		for(int i = startRow; i < endRow; i++) {
			for(int j = 0; j < chart.get(i).size(); j++) {
				Seat tempSeat = chart.get(i).get(j);
				if(tempSeat.isFull() == true){
					System.out.println(ChartManager.IJToNumber(i, j) + ": " + tempSeat.getName());
				}
			}
		}
		
	}
	
	/**
	 * Returns number of available seats in a class
	 * @param chart - seating chart
	 * @param serviceClass - class of service
	 * @return number of available seats
	 */
	public static int availabilityCount(ArrayList<ArrayList<Seat>> chart, String serviceClass) {
		int counter = 0;
		int startRow = 0;
		int endRow = chart.size();
		
		//setting start and end rows of where to traverse for class
		if (serviceClass.equals("First")){
			endRow = 2;
		}
		
		if (serviceClass.equals("Economy")){
			startRow = 2;
		}
		
		//finding availability
		for(int i = startRow; i < endRow; i++) {
			for(int j = 0; j < chart.get(i).size(); j++) {
				Seat tempSeat = chart.get(i).get(j);
				if(tempSeat.isFull() == false){
					counter++;
				}
			}
		}	
		return counter;
	}
	
	/**
	 * Reads from file and parses information into seating chart
	 * @param chart - seating chart
	 * @param data - file name
	 * @throws IOException if file is invalid
	 */
	public static void readFromFile(ArrayList<ArrayList<Seat>> chart, File data) throws IOException {
		FileReader fr = new FileReader(data);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		
		//skip over header
		br.readLine();
		
		//read file and add data to data structure
		while((line = br.readLine())!=null) {
			String[] parsedData = line.split("\\, ");
			String flightNumber = parsedData[0];
			String group = parsedData[1];
			String name = parsedData[parsedData.length-1];
			//convert seat number to row and column
			int[] rowColumn = numberToIJ(parsedData[0]);
			int i = rowColumn[0];
			int j = rowColumn[1];
			
			if(group.equals("I")) {
				chart.get(i).get(j).fill(name);
				//System.out.println("added individual!");
			}
			
			else if(group.equals("G")) {
				String groupName = parsedData[2];
				chart.get(i).get(j).fill(name, groupName);

			}
			//System.out.println(Arrays.toString(parsedData)); //debug line

		}
		br.close();
		fr.close();
	}
	
	/**
	 * Converts seat number to array containing integers [i,j] for row and column in data structure
	 * @param seatNumber - seat number in number-letter format
	 * @return array with row and column
	 */
	public static int[] numberToIJ(String seatNumber) {
		int[] rowColumn = new int[2];
		int row = Integer.parseInt(seatNumber.substring(0,seatNumber.length()-1));
		if(row == 1 || row == 2) {
			row--;
		}
		else {
			row = row - 8;
		}
		rowColumn[0] = row;
		rowColumn[1] = (int)(seatNumber.charAt(seatNumber.length()-1)-65);
		return rowColumn;
	}
}
