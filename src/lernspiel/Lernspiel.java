package lernspiel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.System;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * 
 * Beschreibung
 * 
 * @version 2.0 vom 22.01.2011
 * @author
 */

public class Lernspiel extends JFrame implements SwingConstants {
	// Anfang Attribute
	
    private static final long serialVersionUID = 4361337321071606387L;
	
    
    /**
     * defines the System-time, when something was entered
     */
	long time;
	
	/**
	 * defines the System-time, when a letter/sign to enter has appeared
	 */
	long occtime;
	
	/**
	 * defines the amounts of mistakes done entering
	 */
	int errors =0;
	
	/**
	 * defines the amount of rounds played
	 */
	int rounds=0;
	
	/**
	 * defines the position current combination in the combination-array
	 */
	int combi;
	
	/**
	 * set to true, when the game is going to end
	 */
	boolean end=false;
	
	/**
	 * represents the current symbol to enter
	 */
	char symbol;
	
	/**
	 * defines the key pressed by the player
	 */
	int input;
	
	/**
	 * defines the average reaction time of the current player
	 */
	double average;
	
	/**
	 * defines the sum of the reaction times of all rounds played
	 */
	double sum=0.0;
	
	/**
	 * defines the maximum time for a round before a new round is started(dependent of the difficulty)
	 */
	int endTime;
	
	/**
	 * is true, when the combination entered fits the desired combination
	 */
	boolean correct;
	
	/**
	 * is true, if the first key of a combination was already released
	 */
	boolean firstKey;
	
	/**
	 * is true, if this is the first round played
	 */
	boolean firstRound = true;
	
	/**
	 * Image of the keys
	 */
	BufferedImage keyPic;
	
	/**
	 * Image shown for evaluation
	 */
	BufferedImage evaluationPic;
	
	/**
	 * Timer used for entering (gives user a short time after releasing the first key to release other pressed keys)
	 */
	Timer typingTimer = new Timer();
	
	/**
	 * defines the time for the user to release all keys after the first released
	 */
	int typingTime;
	
	/**
	 * Timer for a new round to start if the user does not enter something in a given time
	 */
	Timer RoundTimer = new Timer();
	
	/**
	 * array of all possible combinations
	 */
	int[][] combis;
	
	/**
	 * represents the 10 keys (true, if this key was pressed in this combination)
	 */
	boolean[] keys = { false, false, false, false, false, false, false,
			false, false, false };

	/**
	 * Label for showing the evaluation image
	 */
	ImageLabel evaluation = new ImageLabel();
	
	/**
	 * Labels for showing each of the 10 keys
	 */
	ImageLabel keyLabel[] = { new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel() };
	
	/**
	 * Label for showing the time needed to enter a combination
	 */
	JLabel timeLabel = new JLabel("");
	
	/**
	 * Label for showing the current symbol to enter
	 */
	JLabel symbolLabel = new JLabel("");
	
	/**
	 * used to catch all keystrokes (user will not see this)
	 */
	JTextField text = new JTextField();
	
	/**
	 * description of the first 5 keys
	 */
	JLabel row1 = new JLabel("Tastenreihe 1");
	
