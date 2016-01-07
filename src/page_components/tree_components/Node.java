package page_components.tree_components;

import contentFactories.ElementFactory;
import org.w3c.dom.Document;
import page_components.PageElement;
import tools.Constants;
import tools.Strings;
import tools.Parsers;
import page_components.SVGRectangle;

/**
 * Created by warzesia on 28/11/15.
 */
public abstract class Node extends PageElement {

    SVGRectangle backgroundRectangle = ElementFactory.getBackgroundRectangle();
    protected Integer level;

    public abstract Node copyWithDifferentPlacement(Double x, Double y, Double width, Double height);
    public abstract void generate();

    @Override
    public org.w3c.dom.Element draw(Document document) {
        org.w3c.dom.Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.SVG);
        element.appendChild(backgroundRectangle.draw(document));
        setAttributes(element);
        return element;
    }

    public String toString(){
        return Parsers.LevelToPrefix(level) + super.toString();
    }

    public Node(Integer level){
        super();
        this.level = level;
    }
    public Node(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height);
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
