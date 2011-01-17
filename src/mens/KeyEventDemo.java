package mens;
 
/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * KeyEventDemo
 */

/*
 * version 0.5 test
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;


@SuppressWarnings({ "serial", "unused" })
public class KeyEventDemo extends JFrame implements KeyListener, ActionListener {
	JTextArea displayArea;
	static final String newline = System.getProperty("line.separator");

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		// Schedule a job for event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		KeyEventDemo frame = new KeyEventDemo("KeyEventDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the content pane.
		frame.addComponentsToPane();

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	private void addComponentsToPane() {

		JButton button = new JButton("Clear");
		button.addActionListener(this);

		// typingArea = new JTextField(20);
		// typingArea.addKeyListener(this);

		// Uncomment this if you wish to turn off focus
		// traversal. The focus subsystem consumes
		// focus traversal keys, such as Tab and Shift Tab.
		// If you uncomment the following line of code, this
		// disables focus traversal and the Tab events will
		// become available to the key event listener.
		// typingArea.setFocusTraversalKeysEnabled(false);

		displayArea = new JTextArea();
		displayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(displayArea);
		scrollPane.setPreferredSize(new Dimension(375, 125));
		displayArea.addKeyListener(this);

		// getContentPane().add(typingArea, BorderLayout.PAGE_START);
		getContentPane().add(scrollPane, BorderLayout.PAGE_START);
		getContentPane().add(button, BorderLayout.PAGE_END);
	}

	public KeyEventDemo(String name) {
		super(name);
	}

	/** Configuration */
	final static int numOfKeys = 3;
	final static int TYPING_TIME = 500;
	
	/** global variables*/
	final static int numOfPoss = (int) Math.pow(2, numOfKeys) - 1;
	static boolean firstKeyPressedflag = false;
	static int flag[] = new int[numOfKeys];
	static Timer timer = new Timer();
	static String s = new String();
	static int mapping[][] = readMapping();
	

	/** read mapping out of txt file, return it within an integer-array */
	
	public static int[][] readMapping() {
		int mapping[][] = new int[numOfKeys + 1][numOfPoss];
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile("Mapping.txt", "r");
			for (int i = 0; i < numOfPoss; i++) {
				for (String line; (line = f.readLine()) != null;) {
					for (int k = 0; k < numOfKeys + 1; k++) {
						int index = line.indexOf(';');
						mapping[k][i] = Integer.parseInt(line.substring(0,
								index));
						line = line.substring(index + 1);
					}
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return mapping;
	}


	
	/** check typed keys against mapping-array and return mapped char */
	public char mapp() {
		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys + 1; k++) {
				if (k == 3)
					return (char) mapping[k][i];
				else if (mapping[k][i] != flag[k])
					break;
			}
		}
		return 0;
	}

	/** Handle the key typed event. */
	public void keyTyped(KeyEvent e) {
		
	}

	/** Handle the key pressed event. */
	public void keyPressed(KeyEvent e) {
		pressedKey(e);
		if (firstKeyPressedflag == false) {
			firstKeyPressedflag = true;
			timer.schedule(new displayFlags(), TYPING_TIME);
		}
	}

	/** Handle the key released event. */
	public void keyReleased(KeyEvent e) {
		releasedKey(e);
	}

	/** set flag if key is pressed */
	public void pressedKey(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a':
			flag[0] = 1;
			break;
		case 's':
			flag[1] = 1;
			break;
		case 'd':
			flag[2] = 1;
			break;
		// case 'f':
		// flag[3]=1;
		// break;
		// case 'j':
		// flag[4]=1;
		// break;
		default:
			System.err.println("Ung�ltige Eingabe!");
		}
	}

	/** update flag if key is released */
	public void releasedKey(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a':
			flag[0] = 0;
			break;
		case 's':
			flag[1] = 0;
			break;
		case 'd':
			flag[2] = 0;
			break;
		// case 'f':
		// flag[3]=0;
		// break;
		// case 'j':
		// flag[4]=0;
		// break;
		default:
			System.err.println("Ung�ltige Eingabe!");
		}
	}
	
	
	class displayFlags extends TimerTask {
		public void run() {
			firstKeyPressedflag = false;
			char map = mapp();
			if (map != 0)
				s = s + map;
			displayArea.setText("Mapping: " + s);
		}
	}

	/** Handle the button click. */
	public void actionPerformed(ActionEvent e) {
		// Clear the text components.
		displayArea.setText("");
		s = "";
	}
}
