package page_components.tree_components;

import content_generators.ColourGenerator;
import content_generators.RandomContentGenerator;
import content_generators.RandomElementGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import page_components.DrawablePageElement;
import page_components.PageElement;
import page_components.SVGRectangle;
import tools.Parsers;

/**
 * Created by warzesia on 28/11/15.
 */
public class LeafNode extends Node {

    SVGRectangle backgroundRectangle = RandomContentGenerator.getBackgroundRectangle();
    DrawablePageElement contentElement;


    @Override
    public void generate() {
        contentElement = RandomElementGenerator.getRandomDrawable();
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        element.appendChild(backgroundRectangle.draw(document));
        element.appendChild(contentElement.draw(document));
        return element;
    }

    @Override
    public LeafNode copyWithDifferentPlacement(Double x, Double y, Double width, Double height){
        LeafNode leafCopy = new LeafNode(x, y, width, height, this.level);
        leafCopy.setBackgroundRectangle(this.backgroundRectangle);
        leafCopy.setContentElement(this.contentElement);
        return leafCopy;
    }

    public LeafNode copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public LeafNode getMutation() {
        LeafNode leafMutation = this.copy();
        leafMutation.setContentElement(RandomElementGenerator.getRandomlyPlacedDrawable(this.contentElement));
        return leafMutation;
    }

    @Override
    public void paintBackground(String colour) {
//        colour = this.getMetadata().isEmpty() ? colour : ColourGenerator.ColourGen.getRandomColour();
        backgroundRectangle.setFillColour(colour);
    }

    public LeafNode(Integer level) {super(level);}
    public LeafNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public DrawablePageElement getContentElement() {
        return contentElement;
    }

    public void setContentElement(DrawablePageElement contentElement) {
        this.contentElement = contentElement;
    }

    public SVGRectangle getBackgroundRectangle() {
        return backgroundRectangle;
    }

    public void setBackgroundRectangle(SVGRectangle backgroundRectangle) {
        this.backgroundRectangle = backgroundRectangle;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = Parsers.LevelToPrefix(this.level+1) + contentElement.toString();
        return mainAttributes + childrenAttributes;
    }

}
