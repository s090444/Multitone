package lernspiel;

import java.util.TimerTask;


class MappedKey extends TimerTask {
	
	RootMenu parent;
	String calledObject;
	
	public MappedKey(RootMenu root, String who){
		parent = root;
		calledObject = who;
	}
	
	public void run() {
		
        //System.out.println("(MappedKey) FirstKeyPressedflag gesetzt auf " + parent.isFirstKeyPressedflag());
		char mappedKey = mapp();
		
		//System.out.println("Var map: " + mappedKey);		

		parent.setFirstKeyPressedflag(false);		
		if (mappedKey != 0){
			parent.setMap(mappedKey);

			System.out.println("MappedKey: " + parent.getMap() + " called by " + this.calledObject);
			parent.resetFlags();
			parent.runApplication(mappedKey);
		} else {
			parent.setMap((char)0);
		}
		
	}

	/** check typed keys against mapping-array and return mapped char */
	public char mapp() {
		System.out.println("numOfPoss: " + parent.getNumOfPoss());
		System.out.println("numOfKeys: " + parent.getNumOfKeys());
		System.out.println("Mapping: " + parent.getMapping()[10][0]);
		System.out.println("flag: " + parent.getFlag()[0]);
		
		for (int i = 0; i < parent.getNumOfPoss(); i++) {
			for (int k = 0; k < parent.getNumOfKeys() + 1; k++) {
				if (k == parent.getNumOfKeys()){
					return (char) parent.getMapping()[k][i];
				}
				else if (parent.getMapping()[k][i] != parent.getFlag()[k])
					break;
			}
		}
		return 0;
	}	
}