	/**
	 * description of the second 5 keys
	 */
	JLabel row2 = new JLabel("Tastenreihe 2");

	
	/**
	 * RootMenu is the parent of Lernspiel
	 */
	static RootMenu parent;
	

	

	
	public Lernspiel(String title, RootMenu root, int difficulty) {
		
		super(title);
		
		parent = root;
		
		//hide the main menu
		parent.setVisible(false);
		
		// get the mappings, save it into kombis
		this.combis = parent.getMapping();
		
		/*
		 * uncomment the following to use the typingTime of the main menu
		 * typingTime=parent.typingTime;
		 */
		
		//set typingTime to custom value
		typingTime=500;
		
		
		
		
		//set endTime depending on the difficulty
		switch (difficulty){
		case 1: endTime=10000; break;
		case 2: endTime=5000; break;
		case 3: endTime=3000; break;
		default: endTime=5000; break;
		}
			
		//initialize frame
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 800;
		int frameHeight = 600;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		Container cp = getContentPane();
		cp.setLayout(null);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		
		
		
		//initialize components
		text.setBounds(0, 0, 400, 0);
		cp.add(text);
		evaluation.setBounds(0, 0, 400, 300);
		evaluation.setBackground(Color.WHITE);
		cp.add(evaluation);

		//upper key-row
		for (int i = 0; i < 5; i++) { 
			keyLabel[i].setBounds(400 + 80 * i, 0, 80, 80);
			keyLabel[i].setBackground(Color.WHITE);
			cp.add(keyLabel[i]);

		}
		//lower key-row
		for (int i = 5; i < 10; i++) { 
			keyLabel[i].setBounds(400 + (80 * (i - 5)), 150, 80, 80);
			keyLabel[i].setBackground(Color.WHITE);
			cp.add(keyLabel[i]);
		}
		row1.setBounds(400, 80, 400, 70);
		row1.setOpaque(true);
		row1.setBackground(Color.WHITE);
		row1.setFont(new Font("Arial", 255, 32));
		row1.setHorizontalAlignment(CENTER);
		cp.add(row1);

		row2.setBounds(400, 230, 400, 70);
		row2.setOpaque(true);
		row2.setBackground(Color.WHITE);
		row2.setFont(new Font("Arial", 255, 32));
		row2.setHorizontalAlignment(CENTER);
		cp.add(row2);

		timeLabel.setBounds(0, 300, 400, 300);
		timeLabel.setOpaque(true);
		timeLabel.setBackground(Color.WHITE);
		timeLabel.setFont(new Font("Arial", 255, 32));
		timeLabel.setHorizontalAlignment(CENTER);
		cp.add(timeLabel);
		symbolLabel.setBounds(400, 300, 395, 272);
		symbolLabel.setOpaque(true);
		symbolLabel.setBackground(Color.WHITE);
		symbolLabel.setFont(new Font("Arial", 255, 200));
		symbolLabel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
		symbolLabel.setHorizontalAlignment(CENTER);
		cp.add(symbolLabel);
		
		
		
		//shows hint for user how to end the game
		updateEvaluation("beenden.jpeg");

		//adds the KeyListener to text field
		setListener();

		//start the first round
		newround();
		


	}

