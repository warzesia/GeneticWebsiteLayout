package view;

import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.svg.SVGDocument;
import tools.Constants;
import page_components.tree_components.enums.NodeType;
import tools.Parameters;

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
public class View {

    private static View instance;
    public static View getInstance(){
        if(instance==null)
            instance = new View();
        return instance;
    }

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


    private Strategy strategy;

    private JFrame frame = new JFrame(Constants.APP_NAME);
    private JPanel mainPanel = new JPanel();

    /*****/private JPanel layoutGenerationPanel = new JPanel();
    /*****//*****/private JPanel svgCanvasGroupPanel = new JPanel();
    /*****//*****//*****/private JPanel svgCanvasPanel = new JPanel();
    /*****//*****//*****/private JPanel svgCanvasPanel2 = new JPanel();
    /*****//*****//*****/private JPanel svgCanvasPanel3 = new JPanel();
    /*****//*****//*****/private JPanel svgCanvasPanel4 = new JPanel();
    /*****//*****/private JPanel buttonsPanel = new JPanel();

    /*****/private JPanel controlPanel = new JPanel();
    /*****//*****/private JPanel slidersPanel = new JPanel();
    /*****//*****//*****/private JPanel leafPanel = new JPanel();
    /*****//*****//*****/private JPanel viewportPanel = new JPanel();
    /*****//*****//*****/private JPanel viewportGroupPanel = new JPanel();


    private JSVGCanvas svgCanvas = new JSVGCanvas();
    private JSVGCanvas svgCanvas2 = new JSVGCanvas();
    private JSVGCanvas svgCanvas3 = new JSVGCanvas();
    private JSVGCanvas svgCanvas4 = new JSVGCanvas();
    private JButton nextButton = new JButton();

    private JSlider leafSlider;
    private JLabel leafLabel;

    private JSlider viewportSlider;
    private JLabel viewportLabel;

    private JSlider viewportGroupSlider;
    private JLabel viewportGroupLabel;

    private JButton resetButton = new JButton();

