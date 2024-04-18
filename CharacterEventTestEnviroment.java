import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CharacterEventTestEnviroment {

	private JFrame frame;
	getRandomEvent rand = new getRandomEvent();
	
	//Party Members
		private Character hattie = new Character("Hattie", "Female",13);
		private Character augusta = new Character("Augusta","Female", 43);
		private Character charles = new Character("Charles","Male",47);
		private Character june = new Character("June", "Female", 36);
		private Character tim = new Character("Tim", "Male", 40);
		private ArrayList<Character> party = new ArrayList<Character>(Arrays.asList(hattie,augusta,charles,june,tim));	//Stores all members of the party

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CharacterEventTestEnviroment window = new CharacterEventTestEnviroment();
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
	public CharacterEventTestEnviroment() {
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
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(98, 56, 227, 70);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				lblNewLabel.setText(rand.getCharacterEvent(rand.getRandomValue(),party));
				
			}
		});
		btnNewButton.setBounds(163, 177, 85, 21);
		frame.getContentPane().add(btnNewButton);
	}
}
