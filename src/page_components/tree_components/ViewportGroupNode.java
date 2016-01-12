package page_components.tree_components;

import content_generators.ColourGenerator;
import content_generators.LayoutGenerator;
import content_generators.RandomLayoutGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import page_components.DrawablePageElement;
import page_components.enums.ContentType;

import java.util.LinkedList;
import java.util.List;
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
    public ViewportGroupNode shallowCopyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        ViewportGroupNode viewportGroup = new ViewportGroupNode(x, y, width, height, this.level);
        viewportGroup.setTwinChildren(this.twinChildren);
        viewportGroup.setMetadata(this.getMetadata());
        return viewportGroup;
    }

    public ViewportGroupNode shallowCopy() {
        return shallowCopyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public ViewportGroupNode getMutation() {
        ViewportGroupNode viewportGroupMutation = this.copy();
        viewportGroupMutation.setTwinChildren(
                RandomLayoutGenerator.getRandomlyPlacedTwinChildren(twinChildren.getFirst()));
        return viewportGroupMutation;
    }

    @Override
    public LinkedList<Node> getMutations(int count) {
        LinkedList<Node> mutatedNodes = new LinkedList<>();
        List<LinkedList<Node>> mutatedChildrenList = LayoutGenerator.mutateTwinChildren(this.child, count);

        for(int i=0; i<count+1; i++){
            ViewportGroupNode mutatedNode = this.copy();
            mutatedNode.setTwinChildren((mutatedChildrenList.get(i)));
            mutatedNodes.add(mutatedNode);
        }
        return mutatedNodes;
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
        childBeta.setLevel(childAlpha.getLevel());
        this.setChild(childBeta);
    }

    @Override
    public void paintBackground(String colour, Boolean asBlock) {
        if(asBlock)
            this.child.paintBackground(colour, true);
        else if(this.getMetadata().contains(ContentType.BLOCK)){
            colour = ColourGenerator.getInstance().getRandomColourDifferentTo(colour);
            this.child.paintBackground(colour, true);
        }
        else{
            colour = ColourGenerator.getInstance().getRandomColourDifferentTo(colour);
            this.child.paintBackground(colour, false);
        }
        this.updateTwinChildren();
    }

    public ViewportGroupNode(Integer level) {
        super(level);
    }

    public ViewportGroupNode(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height, level);
    }

    public LinkedList<Node> getTwinChildren() {
        return this.twinChildren;
    }

    public void setTwinChildren(LinkedList<Node> twinChildren) {
        this.child = twinChildren.getFirst();
        this.twinChildren = twinChildren;
        updateTwinChildren();
    }

    private void updateTwinChildren() {
        LinkedList<Node> newTwinChildren = new LinkedList<>();
        for(Node oldChild: this.twinChildren)
            newTwinChildren.add(this.child.shallowCopyWithDifferentPlacement(
                    oldChild.getX(), oldChild.getY(), oldChild.getWidth(), oldChild.getHeight()
            ));
        this.twinChildren = newTwinChildren;
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
