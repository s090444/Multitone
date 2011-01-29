package lernspiel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;


import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Excalipoor
 */
class ImageLabel extends JLabel {

	private BufferedImage image; 
	 
	  public void setImage( BufferedImage image ) 
	  { 
	    this.image = image; 
	    setPreferredSize( new Dimension(image.getWidth(), image.getHeight()) ); 
	    repaint(); 
	    invalidate(); 
	  }
	  
//	  public void setImage(String image){
//		  setIcon(new ImageIcon("src/Pics/" + image));
//		  System.out.println("src/Pics/" + image);
//	  }
	 
	  @Override 
	  protected void paintComponent( Graphics g ) 
	  { 
	    if ( image != null ) 
	      g.drawImage( image, 0, 0,400,300, this ); 
	  } 

}
