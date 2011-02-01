package lernspiel;

import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

/**
 * Label specialized to display images
 * @author Kevin Articus
 */
class ImageLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4786625303435614129L;
	private BufferedImage image; 
	 
	/**
	 * displays the {@link BufferedImage} in the {@link ImageLabel}
	 * @param image {@link BufferedImage} to be displayed in the {@link ImageLabel}
	 */
	  public void setImage( BufferedImage image ) 
	  { 
	    this.image = image; 
	    setPreferredSize( new Dimension(image.getWidth(), image.getHeight()) ); 
	    repaint(); 
	    invalidate(); 
	  }
	  
	 
	  /**
	   * draws the image
	   */
	  @Override 
	  protected void paintComponent( Graphics g ) 
	  { 
	    if ( image != null ) {
	      g.drawImage( image, 0, 0,image.getWidth(),image.getHeight(), this ); 
	    }
	    else {
	    	System.out.println("Bild konnte nicht geladen werden");
	    }
	  } 

}
