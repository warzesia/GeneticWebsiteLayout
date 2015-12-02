package treeComponents;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Constants;
import tools.Strings;

/**
 * Created by warzesia on 28/11/15.
 */
public abstract class SVGNode extends SVGElement {

    protected Integer level;

    public abstract void generate();
    public static SVGNode generateRandomFirstNode(Integer n){
        SVGNode svgNode;
        if (n<10)
            svgNode = new SVGLeaf(0.0, 0.0, 1.0, 1.0, 0);
        else
            svgNode = new SVGViewport(0.0, 0.0, 1.0, 1.0, 0);
        svgNode.generate();
        return svgNode;
    }

    @Override
    public Element draw(Document document) {
        Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.SVG);
        super.setAttributes(element);
        return element;
    }

    public SVGNode(){}
    public SVGNode(Double x, Double y, Double width, Double height, Integer level) {
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
