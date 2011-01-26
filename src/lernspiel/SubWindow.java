package lernspiel;

import javax.swing.JFrame;
//import javax.swing.JPanel;

public class SubWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2544644857435994692L;

	public SubWindow(){       
        //JPanel panel = new JPanel();
        //((SubWindow) panel).WindowProperties();
	}
	
	public void WindowProperties(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setSize(800, 600);
        
        setLocationRelativeTo(null);
	}
	
	public void setComponentsToPanel(Object component){
		//this.getContentPane().add(component);
	}
	
}
