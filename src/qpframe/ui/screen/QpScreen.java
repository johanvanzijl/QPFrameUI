package qpframe.ui.screen;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class QpScreen extends MainScreen {

	protected QpScreenManager screenMan;
    protected UiSettings settings;
    protected String title;
	protected String appname;
	
	public QpScreen(String appname, String title, UiSettings settings) {
		super();
        this.settings = settings;
        this.appname  = appname;
        this.title    = title;
		screenMan   = new QpScreenManager(appname, title, settings);
		super.add(this.screenMan);		
	}
	
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}
	
	

	public UiSettings getSettings() {
		return settings;
	}

	public void setSettings(UiSettings settings) {
		this.settings = settings;
		//force update in sub-components
		this.screenMan.setSettings(settings);
	}

	public void setScreenTitle(String title) {
		this.title = title;
		this.screenMan.setScreenTitle(title);
	}

	public VerticalFieldManager getBodyMan() {
		return this.screenMan.getBodyMan();
	}

	public void add(Field field) {
		this.screenMan.add(field);
	}

	public void deleteAll() {
		this.screenMan.deleteAll();
	}

	public void delete(Field field) {
		this.screenMan.delete(field);
	}

	public void setBGColor(int color) {
		this.screenMan.getBodyMan().setBackgroundColor(color);
		this.screenMan.setBgColor(color);
		this.settings.setBgcolor(color);
	}

	

}


