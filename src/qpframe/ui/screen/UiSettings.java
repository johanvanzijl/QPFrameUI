package qpframe.ui.screen;

/**
 * @author johanvz
 * General Settings to be used in user interfaces
 */
public class UiSettings {
	/**
	 * Current UI Scheme
	 */
	protected String scheme  = "QP";
	/**
	 * Foreground Colour
	 */
	protected int fgcolor    = Integer.parseInt("000000", 16);
	/**
	 * Background Colour
	 */
	protected int bgcolor    = Integer.parseInt("F8F8FB", 16);
	/**
	 * Foreground Title bar Colour
	 */
	protected int fgtitle    = Integer.parseInt("FFFFFF", 16);
	/**
	 * Background Title bar Colour
	 */
	protected int bgtitle    = Integer.parseInt("001C72", 16);
	/**
	 * Border Colour
	 */
	protected int bordercol  = Integer.parseInt("BFC7E3", 16);
	/**
	 * General Field Back Ground Colour
	 */
	protected int fieldbgcol = Integer.parseInt("E3E3FB", 16);
	/**
	 * Edit Field Background Colour
	 */
	protected int editbgcol  = Integer.parseInt("FFFFFF", 16);
	
	/**
	 * @return Foreground Colour
	 */
	public int getFgcolor() {
		return fgcolor;
	}
	/**
	 * @param Sets Foreground Colour 
	 */
	public void setFgcolor(int fgcolor) {
		this.fgcolor = fgcolor;
	}	
	/**
	 * @param Sets Foreground Colour - Hex Value
	 */
	public void setFgcolor(String fgcolor) {
		this.fgcolor =  Integer.parseInt(fgcolor, 16);
	}	
	/**
	 * @return Background Colour
	 */
	public int getBgcolor() {
		return bgcolor;
	}
	/**
	 * @param Sets Background Colour - Hex Value
	 */
	public void setBgcolor(String bgcolor) {
		this.bgcolor =  Integer.parseInt(bgcolor, 16);
	}	
	/**
	 * @param Sets Background Colour
	 */
	public void setBgcolor(int bgcolor) {
		this.bgcolor = bgcolor;
	}
	/**
	 * @return Foreground Title Bar Colour
	 */
	public int getFgtitle() {
		return fgtitle;
	}
	/**
	 * @param Sets Foreground Title Bar Colour
	 */
	public void setFgtitle(int fgtitle) {
		this.fgtitle = fgtitle;
	}
	/**
	 * @param Sets Foreground Colour - Hex Value
	 */
	public void setFgtitle(String fgtitle) {
		this.fgtitle =  Integer.parseInt(fgtitle, 16);
	}	
	
	public int getBgtitle() {
		return bgtitle;
	}
	public void setBgtitle(int bgtitle) {
		this.bgtitle = bgtitle;
	}
	public void setBgtitle(String bgtitle) {
		this.bgtitle =  Integer.parseInt(bgtitle, 16);
	}
	
	public int getBordercol() {
		return bordercol;
	}
	public void setBordercol(int bordercol) {
		this.bordercol = bordercol;
	}
	public void setBordercol(String bordercol) {
		this.bordercol =  Integer.parseInt(bordercol, 16);
	}
	public int getFieldbgcol() {
		return fieldbgcol;
	}
	public void setFieldbgcol(int fieldbgcol) {
		this.fieldbgcol = fieldbgcol;
	}
	public void setFieldbgcol(String fieldbgcol) {
		this.fieldbgcol =  Integer.parseInt(fieldbgcol, 16);
	}
	public int getEditbgcol() {
		return editbgcol;
	}
	public void setEditbgcol(int editbgcol) {
		this.editbgcol = editbgcol;
	}
	public void setEditbgcol(String editbgcol) {
		this.editbgcol =  Integer.parseInt(editbgcol, 16);
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	
	

}
