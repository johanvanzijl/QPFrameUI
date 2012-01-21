package qpframe.ui.field;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;

public class QpLabelField extends LabelField {

	private int _fgcolor = Color.BLACK;
	private int _bgcolor = Color.WHITE;

	public void setColors(int Foreground, int Background) {
		_fgcolor = Foreground;
		_bgcolor = Background;
	}

	public QpLabelField() {
		super();
	}

	public QpLabelField(String Text) {
		super(Text);
	}

	public QpLabelField(String Text, long Style) {
		super(Text, Style);
	}

	protected void drawFocus(Graphics graphics, boolean on) {
		// do nothing here...
	}

	protected void paint(Graphics graphics) {
		int oldfg = graphics.getColor();
		int oldbg = graphics.getBackgroundColor();
		graphics.setColor(_fgcolor);
		graphics.setBackgroundColor(_bgcolor);
		super.paint(graphics);
		graphics.setColor(oldfg);
		graphics.setBackgroundColor(oldbg);
	}

	protected void paintBackground(Graphics graphics) {

		int oldfg = graphics.getColor();
		int oldbg = graphics.getBackgroundColor();
		graphics.setColor(_bgcolor);
		graphics.setBackgroundColor(_bgcolor);
		super.paint(graphics);
		graphics.setColor(oldfg);
		graphics.setBackgroundColor(oldbg);

	}
	
/*	protected void layout(int width, int height) {
		setExtent(width, this.getPreferredHeight());
	} */

	public int getPreferredHeight() {
		return super.getHeight();
	}

/*	public int getPreferredWidth() {
		int width = this.getWidth();
		int strWidth = this.getFont().getAdvance(this.getText());
		if(strWidth>=width) {
			return width;
		} else {
			return strWidth;
		}
	}  */
	
	

}
