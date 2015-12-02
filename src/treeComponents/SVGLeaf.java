package treeComponents;

import evolution.LayoutFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by warzesia on 28/11/15.
 */
public class SVGLeaf extends SVGNode {

    SVGElement contentElement;

    //generate to przypisanie wartości dla pól które posiada węzeł
    @Override
    public void generate() {
        contentElement = LayoutFactory.getRandomDrawable();
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        element.appendChild(contentElement.draw(document));
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
