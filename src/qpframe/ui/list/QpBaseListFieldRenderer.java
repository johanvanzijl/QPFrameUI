package qpframe.ui.list;

import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

/**
 *
 */
public class QpBaseListFieldRenderer implements ListFieldCallback {
	// Amount of padding around top and bottom of entry
	public static final int PADDING = 3;

	private QpRendererFactory _rowRendererFactory = new QpRendererFactory();

	public QpBaseListFieldRenderer() {
	}

	/**
	 * Implementations must provide the height for a row.
	 * 
	 * @return row height.
	 */
	public int getRowHeight(Font font) {
		return 2;
	}

	/**
	 * Subclasses can override this if they wish to set a font.
	 * 
	 * @param font
	 *            Font to be set.
	 */
	public void setFont(Font font) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.rim.device.api.ui.component.ListFieldCallback#indexOfList(net.rim
	 * .device.api.ui.component.ListField, java.lang.String, int)
	 */
	public int indexOfList(ListField listField, String prefix, int start) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.rim.device.api.ui.component.ListFieldCallback#getPreferredWidth(net
	 * .rim.device.api.ui.component.ListField)
	 */
	public int getPreferredWidth(ListField listField) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.rim.device.api.ui.component.ListFieldCallback#get(net.rim.device.
	 * api.ui.component.ListField, int)
	 */
	public Object get(ListField listField, int index) {
		return null;
	}

	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int width) {
	
		
		if (listField.isEmpty()) {
			graphics.drawText(listField.getEmptyString(), listField.getWidth(),
					y + 2, listField.getEmptyStringStyle());
		}

		QpRichListField collectionListField = (QpRichListField) listField;
		Object element = collectionListField.get(collectionListField, index);
		if (element == null) {
			String emptyString = listField.getEmptyString();
			graphics.drawText(emptyString, 0, emptyString.length(), 0, y,
					DrawStyle.HCENTER, listField.getWidth());
			return;
		}

		if (_rowRendererFactory == null)
			return;

		QpBaseListFieldRenderer rowRenderer = _rowRendererFactory
				.getRenderer(element);

		if (rowRenderer != null) {
			try {
				rowRenderer.calculateRowHeight(listField, index, element,
						graphics);
				// #ifndef VER_4.5.0 | VER_4.3.0 | VER_4.2.2 | VER_4.2.1 |
				// VER_4.2.0
				// rowRenderer.drawRow(element,graphics,listField.getRowHeight(),
				// y,width);
				// #else
				rowRenderer.drawRow(element, graphics,
						listField.getRowHeight(), y, width);
				// #endif
			} catch (ClassCastException ce) {
				String msg4 = ce.getMessage();
			}

		}
	}

	// subclass must override this class to have a custom view of the row
	public void drawRow(Object element, Graphics graphics, int rowHeight,
			int y, int width) {
	}

	public void calculateRowHeight(ListField listField, int index,
			Object model, Graphics graphics) {
		// font height + 2*padding is the default row height
		Font font = graphics.getFont();

		int height = font.getHeight() + 2 * PADDING;

		// #ifndef VER_4.5.0 | VER_4.3.0 | VER_4.2.2 | VER_4.2.1 | VER_4.2.0
		/*
		 * if(height > listField.getRowHeight(index))
		 * listField.setRowHeight(height);
		 */
		// #else
		if (height > listField.getRowHeight())
			listField.setRowHeight(height);
		// #endif
	}

	public QpRendererFactory getRowRendererFactory() {
		return _rowRendererFactory;
	}

	public void setRowRendererFactory(QpRendererFactory rowRendererFactory) {
		_rowRendererFactory = rowRendererFactory;
	}
}
