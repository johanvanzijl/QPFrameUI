package qpframe.ui.managers;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Characters;
import net.rim.device.api.system.Display;
import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;

public class QpCustomComponent extends Manager {
	/**
	 * Default margins
	 */
	private final static int DEFAULT_LEFT_MARGIN = 0;
	private final static int DEFAULT_RIGHT_MARGIN = 0;
	private final static int DEFAULT_TOP_MARGIN = 0;
	private final static int DEFAULT_BOTTOM_MARGIN = 0;

	/**
	 * Default paddings
	 */
	private final static int DEFAULT_LEFT_PADDING = 0;
	private final static int DEFAULT_RIGHT_PADDING = 0;
	private final static int DEFAULT_TOP_PADDING = 0;
	private final static int DEFAULT_BOTTOM_PADDING = 0;

	/**
	 * Margins around the text box
	 */
	protected int topMargin = DEFAULT_TOP_MARGIN;
	protected int bottomMargin = DEFAULT_BOTTOM_MARGIN;
	protected int leftMargin = DEFAULT_LEFT_MARGIN;
	protected int rightMargin = DEFAULT_RIGHT_MARGIN;

	/**
	 * Padding around the text box
	 */
	protected int topPadding = DEFAULT_TOP_PADDING;
	protected int bottomPadding = DEFAULT_BOTTOM_PADDING;
	protected int leftPadding = DEFAULT_LEFT_PADDING;
	protected int rightPadding = DEFAULT_RIGHT_PADDING;

	/**
	 * Amount of empty space horizontally around the text box
	 */
	protected int totalHorizontalEmptySpace = leftMargin + leftPadding
			+ rightPadding + rightMargin;

	/**
	 * Amount of empty space vertically around the text box
	 */
	protected int totalVerticalEmptySpace = topMargin + topPadding
			+ bottomPadding + bottomMargin;

	/**
	 * Minimum height of the text box required to display the text entered
	 */
	protected int minHeight = getFont().getHeight() + totalVerticalEmptySpace;

	/**
	 * Width of the text box
	 */
	protected int width = Display.getWidth() - 10; // jvz - this screws if used
													// in grid

	/**
	 * Height of the text box
	 */
	protected int height = minHeight;

	/**
	 * Background image for the text box
	 */
	protected EncodedImage backgroundImage;
	protected int _bgcolor = -1;

	/**
	 * Bitmap version of the backgroundImage. Needed to reduce the calculation
	 * overhead incurred by scaling of the given image and derivation of Bitmap
	 * from the EncodedImage every time it is needed.
	 */
	protected Bitmap bitmapBackgroundImage;
	protected int BGColor = -1;

	public QpCustomComponent(int iWidth) {
		// Let the super class initialize the core
		super(0);
		width = iWidth;
		// An edit field is the sole field of this manager.

	}

	public QpCustomComponent(int iWidth, EncodedImage backgroundImage) {
		this(iWidth);
		setBackgroundImage(backgroundImage);
	}

	public QpCustomComponent(int iWidth, int BackgroundColor) {
		this(iWidth);
		_bgcolor = BackgroundColor;

	}

	public void setSize(int width, int height) {
		boolean isChanged = false;

		if (width > 0 // Ignore invalid width
				&& this.width != width) {
			this.width = width;
			isChanged = true;
		}

		// Ignore the specified height if it is less
		// than the minimum height required to display the text.
		if (height > minHeight && height != this.height) {
			this.height = height;
			isChanged = true;
		}

		// If width/height has been changed and background image
		// is available, adapt it to the new dimension
		if (isChanged == true && backgroundImage != null) {
			bitmapBackgroundImage = getScaledBitmapImage(backgroundImage,
					this.width - (leftMargin + rightMargin), this.height
							- (topMargin + bottomMargin));
		}
	}

	public void setWidth(int width) {

		if (width > 0 && width != this.width) {
			this.width = width;

			// If background image is available, adapt it to the new width
			if (backgroundImage != null) {
				bitmapBackgroundImage = getScaledBitmapImage(backgroundImage,
						this.width - (leftMargin + rightMargin), this.height
								- (topMargin + bottomMargin));
			}
		}
	}

