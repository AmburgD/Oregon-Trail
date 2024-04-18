/**
 * @author Dax Amburgy
 * 
 * @version 6.0
 * 
 * @release 3/24/22
 * 
 * @update date  4/10/22
 * @update date 4/22/22
 * @update date 4/26/22
 * @update date 4/27/22
 * @update date 4/30/22
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
 * Version 4.0 Now includes the removal of health if the weather gets too cold and the characters don't have enough clothes. It also
 * allows the characters to get lost and lose days on the trail, and it prevents the characters from exceeding the 100 point health cap.
 * 
 * Version 5.0 Fixed issues where the only value that came from a randomly selected number  is 1. It also changes some variable names as to be
 * more consistent with what the code is doing (Ex. food gained when food is being added instead of food lost when food is being added)
 * 
 * Version 6.0 Added the ability of the characterRandomEvent method to read out to the player which character has been injured
 * instead of just saying "you".
 */

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class getRandomEvent {
		
		//overall random value
		private double randomValue;
		
		//list of NPC's that can randomly be selected along the trail
		private NPCtext judson = new NPCtext( "Judson Zeigler", "I need to be movin’ ‘long the trail. I hear there be gold somewhere on the other side of this here great continent. Rumor has it the rivers be flowing with the stuff." );
		private NPCtext holliway = new NPCtext( "Holliway Clausey", "There are these new modes of transportation croppin’ up back home called low - coe - motivs. It’s like this giant metal box on wheels that uses the power of steam to move along these solid metal rails. I hear the folks sayin’ eventually they will stretch across our fine country and we can go from one side to another in a few days! Now aint’ that an ide’ar.");
		private NPCtext mayberry = new NPCtext( "Mayberry Esterbrook", "I heard there wus a great big ole’ fight down in that place they call Texas. They is callin’ it the battle of the Alamo. That Texas place is sayin’ they is want’n their independence. Others are sayin’ them fellas in Washington is gon’ make them part of the USA. I wonder what will happen?" );
		private NPCtext wayland = new NPCtext( "Wayland Epifanio Knowes", "There are these gentlemen back to the east that have been experimenting with lighting. Apparently now, they can send these beeps down a wire which is understood by a gentleman at the otha’ end. Its sum kin’ of magic I tell you!" );
		private NPCtext samuel = new NPCtext( "Samuel Langhorne Clemens", "I brought this newfangled invention with me called a typewriter. It makes tellin’ my stories a breeze. I’m hopefully gonna be bringin’ my stories back with me and publish em’ so everyone can enjoy them. Im currently working on an adventure book. I think i’ll be callin’ the main character Tom" );
		private NPCtext eugene = new NPCtext( "Eugene P. Coffey", "There have been more experiments with sound. I hear now they are tryin’ to create these tubes that can record what a person says. Maybe they can put it in front of one of those musicians so we can listen to music at any time." );
		private NPCtext malcom = new NPCtext( "Malcom Orland Fetherkils", "They say that people be starting new lives all over the place out yonder west. I be takin’ my family out west so we can start our own farm and blacksmith shop." );
		private NPCtext  states = new NPCtext( "States Gibson McCone", "Moving from town to town, I've seen hundreds of these giant creatures they call bison. I have hunted a couple of them. Their hides are tough, but they are good eatin’" );
		private NPCtext sanders = new NPCtext( "Colonel Sanders Sr.", "My family owns a chicken farm way back out east in Kentucky. The plan is to start me small restaurant when I is gettin’ back. I am just out here tryin’ to find meself some unique herbs and spices to be seasonin’ my chicken with. I reckon 11 will do." );
		private NPCtext  cam = new NPCtext( "Cameahwait (Shoshone)", "Some people who travel across the plain seem to be afraid of my people. I do not understand why. We only come in peace to share the land. " );
		private NPCtext manto = new NPCtext( "Mantotohpa (Cheyenne)", " I am sensing a great imbalance in nature. I fear that the settlers overhunting is causing the wildlife population to dwindle. Their reckless sense of entitlement and power is upsetting the Great Spirit." );
		private NPCtext odah = new NPCtext( "Odahingum (Cheyenne)", "I caught a couple of emigrants raiding one of our fields. They said that they had no more food because they did not pack wisely" );
		private NPCtext sarah = new NPCtext( "Sarah Winnemucca", "The leader of the US wants to continue displacing my people. They keep us locked to reservations, while they claim the land we once lived on for themselves. I am traveling east to your people's capitol to speak on behalf of my tribe and all of my people. The land is to be shared. I will not stand for the mistreatment of my people any more." );
		private NPCtext carol = new NPCtext( "Carol Hart Marely", "Tells ya what, a woman is freer out here in the west than she is back home. I gets to drive the wagon, herd the livestock, I even gets to wield a gun to protect my family." );
		private NPCtext loretto = new NPCtext( "Loretto L. Allen", "I hear there be a woman back east who had been teachin’ ‘er two boys mechanical skills. Susan Wright they call ‘er. Now those boys be tryin’ to invent sum kin’ of flying machine. Only a bird s’posed to fly." );
		private NPCtext  dorthea = new NPCtext( "Dorthea E. Tahn", "I hear tell stories of a group travelin’ through the mountains. Apparently, they went and got themselves caught in an avalanche. As they say, anything can happen on the trail." );
		private NPCtext chloe = new NPCtext( "Chloe Clarke Willson", "Once I reach Oregon, I am gonna set up a school for all the children who travel along the trail with their parents. That way, they can continue their education. Then their children can go to school there too." );
		private NPCtext alvina = new NPCtext( "Alvina Z. Kasson", "I been talkin’ with some of the otha’ wives I met along the trail. They taught me some neat tricks to cook as we are walkin’, like puttin’ some of the embers of a fire under a can a’ beans, or hangin’ a butter churn off the back of our cart so the carts shakin’ churns the butter." );
		private NPCtext myrtle = new NPCtext( "Myrtle Bobbie Colvert", "I hear tell of a woman back cross the sea once we came that is workin’ on a machine able to add and subtract numbers akin to a person’s brain! Ada Lovelace is the name. The things that some of these machine builders are able to come up with is incredible." );
		private NPCtext oma = new NPCtext( "Oma Almedia Mann", "They say there is a woman back in New York who got her medical degree and is now workin’ as a licensed doctor. I think I heard her name is Elizabeth Blackwell. ");
		private NPCtext lyle = new NPCtext( "Dorotha Lyle", "Keeping the children safe as we’re travelin’ long the trail be a daunting task in the least. There be so many new kinds of dangers out here in the west; snakes, bugs, ky-otes. It seems like they are getting sick at least once a month. We can’t get to Oregon soon enough.");
		private NPCtext emmett = new NPCtext( "Dr. Emmett Brown", "One would consider me not from around these parts. I am currently headed in a westward fashion to a place locally called Hill Valley, California. I hear it's a great up and coming town. They are currently in the process of building a clock tower. But then again that is the home of the legendary outlaw Buford “Mad Dog” Tannen. The west may be a wild place to live, but its my favorite era…(stammers)  I mean it's my favorite area (ah yes) to live in throughout all of my travels in the United States." );
		private ArrayList <NPCtext> npcs = new ArrayList <NPCtext>(Arrays.asList(judson,holliway,mayberry,wayland,samuel,eugene,malcom,states,sanders,cam,manto,odah,sarah,carol,loretto,dorthea,chloe,alvina,myrtle,lyle,emmett));
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
		 * 
		 * @param month - an integer representing what month the player is in
		 * @param party - an arraylist of the data type character
		 * @param inventory - an arraylist of the data type item
		 * @return currentWeather - the current weather value displayed in a string
		 */
		public String getWeatherEvent( int month, ArrayList<Character> party , ArrayList<Item> inventory ) {
			
			String currentWeather = "";
			
			Random rand = new Random();
			//determines which character at random is getting injured
			 int chance = rand.nextInt(100);
			
			
			// each if statement pulls the random value and gives a percent chance of which
			// weather effect is being applied. This is fed into a string which will be 
			// displayed on the main screen.
			
			//if it is this month, there is this percent chance of getting this type of weather
			if(month == 1) {
				if(chance > 0 && chance <=30) {
					currentWeather = "snowing";
					//for every character in the party
					for(Character i : party) {
						
					//if the party size is greater than the amount of clothes that the player has
						if(party.size() > inventory.get(2).getAmount()) {
						
							//decrement the parties health
							i.setHealth(i.getHealth()-10);
						}
						
					}
					
				}
				else if(chance >30 && chance <=100) {
					currentWeather = "blizzaring";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-20);
						}
					}
			   }
			}
			if(month == 2) {
				if(chance > 0 && chance <=40) {
					currentWeather = "snowing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-10);
						}
					}
				}
				else if(chance >40 && chance <=90) {
					currentWeather = "blizzaring";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-20);
						}
					}
				}
				else if(chance >90 && chance <=100) {
					currentWeather = "hailing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
				}
			}
			if(month == 3) {	
				 if(chance >0 && chance <=20) {
					currentWeather = "warm";
				}
				else if(chance >20 && chance <=40) {
				currentWeather = "cold";
				for(Character i : party) {
					if(party.size() > inventory.get(2).getAmount()) {
						i.setHealth(i.getHealth()-5);
					}
				}
				}
				else if(chance > 40 && chance <=70) {
					currentWeather = "snowing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-10);
						}
					}
				}
				else if(chance >70 && chance <=90) {
					currentWeather = "blizzaring";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
				}
				else if(chance >90 && chance <=100) {
					currentWeather = "hailing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-5);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-5);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-5);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-5);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-5);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-5);
						}
					}
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
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-10);
						}
					}
				}
				else if(chance >40 && chance <=90) {
					currentWeather = "blizzarding";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
				}
				else if(chance >90 && chance <=100) {
					currentWeather = "hailing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
				}
			}
			if(month == 11) {
				if(chance > 0 && chance <=40) {
					currentWeather = "snowing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-10);
						}
					}
				}
				else if(chance >40 && chance <=100) {
					currentWeather = "blizzaring";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
				}

			}
			if(month == 12) {
				if(chance > 0 && chance <=30) {
					currentWeather = "snowing";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-10);
						}
					}
				}
				else if(chance >30 && chance <=100) {
					currentWeather = "blizzaring";
					for(Character i : party) {
						if(party.size() > inventory.get(2).getAmount()) {
							i.setHealth(i.getHealth()-15);
						}
					}
				}
			}
		

			System.out.println(currentWeather);
			return currentWeather;
		}
		
		/**
		 * @param chance - a random number from 0 - 100 giving the percent chance of what could happen on the trail
		 * @param inventory - an arraylist of inventory items
		 * @return effect - what was stolen from the inventory
		 */
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
					Random rand = new Random();
					int amountLost = rand.nextInt((int)amount);
					
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
							if(amountFood <= 0) {
								inventory.get(1).setAmount(0);
							}else {
								
							Random rand = new Random();
							int amountFoodLost = rand.nextInt((int)amountFood);
							double newFoodAmount = amountFood - amountFoodLost;
							inventory.get(1).setAmount(newFoodAmount);
							}
						}
						if(i == 1 && remove == true) {
							robbed = robbed + "Money ";
							if(amountMoney <= 0) {
								inventory.get(0).setAmount(0);
							}else {
								Random rand = new Random();
								int amountMoneyLost = rand.nextInt((int)amountMoney);
							double newMoneyAmount = amountMoney - amountMoneyLost;
							inventory.get(0).setAmount(newMoneyAmount);
							}
						}
						if(i == 2 && remove == true) {
							robbed = robbed + "Heirlooms ";
							if(amountHeirloom <= 0) {
								inventory.get(5).setAmount(0);
							}else {
								Random rand = new Random();
								int amountHeirloomLost = rand.nextInt((int)amountHeirloom);
							double newHeirloomAmount = amountHeirloom - amountHeirloomLost;
							inventory.get(5).setAmount(newHeirloomAmount);
							}
						}
						if(i == 3 && remove == true) {
							robbed = robbed + "spare parts ";
							if(amountSpareParts <= 0) {
								inventory.get(3).setAmount(0);
							}else {
								Random rand = new Random();
								int amountSparePartsLost = rand.nextInt((int)amountSpareParts);
							double newSparePartsAmount = amountSpareParts - amountSparePartsLost;
							inventory.get(3).setAmount(newSparePartsAmount);
							}
						}
						if(i == 4 && remove == true) {
							robbed = robbed + "clothes ";
							if(amountClothes <= 0) {
								inventory.get(2).setAmount(0);
							}else {
								Random rand = new Random();
								int amountClothesLost = rand.nextInt((int)amountClothes);
							double newClothesAmount = amountClothes - amountClothesLost;
							inventory.get(2).setAmount(newClothesAmount);
							}
						}
						if(i == 5 && remove == true) {
							robbed = robbed + "medical supplies ";
							if(amountMedSupply <= 0) {
								inventory.get(4).setAmount(0);
							}else {
								Random rand = new Random();
								int amountMedSupplyLost = rand.nextInt((int)amountMedSupply);
							double newAmountMedSupply = amountMedSupply - amountMedSupplyLost;
							inventory.get(4).setAmount(newAmountMedSupply);
						}
						}
						
					
					effect = "Your " + robbed + " were stolen";
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
			
			
			Random rand = new Random();
			//determines which character at random is getting injured
			 int player = rand.nextInt(party.size());
			 
			 System.out.println(party.size());
			System.out.println("player selected " + player);
			//basic random chance generator, if the dice was rolled and it landed on this number, then you got this outcome.
			if(chance >=0 && chance <= 9) {
				injury = party.get(player).getName() + "has heat exhaustion!"; 
				
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
				injury = party.get(player).getName() + " has typhoid!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
				
			}
			else if(chance > 20 && chance <=30) {
				injury = party.get(player).getName() + "has cholera!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >30 && chance <=40) {
				injury = party.get(player).getName() + " has measles!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >40 && chance <=50) {
				injury = party.get(player).getName() + " has dysentary!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >50 && chance <=60) {
				injury = party.get(player).getName() + " has mountain fever!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >60 && chance <=65) {
				injury = party.get(player).getName() + " has injured their arm!";
				
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
					injury = party.get(player).getName() + " has drank bad water!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
				
			}
			else if(chance >70 && chance <=80) {
				injury = party.get(player).getName() + " has injured their leg!";
				party.get(player).setToBroken(true);
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >80 && chance <=90) {
				injury = party.get(player).getName() + " has been bitten by a snake!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else if(chance >90 && chance <=100) {
				injury = party.get(player).getName() + " has accidentally eaten a poisonous berry!";
				
				//pull the player health data
				int playerHealth = party.get(player).getHealth();
				
				//infect them with the adverse health effect
				playerHealth = playerHealth/2;
				
				//push it back to the array
				party.get(player).setHealth(playerHealth);
			}
			else {
				
				//output who was healed
				injury = party.get(player).getName() + " has recovered! Phew!";
				
					//selection sort algorithm - modified to heal the player
					 int size = party.size();
					 int charLowestHealth = 0;
					 
				        // loop through the entire list of characters
				        for (int i = 0; i < size-1; i++)
				        {
				            // Find the lowest health value
				             charLowestHealth = i;
				            for (int j = i+1; j < size; j++)
				                if (party.get(j).getHealth() < party.get(charLowestHealth).getHealth())
				                    charLowestHealth = j;
				        }
				    
				     //set injured players health to 100
			            party.get(charLowestHealth).setHealth(100);
			          //set the broken bone boolean to true if fixed
						party.get(charLowestHealth).setToFix(true);
			}
			System.out.println(party.get(player).getName() + " has experienced: " + injury);
			return injury;
		}
		
	/**
	 * Generates a random event that does not fit in with a trail attack, a wagon event, or a character event	
	 * @param chance - the percent chance of one of the events happening ( random number generated from 1-100)
	 * @param inventory - an ArrayList of the items on the cart
	 * @param currentDate - the current date
	 * @return inventory - the updated inventory
	 */
		public static String getOtherRandomEvent(double chance, ArrayList<Item> inventory, Date currentDate) {
			
			String other = "";
			
			if(chance >=0 && chance <= 10) {
				other = "You lost the trail!"; 
				currentDate.incrementDate();
				
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
						Random rand = new Random();
						int amountFoodGained = rand.nextInt((int)amountFood);
						double newFoodAmount = amountFood + amountFoodGained;
						inventory.get(1).setAmount(newFoodAmount);
					}
					if(i == 1 && add == true) {
						suprize = suprize + "Money ";
						double amountMoney = inventory.get(0).getAmount();
						Random rand = new Random();
						int amountMoneyGained = rand.nextInt((int)amountMoney);
						double newMoneyAmount = amountMoney + amountMoneyGained;
						inventory.get(0).setAmount(newMoneyAmount);
					}
					if(i == 2 && add == true) {
						suprize = suprize + "Heirlooms ";
						double amountHeirloom = inventory.get(5).getAmount();
						Random rand = new Random();
						int amountHeirloomGained = rand.nextInt((int)amountHeirloom);
						double newHeirloomAmount = amountHeirloom + amountHeirloomGained;
						inventory.get(5).setAmount(newHeirloomAmount);
					}
					if(i == 3 && add == true) {
						suprize = suprize + "spare parts ";
						double amountSpareParts = inventory.get(3).getAmount();
						Random rand = new Random();
						int amountSparePartsGained = rand.nextInt((int)amountSpareParts);
						double newSparePartsAmount = amountSpareParts + amountSparePartsGained;
						inventory.get(3).setAmount(newSparePartsAmount);
					}
					if(i == 4 && add == true) {
						suprize = suprize + "clothes ";
						double amountClothes = inventory.get(2).getAmount();
						Random rand = new Random();
						int amountClothesGained = rand.nextInt((int)amountClothes);
						double newClothesAmount = amountClothes + amountClothesGained;
						inventory.get(2).setAmount(newClothesAmount);
					}
					if(i == 5 && add == true) {
						suprize = suprize + "medical supplies ";
						double amountMedSupply = inventory.get(4).getAmount();
						Random rand = new Random();
						int amountMedSupplyGained = rand.nextInt((int)amountMedSupply);
						double newAmountMedSupply = amountMedSupply + amountMedSupplyGained;
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
				Random rand = new Random();
				int amountHeirloomLost = rand.nextInt((int)amountHeirloom);
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
						Random rand = new Random();
						int amountFoodLost = rand.nextInt((int)amountFood);
						double newFoodAmount = amountFood - amountFoodLost;
						inventory.get(1).setAmount(newFoodAmount);
					}
					if(i == 1 && add == true) {
						burned = burned + "Money ";
						double amountMoney = inventory.get(0).getAmount();
						Random rand = new Random();
						int amountMoneyLost = rand.nextInt((int)amountMoney);
						double newMoneyAmount = amountMoney - amountMoneyLost;
						inventory.get(0).setAmount(newMoneyAmount);
					}
					if(i == 2 && add == true) {
						burned = burned + "Heirlooms ";
						double amountHeirloom = inventory.get(5).getAmount();
						Random rand = new Random();
						int amountHeirloomLost = rand.nextInt((int)amountHeirloom);
						double newHeirloomAmount = amountHeirloom - amountHeirloomLost;
						inventory.get(5).setAmount(newHeirloomAmount);
					}
					if(i == 3 && add == true) {
						burned = burned + "spare parts ";
						double amountSpareParts = inventory.get(3).getAmount();
						Random rand = new Random();
						int amountSparePartsLost = rand.nextInt((int)amountSpareParts);
						double newSparePartsAmount = amountSpareParts - amountSparePartsLost;
						inventory.get(3).setAmount(newSparePartsAmount);
					}
					if(i == 4 && add == true) {
						burned = burned + "clothes ";
						double amountClothes = inventory.get(2).getAmount();
						Random rand = new Random();
						int amountClothesLost = rand.nextInt((int)amountClothes);
						double newClothesAmount = amountClothes - amountClothesLost;
						inventory.get(2).setAmount(newClothesAmount);
					}
					if(i == 5 && add == true) {
						burned = burned + "medical supplies ";
						double amountMedSupply = inventory.get(4).getAmount();
						Random rand = new Random();
						int amountMedSupplyLost = rand.nextInt((int)amountMedSupply);
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
				Random rand = new Random();
				int amountFoodGained = rand.nextInt((int)amountFood/3);
				double newFoodAmount = amountFood + amountFoodGained;
				inventory.get(1).setAmount(newFoodAmount);
				}
				else if (berry >7 && berry <= 10) {
					other = "A rabbit stole the berries you found! Dat Wascally Wabbit!";
					double amountFood = inventory.get(1).getAmount();
					Random rand = new Random();
					int amountFoodLost = rand.nextInt((int)amountFood);
					double newFoodAmount = amountFood - amountFoodLost;
					inventory.get(1).setAmount(newFoodAmount);
				}
			}
		else if(chance >60 && chance <=80) {
				other = "Your food spoiled in the heat of the sun!";
				double amountFood = inventory.get(1).getAmount();
				Random rand = new Random();
				int amountFoodLost = rand.nextInt((int)amountFood);
				double newFoodAmount = amountFood - amountFoodLost;
				inventory.get(1).setAmount(newFoodAmount);
			}
			//mountain case
			else if(chance >80 && chance <=90) {
				double trail =(int) Math.random()*10+1;
				if(trail >0 && trail <=8) {
						other = "You found a rough trail!";
						currentDate.incrementDate();
				}
					else if(trail >8 && trail <=10) {
				other = "You found an impassable trail!";
				currentDate.incrementDate();
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
				
				Random rand = new Random();
				int amountLost = rand.nextInt((int)amount);
				double newAmount = amount - amountLost;
				inventory.get(3).setAmount(newAmount);
			}
			else if(chance > 30 && chance <= 60) {
				part = "and a wagon axel broke!";
				
				Random rand = new Random();
				int amountLost = rand.nextInt((int)amount);
				double newAmount = amount - amountLost;
				inventory.get(3).setAmount(newAmount);
			}
			else if(chance > 60 && chance <= 90) {
				part = "and a wagon tongue broke!";
				
				Random rand = new Random();
				int amountLost = rand.nextInt((int)amount);
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
		 * 
		 * @return
		 */
		//public String getNPCtext() {
			
			
			
			//return
		//}
		/**
		 * A function that generates random events, it controls what occurs day to day
		 * @param chance - a randomized variable from 1 to 100
		 * @param inventory - the current inventory of the wagon
		 * @param party - an array list of all the characters
		 * @param currentDate - the current date
		 * @return whatHappened - tells the user what happened on this day
		 * **/
		public String generateRandomEvent(double chance, ArrayList<Item> inventory,ArrayList<Character> party, Date currentDate ) {

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
				whatHappened = getRandomEvent.getCharacterEvent(characterEventChance, party) + "!";
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
			whatHappened = getRandomEvent.getOtherRandomEvent(otherRandomEventChance, inventory, currentDate);
			}
			return whatHappened;
		}
}