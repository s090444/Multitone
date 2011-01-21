/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lernspiel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

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
    

	/** Configuration */
	final int numOfKeys = 10;
	final int typingTime = 3000;	
	
	
	/** global variables*/
	private boolean firstKeyPressedflag;
	
	public boolean isFirstKeyPressedflag() {
		return firstKeyPressedflag;
	}

	public void setFirstKeyPressedflag(boolean firstKeyPressedflag) {
		this.firstKeyPressedflag = firstKeyPressedflag;
	}

	char map;
	int numOfPoss = 0;

	int flag[] = new int[numOfKeys];
	
	String s = new String();
	int mapping[][];


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
        this.mapping = readMapp.readMapping();
        System.out.println("Einlesen des Mappings. Erste Mapping[0][0] ist : " + this.mapping[0][0]);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

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
            }
        });
    }

    /*
     * GUI
     */
    
    public void addComponentsToPanel(){

        JPanel panel = new JPanel();

        JButton button = new JButton("SubWindow");
        button.addKeyListener(this);

        panel.add(button);

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
        System.out.println("RootWindow: " + e);
        
        //new Lernspiel("Lernspiel", root);
        //this.dispose();
        
        
    	/** Handle the key pressed event. */
    	this.setFlac(e, 1);

        //System.out.println("(RootM keyPressed) FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
		if (this.isFirstKeyPressedflag() == false) {
			this.setFirstKeyPressedflag(true);
	        System.out.println("FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
			System.out.println("Eingabe beginn...");

			Timer timer = new Timer();
			timer.schedule(new MappedKey(root, this.numOfKeys, this.numOfPoss), typingTime);
			
			System.out.println("MappedKey: " + map);			
		}

    	/** set flag if key is pressed */
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
    	
    	/** update flag if key is released */
    	this.setFlac(e, 0);
    	
        //System.out.println(e);	
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void setFlac(KeyEvent e, int flac){
    	
    	switch (e.getKeyChar()) {
		case 'q': flag[0] = flac; break;
		case 'w': flag[1] = flac; break;
		case 'e': flag[2] = flac; break;
		case 'r': flag[3] = flac; break;
		case 'v': flag[4] = flac; break;
		case 'n': flag[5] = flac; break;
		case 'u': flag[6] = flac; break;
		case 'i': flag[7] = flac; break;
		case 'o': flag[8] = flac; break;
		case 'p': flag[9] = flac; break;
		
		default:
			System.err.println("Ungültige Eingabe!");
		}
    }
    
    public void run(){
    	
    }

}