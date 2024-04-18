/**
 * Item
 * Created on 3/25/22
 * @author Lochlyn Ramsey
 *	Handles all items that can be purchased from the store and carried in the party's inventory
 */

public class Item {
	
	private String name;	//The name of the Item
	private double amount;	//How many of that Item are contained in the Party's Inventory
	private double weight;	//The weight of a one of that Item type
	
	/**
	 * @param name
	 * @param amount
	 * @param weight
	 * @param price
	 */
	public Item(String name, double amount, double weight) {
		super();
		this.name = name;
		this.amount = amount;
		this.weight = weight;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
	
}
