package qpframe.ui.list;

import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.component.ObjectListField;

public class QpRichListField extends ObjectListField {
	public QpRichListField() {
		super();
		QpBaseListFieldRenderer renderer = new QpBaseListFieldRenderer();
		renderer.setRowRendererFactory(new QpRendererFactory());
		setCallback(renderer);
	}

	public void setCallback(ListFieldCallback callback) {
		super.setCallback(callback);
	}

	public Object get(int i) {
		return this.get(this, i);
	}

}
