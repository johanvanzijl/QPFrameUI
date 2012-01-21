package qpframe.ui.managers;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class QpHorizontalFieldManager extends HorizontalFieldManager {

	protected int _bgcolor = -1;
	protected int _borderColor = -1;
	protected int _underline = -1;

	public QpHorizontalFieldManager() {
		super();
	}

	public QpHorizontalFieldManager(long style) {
		super(style);
	}

	public QpHorizontalFieldManager(int borderColor, int bgColor) {
		this();
		this.setBackgroundColor(bgColor);
		this.setBorderColor(borderColor);
	}

	public QpHorizontalFieldManager(long style, int borderColor, int bgColor) {
		this(style);
		this.setBackgroundColor(bgColor);
		this.setBorderColor(borderColor);
	}

	public void setUnderline(int color) {
		_underline = color;
	}

	protected void paintBackground(Graphics graphics) {

		int oldColor = graphics.getColor();
		XYRect extent = this.getExtent();

		if (_bgcolor > -1) {
			graphics.setColor(_bgcolor);
			if (_borderColor > -1) {
				graphics.fillRoundRect(0, 0, extent.width, extent.height, 8, 8);
			} else {
				graphics.fillRect(0, 0, extent.width, extent.height);
			}
		}

		if (_borderColor > -1) {
			graphics.setColor(_borderColor);
			graphics.drawRoundRect(0, 0, extent.width, extent.height, 8, 8);
		}

		if (_underline > -1) {
			graphics.setColor(_underline);
			graphics.drawLine(0, extent.height - 1, extent.width,
					extent.height - 1);
		}

		graphics.setColor(oldColor);
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
}
