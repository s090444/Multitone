package lernspiel;

import java.util.TimerTask;


class MappedKey extends TimerTask {
	
	RootMenu parent;
	int numOfKeys, numOfPoss;
	
	public MappedKey(RootMenu root, int keys, int poss){
		parent = root;
		numOfKeys = keys;
		numOfPoss = poss;
	}
	
	public void run() {
		
		parent.setFirstKeyPressedflag(false);
        System.out.println("(MappedKey) FirstKeyPressedflag gesetzt auf " + parent.isFirstKeyPressedflag());
		System.out.println("hallo :) " + numOfKeys);
		char mappedKey = mapp();
		
		System.out.println("Var map: " + mappedKey);		
		
		if (mappedKey != 0)
			parent.map = mappedKey;
		
	}

	/** check typed keys against mapping-array and return mapped char */
	public char mapp() {
		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys + 1; k++) {
				if (k == numOfKeys){
					return (char) parent.mapping[k][i];
				}
				else if (parent.mapping[k][i] != parent.flag[k])
					break;
			}
		}
		return 0;
	}	
}