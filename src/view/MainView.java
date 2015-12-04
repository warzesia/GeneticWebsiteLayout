package view;

import evolution.LayoutFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tools.Constants;
import tools.NodeType;
import tools.Parameters;
import treeComponents.SVGNode;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by warzesia on 30/11/15.
 */
public class MainView {

    public static final JFrame frame = new JFrame(Constants.APP_NAME);

    public static final JPanel mainPanel = new JPanel();

    public static final JPanel SVGCanvasPanel = new JPanel();

    public static final JPanel ControlPanel = new JPanel();
    public static final JPanel ButtonsPanel = new JPanel();
    public static final JPanel SlidersPanel = new JPanel();


    public static final JSVGCanvas svgCanvas= new JSVGCanvas();
    public static final JButton nextButton = new JButton();

    public static JSlider SVGLeafSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
            Parameters.getNodeTypeProbabilityMap().get(NodeType.LEAF));
    public static JLabel SVGLeafLabel;

    public static JSlider SVGViewportSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
            Parameters.getNodeTypeProbabilityMap().get(NodeType.VIEWPORT));
    public static JLabel SVGViewportLabel;

    public static JSlider SVGViewportGroupSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
            Parameters.getNodeTypeProbabilityMap().get(NodeType.VIEWPORT_GROUP));
    public static JLabel SVGViewportGroupLabel;

    public static final JButton resetButton = new JButton();


    static {

        setupMainPanel();

        MainView.frame.add(mainPanel);
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

    private static void setupMainPanel(){
        MainView.mainPanel.setLayout(new BorderLayout());
        MainView.mainPanel.add("North", ButtonsPanel);
        MainView.mainPanel.add("Center", SVGCanvasPanel);
        MainView.mainPanel.add("South", ControlPanel);

        setupButtonsPanel();
        setupCanvasPanel();
        setupControlPanel();
    }

    private static void setupCanvasPanel() {
        MainView.SVGCanvasPanel.setSize(Constants.SVG_CANVAS_WIDTH, Constants.SVG_CANVAS_HEIGHT);
        MainView.SVGCanvasPanel.add(svgCanvas);
    }

    private static void setupControlPanel(){
        MainView.ControlPanel.setLayout(new FlowLayout());
        MainView.ControlPanel.add(MainView.SlidersPanel);

        setupSliderPanel();
    }

    private static void setupButtonsPanel(){

        MainView.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SVGNode rootNode = LayoutFactory.getRandomRootNode();
                rootNode.generate();
                SVGCreator svgCreator = new SVGCreator();
                svgCreator.drawSVGTree(rootNode);
                MainView.svgCanvas.setSVGDocument(svgCreator.getSVGDocument());
                MainView.frame.setVisible(true);
            }
        });

        MainView.nextButton.setBackground(Color.GREEN);
        MainView.ButtonsPanel.setLayout(new BorderLayout());
        MainView.ButtonsPanel.add("Center", nextButton);
    }

    private static void setupSliderPanel(){

        MainView.SVGLeafLabel = new JLabel("SVGLeaf: ", JLabel.CENTER);
        MainView.SVGLeafLabel.setSize(350, 100);
        MainView.SVGLeafSlider.setMajorTickSpacing(1);
        MainView.SVGLeafSlider.setPaintTicks(true);
        MainView.SVGLeafSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                SVGLeafLabel.setText("SVGLeaf:  " + ((JSlider) e.getSource()).getValue());
            }
        });

        MainView.SVGViewportLabel = new JLabel("SVGLeaf: ", JLabel.CENTER);
        MainView.SVGViewportLabel.setSize(350, 100);
        MainView.SVGViewportSlider.setMajorTickSpacing(1);
        MainView.SVGViewportSlider.setPaintTicks(true);
        MainView.SVGViewportSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                SVGViewportLabel.setText("SVGVewport:  " + ((JSlider) e.getSource()).getValue());
            }
        });

        MainView.SVGViewportGroupLabel = new JLabel("SVGLeaf: ", JLabel.CENTER);
        MainView.SVGViewportGroupLabel.setSize(350, 100);
        MainView.SVGViewportGroupSlider.setMajorTickSpacing(1);
        MainView.SVGViewportGroupSlider.setPaintTicks(true);
        MainView.SVGViewportGroupSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                SVGViewportGroupLabel.setText("SVGVewportGroup:  " + ((JSlider) e.getSource()).getValue());
            }
        });

        MainView.resetButton.setBackground(Color.GREEN);
        MainView.resetButton.setText("RESET PROBABILITY");
        MainView.resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.updateNodeTypeProbabilityMap(NodeType.LEAF, SVGLeafSlider.getValue());
                Parameters.updateNodeTypeProbabilityMap(NodeType.VIEWPORT, SVGViewportSlider.getValue());
                Parameters.updateNodeTypeProbabilityMap(NodeType.VIEWPORT_GROUP, SVGViewportGroupSlider.getValue());
            }
        });


        MainView.SlidersPanel.add(SVGLeafSlider);
        MainView.SlidersPanel.add(SVGLeafLabel);
        MainView.SlidersPanel.add(SVGViewportSlider);
        MainView.SlidersPanel.add(SVGViewportLabel);
        MainView.SlidersPanel.add(SVGViewportGroupSlider);
        MainView.SlidersPanel.add(SVGViewportGroupLabel);
        MainView.SlidersPanel.add(resetButton);
    }

}
