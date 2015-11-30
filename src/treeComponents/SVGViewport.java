package treeComponents;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class SVGViewport extends SVGNode {

    LinkedList<SVGNode> children;

    @Override
    public SVGElement generate() {
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(SVGNode child: children)
            element.appendChild(child.draw(document));
        return element;
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
