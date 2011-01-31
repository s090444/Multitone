package lernspiel;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

class MappKeyCode extends TimerTask {

	RootMenu parent;
	Editor editor;
	Email email;
	int numOfKeys, numOfPoss;
	boolean isEditor=false;

	public MappKeyCode(RootMenu root, Editor e, int keys, int poss) {
		parent = root;
		editor = e;
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
		/*
		 * System.out.println("numOfPoss: " + numOfPoss);
		 * System.out.println("numOfKeys: " + numOfKeys);
		 * System.out.println("Mapping: " + parent.getMapping()[10][0]);
		 * System.out.println("flag: " + parent.getFlag()[0]);
		 */

//		System.out.println("runMapp()");
//		for (int i = 0; i < 10; i++) {
//			System.out.print(parent.getFlag()[i]);
//		}
//		System.out.println();

		for (int i = 0; i < numOfPoss; i++) {
			for (int k = 0; k < numOfKeys - 3; k++) {
				if (k == numOfKeys - 4) {
					// System.out.println("if k == numOfKeys-1");
					if (parent.getMapping()[k+1][i] == 1 && isEditor)
						activeEditorButton(k + 2, i);
					else {
						for (int m = 0; m < 2; m++) {
							for (int n = 1; n < 5; n++) {
								if (m == 0) {
									if (parent.getMapping()[k + n][i] != 0)
										try {
											Robot r = new Robot();
											System.out.println("Pressed KeyCode: "+parent.getMapping()[k
													+ n][i]);
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
//					System.out.println("break");
//					System.out.println("k: " + k + "i: "+ i);
					break;
				}
			}
		}
	}

	public void activeEditorButton(int k, int i) {
//		System.out.println("Activate Button: " + parent.getMapping()[k][i]);
		editor.getButtonArray()[parent.getMapping()[k][i]].doClick();
	}
}