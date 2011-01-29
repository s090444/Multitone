package clemensNeu;

import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;

public class Sound extends Applet {
      
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3867379223432272441L;

	public static void playSound(char letter){
		
		File file= new File("src/Audio/" + letter+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sound.play();
	}
	
	public static void playSound(int letter){
		
		File file= new File("src/Audio/" + letter+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sound.play();
	}
	
	public static void playSound(String letter){
		
		File file= new File("src/Audio/" + letter+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sound.play();
	}
}
