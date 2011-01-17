package lernspiel;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//package lernspiel;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Excalipoor
 */
class ImageLabel extends JLabel {

  public ImageLabel(String img) {
    this(new ImageIcon(img));
  }

  public ImageLabel(ImageIcon icon) {
    setIcon(icon);
    // setMargin(new Insets(0,0,0,0));
    setIconTextGap(0);
    // setBorderPainted(false);
    setBorder(null);
    setText(null);
    setSize(400, 300);
  }

}
