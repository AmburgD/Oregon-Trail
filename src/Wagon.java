/**
 * Wagon
 * @author Lochlyn Ramsey
 *	Created on 3/25/2022 
 *	Handles all aspects of the Wagon that the party is taking to Oregon
 */

import java.util.ArrayList;

public class Wagon {
	
	private double weightLim = 0;										//Weight limit of the wagon. Determined by the number of oxen attached
	private double currentWeight;										//Weight of all items in the wagon's inventory
	private int numOx;													//The number of yoke of oxen currently attached to the cart
	private int pace;													//The current pace of the cart, selected by the player. A pace of 0 indicates a "Slow" pace, 1 indicates a "Steady" pace, 2 indicates a "Strenuous" pace
	private double speed = 10;											//The current speed of the cart in miles/day. Determined by the number of ox and the pace.
	private boolean operational = true;									//Whether or not the cart is currently functional.
	public ArrayList <Item> inventory = new ArrayList<Item>();			//List of all items on the wagon.
	public ArrayList <Character> people = new ArrayList<Character>(); 	//List of all people currently in the wagon
	
	/**
	 * Creates a new Wagon
	 */
	public Wagon() {
		super();
	}
	
	/**
	 * Returns the weight limit of the current configuration of wagon and oxen
	 * @return the weightLim
	 */
	public double getWeightLim() {
		return weightLim;
	}


	/**
	 * Returns the current amount of weight in the wagon
	 * @return the currentWeight
	 */
	public double getCurrentWeight() {
		return currentWeight;
	}
	
	/**
	 * Sets the current amount of weight in the wagon
	 * @param currentWeight the currentWeight to set
	 */
	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}

	/**
	 * Allows the program to change the weight in the cart rather than just setting it. (May be removed for final product)
	 * @param weightChange the amount of weight to adjust the wagon's load by
	 * @param addOrRemove determines whether the weight change is added or removed. Should either be "add" or "remove"
	 */
	public void changeWeight(double weightChange, String addOrRemove) {
		
		if(addOrRemove.equalsIgnoreCase("add")) {
			this.currentWeight += weightChange;
		}
		else if(addOrRemove.equalsIgnoreCase("remove")) {
			this.currentWeight -= weightChange;
		}
		else {
			System.out.println("Invalid call to changeWeight! Must either add or remove weight from wagon! Check your spelling!");
		}
	}

	/**
	 * Returns the number of yoke of oxen currently attached to the wagon
	 * @return the numOx
	 */
	public int getNumOx() {
		return numOx;
	}
	
	/**
	 * Sets the number of yoke of oxen currently attached to the wagon
	 * @param numOx the numOx to set
	 */
	public void setNumOx(int numOx) {
		this.numOx = numOx;
		this.weightLim = this.numOx*1000;
	}
	

	/**
	 * Allows the program to change the number of yoke of oxen currently attached to cart rather than just setting it. (May be removed for final product)
	 * @param numOxChange the numOx to set
	 * @param addOrRemove determines whether the change to the number of ox is added or removed. Should either be "add" or "remove"
	 */
	public void changeNumOx(int numOxChange, String addOrRemove) {
		
		if(addOrRemove.equalsIgnoreCase("add")) {
			this.numOx += numOxChange;
		}
		else if(addOrRemove.equalsIgnoreCase("remove")) {
			this.numOx -= numOxChange;
		}
		else {
			System.out.println("Invalid call to changeNumOx! Must either add or remove weight from wagon! Check your spelling!");
		}
		
		this.weightLim = this.numOx*1000;
	}

	/**
	 * Returns the current pace setting of the cart
	 * @return the pace. A pace of 0 indicates a "Slow" pace, 1 indicates a "Steady" pace, 2 indicates a "Strenuous" pace
	 */
	public int getPace() {
		return pace;
	}

	/**
	 * Sets the pace of the cart and adjusts the cart's speed accordingly
	 * @param pace the pace to set. A pace of 0 indicates a "Slow" pace, 1 indicates a "Steady" pace, 2 indicates a "Strenuous" pace
	 */
	public void setPace(int pace) {
		System.out.println("Pace set to : " + pace);
		this.pace = pace;
		if(pace == 0) {
			this.speed = 10;
		}
		else {
			this.speed = (0.5*(pace+2))*(this.numOx*5);
		}
	}

	/**
	 * Returns the speed of the cart
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Returns whether the cart is operating or not. True means that the wagon is operating perfectly. False means that the wagon is broken.
	 * @return the operational status of the wagon
	 */
	public boolean isOperational() {
		return operational;
	}

	/**
	 * Sets whether the cart is operating or not. True means that the wagon is operating perfectly. False means that the wagon is broken.
	 * @param operational the status of the wagon.
	 */
	public void setOperational(boolean operational) {
		this.operational = operational;
	}

	
	
	
	
	
}
