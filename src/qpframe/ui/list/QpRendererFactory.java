package qpframe.ui.list;

/**
 *
 */
public class QpRendererFactory {

	private static QpTwoColListFieldRenderer twoColumnListFieldRenderer = new QpTwoColListFieldRenderer();

	public QpRendererFactory() {
	}

	public QpBaseListFieldRenderer getRenderer(Object element) {
		QpBaseListFieldRenderer renderer = null;
		renderer = twoColumnListFieldRenderer;

		return renderer;
	}
}
