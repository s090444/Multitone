package clemensNeu;


import javax.swing.text.StyledEditorKit.FontSizeAction;

public class BiggerFontSize extends FontSizeAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549890497397081552L;
	
	Editor e;
	
	public BiggerFontSize(Editor e, int i){
		super("Bigger", i+2);
		this.e = e;
		e.setFontSize(i+2);

	}
	
	

}
