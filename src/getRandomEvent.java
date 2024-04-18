
/*
 * @author Dax Amburgy
 * 
 * @verison 3.0
 * 
 * @release 3/24/22
 * @update date  4/10/22
 * @update date 4/22/22
 * 
 * This class is designed to get the random events for the Oregon Trail game
 * this is done using random number generators and strings
 * for all of the methods in this class, a random number is passed into the event, which decides what occurs because of the event.
 * in certain cases, a separate random number is needed to make a more randomized decision. 
 * 
 * 
 * version 2.0 includes extra classes that must be used simultaneously to output to the game which random event occurred
 * 
 * Version 3.0 gets rid of those extra classes that were implemented in the last update. It also has simplified the code by an incredible amount
 * There were 1700 lines, now there are only 1000, and the entire function of this class can be accomplished in only one line in the main
 * 
 */

import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

public class getRandomEvent {
		
		//overall random value
		private double randomValue;
		
		/**
		 * @return the randomValue
		 */
		public double getRandomValue() {
			randomValue = Math.random()*100+1;
			return randomValue;
		}
		/**
		 * @param randomValue the randomValue to set
		 */
		public void setRandomValue(double randomValue) {
			this.randomValue = randomValue;
		}

		/**
		 * @param temperature - a randomValue that gives the percent chance of a weather event happening
		 * @param month - an integer representing what month the player is in
		 * @return currentWeather - the current weather value displayed in a string
		 */
		public String getWeatherEvent(double temperature, int month ) {
			
			String currentWeather = "";
			double chance = (int) Math.random()*100+1;
			
			// each if statement pulls the random value and gives a percent chance of which
			// weather effect is being applied. This is fed into a string which will be 
			// displayed on the main screen.
			
			//if it is this month, there is this percent chance of getting this type of weather
			if(month == 1) {
				if(chance > 0 && chance <=30) {
					currentWeather = "snowing";
				}
				else if(chance >30 && chance <=100) {
					currentWeather = "blizzaring";
				}

			}
			if(month == 2) {
				if(chance > 0 && chance <=40) {
					currentWeather = "snowing";
				}
				else if(chance >40 && chance <=90) {
					currentWeather = "blizzaring";
				}
				else if(chance >90 && chance <=100) {
					currentWeather = "hailing";
				}
			}
			if(month == 3) {	
				 if(chance >0 && chance <=20) {
					currentWeather = "warm";
				}
				else if(chance >20 && chance <=40) {
				currentWeather = "cold";
				}
				else if(chance > 40 && chance <=70) {
					currentWeather = "snowing";
				}
				else if(chance >70 && chance <=90) {
					currentWeather = "blizzaring";
				}
				else if(chance >90 && chance <=100) {
					currentWeather = "hailing";
				}
			}
			if(month == 4) {

				 if(chance >0 && chance <=10) {
					currentWeather = "foggy";
				}
				else if(chance >10 && chance <=30) {
					currentWeather = "warm";
				}
				else if(chance >30 && chance <=60) {
					currentWeather = "cold";
				}
				else if(chance >=60 && chance <= 80) {
					currentWeather = "raining"; 
					
				}
				else if(chance >80 && chance <=100) {
					currentWeather = "thunderstorming";
					
				}
			}
			if(month == 5) {

				 if(chance >0 && chance <=5 ) {
					currentWeather = "foggy";
				}
				else if(chance >5 && chance <=15) {
					currentWeather = "hot";
				}
				else if(chance >15 && chance <=50) {
					currentWeather = "warm";
				}
				else if(chance >50 && chance <=60) {
					currentWeather = "cold";
				}
				else if(chance >=60 && chance <= 80) {
					currentWeather = "raining"; 
					
				}
				else if(chance >80 && chance <=100) {
					currentWeather = "thunderstorming";
					
				}
			}
			if(month == 6) {
				 if(chance >0 && chance <=20) {
					currentWeather = "scorching";
				}
				else if(chance >20 && chance <=50) {
					currentWeather = "hot";
				}
				else if(chance >50 && chance <=70) {
					currentWeather = "warm";
				}
				else if(chance >70 && chance <=75) {
					currentWeather = "cold";
				}
				else if(chance >=75 && chance <= 95) {
					currentWeather = "raining"; 
					
				}
				else if(chance >95 && chance <=100) {
					currentWeather = "thunderstorming";
					
				}
			}
			if(month == 7) {
				 if(chance >0 && chance <=40) {
					currentWeather = "scorching";
				}
				else if(chance >40 && chance <=60) {
					currentWeather = "hot";
				}
				else if(chance >60 && chance <=70) {
					currentWeather = "warm";
				}
				else if(chance >70 && chance <=75) {
					currentWeather = "cold";
				}
				else if(chance >=75 && chance <= 85) {
					currentWeather = "raining"; 
					
				}
				else if(chance >85 && chance <=100) {
					currentWeather = "thunderstorming";
					
				}
			}
			if(month == 8) {

				 if(chance >0 && chance <=5) {
					currentWeather = "foggy";
				}
				else if(chance >5 && chance <=15) {
					currentWeather = "scorching";
				}
				else if(chance >15 && chance <=35) {
					currentWeather = "hot";
				}
				else if(chance >35 && chance <=50) {
					currentWeather = "warm";
				}
				else if(chance >50 && chance <=60) {
					currentWeather = "cold";
				}
				else if(chance >=60 && chance <= 80) {
					currentWeather = "raining"; 
					
				}
				else if(chance >80 && chance <=100) {
					currentWeather = "thunderstorming";
					
				}
			}
			if(month == 9) {

				if(chance >0 && chance <=10) {
					currentWeather = "foggy";
				}
				else if(chance >10 && chance <=30) {
					currentWeather = "warm";
				}
				else if(chance >30 && chance <=60) {
					currentWeather = "cold";
				}
				else if(chance >=60 && chance <= 80) {
					currentWeather = "raining"; 
					
				}
				else if(chance >80 && chance <=100) {
					currentWeather = "thunderstorming";
					
				}
			}
			if(month == 10) {
				if(chance > 0 && chance <=40) {
					currentWeather = "snowing";
				}
				else if(chance >40 && chance <=90) {
					currentWeather = "blizzarding";
				}
				else if(chance >90 && chance <=100) {
					currentWeather = "hailing";
				}
			}
			if(month == 11) {
				if(chance > 0 && chance <=40) {
					currentWeather = "snowing";
				}
				else if(chance >40 && chance <=100) {
					currentWeather = "blizzaring";
				}

			}
			if(month == 12) {
				if(chance > 0 && chance <=30) {
					currentWeather = "snowing";
				}
				else if(chance >30 && chance <=100) {
					currentWeather = "blizzaring";
				}
			}

			System.out.println(currentWeather);
			return currentWeather;
		}
		
