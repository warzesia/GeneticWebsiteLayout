package page_components.tree_components;

import contentFactories.ElementFactory;
import org.w3c.dom.Document;
import page_components.PageElement;
import tools.Parsers;

/**
 * Created by warzesia on 28/11/15.
 */
public class LeafNode extends Node {

    PageElement contentElement;

    //generate to przypisanie wartości dla pól które posiada węzeł
    @Override
    public void generate() {
        contentElement = ElementFactory.getRandomDrawable();
    }

    @Override
    public org.w3c.dom.Element draw(Document document) {
        org.w3c.dom.Element element = super.draw(document);
        element.appendChild(contentElement.draw(document));
        return element;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttrbutes = Parsers.LevelToPrefix(this.level+1) + contentElement.toString();
        return mainAttributes + childrenAttrbutes;
    }

    public LeafNode(Integer level) {
        super(level);
    }
    public LeafNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public PageElement getContentElement() {
        return contentElement;
    }

    public void setContentElement(PageElement contentElement) {
        this.contentElement = contentElement;
    }

    public Node copyWithDifferentPlacement(Double x, Double y, Double width, Double height){
        LeafNode leafCopy = new LeafNode(x, y, width, height, this.level);
        leafCopy.setContentElement(this.contentElement);
        return leafCopy;
    }

}