	//beginning of methods

	
	//add and initialize KeyListener
	public void setListener() {
		
		/**
		 * add KeyListener to the text-field
		 */
		text.addKeyListener(new KeyListener() {
			
			/**
			 * action if a key is pressed
			 */
			public void keyPressed(KeyEvent e) {
				
				//get the current key pressed
				//depending on which key is pressed, update the image of the according key
				//and set the according boolean value to true
				input = e.getKeyChar();
				switch (input) {
				case 'q':
					keys[0] = true;
					updateKeys(0, true);
					break;
				case 'w':
					keys[1] = true;
					updateKeys(1, true);
					break;
				case 'e':
					keys[2] = true;
					updateKeys(2, true);
					break;
				case 'r':
					keys[3] = true;
					updateKeys(3, true);
					break;
				case 'v':
					keys[4] = true;
					updateKeys(4, true);
					break;
				case 'n':
					keys[5] = true;
					updateKeys(5, true);
					break;
				case 'u':
					keys[6] = true;
					updateKeys(6, true);
					break;
				case 'i':
				    keys[7] = true;
					updateKeys(7, true);
					break;
				case 'o':
		        	keys[8] = true;
					updateKeys(8, true);
					break;
				case 'p':
			     	keys[9] = true;
					updateKeys(9, true);
					break;
				default:

					break;
				}	
			}
			

			//action if a key is released
			public void keyReleased(KeyEvent e) {
					
				
				//if the no key of the combination was released yet, 
				//set it to true to state that the first one is now released
				if (firstKey==false) {
					firstKey=true;
					
					//end the timer of the round-length, because now a combination is entered
					RoundTimer.cancel();
					
					//start the timer for releasing the left keys
					typingTimer.schedule(new Checker(), typingTime);
					
					//set the reaction time to the current system time
					time = System.currentTimeMillis();
				}
				else{
					
				}
				
				
				//check the input
				//update the image and set the keys for this combination to true
				//like in KeyReleased()
				input = e.getKeyChar();
				switch (input) {
				case 'q':
					keys[0] = true;
					updateKeys(0, false);
					break;
				case 'w':
					keys[1] = true;
					updateKeys(1, false);
					break;
				case 'e':
					keys[2] = true;
					updateKeys(2, false);
					break;
				case 'r':
					keys[3] = true;
					updateKeys(3, false);
					break;
				case 'v':
					keys[4] = true;
					updateKeys(4, false);
					break;
				case 'n':
					keys[5] = true;
					updateKeys(5, false);
					break;
				case 'u':
					keys[6] = true;
					updateKeys(6, false);
					break;
				case 'i':
					keys[7] = true;
					updateKeys(7, false);
					break;
				case 'o':
					keys[8] = true;
					updateKeys(8, false);
					break;
				case 'p':
					keys[9] = true;
					updateKeys(9, false);
					break;
				default:

					break;
				}
				
				
				
				
				//if only the first 5 Keys were pressed, end the game and show the stats-window
				if ((keys[0]==true) && (keys[1]==true) && (keys[2]==true) && (keys[3]==true) && (keys[4]==true) && (keys[5]==false) && (keys[6]==false) && (keys[7]==false) && (keys[8]==false) && (keys[9]==false)) {
					
					//end-boolean sets true
					end=true;
			        
					// calculate average reaction time
					average=sum / (rounds-errors);
					
					//call the stats-window, and 
					Fehler Fehler = new Fehler("Statistik",rounds,errors,average, parent);
					Fehler.setVisible(true);
					
					/**
					 * end all running timers
					 */
					typingTimer.cancel();
					RoundTimer.cancel();
					
					/**
					 * close window
					 */
					dispose();
				} else {
					
				}
			}
			

			public void keyTyped(KeyEvent e) {
			}

		});
	}

	
	//check if entered combination is correct
	public void check() {
		//"expect" a correct result
		correct = true;
		
		//if any key doesn't fit the desired combination, set the correct-boolean to false
		for (int i = 0; i < 10; i++) {
			if (((combis[i][combi] == 0) && (keys[i] == false))
					|| (combis[i][combi] == 1) && (keys[i] == true)) {

			} else {
				correct = false;
			}
		}
		
		//if correct, play the according symbol-sound and an applause-sound
		//get the reaction time (includes updating the evaluation image)
		if (correct==true){
			playsound();
			sleep(500);
			playsound("clap");
			getreaction();
		}
		
		//if wrong, increase errors and update labels and play a sound
		else{
			errors++;
			updateEvaluation("fail.jpeg");
			updateTime("Falsche Eingabe");
			playsound("wrong");
		}
		
		/*
		 * increase rounds
		 */
		rounds++;

		/*
		 * if this was the first round, the next one isn't anymore (kind of logical)
		 */
		if(firstRound==true){
			firstRound=false;
			
		}else{}
			
		//start new round
			newround();	
	}

	
	/**
	 * get the reaction time (time needed to make an input)
	 */
	
	public void getreaction() {
		
		/**
		 * reaction time=current time - time, when the symbol occured
		 */
		double reaction = (Math.round((time - occtime) * 10) / 10000.0);
				
		  sum=sum+reaction;	
		
		/**
		 * update the time-label (show the reaction time)
		 */
		updateTime(reaction);
		
		/**
		 * update the evaluation image(according to reaction time)
		 */
		updateEvaluation(reaction);
	}
	
	
	/**
	 * start new round
	 */
	public void newround() {
		/**
		 * set Font
		 */
		symbolLabel.setFont(new Font("Arial", 255, 200));
		
		/*
		 * choose new symbol and according combination to enter
		 * update the related images
		 */
		
		newSymbol();
		updateSymbol();
		updateKeys();
		
		/*
		 * start timer and set occurence time of the desired symbol
		 */
		RoundTimer=new Timer();
		RoundTimer.schedule(new Next(), endTime);
		occtime = System.currentTimeMillis();
		
		/*
		 * reset firstKey-Flag and all keys
		 */
		firstKey=false;
		for (int i =0; i<10;i++) {
		keys[i] = false;
		}

	}

	
	/**
	 * sets new Symbol
	 */
	public void newSymbol() {
		
		/*
		 * select random combination in the combination-array by using system time
		 */
		int length=combis[0].length;
		long random = (System.currentTimeMillis() % (length-1));
		combi = (int) random;
		
		/*
		 * don't use symbols with ascii-code 0 (used by other classes for navigation)
		 */
		while(combis[10][combi]==0){
			if ((combi+1) > (length-1)){
				combi=0;
			}
			else{
				combi++;
			}
			
		}
		symbol = (((char) combis[10][combi]));
	}