		public static String getTrailAttack(double chance, ArrayList<Item> inventory) { 
			
			String effect = "";
			//these are basically layered if statements. There are 3 cases of something getting stolen
			//from the cart. Each case descending determines the number of items that are stolen, then
			//another random number is generated to determine what is stolen
			
			/**
			 * Here is how items getting stolen work
			 * 
			 *first pull the item amount from the arrayList
			 *		 double amount = inventory.get(2).getAmount();
			 * 
			 * then use that amount to create the maximum value for a random
			 * number generator
					double amountLost = Math.random()*amount+1;
					
				now subtract the amount that you lost from the amount that you had
					double newAmount = amount - amountLost;
					
				and push that to the arrayList
					inventory.get(2).setAmount(newAmount);
			 * 
			 * boom... theft... don't do it kids its bad!
			 * 
			 */
			
			double amountFood = inventory.get(1).getAmount();
			double amountClothes = inventory.get(2).getAmount();
			double amountMoney = inventory.get(0).getAmount();
			double amountHeirloom = inventory.get(5).getAmount();
			double amountSpareParts = inventory.get(3).getAmount();
			double amountMedSupply = inventory.get(4).getAmount();
			
					//initialize our randomized boolean
					Random newRandomValue = new Random();
			
					//string to initialize super string
					String robbed = "";
					//loop 6 times. for every loop, create a new boolean to determine if that item type was stolen. Add that to a large string.
					for(int i = 0; i<6; i++) {
						
						//for every loop, generate a new boolean
						boolean remove = newRandomValue.nextBoolean();
						
						System.out.println("Trail Attack " + newRandomValue);
						// depending on where in the for loop, set that item type to true or false, then feed it into the string
						if(i == 0 && remove == true) {
							robbed = robbed + "food ";
							if(amountFood == 0) {
								
							}else {
							double amountFoodLost = (int)Math.random()*amountFood+1;
							double newFoodAmount = amountFood - amountFoodLost;
							inventory.get(1).setAmount(newFoodAmount);
							}
						}
						if(i == 1 && remove == true) {
							robbed = robbed + "Money ";
							if(amountMoney == 0) {
							
							}else {
							double amountMoneyLost = (int)Math.random()*amountMoney+1;
							double newMoneyAmount = amountMoney - amountMoneyLost;
							inventory.get(0).setAmount(newMoneyAmount);
							}
						}
						if(i == 2 && remove == true) {
							robbed = robbed + "Heirlooms ";
							if(amountHeirloom == 0) {
							
							}else {
							double amountHeirloomLost =(int) Math.random()*amountHeirloom+1;
							double newHeirloomAmount = amountHeirloom - amountHeirloomLost;
							inventory.get(5).setAmount(newHeirloomAmount);
							}
						}
						if(i == 3 && remove == true) {
							robbed = robbed + "spare parts ";
							if(amountSpareParts == 0) {
							
							}else {
							double amountSparePartsLost =(int) Math.random()*amountSpareParts+1;
							double newSparePartsAmount = amountSpareParts - amountSparePartsLost;
							inventory.get(3).setAmount(newSparePartsAmount);
							}
						}
						if(i == 4 && remove == true) {
							robbed = robbed + "clothes ";
							if(amountClothes == 0) {
						
							}else {
							double amountClothesLost =(int) Math.random()*amountClothes+1;
							double newClothesAmount = amountClothes - amountClothesLost;
							inventory.get(2).setAmount(newClothesAmount);
							}
						}
						if(i == 5 && remove == true) {
							robbed = robbed + "medical supplies ";
							if(amountMedSupply == 0) {
						
							}else {
							double amountMedSupplyLost =(int) Math.random()*amountMedSupply+1;
							double newAmountMedSupply = amountMedSupply - amountMedSupplyLost;
							inventory.get(4).setAmount(newAmountMedSupply);
						}
						}
						
					
					effect = "Your " + robbed + "were stolen";
					}
					System.out.println(effect);
				return effect;
			}
		

