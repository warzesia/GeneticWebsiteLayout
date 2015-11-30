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

    public static final JPanel panel_1 = new JPanel();
    public static final JPanel panel_2 = new JPanel();

    public static final JSVGCanvas svgCanvas_1 = new JSVGCanvas();
    public static final JSVGCanvas svgCanvas_2 = new JSVGCanvas();

    public static final JButton button_1 = new JButton();
    public static final JButton button_2 = new JButton();


    static {


        MainView.button_1.setBackground(Color.GREEN);
        MainView.button_2.setBackground(Color.BLUE);

        MainView.panel_1.setLayout(new BorderLayout());
        MainView.panel_1.add("Center", svgCanvas_1);
        MainView.panel_1.add("South", button_1);

//        MainView.panel_1.setLayout(new BorderLayout());
//        MainView.panel_1.add(svgCanvas_1, BorderLayout.NORTH);
//        MainView.panel_1.add(button_1, BorderLayout.SOUTH);

        MainView.panel_2.add(svgCanvas_2);
        MainView.panel_2.add(button_2);


        MainView.frame.setLayout(new BorderLayout());
        MainView.frame.add("Center", panel_1);
//        MainView.frame.add(panel_2);

        MainView.frame.setSize(Constants.APP_FRAME_WIDTH, Constants.APP_FRAME_HEIGHT);
        MainView.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static void setSVGDocuments(SVGDocument svgDocument_1, SVGDocument svgDocument_2){
        MainView.svgCanvas_1.setSVGDocument(svgDocument_1);
        MainView.svgCanvas_2.setSVGDocument(svgDocument_2);
        MainView.frame.setVisible(true);
    }


}
