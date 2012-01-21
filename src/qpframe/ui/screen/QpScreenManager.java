package qpframe.ui.screen;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import qpframe.ui.managers.QpVerticalFieldManager;

public class QpScreenManager extends VerticalFieldManager {

	private QpVerticalFieldManager _bodyman;
	private QpTitlebar _titlebar;
	protected UiSettings settings;
	protected String title;
	protected String appname;

	public QpScreenManager(String appname, String title, UiSettings settings) {
		super();
		this.settings = settings;
		this.appname  = appname;
		this.title    = title;
		this.buildManager();
	}
	
	protected void buildManager() {
		_titlebar = new QpTitlebar(appname, title, this.settings);
		_bodyman  = new QpVerticalFieldManager(Manager.VERTICAL_SCROLL
				| Manager.VERTICAL_SCROLLBAR, -1, settings.getBgcolor());
		super.add(_titlebar);
		super.add(_bodyman);
	}

	public QpVerticalFieldManager getBodyMan() {
		return _bodyman;
	}

	public void add(Field field) {
		_bodyman.add(field);
	}

	public void setScreenTitle(String title) {
		_titlebar.setTitle(title);
	}

	public void delete(Field field) {
		_bodyman.delete(field);
	}

	public void deleteAll() {
		_bodyman.deleteAll();
	}

	protected void sublayout(int width, int height) {
		// super.sublayout(width, height);
		//
		// layout Title
		
		this.setPositionChild(_titlebar, 0, 0);
		this.layoutChild(_titlebar, Display.getWidth(), 
					     _titlebar.getPreferredHeight() );

		this.setPositionChild(_bodyman, 0, _titlebar.getPreferredHeight() );

		this.layoutChild(_bodyman, Display.getWidth(), Display.getHeight()
				- _titlebar.getPreferredHeight() );
		this.setExtent(Display.getWidth(), Display.getHeight());
		//super.sublayout(width, height);
	}

	protected void paintBackground(Graphics graphics) {
		int oldColor = graphics.getColor();
		graphics.setColor(settings.bgcolor);
		graphics.fillRect(0, 0, Display.getWidth(), Display.getHeight());
		super.paint(graphics);
		graphics.setColor(oldColor);
	}

	public int getBgColor() {
		return settings.bgcolor;
	}

	public void setBgColor(int bgColor) {
		this.settings.setBgcolor(bgColor);
	}

	public UiSettings getSettings() {
		return settings;
	}

	public void setSettings(UiSettings settings) {
		this.settings = settings;
		this._bodyman.setBackgroundColor(settings.getBgcolor());
		this._titlebar.setSettings(settings);
	}

	
}
