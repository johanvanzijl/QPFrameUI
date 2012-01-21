package qpframe.ui.hourglass;

import net.rim.device.api.system.GIFEncodedImage;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import qpframe.ui.field.QpAnimatedGIF;

public class QpHourglassScreen extends PopupScreen {

	private static QpHourglassScreen _hourglass;
	private static int _sleepTime;
	//private static ButtonField _cancelBtn;

	protected QpHourglassScreen(Manager man) {

		super(man);
	}

	public static void showHourglass() {

		if (_hourglass == null) {
			VerticalFieldManager man = new VerticalFieldManager();
			 QpAnimatedGIF rotator = new
			 QpAnimatedGIF((GIFEncodedImage)(GIFEncodedImage.getEncodedImageResource(
			 "rotator.gif" )),QpAnimatedGIF.FIELD_LEFT);
			//LabelField rotator = new LabelField();
			//rotator.setText("Loading...");
			man.add(rotator);
			/*_cancelBtn = new ButtonField("Cancel");
			_cancelBtn.setChangeListener(new FieldChangeListener(){

				public void fieldChanged(Field field, int context) {
					if (field == _cancelBtn){
						CnmHourglass.stopHg();
					}
				}
				
			});
			man.add(_cancelBtn);*/
			_hourglass = new QpHourglassScreen(man);
			UiApplication.getUiApplication().pushScreen(_hourglass);
			// UiApplication.getUiApplication().repaint();
			_hourglass.doPaint();
		}

	}

	public static int getSleepTime() {
		return _sleepTime;
	}

	public static void closeHourglass() {
		if (_hourglass != null) {
			// _hourglass.close();
			UiApplication.getUiApplication().popScreen(_hourglass);
			_hourglass = null;
		}

	}

	public static void paintHourglass() {
		if (_hourglass != null) {

			_hourglass.invalidate();
			_hourglass.doPaint();
			// UiApplication.getUiApplication().repaint();
			// _hourglass.();
		}

	}

	protected boolean keyChar(char c, int status, int time) {
		// disable all keys
		return true;
	}

	

}