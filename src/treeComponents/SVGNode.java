package treeComponents;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Strings;

/**
 * Created by warzesia on 28/11/15.
 */
public abstract class SVGNode extends SVGElement {

    protected Integer level;

    @Override
    public Element draw(Document document, String svgNamespace) {
        Element element = document.createElementNS(svgNamespace, Strings.SVG);
        super.setAttributes(element);
        return element;
    }

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
