package treeComponents;

import evolution.LayoutFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class SVGViewportGroup extends SVGNode {

    private LinkedList<SVGNode> twinChildren;


    @Override
    public SVGNode copyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        SVGViewportGroup viewportGroup = new SVGViewportGroup(x, y, width, height, this.level);
        viewportGroup.setTwinChildren(this.twinChildren);
        return  viewportGroup;
    }

    @Override
    public void generate() {
        SVGNode twinChild = LayoutFactory.getRandomNode(0.0, 0.0, 0.0, 0.0, this.level);
        twinChild.generate();
        twinChildren = LayoutFactory.getRandomlyPlacedTwinChildren(twinChild);
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(SVGNode twinChild: twinChildren)
            element.appendChild(twinChild.draw(document));
        return element;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = "";
        for(SVGNode child: twinChildren)
            childrenAttributes += (child.toString());
        return mainAttributes + childrenAttributes;
    }

    public SVGViewportGroup(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public LinkedList<SVGNode> getTwinChildren() {
        return twinChildren;
    }

    public void setTwinChildren(LinkedList<SVGNode> twinChildren) {
        this.twinChildren = twinChildren;
    }
}
