package page_components.tree_components;

import contentFactories.LayoutFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class ViewportNode extends Node {

    private LinkedList<Node> children;


    @Override
    public void generate() {
        children = LayoutFactory.getRandomlyPlacedNodes(this.level);
        for(Node child: children)
            child.generate();
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(Node child: children)
            element.appendChild(child.draw(document));
        return element;
    }

    @Override
    public Node copyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        ViewportNode viewport = new ViewportNode(x, y, width, height, this.level);
        viewport.setChildren(this.children);
        return viewport;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = "";
        for(Node child: children)
            childrenAttributes += (child.toString());
        return mainAttributes + childrenAttributes;
    }


    public ViewportNode(Integer level) {
        super(level);
    }
    public ViewportNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }


    public LinkedList<Node> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }
}
