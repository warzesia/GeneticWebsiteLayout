package view;

import org.apache.batik.swing.JSVGCanvas;
import page_components.tree_components.Node;

import javax.swing.*;

/**
 * Created by warzesia on 12/01/16.
 */
public interface Strategy {

    void setupSVGCanvasFields(JSVGCanvas svgCanvas, JSVGCanvas svgCanvas2, JSVGCanvas svgCanvas3, JSVGCanvas svgCanvas4);
    void adjustSvgCanvasGroup(JPanel svgCanvasGroupPanel, JPanel panel, JPanel panel2, JPanel panel3, JPanel panel4);
    void reset();
    Node getRoot();

}

