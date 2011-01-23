package lernspiel;

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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fehler extends JFrame implements SwingConstants {
	
	/**
	 * 
	 */
	JLabel rounds = new JLabel("");
	JLabel errors = new JLabel("");
	JLabel stats = new JLabel("");
	boolean keypress;
	
	private static final long serialVersionUID = 2304903219655115269L;
	private RootMenu parent;

	public Fehler(String title, int runden, int fehler, double durchschnitt, RootMenu root) {
		super(title);
		parent = root;	
		
		
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
		
		rounds.setBounds(0, 0, 400, 300);
		rounds.setOpaque(true);
		rounds.setBackground(Color.WHITE);
		rounds.setFont(new Font("Arial", 255, 32));
		rounds.setHorizontalAlignment(CENTER);
		
		stats.setBounds(400, 0, 400, 400);
		stats.setOpaque(true);
		stats.setBackground(Color.WHITE);
		stats.setFont(new Font("Arial", 255, 40));
		stats.setHorizontalAlignment(CENTER);
		
		JButton button = new JButton("Hauptmen�");
		button.setBounds(340, 500, 120, 50);
        button.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				parent.setVisible(true);
				dispose();				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        
		cp.add(rounds);
		cp.add(stats);
		cp.add(button);
		
		rounds.setText("  Eingaben: " + runden + "\n \n davon falsch: " + fehler);
		stats.setText("Prozent richtig: " + Math.round(((runden-fehler)*100) / 100) + "%" + "\n \n durchschnittliche Antwortzeit: " + durchschnitt + " s");
		
		
		
	}

}
