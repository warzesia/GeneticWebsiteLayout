package treeComponents;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by warzesia on 28/11/15.
 */
public class SVGLeaf extends SVGNode {

    SVGElement contentElement;

    @Override
    public Element draw(Document document, String svgNamespace) {
        Element element = super.draw(document, svgNamespace);
        element.appendChild(contentElement.draw(document, svgNamespace));
        return element;
    }

    public SVGLeaf(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public SVGElement getContentElement() {
        return contentElement;
    }

    public void setContentElement(SVGElement contentElement) {
        this.contentElement = contentElement;
    }
}