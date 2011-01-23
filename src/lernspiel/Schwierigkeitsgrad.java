package lernspiel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Schwierigkeitsgrad extends JFrame 
	implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4715673826248226757L;
	private RootMenu parent;
	
	char mappedKey;

	public Schwierigkeitsgrad(RootMenu root){
		super();
		parent = root;
		
		parent.setVisible(false);
        parent.setFirstKeyPressedflag(false);
        parent.resetFlags();
        System.out.println("FirstKeyPressedflag gesetzt auf " + parent.isFirstKeyPressedflag());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addComponentsToPanel();
        //root.pack();

        this.setVisible(true);
	}
	

    public void addComponentsToPanel(){

    	JPanel panel = new JPanel();
    	
        JButton button = new JButton("Schwierigkeitsgrad 1 - 1seks");
        JButton button2 = new JButton("Schwierigkeitsgrad 2 - 0,5seks");
        JButton button3 = new JButton("Schwierigkeitsgrad 3 - 0,3seks");
        
        button.addKeyListener(this);
        button2.addKeyListener(this);
        button3.addKeyListener(this);

        panel.add(button);
        panel.add(button2);
        panel.add(button3);

        this.getContentPane().add(panel);

        this.setTitle("Lernspiel-Schwierigkeitsgrad");
        this.setSize(800, 600);

        this.setLocationRelativeTo(null);
        
    }


	@Override
	public void keyPressed(KeyEvent e) {
			
    	/** Handle the key pressed event. */
    	/*parent.setFlac(e, 1);

        //System.out.println("(RootM keyPressed) FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
		if (parent.isFirstKeyPressedflag() == false) {
			parent.setFirstKeyPressedflag(true);
	        //System.out.println("FirstKeyPressedflag gesetzt auf " + this.isFirstKeyPressedflag());
			//System.out.println("Eingabe beginn...");

			Timer timer = new Timer();
			timer.schedule(new MappedKey(parent, parent.getNumOfKeys(), parent.getNumOfPoss()), parent.getTypingTime());
				
		}*/
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
    	//parent.setFlac(e, 0);		
    	parent.setFlac(e, 1);
    	
		if(parent.isFirstKeyPressedflag() == false){
			System.out.println("Mapp(vor): " + parent.getMap());
			parent.keyReleased(e);
			
			if(parent.getMap() != 0)
				System.out.println("Mapp(nach): " + parent.getMap());
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getDefaultCloseOperation(){
		System.out.println("Hallo close");
		return 0;
	}
	
}
