import content_generators.ColourGenerator;
import tools.JsonParser;
import page_components.tree_components.Node;
import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {


        Node rootNode = JsonParser.run();
        rootNode.paintBackground(ColourGenerator.ColourGen.getRandomColour());

        System.out.print(rootNode);

        Node rootNodeMutation1 = rootNode.getMutation();
        Node rootNodeMutation2 = rootNode.getMutation();
        Node rootNodeMutation3 = rootNode.getMutation();

        SVGCreator svgCreator = new SVGCreator();
        SVGCreator svgCreator2 = new SVGCreator();
        SVGCreator svgCreator3 = new SVGCreator();
        SVGCreator svgCreator4 = new SVGCreator();

        svgCreator.drawSVGTree(rootNode);
        svgCreator2.drawSVGTree(rootNodeMutation1);
        svgCreator3.drawSVGTree(rootNodeMutation2);
        svgCreator4.drawSVGTree(rootNodeMutation3);

        MainView.setSVGDocuments(
                svgCreator.getSVGDocument(), svgCreator2.getSVGDocument(), svgCreator3.getSVGDocument(), svgCreator4.getSVGDocument());

//        System.out.println(JsonParser.run());

//        System.out.println(ConcreteNodesFactory.getPage(1));






    }
}
