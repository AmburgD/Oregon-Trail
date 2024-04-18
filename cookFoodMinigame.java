/**
 * @author Dax Amburgy
 * @version 2.0
 * 
 * This code is to be copy and pasted into the main window of our Oregon trail game.
 * It creates a minigame that can be played while you are traveling along the trail to boost
 * the health of the party. Certain parts of the code are for test purposes and need to be 
 * removed during the transition. These are labeled inside the code. 
 * 
 * @update 2.0 the clock can now stop if the player does not press the stop button after 9 seconds. This will burn the food.
 * it also fixes a bug where the player can spam the stop cooking button and send their party health to negative infinity, while
 * also sending their food value to the negative infinity.  
 */

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;


//this frame is designed to be a testing ground for the cooking food minigame inside the Oregon Trail game.
public class cookFoodMinigame {

	//variables that need to be added to the main
	private JFrame frmLabDemo;
	private JTextField digitTextField;
	private int counter = 0;
	private String[] digitString = {"ZERO", "ONE", "TWO", "THREE", "FOUR",
            "FIVE", "SIX", "SEVEN", "EIGHT", "NINE",};
	private Timer clock;
	
	//Party Members
		private Character hattie = new Character("Hattie", "Female",13, 100,100, true);
		private Character augusta = new Character("Augusta","Female", 42, 100,100, true);
		private Character charles = new Character("Charles","Male",44, 100,100, true);
		private Character june = new Character("Billy", "Male", 16, 100,100, true);
		private Character tim = new Character("Tim", "Male", 5, 100,100, true);
		private ArrayList<Character> party = new ArrayList<Character>(Arrays.asList(hattie,augusta,charles,june,tim));	//Stores all members of the party
		
