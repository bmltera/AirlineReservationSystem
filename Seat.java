/**
 * A seat is used in the Seating Chart data structure. It includes variables for name, group, seat type, class type,
 * and booleans to indicate part of group and is full.
 * @author Bill Li
 *
 */
public class Seat {
	
	String name = "";
	String group = "";
	String seatType;     // W C A
	String classType;    // First or Economy
	boolean partOfGroup;
	boolean isFull;
	
	
	/**
	 * Constructs an empty seat.
	 */
	public Seat() {
		isFull = false;
		partOfGroup = false;
	}
	
	/**
	 * Constructs an empty seat with a specified seat type.
	 * @param seatType - type of seat ((W)indow, (C)enter, (A)isle)
	 */
	public Seat(String seatType, String classType) {
		isFull = false;
		partOfGroup = false;
		this.seatType = seatType;
		this.classType = classType;
	}
	
	/**
	 * Returns name of passenger.
	 * @return the name of passenger
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets name of passenger.
	 * @param name - name of passenger
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns seat type.
	 * @return the type of seat
	 */
	public String getSeatType() {
		return seatType;
	}
	
	/**
	 * Sets the seat type.
	 * @param seatType - type of seat
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
	/**
	 * Returns the group which the seat belongs to.
	 * @return the group of seat
	 */
	public String getGroup() {
		return group;
	}
	
	/**
	 * Sets the group which the seat belongs to.
	 * @param group - group of seat
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	
	/**
	 * Returns the service class of the seat.
	 * @return the service class
	 */
	public String getClassType() {
		return classType;
	}
	
	/**
	 * Sets the service class of the seat.
	 * @param classType - the service class
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	
	
	/**
	 * Tells if seat is full.
	 * @return true if full, false if not full
	 */
	public boolean isFull() {
		return isFull;
	}
	
	
	/**
	 *  Fills seat with a passenger.
	 * @param name - passenger name
	 */
	public void fill(String name) {
		this.name = name;
		isFull = true;
	}
	
	/**
	 * Fills seat with a passenger in a group.
	 * @param name - passenger name
	 * @param group - group name
	 */
	public void fill(String name, String group) {
		this.name = name;
		this.group = group;
		partOfGroup = true;
		isFull = true;
	}
	
	/**
	 * Empties seat, resets variables to default
	 */
	public void empty() {
		name = "";
		group = "";
		isFull = false;
		partOfGroup = false;
	}
	
	

}
