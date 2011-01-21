/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// Uebung 1 - simple window example

package test;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;

// Uebung 2 - Quitbutton

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


/**
 *
 * @author Bernd Jelitte
 */
public class quitbutton extends JFrame {

    public interface WindowConstants {
    }

    public quitbutton (){

        // Create a Panel / ContentPane to put things on it
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Set Position of the panel centered
        panel.setLayout(null);

        // Create a "Quit" Button with an Action / Event Listener
        // This button close the application by System.exit(0), if the
        // user press the button
        // setBounds is positioning the button on the Panel
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50,60,80,30);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Dispose close the active window and gives the resources free
                dispose();
            }        
        });

        // add the "Quit"-Button to the Panel
        panel.add(quitButton);


        setTitle("QuitButton");
        setSize(200,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /* We create an instance of our code example and make it
         * visible on the screen. The invokeLater() method places the
         * application on the Swing Event Queue. It is used to ensure
         * that all UI updates are concurrency-safe. In other words,
         * it is to prevent GUI from hanging in certain situations.         *
         */

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                quitbutton ex = new quitbutton();
                ex.setVisible(true);
            }
        });

    }

}