package lernspiel;

import java.util.TimerTask;


class MappedKey extends TimerTask {
	
	RootMenu parent;
	
	public MappedKey(RootMenu root){
		parent = root;
		
		parent.firstKeyPressedflag = false;
		char mappedKey = mapp();
		
		System.out.println("Var map: " + mappedKey);
		
		
		if (mappedKey != 0)
			parent.map = mappedKey;
	}
	
	public void run() {
	}

	/** check typed keys against mapping-array and return mapped char */
	public char mapp() {
		for (int i = 0; i < parent.numOfPoss; i++) {
			for (int k = 0; k < parent.numOfKeys + 1; k++) {
				if (k == parent.numOfKeys){
					return (char) parent.mapping[k][i];
				}
				else if (parent.mapping[k][i] != parent.flag[k])
					break;
			}
		}
		return 0;
	}	
}