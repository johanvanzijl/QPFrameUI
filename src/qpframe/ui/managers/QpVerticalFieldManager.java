package qpframe.ui.managers;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class QpVerticalFieldManager extends VerticalFieldManager {

	protected int _bgcolor = -1;
	protected int _borderColor = -1;
	protected int _prefWidth = 0;

	public QpVerticalFieldManager() {
		super();
	}

	public QpVerticalFieldManager(long style) {
		super(style);
	}

	public QpVerticalFieldManager(int borderColor, int bgColor) {
		this();
		this.setBackgroundColor(bgColor);
		this.setBorderColor(borderColor);
	}

	public QpVerticalFieldManager(long style, int borderColor, int bgColor) {
		this(style);
		this.setBackgroundColor(bgColor);
		this.setBorderColor(borderColor);
	}

	protected void paintBackground(Graphics graphics) {

		int oldColor = graphics.getColor();
		XYRect extent = this.getExtent();

		if (_bgcolor > -1) {
			graphics.setColor(_bgcolor);
			if (_borderColor > -1) {
				graphics.fillRoundRect(0, 0, this.getPreferredWidth(),
						extent.height, 8, 8);
			} else {
				graphics
						.fillRect(0, 0, this.getPreferredWidth(), extent.height);
			}
		}

		if (_borderColor > -1) {
			graphics.setColor(_borderColor);
			graphics.drawRoundRect(0, 0, extent.width, extent.height, 8, 8);
			graphics.setColor(oldColor);
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

	public int getPreferredWidth() {
		if (_prefWidth <= 0) {
			return super.getPreferredWidth();
		} else {
			return _prefWidth;
		}
	}

	public void setPreferredWidth(int prefWidth) {
		_prefWidth = prefWidth;
	}

}
