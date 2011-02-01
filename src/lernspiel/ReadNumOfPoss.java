package lernspiel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadNumOfPoss {
		
	public ReadNumOfPoss(){
	}
	
	
	public int readNumOfPoss(){
		
		RandomAccessFile f = null;
		int counter = 0;
		try{
			f = new RandomAccessFile("Mapping.txt", "r");
			for (; (f.readLine())!= null;)
				counter++;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return counter;
		
	}
}
