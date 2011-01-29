package clemensNeu;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

class MappKeyCode extends TimerTask {

	RootMenu parent;
	Editor editor;
	int numOfKeys, numOfPoss;

	public MappKeyCode(RootMenu root, Editor e, int keys, int poss) {
		parent = root;
		editor = e;
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
			for (int k = 0; k < numOfKeys - 2; k++) {
				if (k == numOfKeys - 3) {
					// System.out.println("if k == numOfKeys-1");
//					System.out.println("parent.getMapping[k][i]: "
//							+ parent.getMapping()[k][i]);
					if (parent.getMapping()[k][i] == 1)
						activeEditorButton(k + 1, i);
					else {
						for (int m = 0; m < 2; m++) {
							for (int n = 0; n < 4; n++) {
								if (m == 0) {
									if (parent.getMapping()[k + n][i] != 0)
										try {
											Robot r = new Robot();
											Editor.forward=true;
											r.keyPress(parent.getMapping()[k
													+ n][i]);
										} catch (AWTException e) {
											e.printStackTrace();
										}
								} else if (parent.getMapping()[k + n][i] != 0)
									try {
										Robot r = new Robot();
										Editor.release=true;
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