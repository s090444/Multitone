/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lernspiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *  
 * @author 		Christian Jelitte	<christian.jelitte@gmx.net>
 * @version		0.8 beta
 * @since		2011.0131
 * 
 * Rootmenü ist der Einstieg in das MultiTouch-Programm, entwickelt von Multitone,
 * einem StudentenProjekt.
 * 
 * 
 * TODO: MappingVisualisierung (optional)
 * 			
 * 		Zur Zeit ist das Mapping fest auf den Bereich von 35-40 eingestellt, da noch keine
 * 		Move-Up / Move-Down - Funktionalität eingebaut ist.
 * 		Zusätzlich muss berücksichtigt werden, wennn das Mapping am Ende des mappings angelangt ist.
 * 
 * TODO: Move-UP (optional)
 * 	
 * 		Eine Move-Up Funktionalität, die in reverser Form die Funktionalität von Move-Down hat, also dem Anwender
 * 		erlaubt, das Menü von unten nach oben durchzulaufen.
 * 
 **/
public class RootMenu extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 7465416001464648515L;
	static RootMenu root;

	/**
	 * 	directory - Verweist auf das Bilderverzeichnis
	 */	
	String directory = "src/Pics/";

	/**
	 * Menü-Button bilder werden geladen
	 * 
	 * Das Menü besteht aus folgenden Punkten:
	 * 	- Lernspiel (in 3 Schwierigkeitsstufen)
	 *  - Texteditor
	 *  - E-Mailprogramm
	 *  - Mapping-Übersicht
	 *  Jeder Button ist in angewählter Form (selected) und nicht-angewählter Form (unselected)
	 *  vorhanden.
	 *  
	 *  Zusätzlich werden noch die Keyboard-Bilder geladen, die es in der
	 *  angewählten und abgewählten Form gibt.
	 * 	
	 **/
	Image lernspiel_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "lernspiel_unselected.png");
	Image lernspiel_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "lernspiel_selected.png");

	Image texteditor_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "texteditor_unselected.png");
	Image texteditor_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "texteditor_selected.png");

	Image email_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "email_unselected.png");
	Image email_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "email_selected.png");

	Image tastenbelegung_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "tastenbelegung_unselected.png");
	Image tastenbelegung_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "tastenbelegung_selected.png");

	Image quit_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "quit_unselected.png");
	Image quit_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "quit_selected.png");

	Image level1_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "level1_unselected.png");
	Image level1_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "level1_selected.png");

	Image level2_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "level2_unselected.png");
	Image level2_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "level2_selected.png");

	Image level3_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "level3_unselected.png");
	Image level3_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "level3_selected.png");

	Image zurueck_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "zurueck_unselected.png");
	Image zurueck_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "zurueck_selected.png");

	Image background = Toolkit.getDefaultToolkit().getImage(
			directory + "background.png");

	Image key_unselected = Toolkit.getDefaultToolkit().getImage(
			directory + "key_unselected.png");
	Image key_selected = Toolkit.getDefaultToolkit().getImage(
			directory + "key_selected.png");

	/**
	 * Globlae Variablen zum Zeichnen des Menüs.
	 * 
	 * Sie werden benötigt, um mit Hilfe der Key-Listener außerhalb der Paint-Methode die Bilder von
	 * select auf unselected bzw. andersherum zu setzen.
	 **/
	Image lernspiel;
	Image editor;
	Image email;
	Image tastenbelegung;
	Image quit;

	/**
	 *  menuCounter ist der aktive Menüpunkt, der zur Zeit selected ist.
	 *  
	 *  menuCounter ist mit eins initialisiert, da der erste Menüpunkt schon vorselektiert 
	 *  sein soll ist.
	 **/
	int menuCounter = 1;

	/**
	 * 	currentMenu ist die aktuelle MenüOberfläche. Zur Zeit gibt es 3:
	 * 	
	 * 	0: 	RootMenü mit den Punkten (Lernspiel, Texteditor, e-Mail, Mapping, Quit)
	 * 	1: 	Lernspiel-Menü mit den Punkten (Level 1, Level 2, Level 3, Zurück)
	 * 	2:	Mapping (hier wird das aktuelle Mapping aus der Text-Datei gelesen und für den 
	 * 			Anwender sichtbar gemacht.
	 * 
	 *	Das currentMenu wird mit 0 für das RootMenü initialisiert.
	 **/
	private int currentMenu = 0;
	JPanel panel;

	/**
	 *	Typing_time ist die Zeit, die der Benutzer für seine Eingaben hat (Release Keys) hat in MilliSeks.
	 * 
	 * 	Diese Variable wird für andere Programme als Getter-Methode bereitgestellt.
	 **/
	final private int typingTime = 50;

	public int getTypingTime() {
		return typingTime;
	}

	/*
	 * numOfKeys
	 */

	final int numOfKeys = 14;

	public int getNumOfKeys() {
		return numOfKeys;
	}

	/** global variables **/

	String s = new String();

	/*
	 * numOfPoss
	 */

	int numOfPoss = 0;

	public int getNumOfPoss() {
		return numOfPoss;
	}

	public void setNumOfPoss(int numOfPoss) {
		this.numOfPoss = numOfPoss;
	}

	/*
	 * firstkeyPressedflag
	 */

	private boolean firstKeyPressedflag;

	public boolean isFirstKeyPressedflag() {
		return firstKeyPressedflag;
	}

	public void setFirstKeyPressedflag(boolean firstKeyPressedflag) {
		this.firstKeyPressedflag = firstKeyPressedflag;
	}

	/*
	 * map
	 */

	char map = 0;

	public char getMap() {
		return map;
	}

	public void setMap(char map) {
		this.map = map;
	}

	/*
	 * Flag
	 */

	int flag[] = new int[numOfKeys];

	public int[] getFlag() {
		return flag;
	}

	public void setFlag(int field, int flac) {
		this.flag[field] = flac;
	}

	/*
	 * Mapping
	 */

	private int mapping[][];

	public int[][] getMapping() {
		return mapping;
	}

	public void setMapping(int[][] mapping) {
		this.mapping = mapping;
	}

	/* 
	 * define if keyEvent is consumed or let through
	 * used in Editor.java and Email.java
	 */
	
	public boolean forward = false;
	public boolean release = false;

	// keyCode
	private int keyCode = 0;

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	/*
	 * Constructor
	 */
	public RootMenu() {
		super();

		this.currentMenu = 0;
		System.out.println("Initializiere RootMenu...");

		this.setFirstKeyPressedflag(false);
		System.out.println("FirstKeyPressedflag gesetzt auf "
				+ this.isFirstKeyPressedflag());

		ReadNumOfPoss readNum = new ReadNumOfPoss();
		this.numOfPoss = readNum.readNumOfPoss();
		System.out.println("Read numbers of Possibilities (out of file): "
				+ this.numOfPoss);

		ReadMapping readMapp = new ReadMapping(this.numOfKeys, this.numOfPoss);
		this.setMapping(readMapp.readMapping());
		System.out.println("Einlesen des Mappings. Erste Mapping[0][0] ist : "
				+ this.mapping[0][0]);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// String curDir = System.getProperty("user.dir");
		// System.out.println("current Directory: " + curDir);

		this.rootMenu();
		this.drawNewPosition();

		this.addComponentsToPanel();
		// root.pack();

		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				root = new RootMenu();
				// Editor e = new Editor(root);
			}
		});
	}

	/*
	 * GUI
	 */

	public void addComponentsToPanel() {

		panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background, 0, 0, 800, 576, this); // 768

				if (currentMenu == 2) {

					Font font = new Font("Sans", Font.BOLD, 14);
					g.setFont(font);

					Image keyImage;
					int zeile, spalte, pos_x, pos_y, pos_x_start, maxKeyPP, keyDis_x, keyDis_y, key_begin, key_end;

					key_begin = 35;
					key_end = 40;

					maxKeyPP = 5;
					pos_y = 130;
					pos_x_start = 300;

					keyDis_x = 20;
					keyDis_y = 40;
					g.drawString("Tastenbelegung " + key_begin + " - "
							+ key_end, 330, 120);
					for (zeile = key_begin; zeile < (key_begin + maxKeyPP); zeile++) {

						pos_x = pos_x_start;

						for (spalte = 0; spalte < 10; spalte++) {
							/*System.out.println("(" + spalte + "/" + zeile
									+ "):" + mapping[spalte][zeile]);*/

							if (mapping[spalte][zeile] == 1) {
								keyImage = key_selected;
							} else {
								keyImage = key_unselected;
							}

							g.drawImage(keyImage, pos_x, pos_y, 20, 20, this);
							// System.out.println("x " + pos_x + " y " + pos_y);

							pos_x = pos_x + keyDis_x;

							if (pos_x == (pos_x_start + (keyDis_x * 5)))
								pos_x = pos_x + 10;

						}

						g.drawString(Character
								.toString((char) mapping[numOfKeys][zeile]),
								(pos_x + 10), (pos_y + 13));

						pos_y = pos_y + keyDis_y;

						// System.out.println();
					}

				} else {

					g.drawImage(lernspiel, 295, 90, 220, 55, this);
					g.drawImage(editor, 295, 135, 220, 55, this);
					g.drawImage(email, 295, 180, 220, 55, this);

					if (currentMenu == 0)
						g.drawImage(tastenbelegung, 295, 225, 220, 55, this);

					g.drawImage(quit, 295, 290, 220, 55, this);
				}

				// Beschreibung für Move-Down anhand des Key-Icons
				g.drawImage(key_selected, 20, 150, 20, 20, this);
				g.drawImage(key_selected, 40, 150, 20, 20, this);
				g.drawImage(key_selected, 60, 150, 20, 20, this);
				g.drawImage(key_unselected, 80, 150, 20, 20, this);
				g.drawImage(key_unselected, 100, 150, 20, 20, this);

				g.drawImage(key_unselected, 130, 150, 20, 20, this);
				g.drawImage(key_unselected, 150, 150, 20, 20, this);
				g.drawImage(key_unselected, 170, 150, 20, 20, this);
				g.drawImage(key_unselected, 190, 150, 20, 20, this);
				g.drawImage(key_unselected, 210, 150, 20, 20, this);

				// Beschreibung für Bestätigung (Enter) anhand des Key-Icons
				g.drawImage(key_selected, 20, 230, 20, 20, this);
				g.drawImage(key_selected, 40, 230, 20, 20, this);
				g.drawImage(key_unselected, 60, 230, 20, 20, this);
				g.drawImage(key_unselected, 80, 230, 20, 20, this);
				g.drawImage(key_unselected, 100, 230, 20, 20, this);

				g.drawImage(key_unselected, 130, 230, 20, 20, this);
				g.drawImage(key_unselected, 150, 230, 20, 20, this);
				g.drawImage(key_unselected, 170, 230, 20, 20, this);
				g.drawImage(key_unselected, 190, 230, 20, 20, this);
				g.drawImage(key_unselected, 210, 230, 20, 20, this);

			}
		};

		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		if (currentMenu != 2) {
			final JLabel moveDownDescription = new JLabel("Move Down");
			moveDownDescription.setBounds(20, 120, 170, 35);
			panel.add(moveDownDescription);

			final JLabel enterDescription = new JLabel("Enter");
			enterDescription.setBounds(20, 200, 170, 35);
			panel.add(enterDescription);
		} else {
			final JLabel anyKey = new JLabel("Press any Key to go back ...");
			anyKey.setBounds(20, 120, 170, 35);
			panel.add(anyKey);
		}

		System.out.println("panel focusable: " + panel.isFocusable());
		if (panel.isFocusable()) {
			panel.setFocusable(true);
			panel.requestFocusInWindow();
		}

		panel.addKeyListener(this);

		this.getContentPane().add(panel);

		setTitle("RootMenu");
		setSize(800, 600);

		setLocationRelativeTo(null);
	}

	public void keyTyped(KeyEvent e) {
		// System.out.println(e);
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void keyPressed(KeyEvent e) {
		// System.out.println("RootWindow: " + e);
		this.setFlac(e, 1);

		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void keyReleased(KeyEvent e) {

		/** Handle the Released keys event. */

		// System.out.println("(RootM keyPressed) FirstKeyPressedflag gesetzt auf "
		// + this.isFirstKeyPressedflag());
		if (this.isFirstKeyPressedflag() == false) {
			this.setFirstKeyPressedflag(true);

			// System.out.println("FirstKeyPressedflag gesetzt auf " +
			// this.isFirstKeyPressedflag());
			// System.out.println("Eingabe beginn...");

			Timer timer = new Timer();
			timer.schedule(new MappedKey(root, ""), typingTime);

		}

		// System.out.println(e);
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void newKeyPush(char newKey) {
		System.out.println("mappedKey: " + newKey + "(curM: " + currentMenu
				+ " men: " + menuCounter);
		switch (newKey) {
		case 'y':
			this.moveMenuPointDown();
			break;
		case 'o':
			this.runApplication();
			break;

		default:
			System.out.println("ungültige Menüeingabe!!");
		}
	}

	public void setFlac(KeyEvent e, int flac) {
		// System.out.println("keyChar: " + e.getKeyChar());

		switch (e.getKeyChar()) {
		case 'q':
			this.setFlag(0, flac);
			break;
		case 'w':
			this.setFlag(1, flac);
			break;
		case 'e':
			this.setFlag(2, flac);
			break;
		case 'r':
			this.setFlag(3, flac);
			break;
		case 'v':
			this.setFlag(4, flac);
			break;
		case 'n':
			this.setFlag(5, flac);
			break;
		case 'u':
			this.setFlag(6, flac);
			break;
		case 'i':
			this.setFlag(7, flac);
			break;
		case 'o':
			this.setFlag(8, flac);
			break;
		case 'p':
			this.setFlag(9, flac);
			break;

		default:
			System.err.println("Ungültige Eingabe!");
		}
	}

	/*
	 * bewegt die Menüpunkte farblich durch, indem es zu erst alle Menüpunkte
	 * reseted, anschließend, anhand der menuCounter-Variable, den aktuellen
	 * Menüpunkt setzt und den menuCounter nach oben zählt.
	 */
	public void moveMenuPointDown() {

		/*
		 * sollte das Menü am Ende angekommen sein, wird der Zähler reseted und
		 * der 1. Menüpunkt erscheint wieder (nach dem 4).
		 */
		menuCounter++;
		if (((currentMenu == 0) && (menuCounter > 5))
				|| ((currentMenu == 1) && (menuCounter > 4)))
			menuCounter = 1;
		this.drawNewPosition();
		// setzt alle Bilder im Menü wieder auf "unselected"

	}

	public void drawNewPosition() {

		// wählt den aktuellen Menüpunkt auf "selected" anhand von
		// menuCounter-Variable
		switch (currentMenu) {
		case 0:

			this.rootMenu();

			switch (menuCounter) {
			case 1:
				this.lernspiel = this.lernspiel_selected;
				break;
			case 2:
				this.editor = this.texteditor_selected;
				break;
			case 3:
				this.email = this.email_selected;
				break;
			case 4:
				this.tastenbelegung = this.tastenbelegung_selected;
				break;
			case 5:
				this.quit = this.quit_selected;
				break;

			default:
				System.out.println("Fehler Menüführung!");
			}

			break;

		case 1:

			this.difficultyGame();

			switch (menuCounter) {
			case 1:
				this.lernspiel = this.level1_selected;
				break;
			case 2:
				this.editor = this.level2_selected;
				break;
			case 3:
				this.email = this.level3_selected;
				break;
			case 4:
				this.quit = this.zurueck_selected;
				break;

			default:
				System.out.println("Fehler Menüführung!");
			}

			break;

		default:
			System.out.println();
		}

		repaint();
		System.out.println("currentMenu: " + currentMenu + " menuCounter: "
				+ menuCounter);

	}

	public void resetFlags() {

		for (int i = 0; i < this.getNumOfKeys(); i++)
			this.setFlag(i, 0);

		System.out.println("Flags zurueckgesetzt...");
	}

	public void difficultyGame() {
		this.currentMenu = 1;

		this.lernspiel = level1_unselected;
		this.editor = level2_unselected;
		this.email = level3_unselected;
		this.quit = zurueck_unselected;

		repaint();
	}

	public void rootMenu() {
		this.currentMenu = 0;

		this.lernspiel = lernspiel_unselected;
		this.editor = texteditor_unselected;
		this.email = email_unselected;
		this.tastenbelegung = tastenbelegung_unselected;
		this.quit = quit_unselected;

		repaint();
	}

	public void runApplication() {

		switch (currentMenu) {
		case 0:

			switch (menuCounter) {
			case 1:
				this.difficultyGame();
				this.drawNewPosition();
				System.out.println("starte Schwierigkeitsgrad ...");
				break;

			case 2:
				new Editor(root);
				System.out.println("starte Editor...");
				break;

			case 3:
				new Email("Email", root);
				System.out.println("starte E-Mailprogramm...");
				break;

			case 4: // Tastenbelegung
				this.currentMenu = 2;
				repaint();
				System.out.println("starte Tastenbelegung...");

				break;

			case 5:
				System.exit(EXIT_ON_CLOSE);
				break;

			default:
				System.out
						.println("ungueltige Tasteneingabe zur Bedienung des Menues!");
			}

			break;

		case 1:

			if (menuCounter <= 3) {
				new Lernspiel("Lernspiel", root, this.menuCounter);
				System.out.println("starte Lernspiel in Schwierigkeitsgrad "
						+ this.menuCounter + " ...");
			}

			this.currentMenu = 0;
			this.menuCounter = 1;
			this.rootMenu();
			this.drawNewPosition();

			break;

		case 2:

			this.currentMenu = 0;
			this.menuCounter = 1;
			this.rootMenu();
			this.drawNewPosition();

			System.out.println("zurück zum Hauptmenü...");

			break;

		default:
			System.out.println("ungültiges Menü!");
		}
	}

}
