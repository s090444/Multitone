package clemensNeu;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

class MappedKey extends TimerTask {

	RootMenu parent;
	int numOfKeys, numOfPoss;

	public MappedKey(RootMenu root, int keys, int poss) {
		parent = root;
		numOfKeys = keys;
		numOfPoss = poss;
	}

	public void run() {
		parent.setFirstKeyPressedflag(false);
		mapp();
	}

	/**
	 * check typed keys against mapping-array and return mapped char
	 * 
	 * @throws AWTException
	 */
	public void mapp() {
		/*
		 * System.out.println("numOfPoss: " + numOfPoss);
		 * System.out.println("numOfKeys: " + numOfKeys);
		 * System.out.println("Mapping: " + parent.getMapping()[10][0]);
		 * System.out.println("flag: " + parent.getFlag()[0]);
		 */
		System.out.println("runMapp()");

		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys - 2; k++) {
				if (k == numOfKeys - 3) {
					// System.out.println("if k == numOfKeys-1");
					// System.out.println("parent.getMapping[k]i: " +
					// parent.getMapping()[k][i]);

					for (int m = 0; m < 2; m++) {
						for (int n = 0; n < 4; n++) {
							if (m == 0) {
								if (parent.getMapping()[k + n][i] != 0)
									try {
										Robot r = new Robot();
										System.out.println("KeyCode ausgelöst: " + parent.getMapping()[k+n][i] );
										parent.setForward(true);
										r.keyPress(parent.getMapping()[k + n][i]);
									} catch (AWTException e) {
										e.printStackTrace();
									}
							} else if (parent.getMapping()[k + n][i] != 0)
								try {
									Robot r = new Robot();
									parent.setForward(true);
									r.keyRelease(parent.getMapping()[k + n][i]);
								} catch (AWTException e) {

									e.printStackTrace();
								}
						}
					}
				} else if (parent.getMapping()[k][i] != parent.getFlag()[k])
					break;
			}
		}
	}
}