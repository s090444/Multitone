package lernspiel;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

class MappKeyCode extends TimerTask {

	RootMenu parent;
	Email email;
	int numOfKeys, numOfPoss;
	boolean isEditor=false;

	public MappKeyCode(RootMenu root, int keys, int poss) {
		parent = root;
		numOfKeys = keys;
		numOfPoss = poss;
		isEditor = true;
	}
	
	public MappKeyCode(RootMenu root, Email e, int keys, int poss) {
		parent = root;
		email = e;
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

		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys - 3; k++) {
				if (k == numOfKeys - 4) {
					if (parent.getMapping()[k+1][i] == 1 && isEditor)
						activeEditorButton(k + 2, i);
					else {
						for (int m = 0; m < 2; m++) {
							for (int n = 1; n < 5; n++) {
								if (m == 0) {
									if (parent.getMapping()[k + n][i] != 0)
										try {
											Robot r = new Robot();
											parent.forward=true;
											r.keyPress(parent.getMapping()[k
													+ n][i]);
										} catch (AWTException e) {
											e.printStackTrace();
										}
								} else if (parent.getMapping()[k + n][i] != 0)
									try {
										Robot r = new Robot();
										parent.release=true;
										r.keyRelease(parent.getMapping()[k + n][i]);
									} catch (AWTException e) {

										e.printStackTrace();
									}
							}
						}
					}
				} else if (parent.getMapping()[k][i] != parent.getFlag()[k]) {
					break;
				}
			}
		}
	}

	public void activeEditorButton(int k, int i) {
		Editor.buttonArray[parent.getMapping()[k][i]].doClick();
	}
}