package treeComponents;

import SVGDomFactory.SVGCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class SVGViewport extends SVGNode {

    LinkedList<SVGNode> children;

    @Override
    public Element draw(Document document, String svgNamespace) {
        Element element = super.draw(document, svgNamespace);
        for(SVGNode child: children)
            element.appendChild(child.draw(document, svgNamespace));
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
