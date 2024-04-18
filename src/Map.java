/**
 * @author logan
 *
 */

public class Map{
	private int segments;
	private int date; 
	private double length; 
	private double traveled; 
	private String weather; 
	
	public Map(int date) { 
		this.segments = 0; 
		this.date = date;  
		this.weather = "None";  
	}

	/**
	 * @return the segments
	 */
	public int getSegments() {
		return segments;
	}

	/**
	 * @param segments the segments to set
	 */
	public void setSegments(int segments) {
		this.segments = segments;
	}

	/**
	 * @return the date
	 */
	public int getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(int date) {
		this.date = date;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * @return the traveled
	 */
	public double getTraveled() {
		return traveled;
	}

	/**
	 * @param traveled the traveled to set
	 */
	public void setTraveled(double traveled) {
		this.traveled = traveled;
	}

	/**
	 * @return the weather
	 */
	public String getWeather() {
		return weather;
	}

	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	
	
}

