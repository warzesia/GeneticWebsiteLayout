package page_components.tree_components;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import page_components.PageElement;
import tools.Constants;
import tools.Strings;
import tools.Parsers;

/**
 * Created by warzesia on 28/11/15.
 */
public abstract class Node extends PageElement {

    protected Integer level;

    public Node copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public abstract Node copyWithDifferentPlacement(Double x, Double y, Double width, Double height);
    public abstract Node getMutation();
    public abstract void paintBackground(String colour);
    public abstract void generate();

    @Override
    public Element draw(Document document) {
        Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.SVG);
        setAttributes(element);
        return element;
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

    public String toString(){
        return Parsers.LevelToPrefix(level) + super.toString();
    }

}
