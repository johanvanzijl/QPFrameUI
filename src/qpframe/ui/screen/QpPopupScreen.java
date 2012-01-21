package qpframe.ui.screen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.PopupScreen;

public class QpPopupScreen extends PopupScreen {

	protected Manager screenMan;
	protected UiSettings settings;

	public QpPopupScreen(Manager manager, int style, UiSettings settings) {
		super(manager, style);
		this.settings = settings;
		screenMan = manager;
		super.add(screenMan);

	}

	public void add(Field field) {
		screenMan.add(field);
	}

	public void deleteAll() {
		screenMan.deleteAll();
	}

	public void delete(Field field) {
		screenMan.delete(field);
	}

}