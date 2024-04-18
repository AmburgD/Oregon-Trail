import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class weatherWindow {

	private JFrame frame;
	private getRandomEvent forecast = new getRandomEvent();
	
	//Party Members
		private Character hattie = new Character("Hattie", "Female",13);
		private Character augusta = new Character("Augusta","Female", 43);
		private Character charles = new Character("Charles","Male",47);
		private Character june = new Character("June", "Female", 36);
		private Character tim = new Character("Tim", "Male", 40);
		private ArrayList<Character> party = new ArrayList<Character>(Arrays.asList(hattie,augusta,charles,june,tim));	//Stores all members of the party
		
		private Date date = new Date(3,31,1848);		

		//Party inventory
		private Item money = new Item("dollars",865.0,0);
		private Item food = new Item("food",0,1);
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
					weatherWindow window = new weatherWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public weatherWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(141, 40, 147, 59);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Get Weather");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double chance = forecast.getRandomValue();
				
				lblNewLabel.setText(forecast.getWeatherEvent(date.getMonth(), party, inventory));
			}
		});
		btnNewButton.setBounds(98, 182, 175, 46);
		frame.getContentPane().add(btnNewButton);
	}
}
