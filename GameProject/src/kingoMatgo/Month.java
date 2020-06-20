package kingoMatgo;
/**
 * Enumeration class for Month
 * @author SKLEE
 *
 */
public enum Month {
	JANUARY(1), FEBRUARY(2), MARCH(3),
	APRIL(4), MAY(5), JUNE(6), 
	JULY(7), AUGUST(8), SEPTEMBER(9), 
	OCTOBER(10), NOVEMBER(11), DECEMBER(12);
	
	// Private fields
	private final int monthValue;
	
	// Constructor
	private Month(int monthValue) {
		this.monthValue = monthValue;
	}
	
	// Public methods
	public int getMonth() {
		return monthValue;
	}
	
	// Print month
	public String printMonth() {
		return String.valueOf(monthValue);
	}
}
