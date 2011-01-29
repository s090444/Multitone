package lernspiel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;



import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Excalipoor
 */
class ImageLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4786625303435614129L;
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
	      g.drawImage( image, 0, 0,image.getWidth(),image.getHeight(), this ); 
	  } 

}
