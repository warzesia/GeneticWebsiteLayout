package view;

import content_generators.RandomLayoutGenerator;
import org.apache.batik.swing.JSVGCanvas;
import page_components.tree_components.Node;
import tools.Constants;
import tools.JsonParser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by warzesia on 12/01/16.
 */
public class CrossoverStrategy implements Strategy {

    private Node root;
    private Node root2;
    private Node root3;
    private Node root4;

    @Override
    public void setupSVGCanvasFields(JSVGCanvas svgCanvas, JSVGCanvas svgCanvas2, JSVGCanvas svgCanvas3, JSVGCanvas svgCanvas4) {

        this.reset();

        SVGCreator svgCreator = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);
        SVGCreator svgCreator2 = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);
        SVGCreator svgCreator3 = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);
        SVGCreator svgCreator4 = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);

        svgCreator.drawSVGTree(root);
        svgCreator2.drawSVGTree(root2);
        svgCreator3.drawSVGTree(root3);
        svgCreator4.drawSVGTree(root4);

        svgCanvas.setSVGDocument(svgCreator.getSVGDocument());
        svgCanvas2.setSVGDocument(svgCreator2.getSVGDocument());
        svgCanvas3.setSVGDocument(svgCreator3.getSVGDocument());
        svgCanvas4.setSVGDocument(svgCreator4.getSVGDocument());
    }

    public CrossoverStrategy(Node root, Node root2){
        this.root = root;
        this.root2 = root2;
    }
    public CrossoverStrategy(){
        this(JsonParser.run(1), JsonParser.run(2));
    }

    public void adjustSvgCanvasGroup (JPanel svgCanvasGroupPanel, JPanel panel, JPanel panel2, JPanel panel3, JPanel panel4){
        svgCanvasGroupPanel.removeAll();
        svgCanvasGroupPanel.setLayout(new GridLayout(2, 2));
        int bla = Constants.SVG_CANVAS_WIDTH_FOUR;
        int bla2 = Constants.SVG_CANVAS_HEIGHT_FOUR;
        svgCanvasGroupPanel.setSize(bla, bla2);
        svgCanvasGroupPanel.add(panel);
        svgCanvasGroupPanel.add(panel2);
        svgCanvasGroupPanel.add(panel3);
        svgCanvasGroupPanel.add(panel4);
    }

    @Override
    public void reset() {
        root3 = root.getCrossover(root2);
        root4 = root2.getCrossover(root);
    }

    @Override
    public Node getRoot() {
        return root;
    }

    public Node getRoot2() {
        return root2;
    }

    public Node getRoot3() {
        return root3;
    }

    public Node getRoot4() {
        return root4;
    }

}
