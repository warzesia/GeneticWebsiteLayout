import content_generators.RandomLayoutGenerator;
import evolution.Population;
import tools.JsonParser;
import tools.Params;
import page_components.tree_components.Node;
import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

        Population population = new Population();
        population.generate(Params.POPULATION_SIZE);

        Node rootNode = RandomLayoutGenerator.getRandomRootNode();
        rootNode.generate();

        SVGCreator svgCreator = new SVGCreator();
        svgCreator.drawSVGTree(JsonParser.run());

        MainView.setSVGDocument(svgCreator.getSVGDocument());

        System.out.println(JsonParser.run());

//        System.out.println(ConcreteNodesFactory.getPage(1));






    }
}
