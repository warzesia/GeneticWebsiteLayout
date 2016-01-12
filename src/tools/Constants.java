package tools;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;

import java.awt.*;
import java.io.File;

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

    static public final Integer APP_FRAME_WIDTH = ((int) screenWidth);
    static public final Integer APP_FRAME_HEIGHT = ((int) screenHeight);

    static public final Integer SVG_CANVAS_WIDTH_ONE = APP_FRAME_WIDTH - 50;
    static public final Integer SVG_CANVAS_HEIGHT_ONE = APP_FRAME_HEIGHT - 50;
    static public final Integer SVG_CANVAS_WIDTH_FOUR = SVG_CANVAS_WIDTH_ONE/2;
    static public final Integer SVG_CANVAS_HEIGHT_FOUR = SVG_CANVAS_HEIGHT_ONE/2;

    static public final String SVG_ROOT_WIDTH_ONE = Integer.toString(SVG_CANVAS_WIDTH_ONE - 5);
    static public final String SVG_ROOT_HEIGHT_ONE = Integer.toString(SVG_CANVAS_HEIGHT_ONE - 5);
    static public final String SVG_ROOT_WIDTH_FOUR = Integer.toString(SVG_CANVAS_WIDTH_FOUR - 10);
    static public final String SVG_ROOT_HEIGHT_FOUR = Integer.toString(SVG_CANVAS_HEIGHT_FOUR - 10);

    static public final String APP_NAME = "Genetic website layout.";

    static public final String RESOURCE_IMAGES_DIR = "/images";
    static public final String RESOURCE_LOGOS_DIR = "/logos";
    static public final String RESOURCE_PATTERNS_DIR = "/patternLayouts";

    static public final String RESOURCE_PATH;
    static public final String RESOURCE_IMAGES_PATH;
    static public final String RESOURCE_LOGOS_PATH;
    static public final String RESOURCE_PATTERNS_PATH;

    static{
        ClassLoader classLoader = Constants.class.getClassLoader();
        File classpathRoot = new File(classLoader.getResource("").getPath());
        RESOURCE_PATH = classpathRoot.getPath();

        RESOURCE_IMAGES_PATH = RESOURCE_PATH + RESOURCE_IMAGES_DIR;
        RESOURCE_LOGOS_PATH = RESOURCE_PATH + RESOURCE_LOGOS_DIR;
        RESOURCE_PATTERNS_PATH = RESOURCE_PATH + RESOURCE_PATTERNS_DIR;
    }


}
