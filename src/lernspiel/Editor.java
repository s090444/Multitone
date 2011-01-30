package lernspiel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.Timer;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.rtf.RTFEditorKit;

public class Editor extends JFrame implements KeyListener, Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jEditorPane = null;
	private JToggleButton jToggleButton = null;
	private JToggleButton jToggleButton1 = null;
	private JToggleButton jToggleButton2 = null;
	private JToggleButton jToggleButton3 = null;
	private JToggleButton jToggleButton4 = null;
	private JToggleButton jToggleButton5 = null;
	private JToggleButton jToggleButton6 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	private JButton jButton2 = null;
	private JButton jButton3 = null;

	static RootMenu parent;

	private int fontSize = 12;
	private File file = new File("Editor.save"); // @jve:decl-index=0:
	private AbstractButton[] buttonArray = new AbstractButton[12];
	public static boolean forward = false;
	public static boolean release = false;

	public AbstractButton[] getButtonArray() {
		return buttonArray;
	}

	public void setButtonArray(AbstractButton[] aButton) {
		this.buttonArray = aButton;
	}

	/**
	 * This is the default constructor
	 */
	public Editor(RootMenu root) {
		super();
		parent = root;
		parent.setVisible(false);
		initialize();
		pack();
		jEditorPane.setFocusable(true);
		jEditorPane.requestFocusInWindow();

		this.setVisible(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("Editor");

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
			int i = 0;
			jToolBar.add(getJToggleButton());
			buttonArray[i++] = getJToggleButton();
			jToolBar.add(getJToggleButton1());
			buttonArray[i++] = getJToggleButton1();
			jToolBar.add(getJToggleButton2());
			buttonArray[i++] = getJToggleButton2();
			jToolBar.add(getJToggleButton3());
			buttonArray[i++] = getJToggleButton3();
			jToolBar.add(getJToggleButton4());
			buttonArray[i++] = getJToggleButton4();
			jToolBar.add(getJToggleButton5());
			buttonArray[i++] = getJToggleButton5();
			jToolBar.add(getJToggleButton6());
			buttonArray[i++] = getJToggleButton6();
			jToolBar.add(getJButton());
			buttonArray[i++] = getJButton();
			jToolBar.add(getJButton1());
			buttonArray[i++] = getJButton1();
			jToolBar.add(getJButton2());
			buttonArray[i++] = getJButton2();
			jToolBar.add(getJButton3());
			buttonArray[i] = getJButton3();
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
		if (parent.forward) {
//			parent.forward = false;
			System.out.println(e.getKeyCode());
		}
//			e.consume();
	}

	// Handle keyTyped Event

	public void keyTyped(KeyEvent e) {

		// let pass events from Robot()
		if (parent.forward) {
			parent.forward = false;
			Sound.playSound((int) e.getKeyChar());
		} else {
			parent.setFlac(e, 1);
			if (parent.isFirstKeyPressedflag() == false) {
				parent.setFirstKeyPressedflag(true);
				Timer timer = new Timer();
				timer.schedule(new MappKeyCode(parent, this, parent.numOfKeys,
						parent.numOfPoss), parent.typingTime+200);
			}
			// consume event, don't let it pass to application
			e.consume();
		}
	}

	// Handle keyReleased Event
	public void keyReleased(KeyEvent e) {
		if (parent.release) {
			parent.release = false;
			e.consume();
		} else {
			parent.setFlac(e, 0);
		}
	}

	/**
	 * This method initializes jToggleButton
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton() {
		if (jToggleButton == null) {
			jToggleButton = new JToggleButton();
			jToggleButton.setText("Fett");
			jToggleButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Sound.playSound("Fett");
					new StyledEditorKit.BoldAction().actionPerformed(e);

				}
			});

		}
		return jToggleButton;
	}

	/**
	 * This method initializes jToggleButton1
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton1() {
		if (jToggleButton1 == null) {
			jToggleButton1 = new JToggleButton();
			jToggleButton1.setText("Kursiv");

			jToggleButton1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Sound.playSound("Kursiv");
					new StyledEditorKit.ItalicAction().actionPerformed(e);
				}
			});
		}
		return jToggleButton1;
	}

	/**
	 * This method initializes jToggleButton2
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton2() {
		if (jToggleButton2 == null) {
			jToggleButton2 = new JToggleButton();
			jToggleButton2.setText("Unterstrichen");
			jToggleButton2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Sound.playSound("Unterstrichen");
					new StyledEditorKit.UnderlineAction().actionPerformed(e);
				}
			});
		}
		return jToggleButton2;
	}

	// unselect all Buttons except of invoking Button
	private void unselectColorButton(int k) {
		for (int i = 3; i < 7; i++) {
			if (buttonArray[i].isSelected() && i != k) {
				buttonArray[i].setSelected(false);
			}
		}
	}

	/**
	 * This method initializes jToggleButton3
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton3() {
		if (jToggleButton3 == null) {
			jToggleButton3 = new JToggleButton();
			jToggleButton3.setText("Rot");
			jToggleButton3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (jToggleButton3.isSelected()) {
						unselectColorButton(3);
						Sound.playSound("Rot");
						new StyledEditorKit.ForegroundAction("Red", Color.red)
								.actionPerformed(arg0);
					} else{
						Sound.playSound("Schwarz");
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}
					
			});
		}
		return jToggleButton3;
	}

	/**
	 * This method initializes jToggleButton4
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton4() {
		if (jToggleButton4 == null) {
			jToggleButton4 = new JToggleButton();
			jToggleButton4.setText("Grün");
			jToggleButton4.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (jToggleButton4.isSelected()) {
						unselectColorButton(4);
						Sound.playSound("Gruen");
						new StyledEditorKit.ForegroundAction("Green",
								Color.green).actionPerformed(arg0);
					} else{
						Sound.playSound("Schwarz");
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}
			});
		}
		return jToggleButton4;
	}

	/**
	 * This method initializes jToggleButton5
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton5() {
		if (jToggleButton5 == null) {
			jToggleButton5 = new JToggleButton();
			jToggleButton5.setText("Blau");
			jToggleButton5.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (jToggleButton5.isSelected()) {
						unselectColorButton(5);
						Sound.playSound("Blau");
						new StyledEditorKit.ForegroundAction("Blue", Color.blue)
								.actionPerformed(arg0);
					} else{
							Sound.playSound("Schwarz");
							new StyledEditorKit.ForegroundAction("Black",
									Color.black).actionPerformed(arg0);
						}
				}
			});
		}
		return jToggleButton5;
	}

	/**
	 * This method initializes jToggleButton6
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton6() {
		if (jToggleButton6 == null) {
			jToggleButton6 = new JToggleButton();
			jToggleButton6.setText("Gelb");
			jToggleButton6.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (jToggleButton6.isSelected()) {
						unselectColorButton(6);
						Sound.playSound("Gelb");
						new StyledEditorKit.ForegroundAction("Yellow",
								Color.yellow).actionPerformed(arg0);
					} else{
						Sound.playSound("Schwarz");
						new StyledEditorKit.ForegroundAction("Black",
								Color.black).actionPerformed(arg0);
					}
				}
			});
		}
		return jToggleButton6;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Größer");
			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Sound.playSound("Groesser");
					if (fontSize <= 22)
						fontSize += 5;
					new StyledEditorKit.FontSizeAction("Bigger", fontSize)
							.actionPerformed(e);
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes jButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Kleiner");
			jButton1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Sound.playSound("Kleiner");
					if (fontSize >= 12)
						fontSize -= 5;
					new StyledEditorKit.FontSizeAction("Smaller", fontSize)
							.actionPerformed(e);
				}
			});

		}
		return jButton1;
	}

	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("Speichern");
			jButton2.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					// String type = jEditorPane.getContentType();
					try {
						OutputStream os = new BufferedOutputStream(
								new FileOutputStream(file));

						Document doc = jEditorPane.getDocument();
						int length = doc.getLength();
						jEditorPane.getEditorKit().write(os, doc, 0, length);
						os.close();
						Sound.playSound("Speichern");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (BadLocationException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}

				}
			});
		}
		return jButton2;
	}

	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("Laden");
			jButton3.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// // TODO Auto-generated method stub

					try {
//						Reader fileRead = new FileReader("Editor.save");
//						jEditorPane.read(fileRead, null);
//						Document doc = jEditorPane.getDocument();
//				        doc.putProperty(Document.StreamDescriptionProperty, null);
//						jEditorPane.setPage("file:Editor.save");
						
						FileInputStream fis= new FileInputStream(file);
jEditorPane.setText("");
						jEditorPane.getEditorKit().read(fis, jEditorPane.getDocument(), 0);
						jEditorPane.setCaretPosition(jEditorPane.getDocument().getLength()-1);
			
						Sound.playSound("Laden");
						fis.close();
						
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}
		return jButton3;
	}

}
