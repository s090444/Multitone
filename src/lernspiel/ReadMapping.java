package lernspiel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/** read mapping out of txt file, return it within an integer-array */

public class ReadMapping {
	
	RootMenu parent;
	
	public ReadMapping(RootMenu root){
		parent = root;
		readMapping();
	}
	
	public void readMapping() {
		int mapping[][] = new int[parent.numOfKeys + 1][parent.numOfPoss];
		RandomAccessFile f = null;
		try {
			f = new RandomAccessFile("Mapping.txt", "r");
			for (int i = 0; i < parent.numOfPoss; i++) {
				for (String line; (line = f.readLine()) != null;) {
					for (int k = 0; k < parent.numOfKeys + 1; k++) {
						int index = line.indexOf(';');
						mapping[k][i] = Integer.parseInt(line.substring(0,
								index));
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
		
		parent.mapping = mapping;
	}
}
