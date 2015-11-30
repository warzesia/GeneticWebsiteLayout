package treeComponents;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Parser;
import tools.Strings;

/**
 * Created by warzesia on 29/11/15.
 */
public abstract class SVGElement {

    Double x;
    Double y;
    Double width;
    Double height;

    public abstract Element draw(Document document, String svgNamespace);
    public void setAttributes(Element element){
        element.setAttributeNS(null, Strings.X, Parser.DoubleToStringPercent(this.getX()));
        element.setAttributeNS(null, Strings.Y, Parser.DoubleToStringPercent(this.getY()));
        element.setAttributeNS(null, Strings.WIDTH, Parser.DoubleToStringPercent(this.getWidth()));
        element.setAttributeNS(null, Strings.HEIGHT, Parser.DoubleToStringPercent(this.getHeight()));
    }


    public SVGElement(Double x, Double y, Double width, Double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
