package treeComponents.drawable;

import SVGDomFactory.SVGCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Strings;
import treeComponents.SVGElement;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGRectangle extends SVGElement {

    String fillColour;
//    String strokeColour;
//    Double fillOpacity;
//    Double strokeOpacity;


    @Override
    public Element draw(Document document, String svgNamespace) {
        Element element = document.createElementNS(svgNamespace, Strings.RECTANGLE);
        super.setAttributes(element);
        element.setAttributeNS(null, Strings.FILL, this.getFillColour());
        return element;
    }

    public SVGRectangle(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }

    public String getFillColour() {
        return fillColour;
    }

    public void setFillColour(String fillColour) {
        this.fillColour = fillColour;
    }
}
