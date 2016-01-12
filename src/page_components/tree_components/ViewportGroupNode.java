package page_components.tree_components;

import content_generators.RandomLayoutGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 28/11/15.
 */
public class ViewportGroupNode extends Node {

    private Node child;
    private LinkedList<Node> twinChildren;


    @Override
    public void generateRandomly() {
        child = RandomLayoutGenerator.getRandomNode(0.0, 0.0, 0.0, 0.0, this.level);
        child.generateRandomly();
        this.setChild(child);
        this.setTwinChildren(RandomLayoutGenerator.getRandomlyPlacedTwinChildren(child));
        this.updateTwinChildren();
    }

    @Override
    public Element draw(Document document) {
        Element element = super.draw(document);
        this.updateTwinChildren();
        for(Node twinChild: this.twinChildren)
            element.appendChild(twinChild.draw(document));
        return element;
    }

    @Override
    public ViewportGroupNode copyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        ViewportGroupNode viewportGroup = new ViewportGroupNode(x, y, width, height, this.level);
        LinkedList<Node> newTwinChildren = new LinkedList<>();
        for(Node child: this.twinChildren)
            newTwinChildren.add(child.copy());

        viewportGroup.setTwinChildren(newTwinChildren);
        viewportGroup.setMetadata(this.getMetadata());
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
    public LinkedList<ViewportNode> getMutations(int count) {
        return null;
    }

    @Override
    public Node getRandomNode(int ttl) {
        return (ttl==0 || ThreadLocalRandom.current().nextBoolean()) ?
                this : getRandomNonLeafChild().getRandomNode(ttl - 1);
    }

    @Override
    public Node getRandomChild() {
        return this.twinChildren.get(ThreadLocalRandom.current().nextInt(this.twinChildren.size()));
    }


    public Node getRandomNonLeafChild() {
        if(this.child.getClass().equals(LeafNode.class))
            return this;
        return this.child;
    }

    @Override
    public void swapChild(Node childAlpha, Node childBeta) {
        this.setChild(childAlpha);
    }

    @Override
    public void paintBackground(String colour) {
        this.child.paintBackground(colour);
        for (Node child: this.twinChildren)
            child.paintBackground(colour);
    }

    public ViewportGroupNode(Integer level) {super(level);}
    public ViewportGroupNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public LinkedList<Node> getTwinChildren() {
        return this.twinChildren;
    }

    public void setTwinChildren(LinkedList<Node> twinChildren) {
        this.child = twinChildren.getFirst();
        this.twinChildren = twinChildren;
    }

    private void updateTwinChildren() {
        LinkedList<Node> newTwinChildren = new LinkedList<>();
        for(Node oldChild: this.twinChildren)
            newTwinChildren.add(this.child.copyWithDifferentPlacement(
                    oldChild.getX(), oldChild.getY(), oldChild.getWidth(), oldChild.getHeight()
            ));
    }

    public void setChild(Node child) {
        this.child = child;
        this.updateTwinChildren();
    }

    public String toString(){
        String mainAttributes = super.toString();
        String childrenAttributes = "";
        for(Node child: twinChildren)
            childrenAttributes += (child.toString());
        return mainAttributes + childrenAttributes;
    }
}
