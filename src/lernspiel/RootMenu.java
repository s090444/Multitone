/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lernspiel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;

/**
 *
 * @author sakura
 */
public class RootMenu extends JFrame
        implements KeyListener{

    private static final long serialVersionUID = 7465416001464648515L;
    
    static RootMenu root;

    /*
     * Constructor
     */
    public RootMenu(){
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addComponentsToPanel();
        //root.pack();

        this.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                root = new RootMenu();
            }
        });
    }

    public void addComponentsToPanel(){

        JPanel panel = new JPanel();

        JButton button = new JButton("SubWindow");
        button.addKeyListener(this);

        panel.add(button);

        this.getContentPane().add(panel);

        setTitle("RootMenu");
        setSize(300, 200);

        setLocationRelativeTo(null);
    }

    public void keyTyped(KeyEvent e) {
        //System.out.println(e);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("RootWindow: " + e);
        
        new Lernspiel("Lernspiel", root);
        //this.dispose();

        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyReleased(KeyEvent e) {
        //System.out.println(e);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}