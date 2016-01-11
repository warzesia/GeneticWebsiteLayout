package page_components.tree_components;

import content_generators.ColourGenerator;
import content_generators.RandomLayoutGenerator;
import content_generators.websiteContentFactories.LayoutGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class ViewportNode extends Node {

    private LinkedList<Node> children;


    @Override
    public void generate() {
        children = RandomLayoutGenerator.getRandomlyPlacedNodes(this.level);
        for(Node child: children)
            child.generate();
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(Node child: children)
            element.appendChild(child.draw(document));
        return element;
    }

    @Override
    public ViewportNode copyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        ViewportNode viewport = new ViewportNode(x, y, width, height, this.level);
        viewport.setChildren(this.children);
        return viewport;
    }
    public ViewportNode copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public ViewportNode getMutation() {
        ViewportNode viewportMutation = this.copy();
        viewportMutation.setChildren(LayoutGenerator.mutateChildren(this.children));
        return viewportMutation;
    }

    @Override
    public void paintBackground(String colour) {
        colour = this.getMetadata().isEmpty() ? colour : ColourGenerator.ColourGen.getRandomColour();
        for (Node child: this.children)
            child.paintBackground(colour);
    }

    public ViewportNode(Integer level) {super(level);}
    public ViewportNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public LinkedList<Node> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Node> children) {
        this.children = children;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = "";
        for(Node child: children)
            childrenAttributes += (child.toString());
        return mainAttributes + childrenAttributes;
    }
}
