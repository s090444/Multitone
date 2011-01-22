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
		
        //System.out.println("(MappedKey) FirstKeyPressedflag gesetzt auf " + parent.isFirstKeyPressedflag());
		char mappedKey = mapp();
		
		//System.out.println("Var map: " + mappedKey);		

		parent.setFirstKeyPressedflag(false);		
		if (mappedKey != 0){
			parent.setMap(mappedKey);
			System.out.println("MappedKey: " + parent.getMap());					
		} else {
			parent.setMap((char)0);
		}
		
	}

	/** check typed keys against mapping-array and return mapped char */
	public char mapp() {
		/*System.out.println("numOfPoss: " + numOfPoss);
		System.out.println("numOfKeys: " + numOfKeys);
		System.out.println("Mapping: " + parent.getMapping()[10][0]);
		System.out.println("flag: " + parent.getFlag()[0]);*/
		
		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys + 1; k++) {
				if (k == numOfKeys){
					return (char) parent.getMapping()[k][i];
				}
				else if (parent.getMapping()[k][i] != parent.getFlag()[k])
					break;
			}
		}
		return 0;
	}	
}