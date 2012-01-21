package qpframe.ui.list;

import net.rim.device.api.system.Bitmap;

/**
 * Classes to implement this interface to provide data to handle list field
 * drawing.
 * 
 * This model interface assumes the left side is a bitmap and right side is
 * one/two line texts
 * 
 * |-------------------------------------| | Bitmap | First line of text | | |
 * Second line of text | |-------------------------------------|
 */
public interface QpTwoColModelProvider {

	/**
	 * <description>
	 * 
	 * @return <description>The left side bitmap
	 */
	public Bitmap getBitmap();

	public String getFirstLineText();

	public String getSecondLineText();

	public String getBackgroundImageName();
}
