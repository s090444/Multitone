package lernspiel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadNumOfPoss {
	
	RootMenu parent;
	
	public ReadNumOfPoss(RootMenu root){
		parent = root;

		readNumOfPoss();
	}
	
	
	public void readNumOfPoss(){
		
		RandomAccessFile f = null;
		int counter = 0;
		try{
			f = new RandomAccessFile("Mapping.txt", "r");
			for (String line; (line = f.readLine())!= null;)
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


		parent.setNumOfPoss(counter);
        System.out.println("Getter NumOfPoss(): " + parent.getNumOfPoss());
        System.out.println("NumOfPoss(): " + parent.numOfPoss);
		
	}
}
