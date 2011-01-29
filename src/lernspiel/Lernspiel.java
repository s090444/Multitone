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
	
	long time;
	long occtime;
	int fehler =0;
	int runden=0;
	int kombi;
	String string;
	char letter;
	int eingabe;
	double durchschnitt;
	double summe;
	int zaehler=0;
	boolean firstKey;
	boolean firstRound = true;
	BufferedImage taste;
	BufferedImage bewertung;
	
	int[][] kombis;
	Timer timer = new Timer();
	
	
	
	
	boolean[] tasten = { false, false, false, false, false, false, false,
			false, false, false };

	long maximum = 100; // Anzahl aller Tastenkombinationen
	int zufall;
	// private JLabel wertung = new JLabel(bwertung);
	ImageLabel wertung = new ImageLabel();
	ImageLabel tastenbild[] = { new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel(),
			new ImageLabel() };
	Sound Sound = new Sound();
	JLabel zeit = new JLabel("");
	JLabel zeichen = new JLabel("");
	JTextField text = new JTextField();
	JLabel row1 = new JLabel("Tastenreihe 1");
	JLabel row2 = new JLabel("Tastenreihe 2");

	// Ende Attribute

	static RootMenu parent;
	int typingTime;
	
	public Lernspiel(String title, RootMenu root, int difficulty) {
		// Frame-Initialisierung
		super(title);
		
		parent = root;
		parent.setVisible(false);
		switch (difficulty){
		case 1: typingTime=3000; break;
		case 2: typingTime=2000; break;
		case 3: typingTime=1000; break;
		}
		
		
		this.kombis = parent.getMapping();
		System.out.println(kombis[10][0]);
		
	
		
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
		// Anfang Komponenten

		text.setBounds(0, 0, 400, 0);
		cp.add(text);
		wertung.setBounds(0, 0, 400, 300);
		wertung.setBackground(Color.WHITE);
		cp.add(wertung);

		for (int i = 0; i < 5; i++) { // faengt obere Tastenreihe hinzu
			tastenbild[i].setBounds(400 + 80 * i, 0, 80, 80);
			tastenbild[i].setBackground(Color.WHITE);
			cp.add(tastenbild[i]);

		}
		for (int i = 5; i < 10; i++) { // fuegt untere Tastenreihe hinzu
			tastenbild[i].setBounds(400 + (80 * (i - 5)), 150, 80, 80);
			tastenbild[i].setBackground(Color.WHITE);
			cp.add(tastenbild[i]);
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

		zeit.setBounds(0, 300, 400, 300);
		zeit.setOpaque(true);
		zeit.setBackground(Color.WHITE);
		zeit.setFont(new Font("Arial", 255, 32));
		zeit.setHorizontalAlignment(CENTER);
		cp.add(zeit);
		zeichen.setBounds(400, 300, 395, 272);
		zeichen.setOpaque(true);
		zeichen.setBackground(Color.WHITE);
		zeichen.setFont(new Font("Arial", 255, 200));
		zeichen.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
		zeichen.setHorizontalAlignment(CENTER);
		cp.add(zeichen);
		// Ende Komponenten
		
		updatewertung("beenden.jpeg");

		setListener();

		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);

		//countdown();

		newround();

		// wertung.revalidate();

	}

	// Anfang Methoden
	// Ende Methoden

	/*
	 * public void update() { wwertung= "src/Pics/fail.jpeg";
	 * Lernspiel.wertung.revalidate(); }
	 */

	public void setListener() {
		text.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) {
				System.out.println(e);
				eingabe = e.getKeyChar();
				switch (eingabe) {
				case 'q':
					tasten[0] = true;
					updatetasten(0, true);
					break;
				case 'w':
					tasten[1] = true;
					updatetasten(1, true);
					break;
				case 'e':
					tasten[2] = true;
					updatetasten(2, true);
					break;
				case 'r':
					tasten[3] = true;
					updatetasten(3, true);
					break;
				case 'v':
					tasten[4] = true;
					updatetasten(4, true);
					break;
				case 'n':
					tasten[5] = true;
					updatetasten(5, true);
					break;
				case 'u':
					tasten[6] = true;
					updatetasten(6, true);
					break;
				case 'i':
				    tasten[7] = true;
					updatetasten(7, true);
					break;
				case 'o':
		        	tasten[8] = true;
					updatetasten(8, true);
					break;
				case 'p':
			     	tasten[9] = true;
					updatetasten(9, true);
					break;
				default:

					break;
				}
				
				if ((tasten[0]==true) && (tasten[1]==true) && (tasten[2]==true) && (tasten[3]==true) && (tasten[4]==true)) {
					//System.exit(0);
					
			        //setVisible(false);
					durchschnitt=summe / zaehler;
					Fehler Fehler = new Fehler("Statistik",runden,fehler,durchschnitt, parent);
					Fehler.setVisible(true);
					//parent.setVisible(true);
					dispose();
				} else {
					
				}
				
			}
			

			public void keyReleased(KeyEvent e) {
				// text.dispatchEvent(e);
				if (firstKey==false) {
					firstKey=true;
					timer.schedule(new Checker(),typingTime);
					time = System.currentTimeMillis();
				}
				else{
					
				}
				System.out.println(e);
				eingabe = e.getKeyChar();
				switch (eingabe) {
				case 'q':
					tasten[0] = true;
					updatetasten(0, false);
					break;
				case 'w':
					tasten[1] = true;
					updatetasten(1, false);
					break;
				case 'e':
					tasten[2] = true;
					updatetasten(2, false);
					break;
				case 'r':
					tasten[3] = true;
					updatetasten(3, false);
					break;
				case 'v':
					tasten[4] = true;
					updatetasten(4, false);
					break;
				case 'n':
					tasten[5] = true;
					updatetasten(5, false);
					break;
				case 'u':
					tasten[6] = true;
					updatetasten(6, false);
					break;
				case 'i':
					tasten[7] = true;
					updatetasten(7, false);
					break;
				case 'o':
					tasten[8] = true;
					updatetasten(8, false);
					break;
				case 'p':
					tasten[9] = true;
					updatetasten(9, false);
					break;
				default:

					break;
				}
				

				// text.dispatchEvent(e);
				/*
				 * System.out.println(e); eingabe = e.getKeyCode(); switch
				 * (eingabe) { case 49: tasten[0] = true; updatetasten(0, true);
				 * break; case 50: tasten[1] = true; updatetasten(1, true);
				 * break; case 51: tasten[2] = true; updatetasten(2, true);
				 * break; case 52: tasten[3] = true; updatetasten(3, true);
				 * break; case 53: tasten[4] = true; updatetasten(4, true);
				 * break; case 54: tasten[5] = true; updatetasten(5, true);
				 * break; case 55: tasten[6] = true; updatetasten(6, true);
				 * break; case 56: tasten[7] = true; updatetasten(7, true);
				 * break; case 57: tasten[8] = true; updatetasten(8, true);
				 * break; case 48: tasten[9] = true; updatetasten(9, true);
				 * break; default:
				 * 
				 * break; }
				 */

			}

			public void keyTyped(KeyEvent e) {
			}

		});
	}

	public void check() {
		boolean richtig = true;
		for (int i = 0; i < 10; i++) {
			if (((kombis[i][kombi] == 0) && (tasten[i] == false))
					|| (kombis[i][kombi] == 1) && (tasten[i] == true)) {

			} else {
				richtig = false;
			}
		}
		if (richtig==true){
			playsound();
			sleep(500);
			playsound("clap");
			getreaction();
		}
		else{
			fehler++;
			updatezeit("Falsche Eingabe");
			playsound("wrong");
		}
		runden++;

			
			newround();
			

		
	}

	public void getreaction() {
		
		double reaction = (Math.round((time - occtime) * 10) / 10000.0);
		if (zaehler==0){
			summe=reaction;
		}
		else{
		  summe=summe+reaction;	
		}
		zaehler++;
		updatezeit(reaction);
		updatewertung(reaction);
	}

	public void newround() {
		zeichen.setFont(new Font("Arial", 255, 200));
		newletter();
		updatezeichen();
		updatetasten();
		occtime = System.currentTimeMillis();
		firstKey=false;
		for (int i =0; i<10;i++) {
		tasten[i] = false;
		}

	}

 /*	public void countdown() {
		for (time=3;time>0;time--){
		timer.schedule(new Checker(), 1000);
	}
		firstRound=false;
	}*/



	/*public void countdown() {
		for (time=3;time>0;time--){
		timer.schedule(new Checker(), 1000);
	}
		firstRound=false;
	}*/
	
	public void newletter() {
		// long random = (System.currentTimeMillis() % 94)+33;
		long random = (System.currentTimeMillis() % kombis[0].length);
		kombi = (int) random;
		letter = (char) kombis[10][kombi];
	}

	public void sleep(Integer sleeptime) {
		try {

			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
		}
	}

	public void updatetasten() { // updatet das Tastenbild-Feld komplett

		for (int i = 0; i < 10; i++) {
			if (kombis[i][kombi] == 1) {
				createImage("pressit.jpeg");
				tastenbild[i].setImage(taste);
			} else {
				createImage("pressthisnot.jpeg");
				tastenbild[i].setImage(taste);
			}
		}

	}

	public void updatetasten(int i, boolean pressed) { // �ndert das Bild, je
														// nachdem welche Tasten
														// im Moment gedr�ckt
														// werden
		
		if ((i >= 0) && (i < 10)) {
			if (pressed == true) {
				createImage("used.jpeg");
				tastenbild[i].setImage(taste);
				sleep(20);
			} else {
				// tastenbild[i].setIcon(new ImageIcon("src/Pics/unused.jpeg"));
				if (kombis[i][kombi] == 1) {
					createImage("pressit.jpeg");
					tastenbild[i].setImage(taste);
				} else {
					createImage("pressthisnot.jpeg");
					tastenbild[i].setImage(taste);;
				}
			}

		} else {
			System.out.println("Seltsamer Fehler !!!  " + i + "  " + pressed);
		}
	}
	
	public void createImage(String image){
		try {
			taste = ImageIO.read( new File("src/Pics/" + image) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei nicht gefunden");
		}
	}

	public void updatewertung(String image){ // updatet das Wertungsbild
		try {
			bewertung = ImageIO.read( new File("src/Pics/" + image) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Datei nicht gefunden");
		}
		
		wertung.setImage(bewertung);
	}

	public void updatewertung(double reaction) { // �ndert das Wertungsbild je
													// nach Reaktionszeit
		if (reaction < 1.5) {
			updatewertung("awesome.gif");
		} else if (reaction < 3) {
			updatewertung("awesome.gif");
		} else {
			updatewertung("awesome.gif");
		}

	}
	
	public void playsound(){
		Sound.playSound(kombis[10][kombi]);
	}
	
	public void playsound(String string){
		Sound.playSound(string);
	}

	public void updatezeit(double time) { // updatet die Zeit

		zeit.setText("Reaktionszeit: " + time + " s");
	}

	public void updatezeit(int time) { // updatet die Zeit

		zeit.setText(time + "");
	}
	
	public void updatezeit(String string){
		zeit.setText(string);
	}

	public void updatezeichen() { // updatet das Zeichen, dass eingegeben werden
									// soll
		if(kombis[10][kombi]>31){
		zeichen.setText(letter + "");
		}
		else{
			zeichen.setFont(new Font("Arial", 255, 32));
			zeichen.setText("Steuerzeichen");
		}
	}

	public String kombipfad() { // legacy method
		return "src/Pics/" + letter + ".jpeg";
	}

	
	/* Starten des Programms in das RootMenu ausgelagert 
	 * Instanziierung des Lernspiels
	 */
	/*public static void main(String[] args) {
		new Lernspiel("Lernspiel");

	}*/
	
	public class Checker extends TimerTask{
		public Checker(){
			
		}
		public void run() {
			
			
				check();
			
				
		}
	}
	
	
	
}
