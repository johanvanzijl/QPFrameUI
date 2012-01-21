package qpframe.ui.managers;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;

public class QpHoriListFieldManager extends QpHorizontalFieldManager {
	private int[] columnWidths;
	private int allRowHeight = -1;
	private int _preferredWidth;
	private int numColumns;
	private int[] columnWidthPerc;
	private int currentCol = 0;

	public QpHoriListFieldManager() {
		super(Manager.NO_HORIZONTAL_SCROLL);
		

	}
	
	public QpHoriListFieldManager(int[] colWidthPercentages,
			int width, long style) {
		super(style);
		
		int currWidth = 0;
		this._preferredWidth = width;
		this.setExtent(width, this.getExtent().height);
		this.numColumns = colWidthPercentages.length;
		columnWidthPerc = colWidthPercentages;
		columnWidths = new int[numColumns];
		for (int i = 0; i < numColumns; i++) {
			
			if(i==numColumns-1) { //adjust the last to fill manager
			  columnWidths[i] = width - currWidth;
			} else {
			  columnWidths[i] = (int) Math.floor(width * columnWidthPerc[i] / 100);
			  currWidth = currWidth + columnWidths[i];
			}
		}
	}
	
	public int getColumnWidth(int colNo) {
	   if(colNo<numColumns) {
		   return columnWidths[colNo];
	   } else {
		   return 0;
	   }	   	
	}

	public int getPreferredWidth() {
		return this._preferredWidth;
	}

	protected void sublayout(int maxWidth, int maxHeight) {
		int y = 0;
		
		Field[] fields = new Field[columnWidths.length];
		int currentColumn = 0;
		int rowHeight = 0;
		for (int i = 0; i < getFieldCount(); i++) {
			fields[currentColumn] = getField(i);
			layoutChild(fields[currentColumn], columnWidths[currentColumn],
					maxHeight - y);
			if (fields[currentColumn].getHeight() > rowHeight) {
				rowHeight = fields[currentColumn].getHeight();
			}
			currentColumn++;
			if (currentColumn == columnWidths.length
					|| i == getFieldCount() - 1) {
				int x = 0;
				if (this.allRowHeight >= 0) {
					rowHeight = this.allRowHeight;
				}
				for (int c = 0; c < currentColumn; c++) {
					long fieldStyle = fields[c].getStyle();
					int fieldXOffset = 0;
					long fieldHalign = fieldStyle & Field.FIELD_HALIGN_MASK;
					if (fieldHalign == Field.FIELD_RIGHT) {
						fieldXOffset = columnWidths[c] - fields[c].getWidth();
					} else if (fieldHalign == Field.FIELD_HCENTER) {
						fieldXOffset = (columnWidths[c] - fields[c].getWidth()) / 2;
					}

					int fieldYOffset = 0;
					long fieldValign = fieldStyle & Field.FIELD_VALIGN_MASK;
					if (fieldValign == Field.FIELD_BOTTOM) {
						fieldYOffset = rowHeight - fields[c].getHeight();
					} else if (fieldValign == Field.FIELD_VCENTER) {
						fieldYOffset = (rowHeight - fields[c].getHeight()) / 2;
					}

					setPositionChild(fields[c], x + fieldXOffset, y
							+ fieldYOffset);
					x += columnWidths[c];
				}
				currentColumn = 0;
				y += rowHeight;
			}
			if (y >= maxHeight) {
				break;
			}
		}
		int totalWidth = 0;
		for (int i = 0; i < columnWidths.length; i++) {
			totalWidth += columnWidths[i];
		}
		setExtent(totalWidth, Math.min(y, maxHeight));
	}
	
	public void add(Field field) {
		currentCol++;
		if(currentCol>=numColumns) {
			currentCol = 0;
		}
		super.add(field);
	}
	
	public int getCurrenCol() {
		return currentCol;
	}
	

	public void deleteAll() {
		super.deleteAll();
		currentCol = 0;
	}
}
