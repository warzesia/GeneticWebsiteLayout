import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

        SVGCreator svgCreator_1 = new SVGCreator();
        svgCreator_1.fillWithTestContent("red");

        SVGCreator svgCreator_2 = new SVGCreator();
        svgCreator_2.fillWithTestContent("yellow");

        MainView.setSVGDocuments(svgCreator_1.getSVGDocument(), svgCreator_2.getSVGDocument());


    }
}
