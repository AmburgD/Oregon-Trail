/**
 * @author logan
 *
 */
public class Character{
	private String name; 
	private String gender; 
	private int age; 
	private int health; 
	private int hunger; 
	private boolean fixBroken; 
	
	
	public Character(String name, String gender, int age, int health, int hunger, boolean fixBroken) { 
		this.name = name; 
		this.gender = gender; 
		this.age = age; 
		this.health = health; 
		this.hunger = hunger; 
		this.fixBroken = fixBroken; 
	}
	
	public Character(String name, String gender, int age) { 
		this.name = name; 
		this.gender = gender; 
		this.age = age; 
		this.health = 100;
		this.hunger = 100; 
		this.fixBroken = true; 
	}
	
	public Character() { 
		this.name = "Joe Bob"; 
		this.gender = "Male"; 
		this.age = 49; 
		this.health = 100; 
		this.hunger = 100; 
		this.fixBroken = true; 
	}

	public int currentHealth(int health) {
		if (health < 0) { 
			health = 0; 
			this.health = 0; 
		}
		if (health > 100) { 
			health = 100; 
			this.health = 100; 
		}
		this.health = health; 
		return health; 
	}
	
	public int currentHunger(int hunger) {
		if (hunger < 0) { 
			hunger = 0; 
			this.hunger = 0; 
		}
		if (hunger > 10) { 
			hunger = 10; 
			this.hunger = 10; 
		}
		this.hunger = hunger; 
		return hunger; 
	}
	
	public boolean setToFix(boolean fixBroken) { 
		if (fixBroken = false) { 
			fixBroken = true;
			this.fixBroken = true; 
		}
		return fixBroken; 
	}
	
	public boolean setToBroken(boolean fixBroken) { 
		if (fixBroken = true) { 
			fixBroken = false;
			this.fixBroken = false; 
		}
		return fixBroken; 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public boolean isFixBroken() {
		return fixBroken;
	}

	public void setFixBroken(boolean fixBroken) {
		this.fixBroken = fixBroken;
	}
	
	
}