package qpframe.ui.hourglass;

import net.rim.device.api.ui.UiApplication;

public class QpHourglass {

	protected HourglassThread _hourglassThread;
	protected static QpHourglass _hg;
	protected int _started = 0;

	public void start() {

		_started = 1;
		QpHourglassScreen.showHourglass();
		/*
		 * Disable threading. if(_hourglassThread==null){ _hourglassThread = new
		 * HourglassThread(); _hourglassThread.start(); _started = 1; } else {
		 * if(_started==0) { _started = 1; _hourglassThread.start(); } }
		 */

	}

	public void stop() {

		_started = 0;
		QpHourglassScreen.closeHourglass();
		/*
		 * Disable Threading if(_hourglassThread!=null){
		 * _hourglassThread.stop(); _hourglassThread = null; } _started = 0;
		 */
	}

	public static void startHg() {
		if (_hg == null) {
			_hg = new QpHourglass();
		}
		_hg.start();

	}

	public static void stopHg() {
		if (_hg != null) {
			_hg.stop();
		}
	}

	// ************************************************************//
	// *** THREAD FOR MODAL DIALOG ***//
	// ************************************************************//

	// A thread to handle the animation.
	private class HourglassThread extends Thread {
		// private CnmHourglass _theField;
		private boolean _keepGoing = true;

		public HourglassThread() {
			_keepGoing = true;
		}

		public synchronized void stop() {
			_keepGoing = false;
			QpHourglassScreen.closeHourglass();
		}

		public void start() {
			_keepGoing = true;
			QpHourglassScreen.showHourglass();
		}

		public void run() {
			while (_keepGoing) {
				// Invalidate the field so that it is redrawn.
				UiApplication.getUiApplication().invokeAndWait(new Runnable() {
					public void run() {
						QpHourglassScreen.paintHourglass();
					}
				});

				try {
					// Sleep for the current frame delay before
					// the next frame is drawn.
					sleep(QpHourglassScreen.getSleepTime());
				} catch (InterruptedException iex) {
				} // Couldn't sleep.

			}
		}
	}

}
