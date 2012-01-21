package qpframe.ui.screen;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import qpframe.ui.field.QpLabelField;
import qpframe.ui.managers.QpHorizontalFieldManager;

public class QpTitlebar extends VerticalFieldManager {

	private LabelField lblTitle;
	private QpLabelField lblLogo;
	protected String appname;
	protected String title;
	protected UiSettings settings;

	public QpTitlebar(String appname, String title, UiSettings settings) {
		super();
		this.appname  = appname;
		this.title    = title;
		this.settings = settings;
		this.buildTitlebar();
	}
	
	protected void buildTitlebar() {
		//make sure we are clean
		QpHorizontalFieldManager horiMan = new QpHorizontalFieldManager(-1,
				settings.getBgtitle());

		lblLogo = new QpLabelField(this.appname, LabelField.FOCUSABLE);

    	lblLogo.setColors(settings.getFgtitle(),settings.getBgtitle());
	    Font font = lblLogo.getFont().derive(Font.BOLD );
		lblLogo.setFont(font);

		font = lblLogo.getFont().derive(Font.PLAIN);
		horiMan.add(lblLogo);
		lblTitle = new LabelField(title);
        lblTitle.setFont(font);
		lblTitle.setText(title);

		lblLogo.setMargin(1, 1, 1, 1);
		lblLogo.setPadding(2, 1, 1, 1);
		lblTitle.setMargin(1, 1, 1, 1);
		lblTitle.setPadding(2, 1, 1, 1);

		horiMan.add(lblTitle);
		horiMan.setUnderline(settings.getBordercol());
		horiMan.setPadding(0, 0, 0, 0);
		horiMan.setMargin(0, 0, 0, 0);
		add(horiMan);
	}

	public void setTitle(String title) {
	    this.title = title;	
		lblTitle.setText(title);
		
	}
	
    public void setAppname(String appname) {
    	this.appname = appname;
		lblLogo.setText(appname);	
	}

	protected void sublayout(int maxWidth, int maxHeight) {
		Font fntTitle = lblTitle.getFont();
		Font fntLogo  = lblLogo.getFont();
		int totWidth = Display.getWidth();

		
 		int leftPos = totWidth - fntTitle.getAdvance(title)
				- fntLogo.getAdvance(this.appname) - 10; // 10 is total of
													// pads+marges
		
		if( leftPos < 0 ) { leftPos = 0; }
		
		lblTitle.setPosition(leftPos);
		super.sublayout(maxWidth, maxHeight);
	}
	
	
	public String getAppname() {
		return appname;
	}

	public UiSettings getSettings() {
		return settings;
	}

	public void setSettings(UiSettings settings) {
		this.settings = settings;

	}

	protected void paint(Graphics graphics) {
		graphics.setColor(settings.fgtitle);
		super.paint(graphics);

	}

}
