import evolution.LayoutFactory;
import evolution.Population;
import tools.Params;
import treeComponents.SVGNode;
import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

//        Population population = new Population();
//        population.generate(Params.POPULATION_SIZE);

        SVGNode rootNode = LayoutFactory.getRandomNode(0.0, 0.0, 1.0, 1.0, 0);
        rootNode.generate();

//        SVGCreator svgCreator = new SVGCreator();
//        svgCreator.fillWithTestContent("red");
//
//        MainView.setSVGDocument(svgCreator.getSVGDocument());


    }
}
