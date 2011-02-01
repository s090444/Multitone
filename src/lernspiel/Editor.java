package lernspiel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Timer;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

@SuppressWarnings("serial")
public class Editor extends JFrame implements KeyListener {

	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jEditorPane = null;
	private JToggleButton jToggleButtonBold = null;
	private JToggleButton jToggleButtonItalic = null;
	private JToggleButton jToggleButtonUnderline = null;
	private JToggleButton jToggleButtonRed = null;
	private JToggleButton jToggleButtonGreen = null;
	private JToggleButton jToggleButtonBlue = null;
	private JToggleButton jToggleButtonYellow = null;
	private JButton jButtonBigger = null;
	private JButton jButtonSmaller = null;
	private JButton jButtonSave = null;
	private JButton jButtonLoad = null;
	private JPanel jPanel1 = null;
	private JPanel jPanel = null;

	static RootMenu parent;

	private int fontSize = 12;

	// file the context of jEditorPane is safe/load to/from
	private File file = new File("Editor.save");  //  @jve:decl-index=0:

	// contain all buttons
	static AbstractButton[] buttonArray = new AbstractButton[11];

	/**
	 * This is the default constructor
	 */
	public Editor(RootMenu root) {
		super();

		// set RootMenu invisible
		parent = root;
		parent.setVisible(false);

		// initialize GUI
		initialize();

		pack();
		jEditorPane.requestFocusInWindow();

		setVisible(true);

		/*
		 * Close jFrame through ESC-Key set RootMenu visible again
		 */

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		Action escapceAction = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.setVisible(true);
				dispose();
			}
		};
		getJEditorPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				escape, "ESCAPE");
		getJEditorPane().getActionMap().put("ESCAPE", escapceAction);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		setSize(800, 600);

		// center window in middle of the screen
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);

		setContentPane(getJContentPane());
		setTitle("Editor");

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJToolBar(), BorderLayout.NORTH);
			jContentPane.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			int i = 0;

			/*
			 * initialize buttons add buttons to buttonArray
			 */

			jToolBar.add(getJToggleButtonBold());
			buttonArray[i++] = getJToggleButtonBold();
			jToolBar.add(getJToggleButtonItalic());
			buttonArray[i++] = getJToggleButtonItalic();
			jToolBar.add(getJToggleButtonUnderline());
			buttonArray[i++] = getJToggleButtonUnderline();
			jToolBar.add(getJToggleButtonRed());
			buttonArray[i++] = getJToggleButtonRed();
			jToolBar.add(getJToggleButtonGreen());
			buttonArray[i++] = getJToggleButtonGreen();
			jToolBar.add(getJToggleButtonBlue());
			buttonArray[i++] = getJToggleButtonBlue();
			jToolBar.add(getJToggleButtonYellow());
			buttonArray[i++] = getJToggleButtonYellow();
			jToolBar.add(getJButtonBigger());
			buttonArray[i++] = getJButtonBigger();
			jToolBar.add(getJButtonSmaller());
			buttonArray[i++] = getJButtonSmaller();
			jToolBar.add(getJButtonSave());
			buttonArray[i++] = getJButtonSave();
			jToolBar.add(getJButtonLoad());
			jToolBar.add(getJPanel1());
			jToolBar.add(getJPanel());
			buttonArray[i] = getJButtonLoad();
		}
		return jToolBar;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder(null);
			jScrollPane.setViewportView(getJEditorPane());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jEditorPane
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			jEditorPane.setContentType("text/rtf");
			jEditorPane.setPreferredSize(new Dimension(781, 527));
			jEditorPane.addKeyListener(this);
		}
		return jEditorPane;
	}

	public void keyPressed(KeyEvent e) {
	}

	// Handle keyTyped Event

	public void keyTyped(KeyEvent e) {

		// let pass events from Robot()
		if (parent.forward) {
			parent.forward = false;
			Sound.playSound((int) e.getKeyChar());
		} else {
			parent.setFlag(e, 1);
			if (parent.isFirstKeyPressedflag() == false) {
				parent.setFirstKeyPressedflag(true);
				Timer timer = new Timer();
				timer.schedule(new MappKeyCode(parent, parent.numOfKeys,
						parent.numOfPoss), parent.typingTime);
			}

			// consume events, don't let it pass to application
			e.consume();
		}
	}

	// Handle keyReleased Event
	public void keyReleased(KeyEvent e) {

		/*
		 * only set flag to 0 if, event isn't from Robot()
		 */
		if (parent.release) {
			parent.release = false;
			e.consume();
		} else {
			parent.setFlag(e, 0);
		}
	}

	/**
	 * This method initializes jToggleButton
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonBold() {
		if (jToggleButtonBold == null) {
			jToggleButtonBold = new JToggleButton();
			jToggleButtonBold.setText("Fett");

			// set bold action to button
			jToggleButtonBold.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Sound.playSound("Fett");
					new StyledEditorKit.BoldAction().actionPerformed(e);
				}
			});

		}
		return jToggleButtonBold;
	}

	/**
	 * This method initializes jToggleButton1
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonItalic() {
		if (jToggleButtonItalic == null) {
			jToggleButtonItalic = new JToggleButton();
			jToggleButtonItalic.setText("Kursiv");

			// set italic action to button
			jToggleButtonItalic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Sound.playSound("Kursiv");
					new StyledEditorKit.ItalicAction().actionPerformed(e);
				}
			});
		}
		return jToggleButtonItalic;
	}

	/**
	 * This method initializes jToggleButton2
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonUnderline() {
		if (jToggleButtonUnderline == null) {
			jToggleButtonUnderline = new JToggleButton();
			jToggleButtonUnderline.setText("Unterstrichen");

			// set underline action to button
			jToggleButtonUnderline.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Sound.playSound("Unterstrichen");
					new StyledEditorKit.UnderlineAction().actionPerformed(e);
				}
			});
		}
		return jToggleButtonUnderline;
	}

	// unselect all color buttons except of invoking button
	private void unselectColorButton(int k) {
		for (int i = 3; i < 7; i++) {
			if (buttonArray[i].isSelected() && i != k) {
				buttonArray[i].setSelected(false);
			}
		}
	}

	// unselect all buttons
	private void unselectAllButton() {
		for (int i = 0; i < 11; i++) {
			buttonArray[i].setSelected(false);
		}
	}

	/**
	 * This method initializes jToggleButton3
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonRed() {
		if (jToggleButtonRed == null) {
			jToggleButtonRed = new JToggleButton();
			jToggleButtonRed.setText("Rot");
			
			//set red color action to button
			jToggleButtonRed.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (jToggleButtonRed.isSelected()) {
						
						//unselect all other color buttons
						unselectColorButton(3);
						Sound.playSound("Rot");
						new StyledEditorKit.ForegroundAction("Red", Color.red)
								.actionPerformed(arg0);
					} else {
						Sound.playSound("Schwarz");
						
						//set color back to black when unselect button
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}

			});
		}
		return jToggleButtonRed;
	}

	/**
	 * This method initializes jToggleButton4
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonGreen() {
		if (jToggleButtonGreen == null) {
			jToggleButtonGreen = new JToggleButton();
			jToggleButtonGreen.setText("Grün");
			
			//set green color action to button
			jToggleButtonGreen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (jToggleButtonGreen.isSelected()) {
						
						//unselect all other color buttons
						unselectColorButton(4);
						Sound.playSound("Gruen");
						new StyledEditorKit.ForegroundAction("Green",
								Color.green).actionPerformed(arg0);
					} else {
						Sound.playSound("Schwarz");
						//set color back to black when unselect button
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}
			});
		}
		return jToggleButtonGreen;
	}

	/**
	 * This method initializes jToggleButton5
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonBlue() {
		if (jToggleButtonBlue == null) {
			jToggleButtonBlue = new JToggleButton();
			jToggleButtonBlue.setText("Blau");
			
			//set blue color action to button
			jToggleButtonBlue.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (jToggleButtonBlue.isSelected()) {
						
						//unselect all other color buttons
						unselectColorButton(5);
						Sound.playSound("Blau");
						new StyledEditorKit.ForegroundAction("Blue", Color.blue)
								.actionPerformed(arg0);
					} else {
						Sound.playSound("Schwarz");
						//set color back to black when unselect button
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}
			});
		}
		return jToggleButtonBlue;
	}

	/**
	 * This method initializes jToggleButton6
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButtonYellow() {
		if (jToggleButtonYellow == null) {
			jToggleButtonYellow = new JToggleButton();
			jToggleButtonYellow.setText("Gelb");
			
			//set yellow color action to button
			jToggleButtonYellow.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (jToggleButtonYellow.isSelected()) {
						
						//unselect all other color button
						unselectColorButton(6);
						Sound.playSound("Gelb");
						new StyledEditorKit.ForegroundAction("Yellow",
								Color.yellow).actionPerformed(arg0);
					} else {
						Sound.playSound("Schwarz");
						
						//set color back to black when unselect button
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}
			});
		}
		return jToggleButtonYellow;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonBigger() {
		if (jButtonBigger == null) {
			jButtonBigger = new JButton();
			jButtonBigger.setText("Größer");
			
			//set font size bigger action to button
			jButtonBigger.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Sound.playSound("Groesser");
					
					//increment font size by 5 up to 27
					if (fontSize <= 22)
						fontSize += 5;
					new StyledEditorKit.FontSizeAction("Bigger", fontSize)
							.actionPerformed(e);
				}
			});
		}
		return jButtonBigger;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSmaller() {
		if (jButtonSmaller == null) {
			jButtonSmaller = new JButton();
			jButtonSmaller.setText("Kleiner");
			
			//set font size smaller action to button
			jButtonSmaller.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Sound.playSound("Kleiner");
					
					//decrement font size by 5 down to 7
					if (fontSize >= 12)
						fontSize -= 5;
					new StyledEditorKit.FontSizeAction("Smaller", fontSize)
							.actionPerformed(e);
				}
			});

		}
		return jButtonSmaller;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSave() {
		if (jButtonSave == null) {
			jButtonSave = new JButton();
			jButtonSave.setText("Speichern");
			
			//set save action to button
			jButtonSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						
						//open output stream
						OutputStream os = new BufferedOutputStream(
								new FileOutputStream(file));

						Document doc = jEditorPane.getDocument();
						int length = doc.getLength();
						
						//write to output stream
						jEditorPane.getEditorKit().write(os, doc, 0, length);
						os.close();
						Sound.playSound("Speichern");
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (BadLocationException e3) {
						e3.printStackTrace();
					}

				}
			});
		}
		return jButtonSave;
	}

	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonLoad() {
		if (jButtonLoad == null) {
			jButtonLoad = new JButton();
			jButtonLoad.setText("Laden");
			
			//set load action to button
			jButtonLoad.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					try {
						//open input stream
						FileInputStream fis = new FileInputStream(file);
						
						//clear jEditorPane
						jEditorPane.setText("");
						
						//read from file
						jEditorPane.getEditorKit().read(fis,
								jEditorPane.getDocument(), 0);
						fis.close();
						
						//set caret position to end of text
						jEditorPane.setCaretPosition(jEditorPane.getDocument()
								.getLength() - 1);

						Sound.playSound("Laden");
						
						/*
						 * reset formatting (new text is unformatted)
						 */
						SimpleAttributeSet sas = new SimpleAttributeSet();
						StyleConstants.setBold(sas, false);
						StyleConstants.setUnderline(sas, false);
						StyleConstants.setItalic(sas, false);
						StyleConstants.setForeground(sas, Color.black);
						unselectAllButton();
						jEditorPane.getDocument().insertString(
								jEditorPane.getDocument().getLength() - 1, " ",
								sas);

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonLoad;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {

			jPanel1 = new JPanel();
			final JLabel moveDownDescription = new JLabel("Exit: ");
			jPanel1.setPreferredSize(new Dimension(35, 28));
			jPanel1.add(moveDownDescription, moveDownDescription.getName());
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel
	 * Contains mapping pictures for exit	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				String directory = "src/Pics/";
				Image key_unselected = Toolkit.getDefaultToolkit().getImage(
						directory + "key_unselected.png");
				Image key_selected = Toolkit.getDefaultToolkit().getImage(
						directory + "key_selected.png");
				
				g.drawImage(key_unselected, 5, 5, 20, 20, this);
				g.drawImage(key_unselected, 25, 5, 20, 20, this);
				g.drawImage(key_unselected, 45, 5, 20, 20, this);
				g.drawImage(key_unselected, 65, 5, 20, 20, this);
				g.drawImage(key_unselected, 85, 5, 20, 20, this);
				
				g.drawImage(key_selected, 110, 5, 20, 20, this);
				g.drawImage(key_unselected, 130, 5, 20, 20, this);
				g.drawImage(key_unselected, 150, 5, 20, 20, this);
				g.drawImage(key_unselected, 170, 5, 20, 20, this);
				g.drawImage(key_selected, 190, 5, 20, 20, this);
	
			}
			};
			
			jPanel.setPreferredSize(new Dimension(220, 28));
			jPanel.setBounds(600, 1, 220, 28);
		}
		return jPanel;
	}


	
	

} 
