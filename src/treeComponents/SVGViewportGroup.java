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
    public void generate() {
        SVGNode twinChild = LayoutFactory.getRandomNode(this.level);
        twinChild.generate();
        twinChildren = LayoutFactory.twinNode(twinChild);
        //populate twinChild into twinChildren and add them all to twinChildren

    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(SVGNode twinChild: twinChildren)
            element.appendChild(twinChild.draw(document));
        return element;
    }

    public SVGViewportGroup(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }


}
