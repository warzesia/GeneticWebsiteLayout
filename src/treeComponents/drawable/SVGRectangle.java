package treeComponents.drawable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Constants;
import tools.Parsers;
import tools.Strings;
import treeComponents.SVGElement;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGRectangle extends SVGElement {

    String fillColour = "blue";
    String strokeColour = "black";
    Double fillOpacity= 0.5;
    Double strokeOpacity = 1.0;
    Integer strokeWidth = 4;



    @Override
    public Element draw(Document document) {
        Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.RECTANGLE);
        setAttributes(element);
        element.setAttributeNS(null, Strings.FILL, this.getFillColour());
        element.setAttributeNS(null, Strings.STROKE, this.getStrokeColour());
        element.setAttributeNS(null, Strings.FILL_OPACITY, Parsers.DoubleToString(this.getFillOpacity()));
        return element;
    }

    public SVGRectangle(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }

    public SVGRectangle(Double x, Double y, Double width, Double height,
                        String fillColour, Double fillOpacity,
                        String strokeColour, Integer strokeWidth, Double strokeOpacity) {
        super(x, y, width, height);
        this.fillColour = fillColour;
        this.fillOpacity = fillOpacity;
        this.strokeColour = strokeColour;
        this.strokeWidth = strokeWidth;
        this.strokeOpacity = strokeOpacity;
    }

    public String getFillColour() {
        return fillColour;
    }

    public void setFillColour(String fillColour) {
        this.fillColour = fillColour;
    }

    public String getStrokeColour() {
        return strokeColour;
    }

    public Double getFillOpacity() {
        return fillOpacity;
    }
}
