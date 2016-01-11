package page_components.tree_components;

import content_generators.ColourGenerator;
import content_generators.RandomElementGenerator;
import content_generators.RandomLayoutGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public class ViewportGroupNode extends Node {

    private LinkedList<Node> twinChildren;


    @Override
    public void generate() {
        Node twinChild = RandomLayoutGenerator.getRandomNode(0.0, 0.0, 0.0, 0.0, this.level);
        twinChild.generate();
        twinChildren = RandomLayoutGenerator.getRandomlyPlacedTwinChildren(twinChild);
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        for(Node twinChild: twinChildren)
            element.appendChild(twinChild.draw(document));
        return element;
    }

    @Override
    public ViewportGroupNode copyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        ViewportGroupNode viewportGroup = new ViewportGroupNode(x, y, width, height, this.level);
        viewportGroup.setTwinChildren(this.twinChildren);
        return viewportGroup;
    }

    public ViewportGroupNode copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public ViewportGroupNode getMutation() {
        ViewportGroupNode viewportGroupMutation = this.copy();
        viewportGroupMutation.setTwinChildren(RandomLayoutGenerator.getRandomlyPlacedTwinChildren(twinChildren.getFirst()));
        return viewportGroupMutation;
    }

    @Override
    public void paintBackground(String colour) {
        colour = this.getMetadata().isEmpty() ? colour : ColourGenerator.ColourGen.getRandomColour();
        for (Node child: this.twinChildren)
            child.paintBackground(colour);
    }

    public ViewportGroupNode(Integer level) {super(level);}
    public ViewportGroupNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public LinkedList<Node> getTwinChildren() {
        return twinChildren;
    }

    public void setTwinChildren(LinkedList<Node> twinChildren) {
        this.twinChildren = twinChildren;
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = "";
        for(Node child: twinChildren)
            childrenAttributes += (child.toString());
        return mainAttributes + childrenAttributes;
    }
}
