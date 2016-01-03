package treeComponents;

import evolution.LayoutFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class SVGViewport extends SVGNode {

    private LinkedList<SVGNode> children;


    @Override
    public SVGNode copyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        SVGViewport viewport = new SVGViewport(x, y, width, height, this.level);
        viewport.setChildren(this.children);
        return viewport;
    }

    @Override
    public void generate() {
        children = LayoutFactory.getRandomlyPlacedNodes(this.level, false);
        for(SVGNode child: children)
            child.generate();
        //children.forEach(treeComponents.SVGNode::generate);
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(SVGNode child: children)
            element.appendChild(child.draw(document));
        return element;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = "";
        for(SVGNode child: children)
            childrenAttributes += (child.toString());
        return mainAttributes + childrenAttributes;
    }


    public SVGViewport(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }


    public LinkedList<SVGNode> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<SVGNode> children) {
        this.children = children;
    }
}
