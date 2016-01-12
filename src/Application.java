import view.CrossoverStrategy;
import view.RandomTreesStrategy;
import view.Strategy;
import view.View;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

//        Node root, root2, root3, root4;
//
//
//
//        /***************************** Offspring ***********************************/
//        root = JsonParser.run(4);
//        root2 = JsonParser.run(6);
//        root3 = root.getCrossover(root2);
//        root4 = root2.getCrossover(root);
//
//        root.paintBackground(ColourGenerator.ColourGen.getRandomColour());
//        root2.paintBackground(ColourGenerator.ColourGen.getRandomColour());
//        root3.paintBackground(ColourGenerator.ColourGen.getRandomColour());
//        root4.paintBackground(ColourGenerator.ColourGen.getRandomColour());
//
////        root3 = root.getCrossoverAtMaxLevel(root2, 0);
////        root4 = root.getCrossoverAtMaxLevel(root2, 0);
//
//        System.out.println("Node one_________________");
//        System.out.println(root);
//        System.out.println("Node two_________________");
//        System.out.println(root2);
//        System.out.println("OFFSPRING 1_________________");
//        System.out.println(root3);
//        System.out.println("OFFSPRING 2_________________");
//        System.out.println(root4);
//
//
//        /***************************** Mutations on root level ***********************************/
////        LinkedList<ViewportNode> mutatedNodes = root.getMutations(3);
////        root2 = mutatedNodes.get(0);
////        root3 = mutatedNodes.get(1);
////        root4= mutatedNodes.get(2);
//
//
//
//
//        /*************************** Display ***************************************/
//        SVGCreator svgCreator = new SVGCreator();
//        SVGCreator svgCreator2 = new SVGCreator();
//        SVGCreator svgCreator3 = new SVGCreator();
//        SVGCreator svgCreator4 = new SVGCreator();
//        svgCreator.drawSVGTree(root);
//        svgCreator2.drawSVGTree(root2);
//        svgCreator3.drawSVGTree(root3);
//        svgCreator4.drawSVGTree(root4);
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        View.setSVGDocuments(
//                svgCreator.getSVGDocument(), svgCreator2.getSVGDocument(), svgCreator3.getSVGDocument(), svgCreator4.getSVGDocument());

        View view = View.getInstance();
        view.setStrategy(new CrossoverStrategy());

        view.run();
//
//        CrossoverStrategy strategy = new CrossoverStrategy();
//        System.out.println(strategy.getRoot());
//        System.out.println(strategy.getRoot2());
//        System.out.println(strategy.getRoot3());
//        System.out.println(strategy.getRoot4());

    }
}
