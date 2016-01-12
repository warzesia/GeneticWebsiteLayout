package page_components;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Constants;
import tools.Parsers;
import tools.Strings;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGRectangle extends DrawablePageElement {

    String fillColour = "blue";
    String strokeColour = "black";
    Double fillOpacity= 1.0;
    Double strokeOpacity = 1.0;
    Integer strokeWidth = 4;


    @Override
    public Element draw(Document document) {
        Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.RECTANGLE);
        setAttributes(element);
        element.setAttributeNS(null, Strings.FILL, this.getFillColour());
//        element.setAttributeNS(null, Strings.STROKE, this.getStrokeColour());
//        element.setAttributeNS(null, Strings.FILL_OPACITY, Parsers.DoubleToString(this.getFillOpacity()));
        return element;
    }



    public SVGRectangle copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public SVGRectangle copyWithDifferentPlacement(Double x, Double y, Double width, Double height){
        SVGRectangle rectangleCopy = new SVGRectangle(x, y, width, height);
        rectangleCopy.setFillColour(this.fillColour);
        rectangleCopy.setFillOpacity(this.fillOpacity);
        rectangleCopy.setStrokeColour(this.strokeColour);
        rectangleCopy.setStrokeOpacity(this.strokeOpacity);
        rectangleCopy.setStrokeWidth(this.strokeWidth);
        return rectangleCopy;
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

    public String getStrokeColour() {
        return strokeColour;
    }

    public void setStrokeColour(String strokeColour) {
        this.strokeColour = strokeColour;
    }

    public Double getFillOpacity() {
        return fillOpacity;
    }

    public void setFillOpacity(Double fillOpacity) {
        this.fillOpacity = fillOpacity;
    }

    public Double getStrokeOpacity() {
        return strokeOpacity;
    }

    public void setStrokeOpacity(Double strokeOpacity) {
        this.strokeOpacity = strokeOpacity;
    }

    public Integer getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Integer strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