		/**
		 * @param chance - a randomValue that gives the percent chance of an event happening
		 * @param party - an arraylist of our characters
		 * @return party - what was healed or broken on the character
		 */
		public static String getCharacterEvent(double chance, ArrayList<Character> party) {
			
			String injury = "";
			
			//determines which character at random is getting injured
			 int player = (int)Math.random()*party.size()+1;
			System.out.println("player selected " + player);
			//basic random chance generator, if the dice was rolled and it landed on this number, then you got this outcome.
			if(chance >=0 && chance <= 9) {
				injury = "heat exhaustion"; 
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >=9 && chance <= 10) {
				injury = "You lost a party member! How could you?!"; 
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//Remove all health points (this removes them from the party)
				playerHealth = 0;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >10 && chance <=20) {
				injury = "typhoid";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
				
			}
			else if(chance > 20 && chance <=30) {
				injury = "cholera";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >30 && chance <=40) {
				injury = "measles";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >40 && chance <=50) {
				injury = "dysentary";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >50 && chance <=60) {
				injury = "mountain fever";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >60 && chance <=65) {
				injury = "an injured arm";
				
				//push it back to the array
				party.get(player).setToBroken(true);
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
				
			}
			else if (chance >65 && chance <=70) {
					injury = "You drank bad water!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
				
			}
			else if(chance >70 && chance <=80) {
				injury = "an injured leg";
				party.get(player).setToBroken(true);
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >80 && chance <=90) {
				injury = "been bitten by a snake";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >90 && chance <=100) {
				injury = "accidentally eaten a poisonous berry";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else {
				injury = "have recovered! Phew";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth*2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
				
				//set the broken bone boolean to true if fixed
				party.get(player).setToFix(true);
			}
			System.out.println(party.get(player).getName() + " has experienced: " + injury);
			return injury;
		}
		
	/**
	 * Generates a random event that does not fit in with a trail attack, a wagon event, or a character event	
	 * @param chance - the percent chance of one of the events happening ( random number generated from 1-100)
	 * @param inventory - an ArrayList of the items on the cart
	 * @return inventory - the updated inventory
	 */
		public static String getOtherRandomEvent(double chance, ArrayList<Item> inventory) {
			
			String other = "";
			
			if(chance >=0 && chance <= 10) {
				other = "You lost the trail!"; 
				
			}
			else if(chance >10 && chance <=20) {
				other = "You found an abandoned wagon! There might be supplies inside you can use! ";

				//string to initialize super string
				String suprize = "";
				
				//boolean generator for finding a lost wagon cart
				Random item = new Random();
				
				
				//loop 6 times. for every loop, create a new boolean determine if that item was found. Add that to a large string.
				for(int i = 0; i<6; i++) {
					
					//create a new boolean for what to add to the cart
					boolean add = item.nextBoolean();
					
					System.out.println("Find Stuff in Wagon Event " + item);
					
					// depending on where in the for loop, set that item type to true or false, then feed it into the string
					//the algorithm to add the items is the same as the one to remove except it adds it to the original amount
					//instead of subtracting it
					if(i == 0 && add == true) {
						suprize = suprize + "food ";
						double amountFood = inventory.get(1).getAmount();
						double amountFoodLost = (int)Math.random()*amountFood+1;
						double newFoodAmount = amountFood + amountFoodLost;
						inventory.get(1).setAmount(newFoodAmount);
					}
					if(i == 1 && add == true) {
						suprize = suprize + "Money ";
						double amountMoney = inventory.get(0).getAmount();
						double amountMoneyLost = (int)Math.random()*amountMoney+1;
						double newMoneyAmount = amountMoney + amountMoneyLost;
						inventory.get(0).setAmount(newMoneyAmount);
					}
					if(i == 2 && add == true) {
						suprize = suprize + "Heirlooms ";
						double amountHeirloom = inventory.get(5).getAmount();
						double amountHeirloomLost = (int)Math.random()*amountHeirloom+1;
						double newHeirloomAmount = amountHeirloom + amountHeirloomLost;
						inventory.get(5).setAmount(newHeirloomAmount);
					}
					if(i == 3 && add == true) {
						suprize = suprize + "spare parts ";
						double amountSpareParts = inventory.get(3).getAmount();
						double amountSparePartsLost = (int)Math.random()*amountSpareParts+1;
						double newSparePartsAmount = amountSpareParts + amountSparePartsLost;
						inventory.get(3).setAmount(newSparePartsAmount);
					}
					if(i == 4 && add == true) {
						suprize = suprize + "clothes ";
						double amountClothes = inventory.get(2).getAmount();
						double amountClothesLost = (int)Math.random()*amountClothes+1;
						double newClothesAmount = amountClothes + amountClothesLost;
						inventory.get(2).setAmount(newClothesAmount);
					}
					if(i == 5 && add == true) {
						suprize = suprize + "medical supplies ";
						double amountMedSupply = inventory.get(4).getAmount();
						double amountMedSupplyLost = (int)Math.random()*amountMedSupply+1;
						double newAmountMedSupply = amountMedSupply + amountMedSupplyLost;
						inventory.get(4).setAmount(newAmountMedSupply);
					}
					
				}
				//feed what was set true or false into the output string
				other = other + "You found " + suprize + " in the abandoned cart";
			}
			else if(chance > 20 && chance <=30) {
				other = "You trip on a branch. Its ok, nothing is broken";
			}
			else if(chance >30 && chance <=40) {
				other = "You lost an Heirloom!";
				double amountHeirloom = inventory.get(5).getAmount();
				double amountHeirloomLost = (int)Math.random()*amountHeirloom+1;
				double newHeirloomAmount = amountHeirloom - amountHeirloomLost;
				inventory.get(5).setAmount(newHeirloomAmount);
			}
			else if(chance >40 && chance <=50) {
				other = "The wagon caught fire! Stop, drop, and roll!";
				
				//boolean generator
				Random bool = new Random();
				
				//string to initialize super string
				String burned = "";
				//loop 6 times. for every loop, create a new boolean to determine if that item type was stolen. Add that to a large string.
				for(int i = 0; i<6; i++) {
					
					//initialize a new boolean
					boolean add = bool.nextBoolean();
					System.out.println("type 3 trail attack " + bool);
					// depending on where in the for loop, set that item type to true or false, then feed it into the string
					if(i == 0 && add == true) {
						burned = burned + "food ";
						double amountFood = inventory.get(1).getAmount();
						double amountFoodLost = (int)Math.random()*amountFood+1;
						double newFoodAmount = amountFood - amountFoodLost;
						inventory.get(1).setAmount(newFoodAmount);
					}
					if(i == 1 && add == true) {
						burned = burned + "Money ";
						double amountMoney = inventory.get(0).getAmount();
						double amountMoneyLost = (int)Math.random()*amountMoney+1;
						double newMoneyAmount = amountMoney - amountMoneyLost;
						inventory.get(0).setAmount(newMoneyAmount);
					}
					if(i == 2 && add == true) {
						burned = burned + "Heirlooms ";
						double amountHeirloom = inventory.get(5).getAmount();
						double amountHeirloomLost =(int) Math.random()*amountHeirloom+1;
						double newHeirloomAmount = amountHeirloom - amountHeirloomLost;
						inventory.get(5).setAmount(newHeirloomAmount);
					}
					if(i == 3 && add == true) {
						burned = burned + "spare parts ";
						double amountSpareParts = inventory.get(3).getAmount();
						double amountSparePartsLost =(int) Math.random()*amountSpareParts+1;
						double newSparePartsAmount = amountSpareParts - amountSparePartsLost;
						inventory.get(3).setAmount(newSparePartsAmount);
					}
					if(i == 4 && add == true) {
						burned = burned + "clothes ";
						double amountClothes = inventory.get(2).getAmount();
						double amountClothesLost =(int) Math.random()*amountClothes+1;
						double newClothesAmount = amountClothes - amountClothesLost;
						inventory.get(2).setAmount(newClothesAmount);
					}
					if(i == 5 && add == true) {
						burned = burned + "medical supplies ";
						double amountMedSupply = inventory.get(4).getAmount();
						double amountMedSupplyLost = (int)Math.random()*amountMedSupply+1;
						double newAmountMedSupply = amountMedSupply - amountMedSupplyLost;
						inventory.get(4).setAmount(newAmountMedSupply);
					}
					
				}
				other = other + "Your " + burned + "burned in the fire!";

			}
			//special case for berries
			else if(chance >50 && chance <=60) {
				
				//a berry event randomizer
				 double berry = (int)Math.random()*10+1;
				
				System.out.println("berry event "+ berry);
				
				if (berry >0 && berry <= 7) {
				other = "You found some wild fruit!";
				double amountFood = inventory.get(1).getAmount();
				double amountFoodLost = (int)Math.random()*(amountFood/3)+1;
				double newFoodAmount = amountFood + amountFoodLost;
				inventory.get(1).setAmount(newFoodAmount);
				}
				else if (berry >7 && berry <= 10) {
					other = "A rabbit stole the berries you found! Dat Wascally Wabbit!";
					double amountFood = inventory.get(1).getAmount();
					double amountFoodLost = (int)Math.random()*(amountFood/3)+1;
					double newFoodAmount = amountFood - amountFoodLost;
					inventory.get(1).setAmount(newFoodAmount);
				}
			}
		else if(chance >60 && chance <=80) {
				other = "Your food spoiled in the heat of the sun!";
				double amountFood = inventory.get(1).getAmount();
				double amountFoodLost =(int) Math.random()*(amountFood/3)+1;
				double newFoodAmount = amountFood - amountFoodLost;
				inventory.get(1).setAmount(newFoodAmount);
			}
			//mountain case
			else if(chance >80 && chance <=90) {
				double trail =(int) Math.random()*10+1;
				if(trail >0 && trail <=8) {
						other = "You found a rough trail!";
				}
					else if(trail >8 && trail <=10) {
				other = "You found an impassable trail!";
					}
			}
			else {
				other = "Nothing happened ?!";
			}
			return other;
		}
		/**
		 * @param chance - a randomValue that gives the percent chance of an event happening
		 * @param inventory - an ArrayList of the items on the cart
		 * @return inventory - update the spare parts that were used
		 */
		public static String getWagonEvent(double chance, ArrayList<Item> inventory) {

			String part = "";
			
			double amount = inventory.get(3).getAmount();
			
			//basic random chance generator, if the dice was rolled and it landed on this number, then you got this outcome.
			if(amount == 0 ) {
				
				part = "We have no more parts to fix the wagon. Now we are stranded unless someone comes along!";
				return part;
			}
			else {
			if(chance > 0 && chance <= 30) {
				part = "and a wagon wheel broke!";
				
				double amountLost =(int) Math.random()*amount+1;
				double newAmount = amount - amountLost;
				inventory.get(3).setAmount(newAmount);
			}
			else if(chance > 30 && chance <= 60) {
				part = "and a wagon axel broke!";
				
				double amountLost =(int) Math.random()*amount+1;
				double newAmount = amount - amountLost;
				inventory.get(3).setAmount(newAmount);
			}
			else if(chance > 60 && chance <= 90) {
				part = "and a wagon tongue broke!";
				
				double amountLost =(int) Math.random()*amount+1;
				double newAmount = amount - amountLost;
				inventory.get(3).setAmount(newAmount);
			}
			else {
				part = "but nothing broke! phew!";
			}
			System.out.println();
			return part;
			}
		}

		/**
		 * A function that generates random events, it controls what occurs day to day
		 * @param chance - a randomized variable from 1 to 100
		 * @param inventory - the current inventory of the wagon
		 * @param party - an array list of all the characters
		 * @return whatHappened - tells the user what happened on this day
		 * **/
		public String generateRandomEvent(double chance, ArrayList<Item> inventory,ArrayList<Character> party ) {

			//string for what happened on this day
			String whatHappened;
		
			//these are all basic if statements. A random number is generated. If that random number lands on the specific
			//subsection, then thats what happened for the day. Humorous comments were added to some of the daily events
			//to spice up the gamers interaction with the game
			if(chance > 0 && chance <= 2) {
				int trailAttackChance = (int)(Math.random() * 100 + 1);
				System.out.println("trail attack " +trailAttackChance);
				whatHappened = "Bandits attack! " + getRandomEvent.getTrailAttack(trailAttackChance, inventory);
			}
			else if(chance > 2 && chance <= 27) {
				whatHappened ="Another day on the trail.";
			}
			else if(chance > 27 && chance <= 30) {
				int wagonEventChance = (int)(Math.random() * 100 + 1);
				System.out.println("wagon event" + wagonEventChance);
				whatHappened ="You hit some rocky terrain \n" + getRandomEvent.getWagonEvent(wagonEventChance, inventory);
			}
			else if(chance > 30 && chance <= 40) {
				whatHappened ="You continue along on the long road to Oregon";
			}
			else if(chance > 40 && chance <= 41) {
				whatHappened ="On the road again... Just cant wait to get on the road again!";
			}
			else if(chance > 41 && chance <= 42) {
				whatHappened ="Country roads... take me home... ";
			}
			else if(chance > 42 && chance <= 43) {
				whatHappened ="Haven't we seen that rock formation before?";
			}
			else if(chance > 43 && chance <= 44) {
				whatHappened ="I sure could use a snack right now!";
			}
			else if(chance > 44 && chance <= 45) {
				whatHappened ="Is that a river out in the distance?";
			}
			else if(chance > 45 && chance <= 46) {
				whatHappened ="We are getting closer to the next town!";
			}
			else if(chance > 46 && chance <= 47) {
				whatHappened ="Another day, another opportunity to head west!";
			}
			else if(chance > 47 && chance <= 48) {
				whatHappened ="The journey of the Oregon Trail begins with the first step!";
			}
			else if(chance > 48 && chance <= 49) {
				whatHappened ="You ate too many snacks... you stomach now hurts.";
			}
			else if(chance > 49 && chance <= 50) {
				whatHappened ="You pass another group of travelers. They wave to you.";
			}
			else if(chance > 50 && chance <= 55){
				int characterEventChance = (int)(Math.random() * 100 + 1);
				System.out.println("character event" +characterEventChance);
				whatHappened ="You have " + getRandomEvent.getCharacterEvent(characterEventChance, party) + "!";
			}
			else if(chance > 55 && chance <= 56) {
				whatHappened ="Hey look kids, rocks!";
			}
			else if(chance > 56 && chance <= 57) {
				whatHappened ="Haven't we seen that tumbleweed before?";
			}
			else if(chance > 57 && chance <= 58) {
				whatHappened ="I sure could use a snack right now!";
			}
			else if(chance > 58 && chance <= 59) {
				whatHappened ="Is that a town out in the distance?";
			}
			else if(chance > 59 && chance <= 60) {
				whatHappened ="We have to keep pushing if we are going to make it to Oregon!";
			}
			else if(chance > 60 && chance <= 61) {
				whatHappened ="Another day living the American Dream!";
			}
			else if(chance > 61 && chance <= 62) {
				whatHappened ="Westward Ho!";
			}
			else if(chance > 62 && chance <= 63) {
				whatHappened ="If you kids dont settle down I am going to turn this cart around!";
			}
			else if(chance > 63 && chance <= 64) {
				whatHappened ="You pass another group of travelers. They wave to you.";
			}
			else if(chance > 64 && chance <= 65) {
				whatHappened ="You start to sing a traveling tune. The rest of the party seems annoyed.";
			}
			else if(chance > 65 && chance <= 90) {
				whatHappened ="Just another normal day on the trail to Oregon.";
			}
			else {
				int otherRandomEventChance = (int)(Math.random() * 100 + 1);
			System.out.println("other event" +otherRandomEventChance);
			whatHappened = getRandomEvent.getOtherRandomEvent(otherRandomEventChance, inventory);
			}
			return whatHappened;
		}
}
