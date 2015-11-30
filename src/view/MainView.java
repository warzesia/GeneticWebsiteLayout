package view;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tools.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by warzesia on 30/11/15.
 */
public class MainView {

    public static final JFrame frame = new JFrame(Constants.APP_NAME);
    public static final JSVGCanvas svgCanvas = new JSVGCanvas();

    static {
        MainView.frame.getContentPane().add(createMainPanel());
        MainView.frame.setSize(Constants.APP_FRAME_WIDTH, Constants.APP_FRAME_HEIGHT);
        MainView.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static void setSVGDocument(SVGDocument svgDocument){
        MainView.svgCanvas.setSVGDocument(svgDocument);
        MainView.frame.setVisible(true);
    }

    private static JComponent createMainPanel(){
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add("Center", svgCanvas);
        return mainPanel;
    }

}
