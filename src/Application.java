import evolution.Population;
import tools.Params;
import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

        Population population = new Population();
        population.generate(Params.POPULATION_SIZE);

        SVGCreator svgCreator = new SVGCreator();
        svgCreator.fillWithTestContent("red");

        MainView.setSVGDocument(svgCreator.getSVGDocument());


    }
}
