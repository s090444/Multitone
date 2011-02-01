package lernspiel;

import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;

/**
 * Class for playing sounds; all sound-files are expected to be in "src/bin/"
 * @author Kevin Articus
 */

public class Sound extends Applet {
      
	
	
	private static final long serialVersionUID = 3867379223432272441L;
	
	/**
	 * creates and plays an {@link AudioClip}
	 * @param letter sign, which has to be spoken
	 */
	public static void playSound(char letter){
		
		File file= new File("src/Audio/" + letter+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Sounddatei nicht gefunden:" + "src/Audio/" + letter + ".wav");
		}
		
		sound.play();
	}
	
	/**
	 * creates and plays an {@link AudioClip}
	 * @param letter ASCII code of the sign, which has to be spoken
	 */
	public static void playSound(int letter){
		
		File file= new File("src/Audio/" + letter+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Sounddatei nicht gefunden:" + "src/Audio/" + letter + ".wav");
		}
		
		sound.play();
	}
	
	/**
	 * creates and plays an {@link AudioClip}
	 * @param string defines the name of the audio-file to be played
	 */
	public static void playSound(String string){
		
		File file= new File("src/Audio/" + string+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			System.out.println("Sounddatei nicht gefunden:" + "src/Audio/" + string + ".wav");
		}
		
		sound.play();
	}
}
