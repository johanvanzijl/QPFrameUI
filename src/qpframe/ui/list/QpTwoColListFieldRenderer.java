package qpframe.ui.list;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.ListField;

//#endif
/**
 * This list field callback only handles objects which implement
 * TwoColumnModelProvider.
 * 
 */
public class QpTwoColListFieldRenderer extends QpBaseListFieldRenderer {
	// #ifndef VER_4.5.0 | VER_4.3.0 | VER_4.2.2 | VER_4.2.1 | VER_4.2.0
	//private Background rowBackground;

	// #endif
	public QpTwoColListFieldRenderer() {
	}

	public void drawRow(Object element, Graphics graphics, int rowHeight,
			int y, int width) {

		QpTwoColModelProvider model = (QpTwoColModelProvider) element;

		int offsetX = 0;
		int offsetY = 0;
		int fontHeight = graphics.getFont().getHeight();
		int verticalPadding = (rowHeight - (fontHeight * 2)) / 3;

		// draw background
		// drawBackground(graphics, y, width, rowHeight,
		// model.getBackgroundImageName());

		Bitmap leftBitmap = model.getBitmap();
		if (leftBitmap != null) {
			// draw bitmap
			offsetX += drawLeftBitmap(leftBitmap, graphics, 0, y, width,
					rowHeight);
		}

		String firstLineText = model.getFirstLineText();
		if (firstLineText != null) {
			// draw first line text
			drawFirstLineText(firstLineText, graphics, offsetX, y, width
					- offsetX);
			offsetY = graphics.getFont().getHeight() + verticalPadding;
		}

		String secondLineText = model.getSecondLineText();
		if (secondLineText != null) {
			// draw the second line text
			drawSecondLineText(secondLineText, graphics, offsetX, y + offsetY,
					width - offsetX);
		}

		XYRect rect = new XYRect(2, y + rowHeight - 1, width - 4 , 0);
	//	graphics.drawRoundRect(2, y, width - 4, y + rowHeight - 1, 5, 5);
		drawSeparateLine(graphics, rect);

	}

	public void calculateRowHeight(ListField listField, int index,
			Object element, Graphics graphics) {
		Font font = graphics.getFont();
		int fontHeight = (font.getHeight() + PADDING) * 2;

		QpTwoColModelProvider model = (QpTwoColModelProvider) element;
		Bitmap leftBitmap = model.getBitmap();
		int bitmapHeight = 0;

		if (leftBitmap != null)
			bitmapHeight = leftBitmap.getHeight() + 2;

		int height = (fontHeight > bitmapHeight ? fontHeight : bitmapHeight);

		// #ifndef VER_4.5.0 | VER_4.3.0 | VER_4.2.2 | VER_4.2.1 | VER_4.2.0
		/*
		 * if (height > listField.getRowHeight(index))
		 * listField.setRowHeight(index, height);
		 */
		// #else
		if (height > listField.getRowHeight()) // use static row height here
			listField.setRowHeight(height);
		// #endif
	}

	/**
	 * <return> x offset
	 */
	public int drawLeftBitmap(Bitmap bitmap, Graphics graphics, int x, int y,
			int width, int rowHeight) {
		graphics.drawBitmap(x + PADDING, y + (rowHeight - bitmap.getHeight())
				/ 2, bitmap.getWidth(), bitmap.getHeight(), bitmap, 0, 0);
		return PADDING * 2 + bitmap.getWidth();
	}

	public void drawFirstLineText(String text, Graphics graphics, int x, int y,
			int width) {
		// FontFamily fontfam[] = FontFamily.getFontFamilies();
		// Font font = fontfam[1].getFont(Font.getDefault().getStyle(), 20);
		graphics.setFont(Font.getDefault());
		graphics.drawText(text, x, y + PADDING, DrawStyle.ELLIPSIS, Display
				.getWidth());
		// graphics.setFont(font);
	}

	public void drawSecondLineText(String text, Graphics graphics, int x,
			int y, int width) {
		/*
		 * FontFamily fontfam[] = FontFamily.getFontFamilies(); Font newFont =
		 * fontfam[1].getFont(Font.ITALIC, 20);
		 */
		graphics.setFont(Font.getDefault());
		graphics.drawText(text, x, y, DrawStyle.ELLIPSIS, width);
	}

	public void drawSeparateLine(Graphics graphics, XYRect rect) {
		graphics.setGlobalAlpha(100);
		graphics.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y
				+ rect.height);
	}

	public void drawBackground(Graphics graphics, int y, int width,
			int rowHeight, String imageName) {
		// int Focus_background_color = 0x2169c6;

		// boolean isSelected =
		// graphics.isDrawingStyleSet(Graphics.DRAWSTYLE_FOCUS);

		/*
		 * if(isSelected){ //draw background with focus int oldColour =
		 * graphics.getColor(); int oldBackgroundColour =
		 * graphics.getBackgroundColor(); try {
		 * 
		 * graphics.setColor( Focus_background_color ); graphics.drawBitmap( 0,
		 * y, width, rowHeight, MatteFactory.getMattedColour( width, rowHeight,
		 * Color.BLACK, graphics.getColor() ), 0, 0 );
		 * ReflectionHighlight.drawReflection( graphics, 0, y, width, rowHeight,
		 * true, true );
		 * 
		 * } finally { graphics.setColor( oldColour );
		 * graphics.setBackgroundColor( oldBackgroundColour ); } }else{
		 * //#ifndef VER_4.5.0 | VER_4.3.0 | VER_4.2.2 | VER_4.2.1 | VER_4.2.0
		 * if (rowBackground == null) { Bitmap bitmap =
		 * Bitmap.getBitmapResource(imageName); rowBackground =
		 * BackgroundHelper.createBitmapBackground( bitmap, 0, y, width,
		 * rowHeight, 5 ); //SCALE_TO_FIT - not available pre 4.6 - but
		 * BackgroundHelper compensates } rowBackground.draw(graphics, new
		 * XYRect(0, y, width, rowHeight)); //#endif }
		 */
	}
}
