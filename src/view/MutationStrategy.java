package view;

import content_generators.RandomLayoutGenerator;
import org.apache.batik.swing.JSVGCanvas;
import page_components.tree_components.LeafNode;
import page_components.tree_components.Node;
import page_components.tree_components.ViewportGroupNode;
import page_components.tree_components.ViewportNode;
import tools.Constants;
import tools.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by warzesia on 12/01/16.
 */
public class MutationStrategy implements Strategy {

    int level = 0;
    int chosenRootNumber = 1;

    private Node root;
    private Node root2;
    private Node root3;
//    private Node root4;

    @Override
    public void setupSVGCanvasFields(JSVGCanvas svgCanvas, JSVGCanvas svgCanvas2, JSVGCanvas svgCanvas3, JSVGCanvas svgCanvas4) {

        switch (this.chosenRootNumber) {
            case 1: {
                /*root remains the same*/
                break;
            }
            case 2: {
                root = root2;
                break;
            }
            case 3: {
                root = root3;
                break;
            }
            default: {
                break;
            }
        }

        this.reset();

        SVGCreator svgCreator = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);
        SVGCreator svgCreator2 = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);
        SVGCreator svgCreator3 = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);
//        SVGCreator svgCreator4 = new SVGCreator(Constants.SVG_ROOT_WIDTH_FOUR, Constants.SVG_ROOT_HEIGHT_FOUR);

        svgCreator.drawSVGTree(root);
        svgCreator2.drawSVGTree(root2);
        svgCreator3.drawSVGTree(root3);
//        svgCreator4.drawSVGTree(root4);

        svgCanvas.setSVGDocument(svgCreator.getSVGDocument());
        svgCanvas2.setSVGDocument(svgCreator2.getSVGDocument());
        svgCanvas3.setSVGDocument(svgCreator3.getSVGDocument());
//        svgCanvas4.setSVGDocument(svgCreator4.getSVGDocument());

        System.out.println(root);
        System.out.println(root2);
        System.out.println(root3);
//        System.out.println(root4);

        level++;
    }

    public MutationStrategy(Node root){
        this.root = root;
    }
    public MutationStrategy(){
        this(JsonParser.run(JsonParser.getRandomLayout()));
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
//        svgCanvasGroupPanel.add(panel4);
    }

    @Override
    public void reset() {
        LinkedList<Node> mutatedNodes = root.getMutations(3);
        root2 = mutatedNodes.get(0);
        root3 = mutatedNodes.get(1);
//        root4= mutatedNodes.get(2);
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

//    public Node getRoot4() {
//        return root4;
//    }


    @Override
    public void setChosenRoot(int chosenRootNumber) {
        this.chosenRootNumber = chosenRootNumber;
    }

    @Override
    public Strategy getNextStrategy() {
        return new MutationStrategy();
    }

}
