package clemensNeu;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Timer;

import javax.swing.Action;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.EditorKit;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;
import javax.swing.JButton;
import java.awt.Dimension;

public class Editor extends JFrame implements KeyListener {

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
	
	static RootMenu parent;
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
			jToolBar.add(getJToggleButton3());
			jToolBar.add(getJToggleButton4());
			jToolBar.add(getJToggleButton5());
			jToolBar.add(getJToggleButton6());
			jToolBar.add(getJButton());
			jToolBar.add(getJButton1());
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

	public void keyTyped(KeyEvent e) {
		//
//		System.out.println("isForward: " + parent.isForward());
//		// System.out.println("keyCode: " + parent.getKeyCode());
//		// System.out.println("keycode from Robot: " + e.getKeyCode());
//		if (parent.isForward() == true) {
//			System.out.println("in if");
//			parent.setForward(false);
//		} else {
//			parent.setFlac(e, 1);
//			// System.out.println("setFlag " + e.getKeyChar());
//			if (parent.isFirstKeyPressedflag() == false) {
//				parent.setFirstKeyPressedflag(true);
//
//				Timer timer = new Timer();
//				timer.schedule(new MappedKey(parent, parent.numOfKeys,
//						parent.numOfPoss), parent.typingTime);
//			}
//			e.consume();
//		}
		//
//		 for(int i = 0; i<10; i++){
//		 System.out.print(parent.getFlag()[i]+";");
//		 }
//		 System.out.println();
	}

	public void keyReleased(KeyEvent e) {
//		if (parent.isForward()) {
//			parent.setForward(false);
//			e.consume();
//		} else
//			parent.setFlac(e, 0);
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
			jToggleButton.setFocusable(false);
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
			jButton.setText("Text größer");
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
			jButton1.setText("Text kleiner");
		}
		return jButton1;
	}

}
