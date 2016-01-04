import evolution.ElementFactory;
import evolution.LayoutFactory;
import evolution.Population;
import tools.JsonTestParser;
import tools.Params;
import treeComponents.SVGNode;
import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

        Population population = new Population();
        population.generate(Params.POPULATION_SIZE);

        SVGNode rootNode = LayoutFactory.getRandomRootNode();
        rootNode.generate();

        SVGCreator svgCreator = new SVGCreator();
        svgCreator.drawSVGTree(rootNode);

        MainView.setSVGDocument(svgCreator.getSVGDocument());

        System.out.println("bla!");

        ElementFactory.getRandomImage();

//        JsonTestParser.run();



    }
}