	/**
	 * sleep for [sleeptime] milliseconds
	 * @param sleeptime
	 */
	public void sleep(Integer sleeptime) {
		try {

			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * update key images according to the desired combination
	 */
	public void updateKeys() { 

		for (int i = 0; i < 10; i++) {
			if (combis[i][combi] == 1) {
				createImage("key_press.jpeg");
				keyLabel[i].setImage(keyPic);
			} else {
				createImage("key_dontpress.jpeg");
				keyLabel[i].setImage(keyPic);
			}
		}

	}

	/**
	 * updates a certain key-image when this key is pressed or release
	 * different images are used if this key is pressed or not and if it shall be pressed or not
	 * @param i defines which key is to be updated
	 * @param pressed defines if this key is pressed
	 */
	public void updateKeys(int i, boolean pressed) { 
		
		if ((i >= 0) && (i < 10)) {
			if (pressed == true)  {
				if(combis[i][combi] == 1){
				createImage("key_green.png");
				keyLabel[i].setImage(keyPic);
				
				}
				else{
					createImage("key_red.png");
					keyLabel[i].setImage(keyPic);
						
				}
					
			} 
			else {
				if (combis[i][combi] == 1) {
					createImage("key_press.jpeg");
					keyLabel[i].setImage(keyPic);
				} else {
					createImage("key_dontpress.jpeg");
					keyLabel[i].setImage(keyPic);;
				}
			}

		} else {
			System.out.println("Ein Fehler ist aufgetreten" + i + "  " + pressed);
		}
	}
	
	/**
	 * creates images for the key images
	 * @param image
	 */
	public void createImage(String image){
		try {
			keyPic = ImageIO.read( new File("src/Pics/" + image) );
		} catch (IOException e) {		
			System.out.println("Datei nicht gefunden");
		}
	}

	/**
	 * updates the evaluation label
	 * @param image defines the picture to be used
	 */
	public void updateEvaluation(String image){ 
		try {
			evaluationPic = ImageIO.read( new File("src/Pics/" + image) );
		} catch (IOException e) {
			System.out.println("Datei nicht gefunden");
		}
		
		evaluation.setImage(evaluationPic);
	}

	
	/**
	 * updates the evaluation label according to the reaction
	 * @param reaction
	 */
	public void updateEvaluation(double reaction) { // ï¿½ndert das Wertungsbild je
													// nach Reaktionszeit
		if (reaction < 1.5) {
			updateEvaluation("awesome" + ((int)(combi %2) + 1) + ".jpeg");
		} else if (reaction < 3) {
			updateEvaluation("nice.jpeg");
		} else {
			updateEvaluation("slow.jpeg");
		}

	}
	
	/**
	 * plays the current desired symbol as a sound
	 */
	public void playsound(){
		Sound.playSound(combis[10][combi]);
	}
	
	/**
	 * plays a certain sound
	 * @param string defines the name of the soundfile
	 */
	public void playsound(String string){
		Sound.playSound(string);
	}
	
	/**
	 * updates the time-label according to the reaction time
	 * @param time
	 */
	public void updateTime(double time) { 

		timeLabel.setText("Reaktionszeit: " + time + " s");
	}

	/**
	 * updates the time label with the given string
	 * @param string
	 */
	public void updateTime(String string){
		timeLabel.setText(string);
	}

	/**
	 * updates the symbol in the symbol label with the desired symbol
	 */
	public void updateSymbol() { 
		if(combis[10][combi]>32){
		symbolLabel.setText(symbol + "");
		}
		else{
			symbolLabel.setFont(new Font("Arial", 255, 32));
			switch(combis[10][combi]){
			case 8: symbolLabel.setText("Rücktaste"); break;
			case 9: symbolLabel.setText("Tabulator"); break;
			case 13: symbolLabel.setText("Enter"); break;
			case 27: symbolLabel.setText("Escape"); break;
			case 32: symbolLabel.setText("Leerzeichen"); break;
			default: symbolLabel.setText("Steuerzeichen"); break;
			}
		}
	}

	/**
	 * 
	 * @author Kevin Articus
	 *
	 */

	public class Checker extends TimerTask{ //Timer-Class to run check() after the typing Time

		
		public void run() {
			if (end==false){
				check();
			}
			
		}
		
	}
	
	public class Next extends TimerTask{ //Timer Class to run check() after automatic round-end has been reached
		
		public void run() {
			if (end==false){
			check();
			timeLabel.setText("zu langsam");
			}
			else{}
		}
	}
	
	

	
	}
