import java.io.*;
import java.util.*;
/**
 * The Seating Chart Writer writes seating chart data to file.
 * @author Bill Li
 *
 */
public class SeatingChartWriter {
	
	/**
	 * Writes the information from Seating Chart to given file
	 * @param file - file to read
	 * @param chart - seating chart data structure
	 * @throws IOException if file is invalid
	 */
	public static void writeToFile(File file, SeatingChart chart) throws IOException{
		
		//clear file first
		
		FileWriter fr = new FileWriter(file);
		BufferedWriter br = new BufferedWriter(fr);
		PrintWriter pr = new PrintWriter(br);
		
		//print file header
		pr.println("First 1-2, Left: A-B, Right: C-D; Economy 10-29, Left: A-C, Right: D-F");
		
		//write to file
		String print = "";
		for(int i = 0; i < chart.size(); i++) {
			for(int j = 0; j < chart.get(i).size(); j++) {
				Seat tempSeat = chart.get(i).get(j);
				if(tempSeat.isFull) {
					if(tempSeat.partOfGroup == false) { //if passenger IS NOT part of group
						print = ChartManager.IJToNumber(i, j) + ", I, " + tempSeat.getName();
					}
					else if(tempSeat.partOfGroup){	//if passenger IS part of group
						print = ChartManager.IJToNumber(i, j) + ", G, " + tempSeat.getGroup() + ", "
								+ tempSeat.getName();
					}
					pr.println(print);
				}
			}
		}
		pr.close();
		br.close();
		fr.close();
	}
}
