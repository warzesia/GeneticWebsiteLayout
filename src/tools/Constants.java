package tools;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;

import java.awt.*;

/**
 * Created by warzesia on 30/11/15.
 */
public class Constants {

    static private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static private final double screenWidth = screenSize.getWidth();
    static private final double screenHeight = screenSize.getHeight();
    
    static public final String XLINK_NAMESPACE = "http://www.w3.org/1999/xlink";
    static public final String SVG_NAMESPACE = SVG12DOMImplementation.SVG_NAMESPACE_URI;

    static public final String SVG_VERSION = "1.2";

    static public final Integer APP_FRAME_WIDTH = 700;//((int) screenWidth);
    static public final Integer APP_FRAME_HEIGHT = 500;//((int) screenHeight);

    static public final Integer SVG_CANVAS_WIDTH = APP_FRAME_WIDTH*8/10;
    static public final Integer SVG_CANVAS_HEIGHT = APP_FRAME_HEIGHT*8/10;

    static public final String SVG_ROOT_WIDTH = Integer.toString(SVG_CANVAS_WIDTH);
    static public final String SVG_ROOT_HEIGHT = Integer.toString(SVG_CANVAS_HEIGHT);

    static public final String APP_NAME = "Genetic website layout.";

}
