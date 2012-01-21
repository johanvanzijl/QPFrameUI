package qpframe.ui.managers;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYRect;

public class QpGridFieldManager extends Manager {
	protected int[] columnWidths;
	protected int columns;
	//private int allRowHeight = -1;
	protected int _borderColor = -1;
	protected int _bgcolor = -1;
	protected int _prefWidth;

	/**
	 * Constructs a new GridFieldManager with the specified number of columns.
	 * Rows will be added as needed to display fields. Fields will be added to
	 * the grid in the order they are added to this manager, filling up each row
	 * left-to-right:
	 * 
	 * For example a 2 column manager:
	 * 
	 * [Field1][Field2] [Field3][Field4] [Field5]
	 * 
	 * Column widths are all equal, and the manager will attempt to use all
	 * available width. The height of each row will be equal to the height of
	 * the tallest Field in that row. Field positional styles are respected, so
	 * fields that are smaller than the row/column they are in can be positioned
	 * left, right, top bottom, or centered. They default to top left.
	 * 
	 * @param columns
	 *            Number of columns in the grid
	 * @param style
	 */

	public QpGridFieldManager(int[] colWidth, int prefWidth) {
		super(0);
		_prefWidth = prefWidth;
		this.columnWidths = colWidth;
		this.columns = colWidth.length;
	}

	public QpGridFieldManager(int[] columnWidths, int rowHeight, int prefWidth) {
		this(columnWidths, prefWidth);
		//this.allRowHeight = rowHeight;
	}

	public int getColumns() {
		return this.columns;
	}

	public int getColWidth(int columnNumber) {
		if (columnNumber >= columns) {
			return 0; // this column does not exist
		} else {
			return columnWidths[columnNumber];
		}
	}

	public void setBorderColor(int borderColor) {
		_borderColor = borderColor;
	}

	public void setBgColor(int borderColor) {
		this._bgcolor = borderColor;
	}

	protected void paintBackground(Graphics graphics) {

		int oldColor = graphics.getColor();
		XYRect extent = this.getExtent();

		if (_bgcolor > -1) {
			graphics.setColor(_bgcolor);
			graphics.fillRoundRect(0, 0, this.getWidth(), extent.height, 8, 8);
		}

		if (_borderColor > -1) {
			graphics.setColor(_borderColor);
			graphics.drawRoundRect(0, 0, this.getWidth(), extent.height, 8, 8);
		}

		graphics.setColor(oldColor);
	}

	protected void sublayout(int width, int height) {
		int currentRow = 0;
		int currentRowHeight = 0;
		int rowYOffset = 0;
		int availableHeight = height;

		if (columnWidths == null) {
			columnWidths = new int[columns];
			for (int i = 0; i < columns; i++) {
				columnWidths[i] = width / columns;
			}
		}

		// For each column size all the fields and get the maximum width
		for (int column = 0; column < columns; column++) {
			for (int fieldIndex = column; fieldIndex < getFieldCount(); fieldIndex += columns) {
				Field field = getField(fieldIndex);
				layoutChild(field, columnWidths[column], availableHeight);
			}
		}
		// Set the position of each field
		for (int fieldIndex = 0; fieldIndex < getFieldCount(); fieldIndex++) {
			Field field = getField(fieldIndex);
			if (fieldIndex % columns == 0) {
				setPositionChild(field, 0, rowYOffset);
			} else {
				setPositionChild(field,
						columnWidths[(fieldIndex % columns) - 1], rowYOffset);
			}
			if (field.getPreferredHeight() > currentRowHeight) {
				currentRowHeight = field.getPreferredHeight();
			}

			if (fieldIndex % columns == columns - 1) {
				currentRow++;
				rowYOffset += currentRowHeight;
				currentRowHeight = 0;
			}
		}
		rowYOffset += currentRowHeight;
		setExtent(width, Math.min(rowYOffset, height));

	}

	public int getPreferredWidth() {
		return this._prefWidth;
	}
}
