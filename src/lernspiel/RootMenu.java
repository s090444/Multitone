/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lernspiel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JFrame;


/**
 *
 * @author sakura
 */
public class RootMenu extends JFrame
        implements KeyListener {

    private static final long serialVersionUID = 7465416001464648515L;    
    static RootMenu root;
    String directory = "src/Pics/";
    
    Image unselected = Toolkit.getDefaultToolkit().getImage(directory + "menu_unselected.png");
    Image selected = Toolkit.getDefaultToolkit().getImage(directory + "menu_selected.png");
    
    Image background = Toolkit.getDefaultToolkit().getImage(directory + "background.JPG");
    
    Image lernspiel;
    Image editor;
    Image email;
    Image quit;
    
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    
    int menuCounter = 0;
    
    int currentMenu = 0;
    JPanel panel;

	/** Configuration */
    
    /*
     * Typing_time
     */
    
	final int typingTime = 50;	

	public int getTypingTime() {
		return typingTime;
	}
	
	/*
	 * numOfKeys
	 */
	
	final int numOfKeys = 10;

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
	
	
	//forward
	private boolean forward=false;
	

	public boolean isForward() {
		return forward;
	}

	public void setForward(boolean forward) {
		this.forward = forward;
	}
	
	//keyCode
	private int keyCode=0;
	

	public int getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	/*
     * Constructor
     */
    public RootMenu(){
        super();

        this.currentMenu = 0;
        System.out.println("Initializiere RootMenu...");
        
        this.setFirstKeyPressedflag(false);
        System.out.println("FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
        
        ReadNumOfPoss readNum = new ReadNumOfPoss();
        this.numOfPoss = readNum.readNumOfPoss();        
        System.out.println("Read numbers of Possibilities (out of file): " + this.numOfPoss);
        
        ReadMapping readMapp = new ReadMapping(this.numOfKeys, this.numOfPoss);
        this.setMapping(readMapp.readMapping());
        System.out.println("Einlesen des Mappings. Erste Mapping[0][0] ist : " + this.mapping[0][0]);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        
        //String curDir = System.getProperty("user.dir");
        //System.out.println("current Directory: " + curDir);
        
        this.resetMenuButtons();
        this.rootMenu();
        
        
        this.addComponentsToPanel();
        //root.pack();

        this.setVisible(true);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                root = new RootMenu();
               // Editor e = new Editor(root);
            }
        });
    }

    /*
     * GUI
     */
    
    public void addComponentsToPanel(){

        panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, this);                
                g.drawImage(image1, 100, 20, 45, 45, this);           
                g.drawImage(image2, 100, 70, 50, 50, this);           
                g.drawImage(image3, 100, 120, 50, 50, this);           
                g.drawImage(image4, 100, 170, 50, 50, this);      
                
                g.drawImage(lernspiel, 150, 20, this);           
                g.drawImage(editor, 150, 70, this);           
                g.drawImage(email, 150, 120, this);           
                g.drawImage(quit, 150, 170, this);
            }
        };

        panel.setBackground(Color.WHITE);
        
  /*      JButton button = new JButton("SubWindow");
        button.addKeyListener(this);

        JLabel label = new JLabel("Starten des Lernspiels mit q,w,e");        
        
        panel.add(button);
        panel.add(label);*/

        System.out.println("panel focusable: " + panel.isFocusable());
        if(panel.isFocusable()){
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
        //System.out.println(e);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
        //System.out.println("RootWindow: " + e);
    	this.setFlac(e, 1);
    	
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {

    	/** Handle the Released keys event. */
    	
        //System.out.println("(RootM keyPressed) FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
		if (this.isFirstKeyPressedflag() == false) {
			this.setFirstKeyPressedflag(true);
	    	
	        //System.out.println("FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
			//System.out.println("Eingabe beginn...");

			Timer timer = new Timer();
			timer.schedule(new MappedKey(root, ""), typingTime);
			
		}

        //System.out.println(e);	
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public void newKeyPush(char newKey){
    	switch (newKey){
    		case 'y' : this.moveMenuPoint(); break;
    		case 'o' : this.runApplication(); break;
    		
    		default : System.out.println("ungültige Menüeingabe!!");
    	}
    }
    
    
    public void setFlac(KeyEvent e, int flac){
    	//System.out.println("keyChar: " + e.getKeyChar());
    	
    	switch (e.getKeyChar()) {
		case 'q': this.setFlag(0, flac); break;
		case 'w': this.setFlag(1, flac); break;
		case 'e': this.setFlag(2, flac); break;
		case 'r': this.setFlag(3, flac); break;
		case 'v': this.setFlag(4, flac); break;
		case 'n': this.setFlag(5, flac); break;
		case 'u': this.setFlag(6, flac); break;
		case 'i': this.setFlag(7, flac); break;
		case 'o': this.setFlag(8, flac); break;
		case 'p': this.setFlag(9, flac); break;
		
		default:
			System.err.println("Ungültige Eingabe!");
		}
    }
    
    /*
     * bewegt die Menüpunkte farblich durch, indem es zu erst alle Menüpunkte
     * reseted, anschließend, anhand der menuCounter-Variable, den aktuellen Menüpunkt setzt
     * und den menuCounter nach oben zählt. 
     */
    public void moveMenuPoint(){

       	/*
       	 * sollte das Menü am Ende angekommen sein, wird der Zähler reseted und der 1. Menüpunkt
       	 * erscheint wieder (nach dem 4).
       	 */
       	menuCounter++;
       	if(menuCounter > 4)
       		menuCounter = 1;
       	
    	// setzt alle Bilder im Menü wieder auf "unselected"
    	this.resetMenuButtons();
    	
    	// wählt den aktuellen Menüpunkt auf "selected" anhand von menuCounter-Variable
    	switch (menuCounter) {
    		case 1 : this.image1 = this.selected; break;
    		case 2 : this.image2 = this.selected; break;
    		case 3 : this.image3 = this.selected; break;
    		case 4 : this.image4 = this.selected; break;
    		
    		default : System.out.println("Fehler Menüführung!");
    	}
    	
       	repaint();
    	System.out.println("currentMenu: " + currentMenu + " menuCounter: " + menuCounter);
       	
    }
    
    public void resetFlags(){
    	
    	for(int i = 0; i < this.getNumOfKeys(); i++)
    		this.setFlag(i, 0);
    	
    	System.out.println("Flags zurueckgesetzt...");
    }

    public void resetMenuButtons(){
    	image1 = unselected;
    	image2 = unselected;
    	image3 = unselected;
    	image4 = unselected;
    }
    
    public void runApplication(){
    	if( (currentMenu == 1) && (menuCounter <= 3) ){
			new Lernspiel("Lernspiel", root, this.menuCounter);
			System.out.println("starte Lernspiel in Schwierigkeitsgrad " + this.menuCounter + " ...");
			this.currentMenu = 0;
			this.menuCounter = 0;
			this.resetMenuButtons();
			this.rootMenu();
    	} else {
	    	switch(menuCounter){
	    		case 1 : 	this.difficultyGame();
	        				System.out.println("starte Schwierigkeitsgrad ...");
	    			break;
	    			
	    		case 2 : 	//new Lernspiel("Lernspiel", root, 1000); 
							System.out.println("starte Editor....");
					break;
					
	    		case 3 : 	//new Editor(root); 
							System.out.println("starte E-Mailprogramm....");
					break;
	    		
	    		case 4 : 	if(currentMenu == 0){
	    						System.exit(EXIT_ON_CLOSE);
							} else {
								this.rootMenu();
								System.out.println("Zurück zum Hauptmenü ...");
							}    			
	    			break;
	    		
	    		default : System.out.println("ungueltige Tasteneingabe zur Bedienung des Menues!"); 
	    	}
    	}
    }
    
    public void difficultyGame(){
    	this.currentMenu = 1;
    	
    	this.lernspiel = Toolkit.getDefaultToolkit().getImage(directory + "schwierigkeitsgrad1.png");
    	this.editor = Toolkit.getDefaultToolkit().getImage(directory + "schwierigkeitsgrad2.png");
    	this.email = Toolkit.getDefaultToolkit().getImage(directory + "schwierigkeitsgrad3.png");
    	this.quit = Toolkit.getDefaultToolkit().getImage(directory + "zurueck.png");
        
        repaint();
    }
    
    public void rootMenu(){
    	this.currentMenu = 0;

    	this.lernspiel = Toolkit.getDefaultToolkit().getImage(directory + "lernspiel.png");
    	this.editor = Toolkit.getDefaultToolkit().getImage(directory + "editor.png");
        this.email = Toolkit.getDefaultToolkit().getImage(directory + "email.png");
        this.quit = Toolkit.getDefaultToolkit().getImage(directory + "quit.png");
        
        repaint();
    }
}