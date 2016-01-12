package view;

import content_generators.RandomLayoutGenerator;
import org.apache.batik.swing.JSVGCanvas;
import page_components.tree_components.Node;
import tools.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by warzesia on 12/01/16.
 */
public class RandomTreesStrategy implements Strategy {

    private Node root;

    @Override
    public void setupSVGCanvasFields(JSVGCanvas svgCanvas, JSVGCanvas svgCanvas2, JSVGCanvas svgCanvas3, JSVGCanvas svgCanvas4) {
        root = RandomLayoutGenerator.getRandomRootNode();
        root.generate();
        SVGCreator svgCreator = new SVGCreator(Constants.SVG_ROOT_WIDTH_ONE, Constants.SVG_ROOT_HEIGHT_ONE);
        svgCreator.drawSVGTree(root);
        svgCanvas.setSVGDocument(svgCreator.getSVGDocument());
    }

    public void adjustSvgCanvasGroup (JPanel svgCanvasGroupPanel, JPanel panel, JPanel panel2, JPanel panel3, JPanel panel4){
        svgCanvasGroupPanel.removeAll();
        svgCanvasGroupPanel.setLayout(new GridLayout(1, 1));
        svgCanvasGroupPanel.setSize(Constants.SVG_CANVAS_WIDTH_ONE, Constants.SVG_CANVAS_HEIGHT_ONE);
        svgCanvasGroupPanel.add(panel);
    }

    @Override
    public void reset() {
        root = RandomLayoutGenerator.getRandomRootNode();
        root.generate();
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