    private View(){
        setupMainPanel();
        frame.add(mainPanel);
        frame.setSize(Constants.APP_FRAME_WIDTH, Constants.APP_FRAME_HEIGHT);

        this.setStrategy(new CrossoverStrategy());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void run(){
        frame.setVisible(true);
    }

    public void setSVGDocument(SVGDocument svgDocument){
        svgCanvasGroupPanel.add(svgCanvasPanel);
        svgCanvas.setSVGDocument(svgDocument);
    }

    public void setSVGDocuments(SVGDocument svgDocument, SVGDocument svgDocument2, SVGDocument svgDocument3, SVGDocument svgDocument4){
        svgCanvas.setSVGDocument(svgDocument);
        svgCanvas2.setSVGDocument(svgDocument2);
        svgCanvas3.setSVGDocument(svgDocument3);
        svgCanvas4.setSVGDocument(svgDocument4);
        svgCanvasGroupPanel.add(svgCanvasPanel);
        svgCanvasGroupPanel.add(svgCanvasPanel2);
        svgCanvasGroupPanel.add(svgCanvasPanel3);
        svgCanvasGroupPanel.add(svgCanvasPanel4);
    }

    private void setupMainPanel(){
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("Center", layoutGenerationPanel);
//        mainPanel.add("West", layoutGenerationPanel);
//        mainPanel.add("East", controlPanel);
        setupLayoutGenerationPanel();
        setupControlPanel();
    }

    private void setupLayoutGenerationPanel(){
        layoutGenerationPanel.setLayout(new BorderLayout());
        layoutGenerationPanel.add("Center", svgCanvasGroupPanel);
        layoutGenerationPanel.add("South", buttonsPanel);
        setupCanvasGroupPanel();
        setupButtonsPanel();
    }

    private void setupCanvasGroupPanel() {
        //WARNING:
        //svgCanvasPanel(s) need to be added to svgCanvasGroupPanel
        svgCanvasPanel.add(svgCanvas);
        svgCanvasPanel2.add(svgCanvas2);
        svgCanvasPanel3.add(svgCanvas3);
        svgCanvasPanel4.add(svgCanvas4);
    }

    private void setupButtonsPanel(){
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add("Center", nextButton);

        nextButton.setBackground(Color.GREEN);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strategy.setupSVGCanvasFields(svgCanvas, svgCanvas2, svgCanvas3, svgCanvas4);
                frame.repaint();
            }
        });
    }

    private void setupControlPanel(){
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add("Center", slidersPanel);
        controlPanel.add("South", resetButton);

        resetButton.setBackground(Color.GREEN);
        resetButton.setText("RESET PROBABILITIES");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parameters.updateNodeTypeProbabilityMap(NodeType.LEAF, leafSlider.getValue());
                Parameters.updateNodeTypeProbabilityMap(NodeType.VIEWPORT, viewportSlider.getValue());
                Parameters.updateNodeTypeProbabilityMap(NodeType.VIEWPORT_GROUP, viewportGroupSlider.getValue());
            }
        });

        setupSlidersPanel();
    }


    private void setupSlidersPanel(){

        setupSliders();

        slidersPanel.setLayout(new GridLayout(3, 1));
        slidersPanel.add(leafPanel);
        slidersPanel.add(viewportPanel);
        slidersPanel.add(viewportGroupPanel);

        leafPanel.setSize(50, 20);
        leafPanel.setLayout(new BorderLayout());
        leafPanel.add("South", leafSlider);
        leafPanel.add("North", leafLabel);

        viewportPanel.setSize(50, 20);
        viewportPanel.setLayout(new BorderLayout());
        viewportPanel.add("South", viewportSlider);
        viewportPanel.add("North", viewportLabel);

        viewportGroupPanel.setSize(50, 20);
        viewportGroupPanel.setLayout(new BorderLayout());
        viewportGroupPanel.add("South", viewportGroupSlider);
        viewportGroupPanel.add("North", viewportGroupLabel);

    }

    private void setupSliders(){

        leafLabel = new JLabel();
        leafLabel.setSize(350, 100);

        leafSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
                Parameters.getNodeTypeProbabilityMap().get(NodeType.LEAF));
//        leafSlider.setToolTipText("Leaf.");
        leafSlider.setName("Leaf.");
        leafSlider.setMajorTickSpacing(1);
        leafSlider.setPaintTicks(true);
        leafSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                leafLabel.setText("" + ((JSlider) e.getSource()).getValue());
            }
        });

        viewportLabel = new JLabel();
        viewportLabel.setSize(350, 100);

        viewportSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
                Parameters.getNodeTypeProbabilityMap().get(NodeType.VIEWPORT));
        viewportSlider.setToolTipText("Viewport.");
        viewportSlider.setMajorTickSpacing(1);
        viewportSlider.setPaintTicks(true);
        viewportSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                viewportLabel.setText("" + ((JSlider) e.getSource()).getValue());
            }
        });

        viewportGroupLabel = new JLabel();
        viewportGroupLabel.setSize(350, 100);

        viewportGroupSlider = new JSlider(JSlider.HORIZONTAL, 0, 10,
                Parameters.getNodeTypeProbabilityMap().get(NodeType.VIEWPORT_GROUP));
        viewportGroupSlider.setToolTipText("Viewport Group.");
        viewportGroupSlider.setMajorTickSpacing(1);
        viewportGroupSlider.setPaintTicks(true);
        viewportGroupSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                viewportGroupLabel.setText("" + ((JSlider) e.getSource()).getValue());
            }
        });

    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        this.strategy.adjustSvgCanvasGroup(svgCanvasGroupPanel, svgCanvasPanel, svgCanvasPanel2, svgCanvasPanel3, svgCanvasPanel4);
    }
}
