package clemensNeu;

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
	private JToggleButton jToggleButton10 = null;

	static RootMenu parent;
	private JButton jButton = null;
	private JButton jButton1 = null;

	private int fontSize = 12;
	private JButton jButton2 = null;
	private File file = new File("Editor.save");  //  @jve:decl-index=0:
	private JButton jButton3 = null;

	/**
	 * This is the default constructor
	 */
	public Editor(RootMenu root) {
		super();
		parent = root;
		parent.setVisible(false);
		initialize();
		pack();
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
			jToolBar.add(getJToggleButton());
			jToolBar.add(getJToggleButton1());
			jToolBar.add(getJToggleButton2());
			jToolBar.add(getJToggleButton10());
			jToolBar.add(getJToggleButton3());
			jToolBar.add(getJToggleButton4());
			jToolBar.add(getJToggleButton5());
			jToolBar.add(getJToggleButton6());
			jToolBar.add(getJButton());
			jToolBar.add(getJButton1());
			jToolBar.add(getJButton2());
			jToolBar.add(getJButton3());
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
			jEditorPane.setContentType("text/html");
			jEditorPane.setPreferredSize(new Dimension(781, 527));
			jEditorPane.addKeyListener(this);
		}
		return jEditorPane;
	}

	public void keyPressed(KeyEvent e) {

		//
		// System.out.println("isForward: "+parent.isForward());
		// System.out.println("keyCode: " + parent.getKeyCode());
		// System.out.println("keycode from Robot: " + e.getKeyCode());
		//
		// if (parent.isForward() == true) {
		// parent.setForward(false);
		// } else {
		// parent.setFlac(e, 1);
		// System.out.println("setFlag" + e.getKeyChar());
		// if (parent.isFirstKeyPressedflag() == false) {
		// parent.setFirstKeyPressedflag(true);
		//
		// Timer timer = new Timer();
		// timer.schedule(new MappedKey(parent, parent.numOfKeys,
		// parent.numOfPoss), parent.typingTime);
		// }
		// e.consume();
		// }
	}

//	Handle keyTyped Event
	
	public void keyTyped(KeyEvent e) {
		
		//let pass events from Robot()
		if (parent.isForward() == true) {
			parent.setForward(false);
		} else {
			parent.setFlac(e, 1);
			if (parent.isFirstKeyPressedflag() == false) {
				parent.setFirstKeyPressedflag(true);
				Timer timer = new Timer();
				timer.schedule(new MappKeyCode(parent, parent.numOfKeys,
						parent.numOfPoss), parent.typingTime);
			}
			//consume event, don't let it pass to application
			e.consume();
		}
	}

	public void keyReleased(KeyEvent e) {
		if (parent.isForward()) {
			parent.setForward(false);
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
			Action action = new StyledEditorKit.BoldAction();
			action.putValue(Action.NAME, "Bold");
			jToggleButton.setAction(action);

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
			Action action = new StyledEditorKit.ItalicAction();
			action.putValue(Action.NAME, "Italic");
			jToggleButton1.setAction(action);
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
			Action action = new StyledEditorKit.UnderlineAction();
			action.putValue(Action.NAME, "Underline");
			jToggleButton2.setAction(action);
		}
		return jToggleButton2;
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
			Action action = new StyledEditorKit.ForegroundAction("Red",
					Color.red);
			jToggleButton3.setAction(action);
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
			jToggleButton4.setText("Gr�n");
			Action action = new StyledEditorKit.ForegroundAction("Green",
					Color.green);
			jToggleButton4.setAction(action);
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
			Action action = new StyledEditorKit.ForegroundAction("Blue",
					Color.blue);
			jToggleButton5.setAction(action);
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
			Action action = new StyledEditorKit.ForegroundAction("Yellow",
					Color.yellow);
			jToggleButton6.setAction(action);
		}
		return jToggleButton6;
	}

	/**
	 * This method initializes jToggleButton10
	 * 
	 * @return javax.swing.JToggleButton
	 */
	private JToggleButton getJToggleButton10() {
		if (jToggleButton10 == null) {
			jToggleButton10 = new JToggleButton();
			jToggleButton10.setText("Schwarz");
			Action action = new StyledEditorKit.ForegroundAction("Black",
					Color.black);
			jToggleButton10.setAction(action);
		}
		return jToggleButton10;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Gr��er");
			jButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (fontSize <= 22)
						fontSize += 5;
					System.out.println(fontSize);
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
//					String type = jEditorPane.getContentType();
					try {
						OutputStream os = new BufferedOutputStream(new FileOutputStream(file));

					Document doc = jEditorPane.getDocument();
					int length = doc.getLength();
					jEditorPane.getEditorKit().write(os, doc, 0, length);
					os.close();
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
//					// TODO Auto-generated method stub
					
					try {
						Reader fileRead = new FileReader("Editor.save");
						jEditorPane.read(fileRead, null);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		return jButton3;
	}
	
}