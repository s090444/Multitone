package lernspiel;

import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This Window shows the stats reached in the game
 * @author Kevin Articus
 *
 */
public class Fehler extends JFrame implements SwingConstants {
	
	/**
	 * 
	 */
	JLabel rounds = new JLabel("");
	JLabel errors = new JLabel("");
	JLabel prozent = new JLabel("");
	JLabel stats = new JLabel("");
	JLabel hinweis = new JLabel("");
	boolean keypress;
	
	private static final long serialVersionUID = 2304903219655115269L;
	private RootMenu parent;

	/**
	 * initialize window
	 * @param title sets the window-title
	 * @param runden amount of rounds played
	 * @param fehler amount of mistakes done
	 * @param durchschnitt average reaction time
	 * @param root parent window
	 */
	public Fehler(String title, int runden, int fehler, double durchschnitt, RootMenu root) {
		super(title);
		parent = root;	
		
		/*
		 * initialize frame
		 */
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
	
		/*
		 * initialize components
		 */
		rounds.setBounds(0, 0, 400, 150);
		rounds.setOpaque(true);
		rounds.setBackground(Color.WHITE);
		rounds.setFont(new Font("Arial", 255, 32));
		rounds.setHorizontalAlignment(CENTER);
		
		prozent.setBounds(0, 300, 800, 150);
		prozent.setOpaque(true);
		prozent.setBackground(Color.WHITE);
		prozent.setFont(new Font("Arial", 255, 32));
		prozent.setHorizontalAlignment(CENTER);
		
		errors.setBounds(400, 0, 400, 150);
		errors.setOpaque(true);
		errors.setBackground(Color.WHITE);
		errors.setFont(new Font("Arial", 255, 32));
		errors.setHorizontalAlignment(CENTER);
		
		hinweis.setBounds(0,450,800,150);
		hinweis.setOpaque(true);
		hinweis.setBackground(Color.WHITE);
		hinweis.setForeground(Color.RED);
		hinweis.setFont(new Font("Arial",255,42));
		hinweis.setHorizontalAlignment(CENTER);
		
		stats.setBounds(0, 150, 800, 150);
		stats.setOpaque(true);
		stats.setBackground(Color.WHITE);
		stats.setFont(new Font("Arial", 255, 40));
		stats.setHorizontalAlignment(CENTER);
		
		
		stats.setFocusable(true);
		stats.requestFocus();
		
		/*
		 * set Listener
		 */
        stats.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
								
			}

			@Override
			public void keyTyped(KeyEvent e) {
			
				parent.setVisible(true);
				dispose();
			}
        	
        });
        
        /*
         * add components
         */
		cp.add(rounds);
		cp.add(errors);
		cp.add(prozent);
		cp.add(stats);
		cp.add(hinweis);
		
		/*
		 * set text of components
		 */
		Sound.playSound("clap");
		rounds.setText("  Eingaben: " + runden);
		errors.setText("davon falsch: " + fehler);
		stats.setText("durchschnittl. Antwortzeit: " + (Math.round(durchschnitt*100) / 100) + " s");
		if (runden !=0){
		prozent.setText("Prozent richtig: " + Math.round((((runden-fehler)*100)/runden) ) + "%");
		if (runden-fehler==0){
			Sound.playSound("incredible");
		}
		else{}
		}
		else{
			prozent.setText("keine Runde gespielt");	
		}
		
		hinweis.setText("Beliebige Taste zum Beenden drücken");
		
		
		
	}

}
