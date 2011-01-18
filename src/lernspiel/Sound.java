package lernspiel;

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;

public class Sound extends Applet {
      
	public void playSound(char letter){
		System.out.println(letter);
		File file= new File("src/Audio/" + letter+ ".wav");
		AudioClip sound = null;
		try {
			sound = Applet.newAudioClip(file.toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sound.play();
	}
}
