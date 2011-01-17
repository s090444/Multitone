package lernspiel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
/**
 *
 * @author Excalipoor
 */
public class ColorField extends JPanel {
  private BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
  private int alpha = 255;
  private int blue = 0;
  private boolean changed = true;

  public void paint(Graphics g) {
  	if (changed) {
  		changed = false;
  		for (int xRed=0; xRed<256; xRed++)
  			for (int yGreen=0; yGreen<256; yGreen++) {
  				img.setRGB(xRed, yGreen, (alpha<<24) | (xRed<<16) | (yGreen<<8) | blue);
  			}
  	}
  	Graphics2D g2 = (Graphics2D) g;
  	AffineTransform at = AffineTransform.getScaleInstance(getWidth()/256d, getHeight()/256d);
  	g2.setTransform(at);
  	g2.drawString("Hallo", 50, 50);
  	g2.drawImage(img, 0, 0, null);
  }

  public void setAlpha(int value) {
  	alpha = value;
  	changed = true;
  }

  public void setBlue(int value) {
  	blue = value;
  	changed = true;
  }

  public static void main(String[] bla) {
  	final JFrame f = new JFrame("Make the world a colorful place");
  	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	f.setSize(400, 400);


  	final ColorField cf = new ColorField();
  	f.getContentPane().add(cf);
  	cf.setToolTipText("nix bla");

  	//Slider for alpha value
  	final JSlider alphaSlider = new JSlider(0, 255, 255);
  	alphaSlider.addChangeListener(new ChangeListener(){
  		public void stateChanged(ChangeEvent e) {
  			cf.setAlpha(alphaSlider.getValue());
  			f.repaint();
  		}
  	});
  	f.getContentPane().add(alphaSlider, BorderLayout.NORTH);

  	//Slider for color blue
  	final JSlider blueSlider = new JSlider(0, 255, 0);
  	blueSlider.addChangeListener(new ChangeListener(){
  		public void stateChanged(ChangeEvent e) {
  			cf.setBlue(blueSlider.getValue());
  			f.repaint();
  		}
  	});
  	f.getContentPane().add(blueSlider, BorderLayout.SOUTH);

  	f.show();
  }
}