	public void setHeight(int height) {
		// Ignore the specified height if it is
		// less than the minimum height required to display the text.
		if (height > minHeight) {
			this.height = height;

			// If background image is available, adapt it to the new width
			if (backgroundImage != null) {
				bitmapBackgroundImage = getScaledBitmapImage(backgroundImage,
						this.width - (leftMargin + rightMargin), this.height
								- (topMargin + bottomMargin));
			}
		}
	}

	public void setBackgroundImage(EncodedImage backgroundImage) {
		this.backgroundImage = backgroundImage;

		// Consider the height of background image in
		// calculating the height of the text box.
		// setHeight() does not ensure that specified
		// height will be in effect, of course, for valid reasons.
		// So derivation of Bitmap image in the setHeight() method is not sure.
		setHeight(backgroundImage.getHeight() + topMargin + bottomMargin);
		if (bitmapBackgroundImage == null) {
			bitmapBackgroundImage = getScaledBitmapImage(backgroundImage,
					this.width - (leftMargin + rightMargin), this.height
							- (topMargin + bottomMargin));
		}
	}

	/**
	 * Generate and return a Bitmap Image scaled according to the specified
	 * width and height.
	 * 
	 * @param image
	 *            EncodedImage object
	 * @param width
	 *            Intended width of the returned Bitmap object
	 * @param height
	 *            Intended height of the returned Bitmap object
	 * @return Bitmap object
	 */
	private Bitmap getScaledBitmapImage(EncodedImage image, int width,
			int height) {
		// Handle null image
		if (image == null) {
			return null;
		}

		int currentWidthFixed32 = Fixed32.toFP(image.getWidth());
		int currentHeightFixed32 = Fixed32.toFP(image.getHeight());

		int requiredWidthFixed32 = Fixed32.toFP(width);
		int requiredHeightFixed32 = Fixed32.toFP(height);

		int scaleXFixed32 = Fixed32.div(currentWidthFixed32,
				requiredWidthFixed32);
		int scaleYFixed32 = Fixed32.div(currentHeightFixed32,
				requiredHeightFixed32);

		image = image.scaleImage32(scaleXFixed32, scaleYFixed32);

		return image.getBitmap();
	}

	protected void sublayout(int width, int height) {
		// We have one and only child - the edit field.
		// Place it at the appropriate place.
		try {
			Field field = getField(0);
			layoutChild(field, this.width - totalHorizontalEmptySpace,
					this.height - totalVerticalEmptySpace);
			setPositionChild(field, leftMargin + leftPadding, topMargin
					+ topPadding);

			setExtent(this.width, this.height);
		} catch (Exception e) {
			// do nothing
		}
	}

	public void setTopMargin(int topMargin) {
		this.topMargin = topMargin;
	}

	public void setBottomMargin(int bottomMargin) {
		this.bottomMargin = bottomMargin;
	}

	protected void paintBackground(Graphics graphics) {

		if (_bgcolor > -1) {
			int oldColor = graphics.getColor();
			graphics.setColor(_bgcolor);
			graphics.fillRect(0, 0, getWidth(), getHeight());
			graphics.setColor(oldColor);
		}

	}

	protected void paint(Graphics graphics) {

		// Draw background image if available, otherwise draw a rectangle

		if (bitmapBackgroundImage != null) {

			graphics.drawBitmap(leftMargin, topMargin, width
					- (leftMargin + rightMargin), height
					- (topMargin + bottomMargin), bitmapBackgroundImage, 0, 0);
		}

		super.paint(graphics);

	}

	public int getPreferredWidth() {
		return width;
	}

	public int getPreferredHeight() {
		return height;
	}

	protected boolean keyChar(char ch, int status, int time) {
		if (ch == Characters.ENTER) {
			return true;
		} else {
			return super.keyChar(ch, status, time);
		}
	}

	public String getText() {
		return new String("");
	}

	public void setText(final String text) {
		// redefine
	}
}
