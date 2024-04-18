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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame {

	//all JFrame components needing to be accessed from outside the initialize method located here
	private JFrame frmOregonTrail;

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
		private JComboBox <String> paceComboBox;
		private JLayeredPane deathPane;
		
	//Pause Pane Components
		JLayeredPane pausePane;
		
	//Settlement Pane Components
	private JLayeredPane settlementPane;
		private JLabel locNameLbl;
	
	//general game variables
	private Wagon wagon = new Wagon();
	private Map map = new Map(1);
	private double storeBalance; 								//Used to store the current cost of all supplies being purchased while in the store
	private int currentDay; 									//Stores the current overall day
	private boolean paused = true; 								//Stores whether the progression of days is paused or not
	private boolean gameDone = false;							//Whether the party has reached Oregon or not.
	private Date date = new Date(3,31,1848);					//Stores the current date
	private getRandomEvent rndEventGen = new getRandomEvent();	//Used for calls to the RandomEvents class
	private int deadCnt;
	private int adultCnt;
	private int susCnt = 0;
	private playMusic currentSong = new playMusic();
	
	//Party Members
	private Character hattie = new Character("Hattie", "Female",13);
	private Character augusta = new Character("Augusta","Female", 42);
	private Character charles = new Character("Charles","Male",44);
	private Character june = new Character("Billy", "Male", 16);
	private Character tim = new Character("Tim", "Male", 5);
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
	private Location ltlBluRvr = new Location("Little Blue River Crossing",1,175,2);
	private Location fortKearney = new Location("Fort Kearney",1,275,1);
	private Location ashHollow = new Location("Ash Hollow",2,160,3);
	private Location chimneyRock = new Location("Chimney Rock",2,234,3);
	private Location fortLaramie = new Location("Fort Laramie",2,305,1);
	private Location pathfinderRvr = new Location ("Pathfinder River Crossing",3,120,2);
	private Location indepRock = new Location("Independence Rock",3,132,3);
	private Location southPass = new Location("South Pass",3,228,4);
	private Location greenRvr = new Location ("Green River Crossing",3.5,59,2);
	private Location fortBridger = new Location("Fort Bridger",3.5,105,1);
	private Location sodaSprings = new Location("Soda Springs",3,347,3);
	private Location fortHall = new Location ("Fort Hall",3,437,1);
	private Location fortBoise = new Location ("Fort Boise",4,192, 1);
	private Location snakeRvr = new Location ("Snake River Crossing",5, 82, 2);
	private Location blueMtns = new Location ("Blue Mountains", 5, 163, 3);
	private Location fortWallaWalla = new Location ("Fort Walla Walla", 5, 218,1);
	private Location theDalles = new Location("The Dalles", 6, 146, 3);
	private Location wilametteValley = new Location ("The Willamette Valley", 6,216, 5);
	
	private ArrayList<Location> landmarks = new ArrayList<Location>(Arrays.asList(independence,ltlBluRvr,fortKearney,ashHollow,chimneyRock,fortLaramie,pathfinderRvr,indepRock,southPass,greenRvr,fortBridger,sodaSprings,fortHall,fortBoise,snakeRvr,blueMtns,fortWallaWalla,theDalles,wilametteValley));	//Stores all game locations
	private ArrayList<String> vistaImage = new ArrayList<String>(Arrays.asList(null,null,null,"/Assets/ashHollow.gif","/Assets/chimneyRock.png",null,null,null,"/Assets/indepRock.gif",null,null,"/Assets/sodaSprings.gif",null,null,null,"/Assets/blueMtns.gif",null,"/Assets/theDalles.gif",null));
	private JLabel bckGnd;
	private JLabel purseDisplay;
	private JButton buryBtn;
	private JButton susBtn;
	private JLayeredPane gameOverPane;
	private JButton quitGameBtn;
	private JLabel forkLbl;
	private JButton forkOpt1;
	private JButton forkOpt2;
	private JLayeredPane forkPane;
	private JLabel weatherLbl;
	private JLabel creditsButton;
	private JButton btnNewButton;
	private JButton btnTalk;
	private JButton btnReadTrailGuide;
	private JButton btnDoSomeChores;
	private JLayeredPane rvrCrossPane;
	
	//River Crossing Stuff
	private JTextField inputField;
	private String weather; 
	private String input; 
	private String ferryInput; 
	private int ifchoose; 
	private int choose; 
	private JTextArea weatherArea; 
	private JTextArea widthArea; 
	private JTextArea depthArea; 
	private JLabel chooseOutput; 
	private Random rand = new Random(); 
	private JTextField ifNeeded;
	private JLabel rvrbckgnd;
	private JLabel storeBckGnd;
	
	//end screen
	private JLayeredPane endPane;
	private JLabel lblNewLabel;
	private JLabel goodQuantity;
	private JLabel fairQuantity;
	private JLabel poorQuantity;
	private JLabel oxenQuantity;
	private JLabel sparePartsQuantity;
	private JLabel clothesQuantity;
	private JLabel foodQuantity;
	private JLabel cashQuantity;
	private JLabel heirloomQuantity;
	private JLabel goodText;
	private JLabel fairText;
	private JLabel poorText;
	private JLabel oxenText;
	private JLabel sparePartsText;
	private JLabel clothesText;
	private JLabel foodText;
	private JLabel cashText;
	private JLabel heirloomText;
	private JLabel goodPoints;
	private JLabel fairPoints;
	private JLabel poorPoints;
	private JLabel oxenPoints;
	private JLabel sparePartsPoints;
	private JLabel clothesPoints;
	private JLabel foodPoints;
	private JLabel cashPoints;
	private JLabel heirloomMulti;
	private JLabel quantityText;
	private JLabel scoreText;
	private JLabel cannibalizedQuantity;
	private JLabel cannibalizedPoints;
	private JLabel cannibalizedText;
	private JLabel scoreLabel;
	
	private JLayeredPane vistaPane;
	private JLabel vistaLbl;
	
	private double tempWeight;
	
	private JLayeredPane creditsPane;
	private JButton diaryBtn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.frmOregonTrail.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameFrame() {
		initialize();
		
		currentSong.setMusicSource("/Oregon Trail Final/src/HouseOfTheRisingSun.WAV");
		currentSong.startMusic(currentSong.getMusicSource());
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOregonTrail = new JFrame();
		frmOregonTrail.setFont(new Font("Blackadder ITC", Font.BOLD, 20));
		frmOregonTrail.setTitle("Oregon Trail");
		frmOregonTrail.setResizable(false);
		frmOregonTrail.setBounds(100, 100, 1172, 795);
		frmOregonTrail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOregonTrail.getContentPane().setLayout(null);
		
		
		settlementPane = new JLayeredPane();
		settlementPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		settlementPane.setBounds(594, 315, 492, 225);
		frmOregonTrail.getContentPane().add(settlementPane);
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
		openShopBtn.setBounds(124, 44, 223, 33);
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
				System.out.println(wagon.getNumOx());
				if(wagon.getNumOx() == 0) {
					noOxWarning.setVisible(true);
				}
				else if(map.getSegments() == 0) {
					System.out.println("Beginning Journey!");
					map.setSegments(1);
					map.setTraveled(0);					
					//game();
				}
				else if(map.getSegments() == 3.5){
					map.setSegments(3);
					map.setTraveled(280);
					
					
				}
				else {
					
					map.setSegments(map.getSegments()+1);
					map.setTraveled(0);
					
				}
				
				if(wagon.getNumOx()>0) {
					settlementPane.setVisible(false);
					assessSituationBtn.setVisible(true);
					progDayBtn.setVisible(true);
					paused = false;
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
		
		diaryBtn = new JButton("Read Diary");
		diaryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diary();
			}
		});
		diaryBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		diaryBtn.setBounds(124, 83, 223, 38);
		settlementPane.add(diaryBtn);
		
		creditsPane = new JLayeredPane();
		creditsPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		creditsPane.setBounds(0,0,1186,758);
		frmOregonTrail.getContentPane().add(creditsPane);
		creditsPane.setLayout(null);
		creditsPane.setVisible(false);
		
		
		vistaPane = new JLayeredPane();
		vistaPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		vistaPane.setBounds(0,0,1186,758);
		frmOregonTrail.getContentPane().add(vistaPane);
		vistaPane.setLayout(null);
		
		vistaLbl = new JLabel("");
		vistaLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				vistaPane.setVisible(false);
				mainPane.setVisible(true);
			}
		});
		
		vistaLbl.setBounds(0, 0, 1165, 758);
		vistaPane.add(vistaLbl);
		vistaPane.setVisible(false);
		
		deathPane = new JLayeredPane();
		deathPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		deathPane.setBounds(0,0,1186,758);
		frmOregonTrail.getContentPane().add(deathPane);
		deathPane.setLayout(null);
		//System.out.println(deathPane.getVisibleRect());
		deathPane.setVisible(false);
		
		
		buryBtn = new JButton("Bury the Body");
		buryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dateLbl.setVisible(true);
				assessSituationBtn.setVisible(true);
				progDayBtn.setVisible(true);
				rationsComboBox.setVisible(true);
				partyHeaderLbl.setVisible(true);
				memberSelector.setVisible(true);
				healthLbl.setVisible(true);
				foodInvLbl.setVisible(true);
				clothingInvLbl.setVisible(true);
				partsInvLbl.setVisible(true);
				medSuppInvLbl.setVisible(true);
				lblHeirloomslbs.setVisible(true);
				clothingInvDisp.setVisible(true);
				partsInvDisp.setVisible(true);
				medSuppInvDisplay.setVisible(true);
				heirloomsInvDisp.setVisible(true);
				foodInvDisp.setVisible(true);
				distanceLbl.setVisible(true);
				paceComboBox.setVisible(true);
				
				deathPane.setVisible(false);
				settlementPane.setVisible(false);
				assessSituationBtn.setVisible(true);
				progDayBtn.setVisible(true);
				paused = false;
				assessSituationBtn.setText("Assess the Situation");
				wagonGifLbl.setVisible(true);
				oxGifLbl1.setVisible(true);
				oxGifLbl2.setVisible(true);
				oxGifLbl3.setVisible(true);
			}
		});
		buryBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		buryBtn.setBounds(462, 232, 200, 50);
		deathPane.add(buryBtn);
		
		susBtn = new JButton("Anything can Happen on The Trail");
		susBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dateLbl.setVisible(true);
				assessSituationBtn.setVisible(true);
				progDayBtn.setVisible(true);
				rationsComboBox.setVisible(true);
				partyHeaderLbl.setVisible(true);
				memberSelector.setVisible(true);
				healthLbl.setVisible(true);
				foodInvLbl.setVisible(true);
				clothingInvLbl.setVisible(true);
				partsInvLbl.setVisible(true);
				medSuppInvLbl.setVisible(true);
				lblHeirloomslbs.setVisible(true);
				clothingInvDisp.setVisible(true);
				partsInvDisp.setVisible(true);
				medSuppInvDisplay.setVisible(true);
				heirloomsInvDisp.setVisible(true);
				foodInvDisp.setVisible(true);
				distanceLbl.setVisible(true);
				paceComboBox.setVisible(true);
				
				deathPane.setVisible(false);
				inventory.get(1).setAmount(inventory.get(1).getAmount()+75);
				updateInventoryDisplay();
				susCnt++;
				susBtn.setVisible(false);
				settlementPane.setVisible(false);
				assessSituationBtn.setVisible(true);
				progDayBtn.setVisible(true);
				paused = false;
				assessSituationBtn.setText("Assess the Situation");
				wagonGifLbl.setVisible(true);
				oxGifLbl1.setVisible(true);
				oxGifLbl2.setVisible(true);
				oxGifLbl3.setVisible(true);
			}
		});
		susBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		susBtn.setBounds(387, 303, 354, 50);
		deathPane.add(susBtn);
		susBtn.setVisible(false);
		
		JLabel deathLbl = new JLabel();
		deathLbl.setBounds(44, 106, 1061, 627);
		deathLbl.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/death_image.png")));
		deathLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		deathPane.add(deathLbl);
		deathLbl.setOpaque(true);
		deathLbl.setVisible(true);
		System.out.println(deathLbl.getVisibleRect() +"");
		deathLbl.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		endPane = new JLayeredPane();
		endPane.setBounds(0, 0, 1158, 758);
		frmOregonTrail.getContentPane().add(endPane);
		endPane.setOpaque(true);
		endPane.setVisible(false);
		
		JLabel winText = new JLabel("Congratulations! You have reached the Wilamette Valley!");
		winText.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		winText.setHorizontalAlignment(SwingConstants.CENTER);
		winText.setBounds(277, 15, 612, 35);
		endPane.add(winText);
		
		scoreLabel = new JLabel("Total Score: (    )");
		scoreLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(742, 561, 416, 35);
		endPane.add(scoreLabel);
		
		goodQuantity = new JLabel("New label");
		goodQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		goodQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		goodQuantity.setBounds(300, 87, 60, 29);
		endPane.add(goodQuantity);
		
		fairQuantity = new JLabel("New label");
		fairQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		fairQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		fairQuantity.setBounds(300, 127, 60, 29);
		endPane.add(fairQuantity);
		
		poorQuantity = new JLabel("New label");
		poorQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		poorQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		poorQuantity.setBounds(300, 167, 60, 29);
		endPane.add(poorQuantity);
		
		oxenQuantity = new JLabel("New label");
		oxenQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		oxenQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		oxenQuantity.setBounds(300, 246, 60, 29);
		endPane.add(oxenQuantity);
		
		sparePartsQuantity = new JLabel("New label");
		sparePartsQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		sparePartsQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		sparePartsQuantity.setBounds(300, 285, 60, 29);
		endPane.add(sparePartsQuantity);
		
		clothesQuantity = new JLabel("New label");
		clothesQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		clothesQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		clothesQuantity.setBounds(300, 324, 60, 29);
		endPane.add(clothesQuantity);
		
		foodQuantity = new JLabel("New label");
		foodQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		foodQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		foodQuantity.setBounds(300, 363, 60, 29);
		endPane.add(foodQuantity);
		
		cashQuantity = new JLabel("New label");
		cashQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		cashQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		cashQuantity.setBounds(300, 402, 60, 29);
		endPane.add(cashQuantity);
		
		heirloomQuantity = new JLabel("New label");
		heirloomQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		heirloomQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		heirloomQuantity.setBounds(300, 441, 60, 29);
		endPane.add(heirloomQuantity);
		
		goodText = new JLabel("Number of Characters with Good health");
		goodText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		goodText.setHorizontalAlignment(SwingConstants.CENTER);
		goodText.setBounds(426, 88, 317, 29);
		endPane.add(goodText);
		
		fairText = new JLabel("Number of Characters with Fair health");
		fairText.setHorizontalAlignment(SwingConstants.CENTER);
		fairText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		fairText.setBounds(426, 127, 317, 29);
		endPane.add(fairText);
		
		poorText = new JLabel("Number of Characters with Poor health");
		poorText.setHorizontalAlignment(SwingConstants.CENTER);
		poorText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		poorText.setBounds(426, 167, 317, 29);
		endPane.add(poorText);
		
		oxenText = new JLabel("Number of Oxen");
		oxenText.setHorizontalAlignment(SwingConstants.CENTER);
		oxenText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		oxenText.setBounds(426, 246, 317, 29);
		endPane.add(oxenText);
		
		sparePartsText = new JLabel("Number of Spare Parts");
		sparePartsText.setHorizontalAlignment(SwingConstants.CENTER);
		sparePartsText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		sparePartsText.setBounds(426, 285, 317, 29);
		endPane.add(sparePartsText);
		
		clothesText = new JLabel("Number of Clothes");
		clothesText.setHorizontalAlignment(SwingConstants.CENTER);
		clothesText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		clothesText.setBounds(426, 324, 317, 29);
		endPane.add(clothesText);
		
		foodText = new JLabel("Pounds of Food");
		foodText.setHorizontalAlignment(SwingConstants.CENTER);
		foodText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		foodText.setBounds(426, 363, 317, 29);
		endPane.add(foodText);
		
		cashText = new JLabel("Cash Remaining");
		cashText.setHorizontalAlignment(SwingConstants.CENTER);
		cashText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		cashText.setBounds(426, 402, 317, 29);
		endPane.add(cashText);
		
		heirloomText = new JLabel("Heirlooms");
		heirloomText.setHorizontalAlignment(SwingConstants.CENTER);
		heirloomText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		heirloomText.setBounds(426, 441, 317, 29);
		endPane.add(heirloomText);
		
		quantityText = new JLabel("Quantity");
		quantityText.setHorizontalAlignment(SwingConstants.CENTER);
		quantityText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		quantityText.setBounds(287, 47, 102, 29);
		endPane.add(quantityText);
		
		scoreText = new JLabel("Score");
		scoreText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		scoreText.setBounds(800, 47, 60, 29);
		endPane.add(scoreText);
		
		goodPoints = new JLabel("New label");
		goodPoints.setHorizontalAlignment(SwingConstants.CENTER);
		goodPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		goodPoints.setBounds(800, 87, 60, 29);
		endPane.add(goodPoints);
		
		fairPoints = new JLabel("New label");
		fairPoints.setHorizontalAlignment(SwingConstants.CENTER);
		fairPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		fairPoints.setBounds(800, 127, 60, 29);
		endPane.add(fairPoints);
		
		poorPoints = new JLabel("New label");
		poorPoints.setHorizontalAlignment(SwingConstants.CENTER);
		poorPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		poorPoints.setBounds(800, 167, 60, 29);
		endPane.add(poorPoints);
		
		oxenPoints = new JLabel("New label");
		oxenPoints.setHorizontalAlignment(SwingConstants.CENTER);
		oxenPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		oxenPoints.setBounds(800, 246, 60, 29);
		endPane.add(oxenPoints);
		
		sparePartsPoints = new JLabel("New label");
		sparePartsPoints.setHorizontalAlignment(SwingConstants.CENTER);
		sparePartsPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		sparePartsPoints.setBounds(800, 285, 60, 29);
		endPane.add(sparePartsPoints);
		
		clothesPoints = new JLabel("New label");
		clothesPoints.setHorizontalAlignment(SwingConstants.CENTER);
		clothesPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		clothesPoints.setBounds(800, 324, 60, 29);
		endPane.add(clothesPoints);
		
		foodPoints = new JLabel("New label");
		foodPoints.setHorizontalAlignment(SwingConstants.CENTER);
		foodPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		foodPoints.setBounds(800, 363, 60, 29);
		endPane.add(foodPoints);
		
		cashPoints = new JLabel("New label");
		cashPoints.setHorizontalAlignment(SwingConstants.CENTER);
		cashPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		cashPoints.setBounds(800, 402, 60, 29);
		endPane.add(cashPoints);
		
		heirloomMulti = new JLabel("New label");
		heirloomMulti.setHorizontalAlignment(SwingConstants.CENTER);
		heirloomMulti.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		heirloomMulti.setBounds(800, 441, 60, 29);
		endPane.add(heirloomMulti);
		
		cannibalizedQuantity = new JLabel("0");
		cannibalizedQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		cannibalizedQuantity.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		cannibalizedQuantity.setBounds(300, 207, 60, 29);
		endPane.add(cannibalizedQuantity);
		
		cannibalizedText = new JLabel("Number of Characters Cannibalized");
		cannibalizedText.setHorizontalAlignment(SwingConstants.CENTER);
		cannibalizedText.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		cannibalizedText.setBounds(426, 207, 317, 29);
		endPane.add(cannibalizedText);
		
		cannibalizedPoints = new JLabel("0");
		cannibalizedPoints.setHorizontalAlignment(SwingConstants.CENTER);
		cannibalizedPoints.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		cannibalizedPoints.setBounds(800, 207, 60, 29);
		endPane.add(cannibalizedPoints);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/wilamette.png")));
		lblNewLabel.setBounds(-50, 10, 1208, 748);
		endPane.add(lblNewLabel);
		
		shopPane = new JLayeredPane();
		shopPane.setBounds(0, 0, 1158, 758);
		frmOregonTrail.getContentPane().add(shopPane);
		shopPane.setOpaque(true);
		shopPane.setVisible(false);
		
		currentWeight = new JLabel("0/0");
		currentWeight.setHorizontalAlignment(SwingConstants.CENTER);
		currentWeight.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		currentWeight.setBounds(110, 329, 158, 26);
		shopPane.add(currentWeight);
		
		JLabel oxenStoreLbl = new JLabel("Oxen");
		oxenStoreLbl.setToolTipText("[Price: $40/yoke] \r\nOxen are sold by the yoke. The more oxen you have, the faster you can travel or the more supplies you can carry. \r\nThe recommended minimum is 3 yoke.");
		oxenStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		oxenStoreLbl.setBounds(40, 81, 261, 26);
		shopPane.add(oxenStoreLbl);
		
		JLabel foodStoreLbl = new JLabel("Food");
		foodStoreLbl.setToolTipText("[Price: $0.20/lb | Weight: 1 lb]\r\nFood is used to feed your family. \r\nThe recommended portions are 200 pounds per person.");
		foodStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		foodStoreLbl.setBounds(40, 117, 261, 26);
		shopPane.add(foodStoreLbl);
		
		JLabel clothingStoreLbl = new JLabel("Clothing");
		clothingStoreLbl.setToolTipText("[Price: $10.00/set | Weight: 3lbs/set]\r\nClothing protects you from the elements. Not having enough clothing for everyone in your party may result in injury or death. \r\nThe recommended amount is 2 sets per person.");
		clothingStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		clothingStoreLbl.setBounds(40, 153, 261, 26);
		shopPane.add(clothingStoreLbl);
		
		JLabel sparePartsStoreLbl = new JLabel("Spare Parts");
		sparePartsStoreLbl.setToolTipText("[Price: $10/each | Weight: 50 lbs/each]\r\nSpare parts for the wagon. Your Pa is handy, but there may be repairs he can't make without extra parts.");
		sparePartsStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		sparePartsStoreLbl.setBounds(40, 189, 261, 26);
		shopPane.add(sparePartsStoreLbl);
		
		JLabel heirloomsStoreLbl = new JLabel("Heirlooms");
		heirloomsStoreLbl.setToolTipText("<html>"+"These are precious family heirlooms. They won't provide any benefit on The Trail, but they'll be a nice reminder of your old home when you reach Oregon.\r\nYou can determine how much you want to bring with you, but be careful! Any heirloms you decide to take are very important to you and you will not be able to get rid of them to regain the lost weight.\r\n(You will receive bonus points if you arrive in Oregon with some heirlooms)"+"</html>");
		heirloomsStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		heirloomsStoreLbl.setBounds(40, 261, 261, 26);
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
		oxenStoreAmt.setBounds(290, 81, 96, 26);
		shopPane.add(oxenStoreAmt);
		oxenStoreAmt.setColumns(10);
		oxenStoreAmt.setOpaque(false);
		
		foodStoreAmt = new JTextField();
		foodStoreAmt.setText("0");
		foodStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		
		foodStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		foodStoreAmt.setColumns(10);
		foodStoreAmt.setBounds(290, 117, 96, 25);
		shopPane.add(foodStoreAmt);
		foodStoreAmt.setOpaque(false);
		
		clothingStoreAmt = new JTextField();
		clothingStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		
		clothingStoreAmt.setText("0");
		clothingStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		clothingStoreAmt.setColumns(10);
		clothingStoreAmt.setBounds(290, 153, 96, 25);
		shopPane.add(clothingStoreAmt);
		clothingStoreAmt.setOpaque(false);
		
		partsStoreAmt = new JTextField();
		partsStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		partsStoreAmt.setText("0");
		partsStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		partsStoreAmt.setColumns(10);
		partsStoreAmt.setBounds(290, 189, 96, 25);
		shopPane.add(partsStoreAmt);
		partsStoreAmt.setOpaque(false);
		
		heirloomStoreAmt = new JTextField();
		heirloomStoreAmt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStoreStuff();
			}
		});
		heirloomStoreAmt.setText("0");
		heirloomStoreAmt.setFont(new Font("Baskerville Old Face", Font.PLAIN, 24));
		heirloomStoreAmt.setColumns(10);
		heirloomStoreAmt.setBounds(290, 263, 96, 25);
		shopPane.add(heirloomStoreAmt);
		heirloomStoreAmt.setOpaque(false);
		
		JLabel currentWeightLbl = new JLabel("Current Weight");
		currentWeightLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentWeightLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		currentWeightLbl.setBounds(100, 307, 182, 22);
		shopPane.add(currentWeightLbl);
		
		JLabel medicalSupplyStoreLbl = new JLabel("Medical Supplies");
		medicalSupplyStoreLbl.setToolTipText("[Price:  $5/each | Weight: 1 lb/each]\r\nUsed to heal injured or sick members of your party. Things can get dangerous out on The Trail!\r\n");
		medicalSupplyStoreLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		medicalSupplyStoreLbl.setBounds(40, 225, 261, 26);
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
		medSupplyStoreAmt.setBounds(290, 226, 96, 25);
		shopPane.add(medSupplyStoreAmt);
		medSupplyStoreAmt.setOpaque(false);
		
		JLabel currentCostLbl = new JLabel("Current Cost");
		currentCostLbl.setHorizontalAlignment(SwingConstants.CENTER);
		currentCostLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		currentCostLbl.setBounds(100, 401, 182, 22);
		shopPane.add(currentCostLbl);
		
		storeBalanceDisplay = new JLabel("$0.00");
		storeBalanceDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		storeBalanceDisplay.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		storeBalanceDisplay.setBounds(100, 433, 182, 22);
		shopPane.add(storeBalanceDisplay);
		
		overWeightWarning = new JLabel("OVERWEIGHT!");
		overWeightWarning.setForeground(Color.RED);
		overWeightWarning.setHorizontalAlignment(SwingConstants.CENTER);
		overWeightWarning.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		overWeightWarning.setBounds(100, 365, 190, 26);
		shopPane.add(overWeightWarning);
		
		JLabel availableFundsLbl = new JLabel("Available Funds");
		availableFundsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		availableFundsLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		availableFundsLbl.setBounds(100, 465, 182, 22);
		shopPane.add(availableFundsLbl);
		
		availableFundsDisplay = new JLabel("$0.00");
		availableFundsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		availableFundsDisplay.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		availableFundsDisplay.setBounds(100, 491, 182, 22);
		shopPane.add(availableFundsDisplay);
		
		exitStoreBtn = new JButton("Exit Store\r\n\r\n");
		exitStoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setCurrentWeight(wagon.getCurrentWeight()+tempWeight);
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
		exitStoreBtn.setBounds(40, 655, 278, 54);
		shopPane.add(exitStoreBtn);
		
		JTextArea txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setWrapStyleWord(true);
		txtrWelcomeToThe.setLineWrap(true);
		txtrWelcomeToThe.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		txtrWelcomeToThe.setText("Welcome to the General Store!\r\nHere you can buy supplies for your journey.\r\nHover over an item to see extra prices, weight, and extra information.\r\nClick \"Exit Store\" when you have finished shopping.");
		txtrWelcomeToThe.setBounds(383, 581, 765, 107);
		shopPane.add(txtrWelcomeToThe);
		
		storeBckGnd = new JLabel("");
		storeBckGnd.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/store.png")));
		storeBckGnd.setBounds(0, 0, 1158, 748);
		shopPane.add(storeBckGnd);
		overWeightWarning.setVisible(false);
		
		
		pausePane = new JLayeredPane();
		pausePane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pausePane.setBounds(577,309,321,339);
		frmOregonTrail.getContentPane().add(pausePane);
		pausePane.setOpaque(true);
		pausePane.setLayout(null);
		pausePane.setVisible(false);
		
		btnNewButton = new JButton("Check Map");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		btnNewButton.setBounds(43, 32, 238, 50);
		pausePane.add(btnNewButton);
		
		btnTalk = new JButton("Talk");
		btnTalk.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		btnTalk.setBounds(43, 112, 238, 50);
		pausePane.add(btnTalk);
		
		btnReadTrailGuide = new JButton("Read Trail Guide");
		btnReadTrailGuide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File("src\\Assets\\The-Prairie-Traveler-A-Hand-book-for-Overland-Expeditions.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
			}
		});
		btnReadTrailGuide.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		btnReadTrailGuide.setBounds(43, 188, 238, 50);
		pausePane.add(btnReadTrailGuide);
		
		btnDoSomeChores = new JButton("Do Some Chores");
		btnDoSomeChores.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		btnDoSomeChores.setBounds(43, 268, 238, 50);
		pausePane.add(btnDoSomeChores);
		
		
		
		mainPane = new JLayeredPane();
		mainPane.setBounds(0, 0, 1163, 758);
		frmOregonTrail.getContentPane().add(mainPane);
		mainPane.setOpaque(true);
		mainPane.setVisible(false);
		
		assessSituationBtn = new JButton("Assess the Situation");
		assessSituationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!paused) {
					assessSituationBtn.setText("Continue On");
					paused = true;
					pausePane.setVisible(true);
					progDayBtn.setVisible(false);
					pauseActivate();
				}
				else {
					assessSituationBtn.setText("Assess the Situation");
					paused = false;
					pausePane.setVisible(false);
					progDayBtn.setVisible(true);
				}
				
			}
		});
		assessSituationBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		assessSituationBtn.setBounds(539, 215, 254, 33);
		mainPane.add(assessSituationBtn);
		assessSituationBtn.setVisible(false);
		assessSituationBtn.setOpaque(false);
		
		infoField = new JTextArea();
		infoField.setWrapStyleWord(true);
		infoField.setEditable(false);
		infoField.setLineWrap(true);
		infoField.setOpaque(false);
		infoField.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		infoField.setText("Welcome to the Oregon Trail!\r\nThe year is 1848. Your name is Hattie Campbell. You and your parents have made the trip to Independence, Missouri to stock up on supplies before you set out on The Trail to the Wilamette Valley.");
		infoField.setBounds(123, 36, 836, 174);
		mainPane.add(infoField);
		
		dateLbl = new JLabel("31 March 1848");
		dateLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(120, 204, 160, 44);
		mainPane.add(dateLbl);
		
		paceComboBox = new JComboBox<String>();
		paceComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Slow", "Steady", "Strenuous"}));
		paceComboBox.setOpaque(false);
		paceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wagon.setPace(paceComboBox.getSelectedIndex());
			}
		});
		paceComboBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		paceComboBox.setBounds(130, 299, 95, 21);
		mainPane.add(paceComboBox);
		
		JLabel paceLbl = new JLabel("Pace:");
		paceLbl.setHorizontalAlignment(SwingConstants.LEFT);
		paceLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		paceLbl.setBounds(130, 274, 65, 21);
		mainPane.add(paceLbl);
		
		JLabel rationsLbl = new JLabel("Rations:");
		rationsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		rationsLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		rationsLbl.setBounds(130, 331, 65, 21);
		mainPane.add(rationsLbl);
		
		rationsComboBox = new JComboBox<String>();
		rationsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Meager", "Balanced", "Filling"}));
		rationsComboBox.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		rationsComboBox.setOpaque(false);
		rationsComboBox.setBounds(130, 356, 95, 21);
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
		progDayBtn.setOpaque(false);
		
		partyHeaderLbl = new JLabel("Party Members");
		partyHeaderLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		partyHeaderLbl.setHorizontalAlignment(SwingConstants.CENTER);
		partyHeaderLbl.setBounds(323, 257, 160, 38);
		mainPane.add(partyHeaderLbl);
		
		memberSelector = new JComboBox<String>();
		memberSelector.setOpaque(false);
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
		memberSelector.setBounds(328, 293, 155, 33);
		mainPane.add(memberSelector);
		
		healthLbl = new JLabel("Health: Good");
		healthLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		healthLbl.setHorizontalAlignment(SwingConstants.LEFT);
		healthLbl.setBounds(306, 330, 200, 50);
		mainPane.add(healthLbl);
		
		foodInvLbl = new JLabel("Food: ");
		foodInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		foodInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		foodInvLbl.setBounds(171, 380, 200, 44);
		mainPane.add(foodInvLbl);
		
		clothingInvLbl = new JLabel("Clothing:");
		clothingInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		clothingInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		clothingInvLbl.setBounds(167, 422, 200, 44);
		mainPane.add(clothingInvLbl);
		
		partsInvLbl = new JLabel("Parts:");
		partsInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		partsInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		partsInvLbl.setBounds(167, 464, 200, 44);
		mainPane.add(partsInvLbl);
		
		medSuppInvLbl = new JLabel("Medical Supplies:");
		medSuppInvLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		medSuppInvLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		medSuppInvLbl.setBounds(172, 500, 200, 44);
		mainPane.add(medSuppInvLbl);
		
		lblHeirloomslbs = new JLabel("Heirlooms (lbs):");
		lblHeirloomslbs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeirloomslbs.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		lblHeirloomslbs.setBounds(171, 539, 200, 44);
		mainPane.add(lblHeirloomslbs);
		
		foodInvDisp = new JTextField();
		foodInvDisp.setEditable(false);
		foodInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		foodInvDisp.setText("0");
		foodInvDisp.setBackground(Color.WHITE);
		foodInvDisp.setBounds(390, 390, 95, 28);
		mainPane.add(foodInvDisp);
		foodInvDisp.setColumns(10);
		foodInvDisp.setOpaque(false);
		
		clothingInvDisp = new JTextField();
		clothingInvDisp.setText("0");
		clothingInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		clothingInvDisp.setEditable(false);
		clothingInvDisp.setColumns(10);
		clothingInvDisp.setBackground(Color.WHITE);
		clothingInvDisp.setBounds(390, 432, 95, 28);
		clothingInvDisp.setOpaque(false);
		mainPane.add(clothingInvDisp);
		
		partsInvDisp = new JTextField();
		partsInvDisp.setText("0");
		partsInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		partsInvDisp.setEditable(false);
		partsInvDisp.setColumns(10);
		partsInvDisp.setBackground(Color.WHITE);
		partsInvDisp.setBounds(390, 474, 95, 28);
		partsInvDisp.setOpaque(false);
		mainPane.add(partsInvDisp);
		
		medSuppInvDisplay = new JTextField();
		medSuppInvDisplay.setText("0");
		medSuppInvDisplay.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		medSuppInvDisplay.setEditable(false);
		medSuppInvDisplay.setColumns(10);
		medSuppInvDisplay.setBackground(Color.WHITE);
		medSuppInvDisplay.setBounds(390, 516, 95, 28);
		medSuppInvDisplay.setOpaque(false);
		mainPane.add(medSuppInvDisplay);
		
		heirloomsInvDisp = new JTextField();
		heirloomsInvDisp.setFont(new Font("Baskerville Old Face", Font.PLAIN, 18));
		heirloomsInvDisp.setText("0");
		heirloomsInvDisp.setEditable(false);
		heirloomsInvDisp.setColumns(10);
		heirloomsInvDisp.setBackground(Color.WHITE);
		heirloomsInvDisp.setBounds(390, 555, 95, 28);
		heirloomsInvDisp.setOpaque(false);
		mainPane.add(heirloomsInvDisp);
		
		distanceLbl = new JLabel("Miled Travelled: 0.0");
		distanceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		distanceLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 18));
		distanceLbl.setBounds(290, 202, 194, 50);
		mainPane.add(distanceLbl);
		
		wagonGifLbl = new JLabel("");
		wagonGifLbl.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/wagon-pixilart.gif")));
		wagonGifLbl.setBounds(585, 519, 100, -1);
		mainPane.add(wagonGifLbl);
		wagonGifLbl.setVisible(false);
		
		oxGifLbl1 = new JLabel("");
		oxGifLbl1.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/ox-pixilart.gif")));
		oxGifLbl1.setBounds(691, 519, 100, -1);
		mainPane.add(oxGifLbl1);
		
		oxGifLbl2 = new JLabel("");
		oxGifLbl2.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/ox-pixilart.gif")));
		oxGifLbl2.setBounds(797, 519, 100, -1);
		mainPane.add(oxGifLbl2);
		oxGifLbl2.setVisible(false);
		
		oxGifLbl3 = new JLabel("");
		oxGifLbl3.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/ox-pixilart.gif")));
		oxGifLbl3.setBounds(903, 519, 100, -1);
		mainPane.add(oxGifLbl3);
		
		purseDisplay = new JLabel("$800.00");
		purseDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		purseDisplay.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		purseDisplay.setBounds(124, 238, 160, 33);
		mainPane.add(purseDisplay);
		
		weatherLbl = new JLabel("Weather: Sunny");
		weatherLbl.setHorizontalAlignment(SwingConstants.CENTER);
		weatherLbl.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		weatherLbl.setBounds(539, 254, 254, 44);
		mainPane.add(weatherLbl);
		
		bckGnd = new JLabel("");
		bckGnd.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/pixil-frame-0.png")));
		bckGnd.setBounds(-20, 10, 1208, 762);
		mainPane.add(bckGnd);
		healthLbl.setVisible(true);
		
		JLayeredPane titlePane = new JLayeredPane();
		titlePane.setBounds(10, 0, 1153, 758);
		frmOregonTrail.getContentPane().add(titlePane);
		titlePane.setLayout(null);
		titlePane.setOpaque(true);
		
		JLabel startButton = new JLabel("");
		startButton.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/wagonHo.png")));
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titlePane.setVisible(false);
				mainPane.setVisible(true);
				settlementPane.setVisible(true);
			}
		});
		startButton.setBounds(112, 498, 372, 187);
		titlePane.add(startButton);
		
		creditsButton = new JLabel("");
		creditsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		creditsButton.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/creditButton.png")));
		creditsButton.setBounds(749, 498, 372, 187);
		titlePane.add(creditsButton);
		
		JLabel trailLogo = new JLabel("");
		trailLogo.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/titleImage.gif")));
		trailLogo.setBounds(50, 10, 1057, 723);
		titlePane.add(trailLogo);
		
		forkPane = new JLayeredPane();
		forkPane.setBounds(0, 0, 1163, 758);
		frmOregonTrail.getContentPane().add(forkPane);
		forkPane.setOpaque(true);
		
		forkOpt1 = new JButton("Soda Springs ");
		forkOpt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forkPane.setVisible(false);
				mainPane.setVisible(true);
			}
		});
		forkOpt1.setFont(new Font("Blackadder ITC", Font.BOLD, 24));
		forkOpt1.setBounds(513, 161, 170, 66);
		forkPane.add(forkOpt1);
		forkOpt1.setOpaque(false);
		
		forkOpt2 = new JButton("Fort Bridger");
		forkOpt2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.setSegments(map.getSegments()+0.5);
				map.setTraveled(0);
				forkPane.setVisible(false);
				mainPane.setVisible(false);
			}
		});
		forkOpt2.setOpaque(false);
		forkOpt2.setFont(new Font("Blackadder ITC", Font.BOLD, 24));
		forkOpt2.setBounds(526, 258, 170, 66);
		forkPane.add(forkOpt2);
		
		forkLbl = new JLabel("");
		forkLbl.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/fork.png")));
		forkLbl.setBounds(63, 20, 1057, 738);
		forkPane.add(forkLbl);
		forkPane.setVisible(false);
		
		gameOverPane = new JLayeredPane();
		gameOverPane.setBounds(0, 0, 1163, 758);
		frmOregonTrail.getContentPane().add(gameOverPane);
		gameOverPane.setOpaque(true);
		gameOverPane.setVisible(false);
		
		quitGameBtn = new JButton("Quit Game");
		quitGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmOregonTrail.dispose();
			}
		});
		quitGameBtn.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
		quitGameBtn.setBounds(706, 613, 200, 50);
		gameOverPane.add(quitGameBtn);
		
		JLabel gameOverLbl = new JLabel();
		gameOverLbl.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/GameOver.png")));
		gameOverLbl.setBounds(0, 0, 1188, 772);
		gameOverPane.add(gameOverLbl);
		
		rvrCrossPane = new JLayeredPane();
		rvrCrossPane.setBounds(0, 0, 1163, 748);
		frmOregonTrail.getContentPane().add(rvrCrossPane);
		
		JLabel weatherLB = new JLabel("Weather:");
		weatherLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		weatherLB.setBounds(39, 140, 304, 19);
		rvrCrossPane.add(weatherLB);
		
		weatherArea = new JTextArea();
		weatherArea.setEditable(false);
		weatherArea.setBounds(192, 140, 62, 22);
		rvrCrossPane.add(weatherArea);
		
		widthArea = new JTextArea();
		widthArea.setEditable(false);
		widthArea.setBounds(192, 170, 62, 22);
		rvrCrossPane.add(widthArea);
		
		depthArea = new JTextArea();
		depthArea.setEditable(false);
		depthArea.setBounds(192, 198, 62, 22);
		rvrCrossPane.add(depthArea);
		
		JLabel widthLB = new JLabel("River Width:");
		widthLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		widthLB.setBounds(39, 169, 304, 22);
		rvrCrossPane.add(widthLB);
		
		JLabel depthLB = new JLabel("River Depth: ");
		depthLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		depthLB.setBounds(39, 196, 304, 25);
		rvrCrossPane.add(depthLB);
		
		JLabel introLB = new JLabel("You may:");
		introLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		introLB.setBounds(39, 231, 304, 22);
		rvrCrossPane.add(introLB);
		
		JLabel oneLb = new JLabel("1. Attempt to ford the river ");
		oneLb.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		oneLb.setBounds(39, 265, 304, 22);
		rvrCrossPane.add(oneLb);
		
		JLabel twoLB = new JLabel("2. Caulk the wagon and float across ");
		twoLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		twoLB.setBounds(39, 288, 367, 22);
		rvrCrossPane.add(twoLB);
		
		JLabel threeLB = new JLabel("3. Take a ferry across ");
		threeLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		threeLB.setBounds(39, 311, 304, 22);
		rvrCrossPane.add(threeLB);
		
		JLabel fourLB = new JLabel("4. Wait to see if the conditions get better ");
		fourLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		fourLB.setBounds(39, 334, 489, 22);
		rvrCrossPane.add(fourLB);
		
		JLabel fiveLB = new JLabel("5. Get more information ");
		fiveLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		fiveLB.setBounds(39, 357, 304, 29);
		rvrCrossPane.add(fiveLB);
		
		JLabel chooseLB = new JLabel("What do you chose? ");
		chooseLB.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		chooseLB.setBounds(39, 408, 304, 24);
		rvrCrossPane.add(chooseLB);
		
		inputField = new JTextField();
		inputField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input = inputField.getText(); 
				choose = Integer.valueOf(input); 
				inputField.setEditable(false);
				if (choose == 1) { 
					int bounds = 101; 
					int randInt = rand.nextInt(bounds); 
					if (randInt <= 20) { 
						chooseOutput.setText("Game over text here."); 
						// code to end game and show game over screen 
					}
					if (randInt > 20 && randInt < 50) { 
						chooseOutput.setText("You make it but something bad happened"); 
						// code to apply what happened
					}
					if (randInt > 50 && randInt < 70) { 
						chooseOutput.setText("You make it but something bad happened to a party memeber"); 
						// code to apply what happened
					}
					if (randInt > 70)  {
						chooseOutput.setText("You make it safely"); 
					}
					}
					if (choose == 2) { 
					// if statement to see if they have what they need to make the wagon waterproof to float across
					// if not they choose again
					chooseOutput.setText("This is working"); 
					inputField.setEditable(true);
					}
					if (choose == 3) { 
					// x happens
					ifNeeded.setEditable(true);
					ifNeeded.setVisible(true);
					chooseOutput.setText("You have to pay the ferry man to get across. ");
					ifNeeded.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {					
							ferryInput = ifNeeded.getText();
							ifchoose = Integer.valueOf(ferryInput); 
							if (ifchoose > 100) { 
								chooseOutput.setText("You pay the ferry man enough to be able to cross the river on his boat. ");
								// code to cross the river
							}
							if (ifchoose < 100) { 
								chooseOutput.setText("You don't pay enough to get on the ferryman's boat. "); 
							}
						}
					});
					}
					if (choose == 4) { 
					// choose how long to wait, then the weather function is called to see if the weather changes
					ifNeeded.setEditable(true);
					ifNeeded.setVisible(true);
					chooseOutput.setText("How long do you want to wait? ");
					ifNeeded.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {					
							ferryInput = ifNeeded.getText();
							ifchoose = Integer.valueOf(ferryInput); 
						}
					});
					// then wait for however long
					}
					if (choose == 5) { 
					// Use the weather, depth, and width to determine the best course of action. Then get another input 
					if (weather.equals("scorching")) { 
						chooseOutput.setText("The weather is scorching and the river is calm. Crossing it should be easy. "); 
					}
					if (weather.equals("hot")) { 
						chooseOutput.setText("The weather is warm and the river is calm. Crossing it should be easy. "); 
					}
					if (weather.equals("warm")) { 
						chooseOutput.setText("The weather is cold and the river is calm. Crossing it should be easy. "); 
					}
					if (weather.equals("cold")) { 
						
					}
					if (weather.equals("raining")) { 
						chooseOutput.setText("It is raining outside and the river is getting rough. Crossing will be hard."); 
					}
					if (weather.equals("thunderstorming")) { 
						chooseOutput.setText("It is thunderstorming outside and the river is raging. Crossing will almost impossible. "); 
					}
					inputField.setEditable(true);
					
					}
				}
		});
		inputField.setBounds(286, 413, 45, 19);
		rvrCrossPane.add(inputField);
		inputField.setColumns(10);
		
		chooseOutput = new JLabel("h");
		chooseOutput.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		chooseOutput.setBounds(492, 145, 618, 166);
		rvrCrossPane.add(chooseOutput);
		
		ifNeeded = new JTextField();
		ifNeeded.setEditable(false);
		ifNeeded.setVisible(false);
		ifNeeded.setBounds(618, 338, 96, 19);
		rvrCrossPane.add(ifNeeded);
		ifNeeded.setColumns(10);
		
		rvrbckgnd = new JLabel("");
		//rvrbckgnd.setIcon(new ImageIcon(GameFrame.class.getResource("/Assets/rvrGif.gif")));
		rvrbckgnd.setBackground(Color.LIGHT_GRAY);
		rvrbckgnd.setBounds(0, 0, 1163, 758);
		rvrCrossPane.add(rvrbckgnd);
		rvrbckgnd.setOpaque(true);
		
		frmOregonTrail.setVisible(true);
		
	}
	
	
	/**
	 * Called any time a player changes the amount of an item the player is planning to purchase from the store.
	 */
	private void updateStoreStuff() {
		tempWeight = Double.parseDouble(foodStoreAmt.getText())+(Double.parseDouble(clothingStoreAmt.getText())*3)+(Double.parseDouble(partsStoreAmt.getText())*50)+(Double.parseDouble(medSupplyStoreAmt.getText()))+(Double.parseDouble(heirloomStoreAmt.getText())) + (inventory.get(1).getAmount())+(inventory.get(2).getAmount()*3)+(inventory.get(3).getAmount()*50)+(inventory.get(4).getAmount())+(inventory.get(5).getAmount());
		currentWeight.setText(wagon.getCurrentWeight()+tempWeight + "/" + wagon.getWeightLim());
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
		if(health <= 0) {
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
		purseDisplay.setText(String.format("$%.2f", inventory.get(0).getAmount()));
		foodInvDisp.setText((int)inventory.get(1).getAmount()+"");
		clothingInvDisp.setText((int)inventory.get(2).getAmount() +"");
		partsInvDisp.setText(""+(int)inventory.get(3).getAmount());
		medSuppInvDisplay.setText(""+(int)inventory.get(4).getAmount());
		heirloomsInvDisp.setText(""+(int)inventory.get(5).getAmount());
		
	}

	/**
	 * Handles all of the tasks that happen every day.
	 */
	private void dayToday() {
		String txtDate =  date.incrementDate();	//Stores the date as a String
		System.out.println("Month: " + date.getMonth());
		weatherLbl.setText("Weather: " + rndEventGen.getWeatherEvent(date.getMonth(), party, inventory));
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
					//Unlock diary Entry
				}
				else if(location.getType() == 2) {	//If landmark is a river crossing
					//river crossing stuff tbd for final product
					rvrCrossPane.setVisible(true);
					weatherArea.setText(rndEventGen.getWeatherEvent(adultCnt, party, inventory));
					
					
				}
				else if(location.getType() == 3) {	//If landmark is a vista
					//Show location image
					vistaLbl.setIcon(new ImageIcon(GameFrame.class.getResource(vistaImage.get(landmarks.indexOf(location)))));
					mainPane.setVisible(false);
					vistaPane.setVisible(true);
					assessSituationBtn.setText("Continue On");
					progDayBtn.setVisible(false);
				}
				else if(location.getType() == 4) {	//If landmark is a fork in the trail
					mainPane.setVisible(false);
					forkPane.setVisible(true);
					infoField.setText(infoField.getText() + "\n This is a fork in the trail. ");
				}
				else if(location.getType() == 5) {	//If the party has reached the end of the trail
					endGame();
					endPane.setVisible(true);
					mainPane.setVisible(false);
					
					
				}
				else {
					System.out.println("Tried to reference an invalid location type! Press Continue On to continue game");
				}
			}
		}
		if(isLandmark == false) { //If the wagon has not reached a landmark today
			double chance = Math.random()*100+1;
			infoField.setText(rndEventGen.generateRandomEvent(chance, inventory, party, date));
	
		}
		
		updateInventoryDisplay(); //Update Inventory based on the results of the random events
		
		//Update characters
		for(Character i : party) {
			if(i.getHealth() <= 0) {
				death(i);
				party.remove(i);
			}
			if(i.getHealth() != 0 && i.getAge()>=18) {
				adultCnt++;
			}
		}
		if (party.size()<=0) {
			gameOverPane.setVisible(true);
			mainPane.setVisible(false);
		}
		else if(adultCnt == 0) { //If there are no adults left in the party, the kids freak out and put themselves at risk
			rationsComboBox.setSelectedIndex(2);
			rationsComboBox.setEnabled(false);
			paceComboBox.setSelectedIndex(2);
			paceComboBox.setEnabled(false);
		}
		
		if((inventory.get(1).getAmount()-(((rationsComboBox.getSelectedIndex()*2)) + 3)*(party.size()))<=0) {
			inventory.get(1).setAmount(0);
		}
		else {
			inventory.get(1).setAmount(inventory.get(1).getAmount()-(((rationsComboBox.getSelectedIndex()*2)) + 3)*(party.size())); //Calculates food consumed
		}
		
		if(inventory.get(1).getAmount() <= 0) { //Case for starving to death
			paceComboBox.setSelectedIndex(0);
			paceComboBox.setEnabled(false);
			
			for(Character i : party) {
				if(i.getHealth() != 0) {
					i.setHealth(i.getHealth()-15);
					System.out.println(i.getName() + ": " + i.getHealth());
				}
			}
		}
		
	}
	
	
	private void pauseActivate() {
		paused = true;
		Location nextLocation = getNextLandmark();
		infoField.setText("Trek Paused!" + "\nYou are " + (nextLocation.getPosition()-map.getTraveled()) + " miles from " + nextLocation.getName() + "\nYou are currently consuming " + ((((rationsComboBox.getSelectedIndex()*2)) + 3)*(party.size()-deadCnt)) + " pounds of food per day. \nAt the current pace, you are travelling " + wagon.getSpeed() + " miles per day.");
		
	}
	
	private Location getNextLandmark() {
		Location nxtLoc = null;
		for(Location location : landmarks) {
			if(location.getSegPos() == map.getSegments()) {
				if(location.getPosition()>map.getTraveled()) {
					nxtLoc = location;
				}
			}
		}
		return nxtLoc;
	}
	
	private void death(Character i) {
		
		dateLbl.setVisible(false);
		assessSituationBtn.setVisible(false);
		progDayBtn.setVisible(false);
		rationsComboBox.setVisible(false);
		partyHeaderLbl.setVisible(false);
		memberSelector.setVisible(false);
		healthLbl.setVisible(false);
		foodInvLbl.setVisible(false);
		clothingInvLbl.setVisible(false);
		partsInvLbl.setVisible(false);
		medSuppInvLbl.setVisible(false);
		lblHeirloomslbs.setVisible(false);
		clothingInvDisp.setVisible(false);
		partsInvDisp.setVisible(false);
		medSuppInvDisplay.setVisible(false);
		heirloomsInvDisp.setVisible(false);
		foodInvDisp.setVisible(false);
		distanceLbl.setVisible(false);
		paceComboBox.setVisible(false);
		
		pauseActivate();
		infoField.setText(i.getName() + " has Died");
		deathPane.setVisible(true);
		if(inventory.get(1).getAmount() < 100) {
			susBtn.setVisible(true);
		}
		
	}
	
	private void endGame() {
		int goodCnt = 0;
		int fairCnt = 0;
		int poorCnt = 0;
		for(Character i : party) {
			if(i.getHealth()>0 && i.getHealth()<33) {
				poorCnt++;
			}
			else if(i.getHealth() >33 && i.getHealth()<66) {
				fairCnt++;
			}
			else {
				goodCnt++;
			}
		}
		score score = new score(goodCnt, fairCnt, poorCnt, susCnt, wagon.getNumOx(), (int)inventory.get(3).getAmount(), (int)inventory.get(2).getAmount(), (int)inventory.get(1).getAmount(), (int)inventory.get(0).getAmount(), (int)inventory.get(5).getAmount());
		scoreLabel.setText("Total Score: " + score.calcScore());
		goodQuantity.setText("" + score.getNumbGood());
		fairQuantity.setText("" + score.getNumbFair());
		poorQuantity.setText("" + score.getNumbFair());
		cannibalizedQuantity.setText("" + score.getNumbCannibalized());
		oxenQuantity.setText("" + score.getOxen());
		sparePartsQuantity.setText("" + score.getSpareParts());
		clothesQuantity.setText("" + score.getClothes());
		foodQuantity.setText("" + score.getFood());
		cashQuantity.setText("" + score.getCash());
		heirloomQuantity.setText("" + score.getHeirlooms());
		goodPoints.setText("" + score.getNumbGoodScore());
		fairPoints.setText("" + score.getNumbFairScore());
		poorPoints.setText("" + score.getNumbPoorScore());
		cannibalizedPoints.setText("-" + score.getNumbCannibalizedScore());
		oxenPoints.setText("" + score.getOxenScore());
		sparePartsPoints.setText("" + score.getSparePartsScore());
		clothesPoints.setText("" + score.getClothesScore());
		foodPoints.setText("" + score.getFoodScore());
		cashPoints.setText("" + score.getCashScore());
		heirloomMulti.setText("x" + score.getHeirloomMultiplier());
		
	}
	
	private void diary() {
		String pageName = "";
		if(settlementPane.isVisible() == true) {
			if(locNameLbl.getText().equals("You are in: Independence, Missouri")) {
				pageName = "src\\Assets\\1.PNG";
			}
			if(locNameLbl.getText().equals("You are in: Fort Kearney")) {
				pageName = "src\\Assets\\2.PNG";
			}
			if(locNameLbl.getText().equals("You are in: Fort Laramie")) {
				pageName = "src\\Assets\\3.PNG";
			}
			if(locNameLbl.getText().equals("You are in: Fort Bridger") || locNameLbl.getText().equals("You are in: Fort Hall")) {
				pageName = "src\\Assets\\4.PNG";
			}
			if(locNameLbl.getText().equals("You are in: Fort Boise")) {
				pageName = "src\\Assets\\5.PNG";
			}
			if(locNameLbl.getText().equals("You are in: Fort Walla Walla")) {
				pageName = "src\\Assets\\6.PNG";
			}
		}
		else {
			pageName = "src\\Assets\\fullDiary.pdf";
		}
		
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(pageName);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no file located
		    }
		}
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