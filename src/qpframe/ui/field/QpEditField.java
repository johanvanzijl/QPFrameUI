package qpframe.ui.field;
 

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.EditField;

public class QpEditField extends EditField {
	/*** Default margins ***/
	private final static int DEFAULT_LEFT_MARGIN = 1;
	private final static int DEFAULT_RIGHT_MARGIN = 1;
	private final static int DEFAULT_TOP_MARGIN = 1;
	private final static int DEFAULT_BOTTOM_MARGIN = 1;

	/*** Default paddings ***/
	private final static int DEFAULT_LEFT_PADDING = 2;
	private final static int DEFAULT_RIGHT_PADDING = 2;
	private final static int DEFAULT_TOP_PADDING = 2;
	private final static int DEFAULT_BOTTOM_PADDING = 2;

  //  protected int width = Display.getWidth() - 10; // Be careful..if used in
  //													// grid;

	protected int _bgcolor = -1;
	protected int _borderColor = -1;

	public QpEditField() {
		// Let the super class initialize the core
		super();
		XYEdges padding = new XYEdges(DEFAULT_TOP_PADDING,
				DEFAULT_RIGHT_PADDING, DEFAULT_BOTTOM_PADDING,
				DEFAULT_LEFT_PADDING);
		XYEdges margins = new XYEdges(DEFAULT_TOP_MARGIN, DEFAULT_RIGHT_MARGIN,
				DEFAULT_BOTTOM_MARGIN, DEFAULT_LEFT_MARGIN);
		this.setPadding(padding);
		this.setMargin(margins);
	}

	public QpEditField(int iWidth) {
		// Let the super class initialize the core
		this();
		this.setWidth(iWidth);
	}

	public QpEditField(int iWidth, int borderColor, int bgColor) {
		// Let the super class initialize the core
		this();
		this.setWidth(iWidth);
		this.setBackgroundColor(bgColor);
		this.setBorderColor(borderColor);
	}

	public void setBorderColor(int borderColor) {
		_borderColor = borderColor;
	}

	public int getBorderColor() {
		return _borderColor;
	}

	public void setBackgroundColor(int bgColor) {
		_bgcolor = bgColor;
	}

	public int getBackgroundColor() {
		return _bgcolor;
	}

	protected void paintBackground(Graphics graphics) {

		int oldColor = graphics.getColor();
		XYRect extent = this.getExtent();

		if (_bgcolor > -1) {
			graphics.setColor(_bgcolor);
			graphics.fillRoundRect(0, 0, extent.width, extent.height, 8, 8);
		//	graphics.fillRoundRect(0, 0, this.width, extent.height, 8, 8);
		}

		if (_borderColor > -1) {
			graphics.setColor(_borderColor);
			graphics.drawRoundRect(0, 0, extent.width, extent.height, 8, 8);
		//	graphics.drawRoundRect(0, 0, this.width, extent.height, 8, 8);
		}

		graphics.setColor(oldColor);
	}

	public void setWidth(int width) {
		if (width > 0 && width != this.getWidth()) {
			int height = this.getHeight();
			this.setExtent(width, height);
		}
	}

	public int getPreferredHeight() {
		return super.getHeight() + DEFAULT_TOP_MARGIN + DEFAULT_BOTTOM_MARGIN;
	}

	public int getPreferredWidth() {
		return super.getWidth() + DEFAULT_LEFT_MARGIN + DEFAULT_RIGHT_MARGIN;

	}
}
