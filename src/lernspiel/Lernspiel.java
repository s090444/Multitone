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
	
	int[][] kombis;
	Timer timer = new Timer();
	
	
	
	
	boolean[] tasten = { false, false, false, false, false, false, false,
			false, false, false };

	long maximum = 100; // Anzahl aller Tastenkombinationen
	int zufall;
	// private JLabel wertung = new JLabel(bwertung);
	ImageLabel wertung = new ImageLabel(new ImageIcon(""));
	ImageLabel tastenbild[] = { new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")),
			new ImageLabel(new ImageIcon("")) };
	Sound Sound = new Sound();
	JLabel zeit = new JLabel("");
	JLabel zeichen = new JLabel("");
	JTextField text = new JTextField();
	JLabel row1 = new JLabel("Tastenreihe 1");
	JLabel row2 = new JLabel("Tastenreihe 2");

	// Ende Attribute

	static RootMenu parent;
	int typingTime = 0;
	
	public Lernspiel(String title, RootMenu root, int TypingTime) {
		// Frame-Initialisierung
		super(title);
		
		parent = root;
		parent.setVisible(false);
		typingTime = TypingTime;
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
		zeichen.setBounds(400, 300, 400, 300);
		zeichen.setOpaque(true);
		zeichen.setBackground(Color.WHITE);
		zeichen.setFont(new Font("Arial", 255, 200));
		zeichen.setHorizontalAlignment(CENTER);
		cp.add(zeichen);
		// Ende Komponenten

		setListener();

		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);

		countdown();

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
				eingabe = e.getKeyCode();
				switch (eingabe) {
				case 49:
					tasten[0] = true;
					updatetasten(0, true);
					break;
				case 50:
					tasten[1] = true;
					updatetasten(1, true);
					break;
				case 51:
					tasten[2] = true;
					updatetasten(2, true);
					break;
				case 52:
					tasten[3] = true;
					updatetasten(3, true);
					break;
				case 53:
					tasten[4] = true;
					updatetasten(4, true);
					break;
				case 54:
					tasten[5] = true;
					updatetasten(5, true);
					break;
				case 55:
					tasten[6] = true;
					updatetasten(6, true);
					break;
				case 56:
					tasten[7] = true;
					updatetasten(7, true);
					break;
				case 57:
					tasten[8] = true;
					updatetasten(8, true);
					break;
				case 48:
					tasten[9] = true;
					updatetasten(9, true);
					break;
				default:

					break;
				}
			}
			

			public void keyReleased(KeyEvent e) {
				// text.dispatchEvent(e);
				if (firstKey==false) {
					firstKey=true;
					timer.schedule(new Checker(),typingTime);
					
				}
				else{
					
				}
				System.out.println(e);
				eingabe = e.getKeyCode();
				switch (eingabe) {
				case 49:
					tasten[0] = true;
					updatetasten(0, false);
					break;
				case 50:
					tasten[1] = true;
					updatetasten(1, false);
					break;
				case 51:
					tasten[2] = true;
					updatetasten(2, false);
					break;
				case 52:
					tasten[3] = true;
					updatetasten(3, false);
					break;
				case 53:
					tasten[4] = true;
					updatetasten(4, false);
					break;
				case 54:
					tasten[5] = true;
					updatetasten(5, false);
					break;
				case 55:
					tasten[6] = true;
					updatetasten(6, false);
					break;
				case 56:
					tasten[7] = true;
					updatetasten(7, false);
					break;
				case 57:
					tasten[8] = true;
					updatetasten(8, false);
					break;
				case 48:
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

				if (e.getKeyChar() == 'q') {
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
			getreaction();
		}
		else{
			fehler++;
			updatezeit("Falsche Eingabe");
		}
		runden++;

			
			newround();

		
	}

	public void getreaction() {
		time = System.currentTimeMillis();
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
		newletter();
		updatezeichen();
		updatetasten();
		occtime = System.currentTimeMillis();
		firstKey=false;
		for (int i =0; i<10;i++) {
		tasten[i] = false;
		}

	}

	public void countdown() {
		updatewertung("src/Pics/start3.jpeg");
		updatezeit(3);
		sleep(1000);
		time--;
		updatewertung("src/Pics/start2.jpeg");
		updatezeit(2);
		time--;
		sleep(1000);
		updatewertung("src/Pics/start1.jpeg");
		updatezeit(1);
		sleep(1000);
		updatewertung("src/Pics/info.jpeg");
		updatetasten();
	}

	public void newletter() {
		// long random = (System.currentTimeMillis() % 94)+33;
		long random = (System.currentTimeMillis() % kombis.length);
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
				tastenbild[i].setIcon(new ImageIcon("src/Pics/pressit.jpeg"));
			} else {
				tastenbild[i].setIcon(new ImageIcon(
						"src/Pics/pressthisnot.jpeg"));
			}
		}

	}

	public void updatetasten(int i, boolean pressed) { // �ndert das Bild, je
														// nachdem welche Tasten
														// im Moment gedr�ckt
														// werden
		if ((i >= 0) && (i < 10)) {
			if (pressed == true) {
				tastenbild[i].setIcon(new ImageIcon("src/Pics/used.jpeg"));
				sleep(20);
			} else {
				// tastenbild[i].setIcon(new ImageIcon("src/Pics/unused.jpeg"));
				if (kombis[i][kombi] == 1) {
					tastenbild[i].setIcon(new ImageIcon("src/Pics/pressit.jpeg"));
				} else {
					tastenbild[i].setIcon(new ImageIcon("src/Pics/pressthisnot.jpeg"));
				}
			}

		} else {
			System.out.println("Seltsamer Fehler !!!  " + i + "  " + pressed);
		}
	}

	public void updatewertung(String image) { // updatet das Wertungsbild
		wertung.setIcon(new ImageIcon(image));
	}

	public void updatewertung(double reaction) { // �ndert das Wertungsbild je
													// nach Reaktionszeit
		if (reaction < 1.5) {
			updatewertung("src/Pics/awesome.jpeg");
		} else if (reaction < 3) {
			updatewertung("src/Pics/nice.jpeg");
		} else {
			updatewertung("src/Pics/fail.jpeg");
		}

	}
	
	public void playsound(){
		Sound.playSound((char) kombis[10][kombi]);
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
		zeichen.setText(letter + "");
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
