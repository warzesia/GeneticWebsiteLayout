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
    public void generate() {
        children = new LinkedList<>();
//        LayoutFactory.getRandomNode(this.level);
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(SVGNode child: children)
            element.appendChild(child.draw(document));
        return element;
    }

    public SVGViewport(){}
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
