/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clemensNeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;


/**
 *
 * @author sakura
 */
public class RootMenu extends JFrame
        implements KeyListener{

    private static final long serialVersionUID = 7465416001464648515L;    
    static RootMenu root;
    String directory = "src/Pics/";
    Image image = null;
    

	/** Configuration */
    
    /*
     * Typing_time
     */
    
	final int typingTime = 1000;	

	public int getTypingTime() {
		return typingTime;
	}
	
	/*
	 * numOfKeys
	 */
	
	final int numOfKeys = 13;

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

        
        String curDir = System.getProperty("user.dir");

        System.out.println("current Directory: " + curDir);
        image = Toolkit.getDefaultToolkit().createImage(directory + "test.png");
        System.out.println(image.getWidth(this));
        
        
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
                Editor e = new Editor(root);
            }
        });
    }

    /*
     * GUI
     */
    
    public void addComponentsToPanel(){

        JPanel panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //System.out.println(image);
                g.drawRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setBackground(Color.WHITE);
        
        JButton button = new JButton("SubWindow");
        button.addKeyListener(this);

        JLabel label = new JLabel("Starten des Lernspiels mit q,w,e");
        
        panel.add(button);
        panel.add(label);

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
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {

    	/** Handle the Released keys event. */
    	this.setFlac(e, 1);

        //System.out.println("(RootM keyPressed) FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
		if (this.isFirstKeyPressedflag() == false) {
			this.setFirstKeyPressedflag(true);
	        //System.out.println("FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
			//System.out.println("Eingabe beginn...");

			Timer timer = new Timer();
			timer.schedule(new MappedKey(root, this.numOfKeys, this.numOfPoss), typingTime);
			
		}

        //System.out.println(e);	
        //throw new UnsupportedOperationException("Not supported yet.");
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
			System.out.println("flag: " + flac);
		}
    }
    
    public void resetFlags(){
    	
    	//this.setMap('~');
    	
    	for(int i = 0; i < this.getNumOfKeys(); i++)
    		this.setFlag(i, 0);
    	
    	System.out.println("Flags zurueckgesetzt...");
    }

    public void runApplication(char mappedChar){
    	switch(mappedChar){
    		//case 'h' : new Schwierigkeitsgrad(root); break;
//    		case 'i' : new Lernspiel("Lernspiel", root, 1000); break;
    		case 'i' : new Editor(root); break;
    		default : System.out.println("ungueltige Tasteneingabe zur Bedienung des Menues!"); 
    	}
    }

    @Override
    public void paint(Graphics g){
            super.paint(g);
            g.drawImage(image, 0, 0, null);
    }
}