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
	// JTextField typingArea;
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
int numOfKeys = 3;
int numOfPoss = (int) Math.pow(2, numOfKeys)-1;
	// read mapping out of txt file, return it within an integer-array
	public int[][] readMapping() {

		int mapping[][] = new int[numOfKeys+1][numOfPoss];
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile("Mapping.txt", "r");

			for (int i = 0; i < numOfPoss; i++) {
				for (String line; (line = f.readLine()) != null;) {
//					System.out.println("Line: " + line);
					for (int k = 0; k < numOfKeys+1; k++) {
						int index = line.indexOf(';');
						mapping[k][i] = Integer.parseInt(line.substring(0,
								index));
//						System.out.println("k: " + k);
//						System.out.println("i: " + i);
//						System.out.println("mapping[k][i]: " + mapping[k][i]);
						line = line.substring(index + 1);
//						System.out.println("subline: " + line);
					}
					break;
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
//		for(int i=0; i<3; i++){
//			for (int k=0; k<4; k++){
//				System.out.print(mapping[k][i]);
//			}
//			System.out.println();
//		}
		return mapping;
	}

	public char mapp() {
		int mapping[][] = readMapping();
		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys+1; k++) {
				if (k == 3)
					return (char)mapping[k][i];
//				System.out.println("k: " + k);
//				System.out.println("i: " + i);
//				System.out.println("mapping[k][i]: " + mapping[k][i]);
//				System.out.println("flag[k]: " + flag[k]);
				else if (mapping[k][i] != flag[k])
					break;
			}
		}
		return 0;

	}

	// global flags
	// byte flag1 = 0;
	// byte flag2 = 0;
	// byte flag3 = 0;
	// byte flag4 = 0;
	// byte flag5 = 0;
	// byte flag6 = 0;

	int flag[] = new int[3];

	/** Handle the key typed event from the text field. */
	public void keyTyped(KeyEvent e) {
		// displayInfo(e, "KEY TYPED: ");
	}

	Timer timer = new Timer();
	final static int TYPING_TIME = 500;
	boolean firstKeyPressedflag = false;
	
	/** Handle the key pressed event from the text field. */
	public void keyPressed(KeyEvent e) {
		pressedKey(e);
//		readMapping();
		if(firstKeyPressedflag==false){
		firstKeyPressedflag = true;	
			
			timer.schedule(new displayFlags(),TYPING_TIME);
		}
		

//		timer.schedule(new displayFlags(), 100);
//		System.out.println(timer);

		// displayFlags();
		// displayInfo(e, "KEY PRESSED: ");
	}

	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {
		releasedKey(e);
		// displayFlags();
		// displayInfo(e, "KEY RELEASED: ");
	}

	// set flag if key is pressed
	public void pressedKey(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a':
			flag[0] = 1;
			// flag1 = 1;
			break;
		case 's':
			flag[1] = 1;
			// flag2 = 1;
			break;
		case 'd':
			flag[2] = 1;
			// flag3 = 1;
			break;
		// case 'f':
		// flag4 = 1;
		// break;
		// case 'j':
		// flag5 = 1;
		// break;
		// case 'k':
		// flag6 = 1;
		// break;
		default:
			System.err.println("Ungültige Eingabe!");
		}
	}

	// update flag if key is released
	public void releasedKey(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'a':
			flag[0] = 0;
			// flag1 = 0;
			break;
		case 's':
			flag[1] = 0;
			// flag2 = 0;
			break;
		case 'd':
			flag[2] = 0;
			// flag3 = 0;
			break;
		// case 'f':
		// flag4 = 0;
		// break;
		// case 'j':
		// flag5 = 0;
		// break;
		// case 'k':
		// flag6 = 0;
		// break;
		default:
			System.err.println("Ungültige Eingabe!");
		}
	}

	String s = new String();
	class displayFlags extends TimerTask {
		public void run() {
			firstKeyPressedflag=false;	
				char map = mapp();	
			if(map!=0)
			s = s+map;
			
			// displayArea.setText("Flag1: " + flag1 + newline + "Flag2: " +
			// flag2
			// + newline + "Flag3: " + flag3 + newline + "Flag4: " + flag4
			// + newline + "Flag5: " + flag5 + newline + "flag6: " + flag6
			// + newline);
			// displayArea.setText("Flag1: " + flag[0] + newline + "Flag2: " +
			// flag[1] + newline + "Flag3: " + flag[2] );
			displayArea.setText("Mapping: " +s);

			// displayArea.setText("");
			// typingArea.setText("");
		}
	}

	/** Handle the button click. */
	public void actionPerformed(ActionEvent e) {
		// Clear the text components.
		displayArea.setText("");
		s="";
		// typingArea.setText("");

		// Return the focus to the typing area.
		// typingArea.requestFocusInWindow();
	}

	/*
	 * We have to jump through some hoops to avoid trying to print non-printing
	 * characters such as Shift. (Not only do they not print, but if you put
	 * them in a String, the characters afterward won't show up in the text
	 * area.)
	 */
	// private void displayInfo(KeyEvent e, String keyStatus) {
	//
	// // You should only rely on the key char if the event
	// // is a key typed event.
	// int id = e.getID();
	// String keyString;
	// if (id == KeyEvent.KEY_TYPED) {
	// char c = e.getKeyChar();
	// keyString = "key character = '" + c + "'";
	// } else {
	// int keyCode = e.getKeyCode();
	// keyString = "key code = " + keyCode + " ("
	// + KeyEvent.getKeyText(keyCode) + ")";
	// }
	//
	// int modifiersEx = e.getModifiersEx();
	// String modString = "extended modifiers = " + modifiersEx;
	// String tmpString = KeyEvent.getModifiersExText(modifiersEx);
	// if (tmpString.length() > 0) {
	// modString += " (" + tmpString + ")";
	// } else {
	// modString += " (no extended modifiers)";
	// }
	//
	// String actionString = "action key? ";
	// if (e.isActionKey()) {
	// actionString += "YES";
	// } else {
	// actionString += "NO";
	// }
	//
	// String locationString = "key location: ";
	// int location = e.getKeyLocation();
	// if (location == KeyEvent.KEY_LOCATION_STANDARD) {
	// locationString += "standard";
	// } else if (location == KeyEvent.KEY_LOCATION_LEFT) {
	// locationString += "left";
	// } else if (location == KeyEvent.KEY_LOCATION_RIGHT) {
	// locationString += "right";
	// } else if (location == KeyEvent.KEY_LOCATION_NUMPAD) {
	// locationString += "numpad";
	// } else { // (location == KeyEvent.KEY_LOCATION_UNKNOWN)
	// locationString += "unknown";
	// }
	//
	// displayArea.append(keyStatus + newline + "    " + keyString + newline
	// + "    " + modString + newline + "    " + actionString
	// + newline + "    " + locationString + newline);
	// displayArea.setCaretPosition(displayArea.getDocument().getLength());
	// }
}
