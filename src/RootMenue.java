/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication3;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Bernd Jelitte
 */
public class RootMenue extends JFrame
    implements KeyListener, ActionListener {


    static String text;
    JButton menue1, menue2, menue3, menue4;
    JLabel label, label2;

    JTextField typingArea;

    int x = 0;
    boolean pressed = false;

    /* Constructor */
    public RootMenue(){

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
                text = "hallo";
                System.out.println(text);
                text = "hallo2";
                System.out.println(text);
            }
        });

    }


    /* Runnable method for class */
    public static void createAndShowGUI(){
        // call the constructor
        RootMenue frame = new RootMenue();
        System.out.println("Create Constructor...");
        
        // set operations what happens, if the "x" button is pressed
        // -> frame application is closing
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // paint buttons and labels on the frame
        frame.addComponentsToPane();
        System.out.println("Add components to Pane...");

        // fits the window by subcomponents
        frame.pack();

        // generates the window
        frame.setVisible(true);
        System.out.println("Pack frame and set visible...");
    }



    private void addComponentsToPane() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(new Insets(40,60,40,60)));

        typingArea = new JTextField(20);
        panel.add(typingArea);
        typingArea.addKeyListener(this);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    
        label = new JLabel("Press 'j' to navigate");
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        label2 = new JLabel("Press 'k' to enter");
        panel.add(label2);
        // positioning on the panel with "new dimension (width, height)"
        panel.add(Box.createRigidArea(new Dimension(0,15)));

        menue1 = new JButton("Quit-window");
        panel.add(menue1);
        menue1.addActionListener(this);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        menue2 = new JButton("KeyEvent Demo");
        panel.add(menue2);
        menue2.addActionListener(this);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        menue3 = new JButton("Blank");
        panel.add(menue3);
        menue3.addActionListener(this);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        menue4 = new JButton("Quit Menue");
        panel.add(menue4);
        menue4.addActionListener(this);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        add(panel);
        
        setTitle("Root Menue");
        setLocationRelativeTo(null);
    }



    public void keyTyped(KeyEvent e){
        //System.out.println("keyTyped: " + e);
    }

    public void keyPressed(KeyEvent e){
        //System.out.println("keyPressed: " + e);
    }

    public void keyReleased(KeyEvent e){
        System.out.println("keyReleased: " + e);

        displayInfo(e);
    }

    public void actionPerformed(ActionEvent e){
        typingArea.requestFocusInWindow();
        
        //System.out.println("actionPerformed: " + e);
    }

    private void displayInfo(KeyEvent e){

        int keyCode = e.getKeyCode();

        // Press 'Esc' for Quit programm
        if(keyCode == 27)
            System.exit(0);

        if(x > 4)
            x = 0;

        if(keyCode == 74){
            x++;
        } 

        if( (keyCode == 75) && (x != 0) ){
            pressed = true;
        }

        System.out.println("int: " + x + " keyCode '" + keyCode + "' Pressed " + pressed);
        
        switch(x) {
            case 1 :    menue1.setBackground(Color.red);

                        menue2.setBackground(UIManager.getColor("Button.background"));
                        menue3.setBackground(UIManager.getColor("Button.background"));
                        menue4.setBackground(UIManager.getColor("Button.background"));

                        if(pressed == true){
                            System.out.print("Starting Quitbutton... ");

                            quitbutton ex = new quitbutton();
                            ex.setVisible(true);

                            System.out.println("Reset pressed button");
                            menue1.setBackground(UIManager.getColor("Button.background"));
                            pressed = false;
                        }

                        break;

            case 2 :    menue2.setBackground(Color.red);

                        menue1.setBackground(UIManager.getColor("Button.background"));
                        menue3.setBackground(UIManager.getColor("Button.background"));
                        menue4.setBackground(UIManager.getColor("Button.background"));

                        if(pressed == true){
                            System.out.print("Starting KeyEventDemo... ");

                            KeyEventDemo frame = new KeyEventDemo("KeyEventDemo");
                            frame.setVisible(true);

                            System.out.println("Reset pressed button");
                            menue2.setBackground(UIManager.getColor("Button.background"));
                            pressed = false;
                        }

                        break;

            case 3 :    menue3.setBackground(Color.red);

                        menue1.setBackground(UIManager.getColor("Button.background"));
                        menue2.setBackground(UIManager.getColor("Button.background"));
                        menue4.setBackground(UIManager.getColor("Button.background"));

                        if(pressed == true){
                            System.out.print("Hier könnte ihr Programm stehen!... ");

                            System.out.println("Reset pressed button");
                            menue3.setBackground(UIManager.getColor("Button.background"));
                            pressed = false;
                        }

                        break;

            case 4 :    menue4.setBackground(Color.red);

                        menue1.setBackground(UIManager.getColor("Button.background"));
                        menue2.setBackground(UIManager.getColor("Button.background"));
                        menue3.setBackground(UIManager.getColor("Button.background"));

                        if(pressed == true){
                            System.out.print("Quiting Root-Menue... ");

                            System.exit(0);
                        }

                        break;

            default :   menue1.setBackground(UIManager.getColor("Button.background"));
                        menue2.setBackground(UIManager.getColor("Button.background"));
                        menue3.setBackground(UIManager.getColor("Button.background"));
                        menue4.setBackground(UIManager.getColor("Button.background"));
        }

    }

}
