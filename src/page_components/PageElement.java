package page_components;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import page_components.enums.ContentType;
import tools.Parsers;
import tools.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by warzesia on 29/11/15.
 */
public abstract class PageElement {

    Double x;
    Double y;
    Double width;
    Double height;

    Set<ContentType> metadata = new HashSet<>();

    public abstract Element draw(Document document);
    public void setAttributes(Element element){
        element.setAttributeNS(null, Strings.X, Parsers.DoubleToStringPercent(this.getX()));
        element.setAttributeNS(null, Strings.Y, Parsers.DoubleToStringPercent(this.getY()));
        element.setAttributeNS(null, Strings.WIDTH, Parsers.DoubleToStringPercent(this.getWidth()));
        element.setAttributeNS(null, Strings.HEIGHT, Parsers.DoubleToStringPercent(this.getHeight()));
    }

    public PageElement(){ this(0.0, 0.0, 1.0, 1.0);}
    public PageElement(Double x, Double y, Double width, Double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String toString(){
        return  Parsers.ShortenedClassName(this.getClass().toString()) +
                " [ " + this.x + ", " + this.y + ", " + this.width + ", " + this.height + "] \n";
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

    public void setMetadata(Set<ContentType> metadata) {
        this.metadata = metadata;
    }

    public Set<ContentType> getMetadata() {
        return metadata;
    }

    public void addMetadata(ContentType tag) {this.metadata.add(tag);}
}
