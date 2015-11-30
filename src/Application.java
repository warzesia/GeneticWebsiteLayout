import view.MainView;
import view.SVGCreator;

/**
 * Created by warzesia on 30/11/15.
 */
public class Application {

    public static void main(String[] args) {

        SVGCreator svgCreator = new SVGCreator();
        svgCreator.fillWithTestContent();
        MainView.setSVGDocument(svgCreator.getSVGDocument());


    }
}
