package lernspiel;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Editor extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JScrollPane jScrollPane = null;
	private JEditorPane jEditorPane = null;
	private JToggleButton jToggleButton = null;
	private JToggleButton jToggleButton1 = null;
	private JToggleButton jToggleButton2 = null;

	static RootMenu parent;

	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// Editor thisClass = new Editor();
	// thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// thisClass.setVisible(true);
	// }
	// });
	// }

	/**
	 * This is the default constructor
	 */
	public Editor(RootMenu root) {
		super();
		parent = root;
		parent.setVisible(false);
		initialize();

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
			jEditorPane.addKeyListener(this);
		}
		return jEditorPane;
	}

	public void keyPressed(KeyEvent e) {

//		if (parent.isForward() == true && e.getKeyCode() == parent.getKeyCode()) {
//			parent.setForward(false);
//		} else {
//			parent.setFlac(e, 1);
//			System.out.println("setFlag" + e.getKeyChar());
//			if (parent.isFirstKeyPressedflag() == false) {
//				parent.setFirstKeyPressedflag(true);
//
//				Timer timer = new Timer();
//				timer.schedule(new MappedKey(parent, parent.numOfKeys,
//						parent.numOfPoss), parent.typingTime);
//			}
//			e.consume();
//		}
	}

	public void keyTyped(KeyEvent e) {

		System.out.println("isForward: "+parent.isForward());
		System.out.println("keyCode: " + parent.getKeyCode());
		System.out.println("keycode from Robot: " + e.getKeyCode());
		if (parent.isForward() == true && e.getKeyCode() == parent.getKeyCode()) {
			System.out.println("in if");
			parent.setForward(false);
		} else {
			parent.setFlac(e, 1);
			System.out.println("setFlag " + e.getKeyChar());
			if (parent.isFirstKeyPressedflag() == false) {
				parent.setFirstKeyPressedflag(true);

				Timer timer = new Timer();
				timer.schedule(new MappedKey(parent, "editor"), parent.typingTime);
			}
			e.consume();
		}
	}

	public void keyReleased(KeyEvent e) {
		parent.setFlac(e, 0);
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

}