		//Party inventory
		private Item money = new Item("dollars",865.0,0);
		private Item food = new Item("food",100,1);
		private Item clothing = new Item("sets of clothes",0,3);
		private Item spareParts = new Item("spare parts",0,50);
		private Item medicalSupplies = new Item("medical supplies",0,1);
		private Item heirlooms = new Item("heirlooms",0,1);
		private ArrayList <Item> inventory = new ArrayList <Item>(Arrays.asList(money,food,clothing,spareParts,medicalSupplies,heirlooms));	//Stores the current inventory of the Wagon
		

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cookFoodMinigame window = new cookFoodMinigame();
					window.frmLabDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public cookFoodMinigame() {
		initialize();
		
		digitTextField.setText(digitString[0]); // initializing display to zero
		
		
		JLabel whatHappened = new JLabel("");
		whatHappened.setFont(new Font("Tahoma", Font.PLAIN, 15));
		whatHappened.setForeground(Color.YELLOW);
		whatHappened.setBounds(38, 168, 388, 43);
		frmLabDemo.getContentPane().add(whatHappened);
		
		JLabel effect = new JLabel("");
		effect.setForeground(Color.GREEN);
		effect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		effect.setBounds(192, 365, 283, 35);
		frmLabDemo.getContentPane().add(effect);
		
		JButton gameReset = new JButton("Reset Game");
		gameReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
				counter = 0;
				digitTextField.setText(digitString[counter]);
			}
		});
		gameReset.setBounds(247, 311, 126, 21);
		frmLabDemo.getContentPane().add(gameReset);
		gameReset.setVisible(false);
		
		JButton stop = new JButton("Check Food");
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock.stop();
				stop.setVisible(false);
				gameReset.setVisible(true);
				//make sure they cannot decrease their food value
				if(inventory.get(1).getAmount() <=0){
					inventory.get(1).setAmount(0);
					effect.setText("This button does nothing since you have no food");
	} else {
				if(counter>=4 && counter<=6) {
					//food is cooked and hunger is decreased
					for(Character i : party) {
						//make sure the health cannot exceed 100
						if(i.currentHealth(i.getHealth())>100) {
							i.currentHealth(100);
						}else {
							//otherwise heal the player
						i.currentHealth(i.getHealth()+30);
						}
						System.out.println("Health: " + i.getHealth() );
					}
					//this means that food is also decreased
					inventory.get(1).setAmount(inventory.get(1).getAmount()-10);
					whatHappened.setText("The food was cooked well");
					effect.setText("You have " + inventory.get(1).getAmount() + " lbs of food left.");
					}
				else if(counter < 4) {
					//food is undercooked and gets rid of party member health 
					for(Character i : party) {
						//if the current health is less than zero, make sure that it doesnt go below zero
						if(i.currentHealth(i.getHealth())<0) {
							i.currentHealth(0);
							//otherwise remove 30 health
						} else {
						i.currentHealth(i.getHealth()-30);
						}
						System.out.println("Health: " + i.getHealth() );
					}
					//remove 10 undercooked lbs of food from the inventory per the cost of the game
					inventory.get(1).setAmount(inventory.get(1).getAmount()-10);
					whatHappened.setText("The food was raw!");
					effect.setText("You have " + inventory.get(1).getAmount() + " lbs of food left.");
				}
				else {
					//food is burned and your food stock goes down
					inventory.get(1).setAmount(inventory.get(1).getAmount()-10);
					whatHappened.setText("The food was burned!");
					effect.setText("You have " + inventory.get(1).getAmount() + " lbs of food left.");
				
				}
				System.out.println("You have " + inventory.get(1).getAmount() + " lbs of food");
				
			}
			}
		});
		stop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stop.setBounds(247, 266, 126, 35);
		frmLabDemo.getContentPane().add(stop);
		stop.setVisible(false);
		
		JButton Start = new JButton("Start Cooking");
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inventory.get(1).getAmount()<10) {
					whatHappened.setText("You do not have enough food to cook!");
				}
				else {
				counter = 0;
				digitTextField.setText(digitString[0]);
				clock.restart();
				stop.setVisible(true);
				gameReset.setVisible(false);
				}
				
				System.out.println("You have " + inventory.get(1).getAmount() + " lbs of food");
			}
			
		});
		Start.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Start.setBounds(237, 221, 145, 35);
		frmLabDemo.getContentPane().add(Start);
		
		JLabel lblNewLabel = new JLabel("Press the Start Cooking button to start cooking. ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(51, 51, 51));
		lblNewLabel.setBackground(new Color(160, 82, 45));
		lblNewLabel.setBounds(10, 0, 416, 38);
		frmLabDemo.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Count to five then press Check Food to stop cooking.");
		lblNewLabel_1.setBackground(new Color(160, 82, 45));
		lblNewLabel_1.setForeground(new Color(51, 51, 51));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 34, 416, 29);
		frmLabDemo.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("If the button is pressed after 5 seconds, the food will be burned. ");
		lblNewLabel_3.setBackground(new Color(160, 82, 45));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setForeground(new Color(51, 51, 51));
		lblNewLabel_3.setBounds(10, 60, 634, 35);
		frmLabDemo.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("If the button is pressed before 5 seconds, the food will be raw and make your party sick!");
		lblNewLabel_4.setBackground(new Color(160, 82, 45));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setForeground(new Color(51, 51, 51));
		lblNewLabel_4.setBounds(10, 92, 634, 29);
		frmLabDemo.getContentPane().add(lblNewLabel_4);
		
		JButton endGame = new JButton("EXIT");
		endGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whatHappened.setVisible(false);
				lblNewLabel_1.setVisible(false);
				lblNewLabel.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				Start.setVisible(false);
				stop.setVisible(false);
				gameReset.setVisible(false);
				endGame.setVisible(false);
				
			}
		});
		endGame.setBounds(509, 374, 85, 21);
		frmLabDemo.getContentPane().add(endGame);
		
		JButton enterGame = new JButton("Open Cooking Minigame");
		enterGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				whatHappened.setVisible(true);
				lblNewLabel_1.setVisible(true);
				lblNewLabel.setVisible(true);
				lblNewLabel_3.setVisible(true);
				lblNewLabel_4.setVisible(true);
				Start.setVisible(true);
				stop.setVisible(true);
				gameReset.setVisible(true);
				endGame.setVisible(true);
			}
		});
		enterGame.setBounds(10, 374, 155, 35);
		frmLabDemo.getContentPane().add(enterGame);
		
		clock = new javax.swing.Timer(1000, new ActionListener() {
		           public void actionPerformed(ActionEvent evt) { 
		              clockActionPerformed( evt ); 
		           }
			        });
	}
	
	public void clockActionPerformed(ActionEvent evt) {
		   counter = (counter + 1) % 10;
			   digitTextField.setText(digitString[counter]);
			   if(counter>=9) {
				   clock.stop(); 
				   
				   JLabel whatHappened = new JLabel("");
					whatHappened.setFont(new Font("Tahoma", Font.PLAIN, 15));
					whatHappened.setForeground(Color.YELLOW);
					whatHappened.setBounds(55, 168, 188, 43);
					frmLabDemo.getContentPane().add(whatHappened);
					
					JLabel effect = new JLabel("");
					effect.setForeground(Color.GREEN);
					effect.setFont(new Font("Tahoma", Font.PLAIN, 15));
					effect.setBounds(192, 365, 283, 35);
					frmLabDemo.getContentPane().add(effect);
					
				 //food is burned and your food stock goes down
					inventory.get(1).setAmount(inventory.get(1).getAmount()-10);
					whatHappened.setText("The food was burned!");
					effect.setText("You have " + inventory.get(1).getAmount() + " lbs of food left.");
					
				   }
			  }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLabDemo = new JFrame();
		frmLabDemo.getContentPane().setBackground(new Color(184, 134, 11));
		frmLabDemo.setTitle("Cooking Minigame");
		frmLabDemo.setBounds(100, 100, 668, 479);
		frmLabDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLabDemo.getContentPane().setLayout(null);
		
		//make this disappear when adding to the main code
		digitTextField = new JTextField();
		digitTextField.setForeground(Color.ORANGE);
		digitTextField.setBackground(Color.WHITE);
		digitTextField.setFont(new Font("Gill Sans MT", Font.PLAIN, 32));
		digitTextField.setText("THREE");
		digitTextField.setBounds(444, 168, 200, 64);
		frmLabDemo.getContentPane().add(digitTextField);
		digitTextField.setColumns(10);
		digitTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		digitTextField.setOpaque(false);

		
	}
}
