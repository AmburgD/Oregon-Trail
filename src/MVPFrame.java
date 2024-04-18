/**
 * MVPFrame
 * Created 3/26/22
 * @author Lochlyn Ramsey
 *	
 *	The main, Jframe, and miscellaneous methods for the Oregon Trail MVP
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTree;

public class MVPFrame {

	//all JFrame components needing to be accessed from outside the initialize method located here
	private JFrame frame;

	//Shop Pane Components
	private JLayeredPane shopPane;
		private JTextField oxenStoreAmt;
		private JTextField foodStoreAmt;
		private JTextField clothingStoreAmt;
		private JTextField partsStoreAmt;
		private JTextField heirloomStoreAmt;
		private JTextField medSupplyStoreAmt;
		private JLabel currentWeight;
		private JLabel storeBalanceDisplay;
		private JLabel overWeightWarning;
		private JButton exitStoreBtn;
		private JLabel availableFundsDisplay;
		private JLabel noOxWarning;
	
	//Main Pane Components
	private JLayeredPane mainPane;
		private JLabel dateLbl;
		private JTextArea infoField;
		private JButton assessSituationBtn;
		private JButton progDayBtn;
		private JComboBox <String> rationsComboBox;
		private JLabel partyHeaderLbl;
		private JComboBox <String> memberSelector;
		private JLabel healthLbl;
		private JLabel foodInvLbl;
		private JLabel clothingInvLbl;
		private JLabel partsInvLbl;
		private JLabel medSuppInvLbl;
		private JLabel lblHeirloomslbs;
		private JTextField clothingInvDisp;
		private JTextField partsInvDisp;
		private JTextField medSuppInvDisplay;
		private JTextField heirloomsInvDisp;
		private JTextField foodInvDisp;
		private JLabel distanceLbl;
		private JLabel wagonGifLbl;
		private JLabel oxGifLbl1;
		private JLabel oxGifLbl2;
		private JLabel oxGifLbl3;
		
	//Settlement Pane Components
	private JLayeredPane settlementPane;
		private JLabel locNameLbl;
	

	
	//general game variables
	private Wagon wagon = new Wagon();
	private Map map = new Map(1);
	private double storeBalance; 							//Used to store the current cost of all supplies being purchased while in the store
	private int currentDay; 								//Stores the current overall day
	private int daysOnTrail; 								//Stores the length of time since the journey began
	private boolean paused = true; 							//Stores whether the progression of days is paused or not
	private boolean gameDone = false;						//Whether the party has reached Oregon or not.
	private Date date = new Date(3,31,1848);				//Stores the current date
	private getRandomEvent rndEventGen = new getRandomEvent();	//Used for calls to the RandomEvents class
	
	//Party Members
	private Character hattie = new Character("Hattie", "Female",13);
	private Character augusta = new Character("Augusta","Female", 43);
	private Character charles = new Character("Charles","Male",47);
	private Character june = new Character("June", "Female", 36);
	private Character tim = new Character("Tim", "Male", 40);
	private ArrayList<Character> party = new ArrayList<Character>(Arrays.asList(hattie,augusta,charles,june,tim));	//Stores all members of the party
	
	//Party inventory
	private Item money = new Item("dollars",865.0,0);
	private Item food = new Item("food",0,1);
	private Item clothing = new Item("sets of clothes",0,3);
	private Item spareParts = new Item("spare parts",0,50);
	private Item medicalSupplies = new Item("medical supplies",0,1);
	private Item heirlooms = new Item("heirlooms",0,1);
	private ArrayList <Item> inventory = new ArrayList <Item>(Arrays.asList(money,food,clothing,spareParts,medicalSupplies,heirlooms));	//Stores the current inventory of the Wagon
	
	//Game Landmarks
	private Location independence = new Location("Independence Missouri", 0, 0, 1);
	//private Location rvrCross1 = new Location("First River Crossing",1,175,2);
	private Location fortKearney = new Location("Fort Kearney",1,275,1);
	private Location ashHollow = new Location("Ash Hollow",2,160,3);
	private ArrayList<Location> landmarks = new ArrayList<Location>(Arrays.asList(independence,fortKearney,ashHollow));	//Stores all game locations
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MVPFrame window = new MVPFrame();
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
	public MVPFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1100, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		settlementPane = new JLayeredPane();
		settlementPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		settlementPane.setBounds(594, 315, 492, 225);
		frame.getContentPane().add(settlementPane);
		settlementPane.setOpaque(true);
		settlementPane.setVisible(false);
		
		locNameLbl = new JLabel("You are in: Independence, Missouri");
		locNameLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		locNameLbl.setBounds(71, 10, 381, 24);
		settlementPane.add(locNameLbl);
		
		JButton openShopBtn = new JButton("Shop");
		openShopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPane.setVisible(false);
				settlementPane.setVisible(false);
				shopping();
			}
		});
		openShopBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		openShopBtn.setBounds(124, 82, 223, 33);
		settlementPane.add(openShopBtn);
		
		noOxWarning = new JLabel("You won't get far without any Oxen!");
		noOxWarning.setHorizontalAlignment(SwingConstants.CENTER);
		noOxWarning.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		noOxWarning.setBounds(50, 173, 381, 31);
		settlementPane.add(noOxWarning);
		noOxWarning.setVisible(false);
		
		JButton leaveSettlementBtn = new JButton("Leave Settlement");
		leaveSettlementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wagon.getNumOx() == 0) {
					noOxWarning.setVisible(true);
				}
				else if(map.getSegments() == 0) {
					System.out.println("Beginning Journey!");
					map.setSegments(1);
					map.setTraveled(0);
					paused = false;
					settlementPane.setVisible(false);
					assessSituationBtn.setVisible(true);
					progDayBtn.setVisible(true);
					wagonGifLbl.setVisible(true);
					oxGifLbl1.setVisible(true);
					oxGifLbl2.setVisible(true);
					oxGifLbl3.setVisible(true);
					
					//game();
				}
				else {
					settlementPane.setVisible(false);
					assessSituationBtn.setVisible(true);
					progDayBtn.setVisible(true);
					paused = false;
					map.setSegments(map.getSegments()+1);
					map.setTraveled(0);
					assessSituationBtn.setText("Assess the Situation");
					wagonGifLbl.setVisible(true);
					oxGifLbl1.setVisible(true);
					oxGifLbl2.setVisible(true);
					oxGifLbl3.setVisible(true);
				}
			}
		});
		leaveSettlementBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		leaveSettlementBtn.setBounds(124, 125, 223, 38);
		settlementPane.add(leaveSettlementBtn);
		
		mainPane = new JLayeredPane();
		mainPane.setBounds(10, 0, 1076, 713);
		frame.getContentPane().add(mainPane);
		mainPane.setOpaque(true);
		mainPane.setVisible(false);
		
		assessSituationBtn = new JButton("Assess the Situation");
		assessSituationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!paused) {
					assessSituationBtn.setText("Continue On");
					paused = true;
				}
				else {
					assessSituationBtn.setText("Assess the Situation");
					paused = false;
				}
				
			}
		});
		assessSituationBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		assessSituationBtn.setBounds(642, 227, 254, 33);
		mainPane.add(assessSituationBtn);
		assessSituationBtn.setVisible(false);
		
		
		infoField = new JTextArea();
		infoField.setWrapStyleWord(true);
		infoField.setEditable(false);
		infoField.setLineWrap(true);
		infoField.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
		infoField.setText("Welcome to the Oregon Trail!\r\nThe year is 1848. Your name is Hattie Campbell. You and your parents have made the trip to Independence, Missouri to stock up on supplies before you set out on The Trail to the Wilamette Valley.");
		infoField.setBounds(124, 20, 836, 174);
		mainPane.add(infoField);
		
		dateLbl = new JLabel("31 March 1848");
		dateLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(120, 204, 160, 44);
		mainPane.add(dateLbl);
		
		JComboBox <String> paceComboBox = new JComboBox<String>();
		paceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Slow", "Steady", "Strenuous"}));
		paceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setPace(paceComboBox.getSelectedIndex());
			}
		});
		paceComboBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		paceComboBox.setBounds(120, 301, 95, 21);
		mainPane.add(paceComboBox);
		
		JLabel paceLbl = new JLabel("Pace:");
		paceLbl.setHorizontalAlignment(SwingConstants.LEFT);
		paceLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		paceLbl.setBounds(120, 276, 65, 21);
		mainPane.add(paceLbl);
		
		JLabel rationsLbl = new JLabel("Rations:");
		rationsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		rationsLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		rationsLbl.setBounds(120, 333, 65, 21);
		mainPane.add(rationsLbl);
		
		rationsComboBox = new JComboBox<String>();
		rationsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Meager", "Balanced", "Filling"}));
		rationsComboBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		rationsComboBox.setBounds(120, 358, 95, 21);
		mainPane.add(rationsComboBox);
		
		progDayBtn = new JButton("Progress Day");
		progDayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayToday();
			}
		});
		progDayBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		progDayBtn.setBounds(50, 647, 165, 33);
		mainPane.add(progDayBtn);
		progDayBtn.setVisible(false);
		
		partyHeaderLbl = new JLabel("Party Members");
		partyHeaderLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		partyHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		partyHeaderLbl.setBounds(296, 276, 160, 38);
		mainPane.add(partyHeaderLbl);
		
		memberSelector = new JComboBox<String>();
		memberSelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(memberSelector.getSelectedIndex()==0) {
					healthLbl.setText("Health: " + health2Txt((party.get(0).getHealth()+party.get(1).getHealth()+party.get(2).getHealth()+party.get(3).getHealth()+party.get(4).getHealth())/5));
				}
				else if(memberSelector.getSelectedIndex()==1) {
					healthLbl.setText("Health: " + health2Txt(party.get(0).getHealth()));
				}
				else if(memberSelector.getSelectedIndex()==2) {
					healthLbl.setText("Health: " + health2Txt(party.get(1).getHealth()));
				}
				else if(memberSelector.getSelectedIndex()==3) {
					healthLbl.setText("Health: " + health2Txt(party.get(2).getHealth()));
				}
				else if(memberSelector.getSelectedIndex()==4) {
					healthLbl.setText("Health: " + health2Txt(party.get(3).getHealth()));
				} 
				else if(memberSelector.getSelectedIndex()==5) {
					healthLbl.setText("Health: " + health2Txt(party.get(4).getHealth()));
				}
			}
		});
		memberSelector.setModel(new DefaultComboBoxModel<String>(new String[] {"Overall",party.get(0).getName(),party.get(1).getName(),party.get(2).getName(),party.get(3).getName(),party.get(4).getName()}));
		memberSelector.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		memberSelector.setBounds(306, 321, 155, 33);
		mainPane.add(memberSelector);
		
		healthLbl = new JLabel("Health: Good");
		healthLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		healthLbl.setHorizontalAlignment(SwingConstants.LEFT);
		healthLbl.setBounds(284, 358, 200, 50);
		mainPane.add(healthLbl);
		
		foodInvLbl = new JLabel("Food: ");
		foodInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		foodInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		foodInvLbl.setBounds(142, 418, 200, 44);
		mainPane.add(foodInvLbl);
		
		clothingInvLbl = new JLabel("Clothing:");
		clothingInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		clothingInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		clothingInvLbl.setBounds(138, 460, 200, 44);
		mainPane.add(clothingInvLbl);
		
		partsInvLbl = new JLabel("Parts:");
		partsInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		partsInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		partsInvLbl.setBounds(138, 502, 200, 44);
		mainPane.add(partsInvLbl);
		
		medSuppInvLbl = new JLabel("Medical Supplies:");
		medSuppInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		medSuppInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		medSuppInvLbl.setBounds(143, 538, 200, 44);
		mainPane.add(medSuppInvLbl);
		
		lblHeirloomslbs = new JLabel("Heirlooms (lbs):");
		lblHeirloomslbs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeirloomslbs.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblHeirloomslbs.setBounds(142, 577, 200, 44);
		mainPane.add(lblHeirloomslbs);
		
		foodInvDisp = new JTextField();
		foodInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		foodInvDisp.setText("0");
		foodInvDisp.setBackground(Color.WHITE);
		foodInvDisp.setEditable(false);
		foodInvDisp.setBounds(361, 428, 95, 28);
		mainPane.add(foodInvDisp);
		foodInvDisp.setColumns(10);
		
		clothingInvDisp = new JTextField();
		clothingInvDisp.setText("0");
		clothingInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		clothingInvDisp.setEditable(false);
		clothingInvDisp.setColumns(10);
		clothingInvDisp.setBackground(Color.WHITE);
		clothingInvDisp.setBounds(361, 470, 95, 28);
		mainPane.add(clothingInvDisp);
		
		partsInvDisp = new JTextField();
		partsInvDisp.setText("0");
		partsInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		partsInvDisp.setEditable(false);
		partsInvDisp.setColumns(10);
		partsInvDisp.setBackground(Color.WHITE);
		partsInvDisp.setBounds(361, 512, 95, 28);
		mainPane.add(partsInvDisp);
		
		medSuppInvDisplay = new JTextField();
		medSuppInvDisplay.setText("0");
		medSuppInvDisplay.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		medSuppInvDisplay.setEditable(false);
		medSuppInvDisplay.setColumns(10);
		medSuppInvDisplay.setBackground(Color.WHITE);
		medSuppInvDisplay.setBounds(361, 554, 95, 28);
		mainPane.add(medSuppInvDisplay);
		
		heirloomsInvDisp = new JTextField();
		heirloomsInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		heirloomsInvDisp.setText("0");
		heirloomsInvDisp.setEditable(false);
		heirloomsInvDisp.setColumns(10);
		heirloomsInvDisp.setBackground(Color.WHITE);
		heirloomsInvDisp.setBounds(361, 593, 95, 28);
		mainPane.add(heirloomsInvDisp);
		
		distanceLbl = new JLabel("Miled Travelled: 0.0");
		distanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		distanceLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		distanceLbl.setBounds(290, 202, 194, 50);
		mainPane.add(distanceLbl);
		
		wagonGifLbl = new JLabel("");
		wagonGifLbl.setIcon(new ImageIcon(MVPFrame.class.getResource("/Assets/wagon-pixilart.gif")));
		wagonGifLbl.setBounds(585, 418, 100, 100);
		mainPane.add(wagonGifLbl);
		wagonGifLbl.setVisible(false);
		
		oxGifLbl1 = new JLabel("");
		oxGifLbl1.setIcon(new ImageIcon(MVPFrame.class.getResource("/Assets/ox-pixilart.gif")));
		oxGifLbl1.setBounds(691, 418, 100, 100);
		mainPane.add(oxGifLbl1);
		oxGifLbl1.setVisible(false);
		
		oxGifLbl2 = new JLabel("");
		oxGifLbl2.setIcon(new ImageIcon(MVPFrame.class.getResource("/Assets/ox-pixilart.gif")));
		oxGifLbl2.setBounds(797, 418, 100, 100);
		mainPane.add(oxGifLbl2);
		oxGifLbl2.setVisible(false);
		
		oxGifLbl3 = new JLabel("");
		oxGifLbl3.setIcon(new ImageIcon(MVPFrame.class.getResource("/Assets/ox-pixilart.gif")));
		oxGifLbl3.setBounds(903, 418, 100, 100);
		mainPane.add(oxGifLbl3);
		healthLbl.setVisible(true);
		oxGifLbl3.setVisible(false);
		
		JLayeredPane wagonPane = new JLayeredPane();
		wagonPane.setBounds(68, 694, 503, -263);
		frame.getContentPane().add(wagonPane);
		wagonPane.setOpaque(true);
		
		JLayeredPane titlePane = new JLayeredPane();
		titlePane.setBounds(10, 0, 1076, 713);
		frame.getContentPane().add(titlePane);
		titlePane.setLayout(null);
		titlePane.setOpaque(true);
		
		JLabel startButton = new JLabel("");
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titlePane.setVisible(false);
				mainPane.setVisible(true);
				settlementPane.setVisible(true);
			}
		});
		startButton.setIcon(new ImageIcon(MVPFrame.class.getResource("/Assets/startButton.png")));
		startButton.setBounds(332, 453, 400, 187);
		titlePane.add(startButton);
		
		JLabel trailLogo = new JLabel("");
		trailLogo.setIcon(new ImageIcon(MVPFrame.class.getResource("/Assets/trailLogoGif.gif")));
		trailLogo.setBounds(118, 33, 800, 400);
		titlePane.add(trailLogo);
		
		shopPane = new JLayeredPane();
		shopPane.setBounds(10, 0, 1076, 713);
		frame.getContentPane().add(shopPane);
		shopPane.setOpaque(true);
		shopPane.setVisible(false);
		
		currentWeight = new JLabel("0/0");
		currentWeight.setHorizontalAlignment(SwingConstants.CENTER);
		currentWeight.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		currentWeight.setBounds(738, 81, 158, 26);
		shopPane.add(currentWeight);
		
		JLabel oxenStoreLbl = new JLabel("Oxen");
		oxenStoreLbl.setToolTipText("[Price: $40/yoke] \r\nOxen are sold by the yoke. The more oxen you have, the faster you can travel or the more supplies you can carry. \r\nThe recommended minimum is 3 yoke.");
		oxenStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		oxenStoreLbl.setBounds(88, 68, 124, 26);
		shopPane.add(oxenStoreLbl);
		
		JLabel foodStoreLbl = new JLabel("Food");
		foodStoreLbl.setToolTipText("[Price: $0.20/lb | Weight: 1 lb]\r\nFood is used to feed your family. \r\nThe recommended portions are 200 pounds per person.");
		foodStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		foodStoreLbl.setBounds(88, 121, 124, 26);
		shopPane.add(foodStoreLbl);
		
		JLabel clothingStoreLbl = new JLabel("Clothing");
		clothingStoreLbl.setToolTipText("[Price: $10.00/set | Weight: 3lbs/set]\r\nClothing protects you from the elements. Not having enough clothing for everyone in your party may result in injury or death. \r\nThe recommended amount is 2 sets per person.");
		clothingStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		clothingStoreLbl.setBounds(88, 181, 124, 26);
		shopPane.add(clothingStoreLbl);
		
		JLabel sparePartsStoreLbl = new JLabel("Spare Parts");
		sparePartsStoreLbl.setToolTipText("[Price: $10/each | Weight: 50 lbs/each]\r\nSpare parts for the wagon. Your Pa is handy, but there may be repairs he can't make without extra parts.");
		sparePartsStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		sparePartsStoreLbl.setBounds(88, 228, 124, 26);
		shopPane.add(sparePartsStoreLbl);
		
		JLabel heirloomsStoreLbl = new JLabel("Heirlooms");
		heirloomsStoreLbl.setToolTipText("These are precious family heirlooms. They won't provide any benefit on The Trail, but they'll be a nice reminder of your old home when you reach Oregon.\r\nYou can determine how much you want to bring with you, but be careful! Any heirloms you decide to take are very important to you and you will not be able to get rid of them to regain the lost weight.\r\n(You will receive bonus points if you arrive in Oregon with some heirlooms)");
		heirloomsStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		heirloomsStoreLbl.setBounds(88, 332, 124, 26);
		shopPane.add(heirloomsStoreLbl);
		
		oxenStoreAmt = new JTextField();
		oxenStoreAmt.setText("0");
		oxenStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setNumOx(Integer.parseInt(oxenStoreAmt.getText()));
				currentWeight.setText(wagon.getCurrentWeight() + "/" + wagon.getWeightLim());
				updateStoreStuff();
			}
		});
		oxenStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		oxenStoreAmt.setBounds(290, 64, 96, 26);
		shopPane.add(oxenStoreAmt);
		oxenStoreAmt.setColumns(10);
		
		foodStoreAmt = new JTextField();
		foodStoreAmt.setText("0");
		foodStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		
		foodStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		foodStoreAmt.setColumns(10);
		foodStoreAmt.setBounds(290, 121, 96, 25);
		shopPane.add(foodStoreAmt);
		
		clothingStoreAmt = new JTextField();
		clothingStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		
		clothingStoreAmt.setText("0");
		clothingStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		clothingStoreAmt.setColumns(10);
		clothingStoreAmt.setBounds(290, 181, 96, 25);
		shopPane.add(clothingStoreAmt);
		
		partsStoreAmt = new JTextField();
		partsStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		partsStoreAmt.setText("0");
		partsStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		partsStoreAmt.setColumns(10);
		partsStoreAmt.setBounds(290, 228, 96, 25);
		shopPane.add(partsStoreAmt);
		
		heirloomStoreAmt = new JTextField();
		heirloomStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		heirloomStoreAmt.setText("0");
		heirloomStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		heirloomStoreAmt.setColumns(10);
		heirloomStoreAmt.setBounds(290, 332, 96, 25);
		shopPane.add(heirloomStoreAmt);
		
		JLabel currentWeightLbl = new JLabel("Current Weight");
		currentWeightLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentWeightLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		currentWeightLbl.setBounds(717, 49, 182, 22);
		shopPane.add(currentWeightLbl);
		
		JLabel medicalSupplyStoreLbl = new JLabel("Medical Supplies");
		medicalSupplyStoreLbl.setToolTipText("[Price:  $5/each | Weight: 1 lb/each]\r\nUsed to heal injured or sick members of your party. Things can get dangerous out on The Trail!\r\n");
		medicalSupplyStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		medicalSupplyStoreLbl.setBounds(88, 278, 182, 26);
		shopPane.add(medicalSupplyStoreLbl);
		
		medSupplyStoreAmt = new JTextField();
		medSupplyStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		medSupplyStoreAmt.setText("0");
		medSupplyStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		medSupplyStoreAmt.setColumns(10);
		medSupplyStoreAmt.setBounds(290, 278, 96, 25);
		shopPane.add(medSupplyStoreAmt);
		
		JLabel currentCostLbl = new JLabel("Current Cost");
		currentCostLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentCostLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		currentCostLbl.setBounds(714, 190, 182, 22);
		shopPane.add(currentCostLbl);
		
		storeBalanceDisplay = new JLabel("$0.00");
		storeBalanceDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		storeBalanceDisplay.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		storeBalanceDisplay.setBounds(717, 230, 182, 22);
		shopPane.add(storeBalanceDisplay);
		
		overWeightWarning = new JLabel("OVERWEIGHT!");
		overWeightWarning.setForeground(Color.RED);
		overWeightWarning.setHorizontalAlignment(SwingConstants.CENTER);
		overWeightWarning.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		overWeightWarning.setBounds(717, 117, 190, 26);
		shopPane.add(overWeightWarning);
		
		JLabel availableFundsLbl = new JLabel("Available Funds");
		availableFundsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		availableFundsLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		availableFundsLbl.setBounds(717, 278, 182, 22);
		shopPane.add(availableFundsLbl);
		
		availableFundsDisplay = new JLabel("$0.00");
		availableFundsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		availableFundsDisplay.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		availableFundsDisplay.setBounds(720, 318, 182, 22);
		shopPane.add(availableFundsDisplay);
		
		exitStoreBtn = new JButton("Exit Store\r\n\r\n");
		exitStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				money.setAmount(money.getAmount() - storeBalance);
				food.setAmount(food.getAmount() + Integer.parseInt(foodStoreAmt.getText()));
				clothing.setAmount(clothing.getAmount() + Integer.parseInt(clothingStoreAmt.getText()));
				spareParts.setAmount(spareParts.getAmount() + Integer.parseInt(partsStoreAmt.getText()));
				medicalSupplies.setAmount(medicalSupplies.getAmount() + Integer.parseInt(medSupplyStoreAmt.getText()));
				heirlooms.setAmount(heirlooms.getAmount() + Integer.parseInt(heirloomStoreAmt.getText()));
				shopPane.setVisible(false);
				oxenStoreAmt.setText("0");
				foodStoreAmt.setText("0");
				clothingStoreAmt.setText("0");
				partsStoreAmt.setText("0");
				medSupplyStoreAmt.setText("0");
				heirloomStoreAmt.setText("0");
				mainPane.setVisible(true);
				settlementPane.setVisible(true);
				updateInventoryDisplay();
			}
		});
		exitStoreBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 35));
		exitStoreBtn.setBounds(385, 584, 278, 54);
		shopPane.add(exitStoreBtn);
		
		JTextArea txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setWrapStyleWord(true);
		txtrWelcomeToThe.setLineWrap(true);
		txtrWelcomeToThe.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		txtrWelcomeToThe.setText("Welcome to the General Store!\r\nHere you can buy supplies for your journey.\r\nHover over an item to see extra prices, weight, and extra information.\r\nClick \"Exit Store\" when you have finished shopping.");
		txtrWelcomeToThe.setBounds(34, 427, 765, 107);
		shopPane.add(txtrWelcomeToThe);
		overWeightWarning.setVisible(false);
	}
	
	
	/**
	 * Called any time a player changes the amount of an item the player is planning to purchase from the store.
	 */
	private void updateStoreStuff() {
		wagon.setCurrentWeight(Double.parseDouble(foodStoreAmt.getText())+(Double.parseDouble(clothingStoreAmt.getText())*3)+(Double.parseDouble(partsStoreAmt.getText())*50)+(Double.parseDouble(medSupplyStoreAmt.getText()))+(Double.parseDouble(heirloomStoreAmt.getText())));
		currentWeight.setText(wagon.getCurrentWeight() + "/" + wagon.getWeightLim());
		if(wagon.getCurrentWeight()>wagon.getWeightLim()) {
			overWeightWarning.setVisible(true);
			exitStoreBtn.setVisible(false);
		}
		else {
			overWeightWarning.setVisible(false);
			exitStoreBtn.setVisible(true);
		}
		storeBalance = ((Double.parseDouble(oxenStoreAmt.getText())*40)+(Double.parseDouble(foodStoreAmt.getText())*0.2)+(Double.parseDouble(clothingStoreAmt.getText())*10)+(Double.parseDouble(partsStoreAmt.getText())*10)+(Double.parseDouble(medSupplyStoreAmt.getText())*5));
		storeBalanceDisplay.setText("$" + storeBalance);
		availableFundsDisplay.setText("$" + (money.getAmount()-storeBalance));
		
		if(money.getAmount()-storeBalance <= 0) {
			exitStoreBtn.setVisible(false);
			availableFundsDisplay.setForeground(Color.RED);
		}
		else {
			exitStoreBtn.setVisible(true);
			availableFundsDisplay.setForeground(Color.BLACK);
			noOxWarning.setVisible(false);
		}
		
		
	}
	
	/**
	 * Called when the player chooses to enter the store in a settlement.
	 */
	private void shopping() {
		shopPane.setVisible(true);
		availableFundsDisplay.setText("$" + money.getAmount());
		
	}
	
	/**
	 * Turns a character's health into a single word
	 * @param health
	 * @return
	 */
	private String health2Txt(int health) {
		if(health == 0) {
			return "Deceased";
		}
		else if(health >0 && health<33) {
			return "Poor";
		}
		else if(health >33 && health<66) {
			return "Fair";
		}
		else {
			return "Good";
		}
	}
	
	/**
	 * Updates the party's inventory
	 */
	private void updateInventoryDisplay() {
		foodInvDisp.setText(""+inventory.get(1).getAmount());
		clothingInvDisp.setText(""+inventory.get(2).getAmount());
		partsInvDisp.setText(""+inventory.get(3).getAmount());
		medSuppInvDisplay.setText(""+inventory.get(4).getAmount());
		heirloomsInvDisp.setText(""+inventory.get(5).getAmount());
		
	}

	/**
	 * Handles all of the tasks that happen every day.
	 */
	private void dayToday() {
		String txtDate =  date.incrementDate();	//Stores the date as a String
		System.out.println(txtDate);
		dateLbl.setText(txtDate);	//Changes the dateLbl to the txtDate
		map.setTraveled(map.getTraveled()+wagon.getSpeed());	//Changes the distance traveled to the the total distance + the current speed of the cart
		System.out.println("Wagon speed = " + wagon.getSpeed());
		System.out.println("Distance Travelled = " + (map.getTraveled()+wagon.getSpeed()));
		distanceLbl.setText("Miles Travelled: " + map.getTraveled());
		boolean isLandmark = false;	//Used to store whether the cart reached or passed a landmark during that day.
		for(Location location : landmarks) {
			if(((location.getPosition() <= map.getTraveled())&&(location.getPosition()>=(map.getTraveled()-wagon.getSpeed()))) && location.getSegPos() == map.getSegments()){	//Checks if the current has reached a landmark
				wagonGifLbl.setVisible(false);
				oxGifLbl1.setVisible(false);
				oxGifLbl2.setVisible(false);
				oxGifLbl3.setVisible(false);
				infoField.setText("You have reached " + location.getName() + "!");
				progDayBtn.setVisible(false);
				assessSituationBtn.setText("Continue On");
				isLandmark = true;
				paused = true; //Pause the progression of days
				if(location.getType() == 1) {	//If landmark is a settlement
					settlementPane.setVisible(true);
					assessSituationBtn.setVisible(false);
					locNameLbl.setText("You are in: " + location.getName());
				}
				else if(location.getType() == 2) {	//If landmark is a river crossing
					//river crossing stuff tbd for final product
				}
				else if(location.getType() == 3) {	//If landmark is a vista
					assessSituationBtn.setText("Continue On");
				}
				else if(location.getType() == 4) {	//If landmark is a fork in the trail
					//trail fork stuff tbd for final product
				}
				else if(location.getType() == 5) {	//If the party has reached the end of the trail
					//endgame stuff tbd for final product
				}
				else {
					System.out.println("Tried to reference an invalid location type! Press Continue On to continue game");
				}
			}
		}
		if(isLandmark == false) { //If the wagon has not reached a landmark today
			double chance = rndEventGen.getRandomValue();
			
			infoField.setText(rndEventGen.generateRandomEvent(chance, inventory, party));
		}
		if(rationsComboBox.getSelectedIndex()==0) {
			inventory.get(1).setAmount(inventory.get(1).getAmount()-3);
		}
		else if(rationsComboBox.getSelectedIndex()==1) {
			inventory.get(1).setAmount(inventory.get(1).getAmount()-5);
		}
		else if(rationsComboBox.getSelectedIndex()==2) {
			inventory.get(1).setAmount(inventory.get(1).getAmount()-7);
		}
		updateInventoryDisplay(); //Update Inventory based on the results of the random events
	}

	/**
	 * This thing runs the game Disabled for MVP, not sure how to make this work.
	 */
	/*private void game() {
		System.out.println("The Game has Started!");
		while(!gameDone){
			while(!paused) {
				dayToday();
				long startTime = System.currentTimeMillis();
				long timediff = 0;
				while(timediff<5000) {
					timediff = System.currentTimeMillis()-startTime;
				}
			}
			System.out.println("Game Paused!");
		}
	}*/
		
}
