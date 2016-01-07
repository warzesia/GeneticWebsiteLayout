package view;

import contentFactories.ConcreteNodesFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tools.Constants;
import tools.NodeType;
import tools.Parameters;
import page_components.tree_components.Node;

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

    //***************************mainPanel*******************************************
    //**                                                                           **
    //**  _________layoutGenerationPanel_______     _________controlPanel________  **
    //**  ||                                 ||     ||                         ||  **
    //**  ||     ...svgCanvasPanel....       ||     ||   ....slidersPanel....  ||  **
    //**  ||     .....................       ||     ||   ....................  ||  **
    //**  ||                                 ||     ||   ....................  ||  **
    //**  ||     ....buttonsPanel.....       ||     ||   ....................  ||  **
    //**  ||     .....................       ||     ||   ....................  ||  **
    //**  ||                                 ||     ||                         ||  **
    //**  _____________________________________     _____________________________  **
    //**                                                                           **
    //*******************************************************************************


    public static final JFrame frame = new JFrame(Constants.APP_NAME);

    public static final JPanel mainPanel = new JPanel();

    /*****/public static final JPanel layoutGenerationPanel = new JPanel();
    /*****//*****/public static final JPanel svgCanvasPanel = new JPanel();
    /*****//*****/public static final JPanel buttonsPanel = new JPanel();

    /*****/public static final JPanel controlPanel = new JPanel();
    /*****//*****/public static final JPanel slidersPanel = new JPanel();
    /*****//*****//*****/public static final JPanel leafPanel = new JPanel();
    /*****//*****//*****/public static final JPanel viewportPanel = new JPanel();
    /*****//*****//*****/public static final JPanel viewportGroupPanel = new JPanel();


    public static final JSVGCanvas svgCanvas= new JSVGCanvas();
    public static final JButton nextButton = new JButton();

    public static JSlider leafSlider;
    public static JLabel leafLabel;

    public static JSlider viewportSlider;
    public static JLabel viewportLabel;

    public static JSlider viewportGroupSlider;
    public static JLabel viewportGroupLabel;

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
        MainView.mainPanel.add("West", MainView.layoutGenerationPanel);
        MainView.mainPanel.add("East", MainView.controlPanel);
        setupLayoutGenerationPanel();
        setupControlPanel();
    }

    private static void setupLayoutGenerationPanel(){
        MainView.layoutGenerationPanel.setLayout(new BorderLayout());
        MainView.layoutGenerationPanel.add("Center", MainView.svgCanvasPanel);
        MainView.layoutGenerationPanel.add("South", MainView.buttonsPanel);
        setupCanvasPanel();
        setupButtonsPanel();
    }

    private static void setupCanvasPanel() {
        MainView.svgCanvasPanel.setSize(Constants.SVG_CANVAS_WIDTH, Constants.SVG_CANVAS_HEIGHT);
        MainView.svgCanvasPanel.add(svgCanvas);
    }

    private static void setupButtonsPanel(){
        MainView.buttonsPanel.setLayout(new BorderLayout());
        MainView.buttonsPanel.add("Center", nextButton);

        MainView.nextButton.setBackground(Color.GREEN);
        MainView.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Node rootNode = ConcreteNodesFactory.getPage(1);
                rootNode.generate();
                SVGCreator svgCreator = new SVGCreator();
                svgCreator.drawSVGTree(rootNode);
                MainView.svgCanvas.setSVGDocument(svgCreator.getSVGDocument());
                MainView.frame.setVisible(true);
                System.out.print(rootNode.toString());
            }
        });
    }

    private static void setupControlPanel(){

        MainView.controlPanel.setLayout(new BorderLayout());
        MainView.controlPanel.add("Center", MainView.slidersPanel);
        MainView.controlPanel.add("South", MainView.resetButton);

        MainView.resetButton.setBackground(Color.GREEN);
        MainView.resetButton.setText("RESET PROBABILITIES");
        MainView.resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.updateNodeTypeProbabilityMap(NodeType.LEAF, leafSlider.getValue());
                Parameters.updateNodeTypeProbabilityMap(NodeType.VIEWPORT, viewportSlider.getValue());
                Parameters.updateNodeTypeProbabilityMap(NodeType.VIEWPORT_GROUP, viewportGroupSlider.getValue());
            }
        });

        setupSlidersPanel();
    }


    private static void setupSlidersPanel(){

        setupSliders();

        MainView.slidersPanel.setLayout(new GridLayout(3, 1));
        MainView.slidersPanel.add(leafPanel);
        MainView.slidersPanel.add(viewportPanel);
        MainView.slidersPanel.add(viewportGroupPanel);

        MainView.leafPanel.setSize(50, 20);
        MainView.leafPanel.setLayout(new BorderLayout());
        MainView.leafPanel.add("South", leafSlider);
        MainView.leafPanel.add("North", leafLabel);

        MainView.viewportPanel.setSize(50, 20);
        MainView.viewportPanel.setLayout(new BorderLayout());
        MainView.viewportPanel.add("South", viewportSlider);
        MainView.viewportPanel.add("North", viewportLabel);

        MainView.viewportGroupPanel.setSize(50, 20);
        MainView.viewportGroupPanel.setLayout(new BorderLayout());
        MainView.viewportGroupPanel.add("South", viewportGroupSlider);
        MainView.viewportGroupPanel.add("North", viewportGroupLabel);

    }

    private static void setupSliders(){

        MainView.leafLabel = new JLabel();
        MainView.leafLabel.setSize(350, 100);

        MainView.leafSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
                Parameters.getNodeTypeProbabilityMap().get(NodeType.LEAF));
//        MainView.leafSlider.setToolTipText("Leaf.");
        MainView.leafSlider.setName("Leaf.");
        MainView.leafSlider.setMajorTickSpacing(1);
        MainView.leafSlider.setPaintTicks(true);
        MainView.leafSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                leafLabel.setText("" + ((JSlider) e.getSource()).getValue());
            }
        });

        MainView.viewportLabel = new JLabel();
        MainView.viewportLabel.setSize(350, 100);

        MainView.viewportSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
                Parameters.getNodeTypeProbabilityMap().get(NodeType.VIEWPORT));
        MainView.viewportSlider.setToolTipText("Viewport.");
        MainView.viewportSlider.setMajorTickSpacing(1);
        MainView.viewportSlider.setPaintTicks(true);
        MainView.viewportSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                viewportLabel.setText("" + ((JSlider) e.getSource()).getValue());
            }
        });

        MainView.viewportGroupLabel = new JLabel();
        MainView.viewportGroupLabel.setSize(350, 100);

        MainView.viewportGroupSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
                Parameters.getNodeTypeProbabilityMap().get(NodeType.VIEWPORT_GROUP));
        MainView.viewportGroupSlider.setToolTipText("Viewport Group.");
        MainView.viewportGroupSlider.setMajorTickSpacing(1);
        MainView.viewportGroupSlider.setPaintTicks(true);
        MainView.viewportGroupSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                viewportGroupLabel.setText("" + ((JSlider) e.getSource()).getValue());
            }
        });

    }

}
