package qpframe.ui.field;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.component.LabelField;

public class QpHyperlinkField extends LabelField {

	public QpHyperlinkField(String label) {
		super(label, FOCUSABLE);
		Font font = this.getFont();
		this.setFont(font.derive(Font.UNDERLINED));

	}



	
	
}
