package lernspiel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/** read mapping out of txt file, return it within an integer-array */

public class ReadMapping {
	
	int numOfKeys, numOfPoss;
	
	public ReadMapping(int keys, int poss){
		numOfKeys = keys;
		numOfPoss = poss;

		//System.out.println("NumOfKeys: " + numOfKeys);
		readMapping();
	}
	
	public int[][] readMapping() {
		
		int mapping[][] = new int[numOfKeys + 1][numOfPoss];
		RandomAccessFile f = null;
		
		try {
			f = new RandomAccessFile("Mapping.txt", "r");
			for (int i = 0; i < numOfPoss; i++) {
				for (String line; (line = f.readLine()) != null;) {
					for (int k = 0; k < numOfKeys +1; k++) {
						int index = line.indexOf(';');
						mapping[k][i] = Integer.parseInt(line.substring(0,
								index));
						//System.out.println("mapped[" + k + "][" + i + "]: " + mapping[k][i]);
						line = line.substring(index + 1);
					}
					break;
				}
			}
		} catch (FileNotFoundException e) {
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
		
		return mapping;
	}
}
