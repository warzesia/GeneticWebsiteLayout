package page_components.tree_components;

import content_generators.ColourGenerator;
import content_generators.RandomLayoutGenerator;
import content_generators.LayoutGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import page_components.enums.ContentType;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 28/11/15.
 */
public class ViewportNode extends Node {

    private LinkedList<Node> children;


    @Override
    public void generateRandomly() {
        children = RandomLayoutGenerator.getRandomlyPlacedNodes(this.level);
        for(Node child: children)
            child.generateRandomly();
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
        LinkedList<Node> newChildren = new LinkedList<>();
        for(Node child: this.children)
            newChildren.add(child.copy());
        viewport.setChildren(newChildren);
        viewport.setMetadata(this.getMetadata());
        return viewport;
    }
    public ViewportNode copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public ViewportNode shallowCopyWithDifferentPlacement(Double x, Double y, Double width, Double height) {
        ViewportNode viewport = new ViewportNode(x, y, width, height, this.level);
        viewport.setChildren(this.children);
        viewport.setMetadata(this.getMetadata());
        return viewport;
    }
    public ViewportNode shallowCopy() {
        return shallowCopyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public Node getRandomNode(int ttl) {
        return (ttl==0 || ThreadLocalRandom.current().nextBoolean()) ?
                this : getRandomNonLeafChild().getRandomNode(ttl-1);
    }

    @Override
    public Node getRandomChild() {
        return this.children.get(ThreadLocalRandom.current().nextInt(this.children.size()));
    }

    public Node getRandomNonLeafChild() {
        LinkedList<Node> nonLeafChildren = new LinkedList<>();
        for(Node child: this.children)
            if(!child.getClass().equals(LeafNode.class))
                nonLeafChildren.add(child);

        if(nonLeafChildren.size()==0) return this;
        return nonLeafChildren.get(ThreadLocalRandom.current().nextInt(nonLeafChildren.size()));
    }

    public Node getRandomViewportChild() {
        for(Node child: this.children)
            if(child.getClass().equals(ViewportGroupNode.class))
                return child;
        return this;
    }

    @Override
    public void swapChild(Node childAlpha, Node childBeta) {
        childBeta.setLevel(childAlpha.getLevel());
        this.children.remove(childAlpha);
        this.children.add(childBeta.copyWithDifferentPlacement(
                childAlpha.getX(), childAlpha.getY(), childAlpha.getWidth(), childAlpha.getHeight()));
    }

    @Override
    public ViewportNode getMutation() {
        ViewportNode viewportMutation = this.copy();
        viewportMutation.setChildren(LayoutGenerator.mutateChildren(this.children));
        return viewportMutation;
    }

    @Override
    public LinkedList<Node> getMutations(int count) {
        LinkedList<Node> mutatedNodes = new LinkedList<>();
        List<LinkedList<Node>> mutatedChildrenList = LayoutGenerator.mutateChildren(this.children, count);
        for(int i=0; i<count+1; i++){
            ViewportNode mutatedNode = this.copy();
            mutatedNode.setChildren(mutatedChildrenList.get(i));
            mutatedNodes.add(mutatedNode);
        }
        return mutatedNodes;
    }

    @Override
    public void paintBackground(String colour, Boolean asBlock) {
        if(asBlock){
            for (Node child: this.children)
                child.paintBackground(colour, true);
        }
        else if(this.getMetadata().contains(ContentType.BLOCK)) {
            colour = ColourGenerator.getInstance().getRandomColourDifferentTo(colour);
            for (Node child: this.children)
                child.paintBackground(colour, true);
        } else {
            LinkedList<String> childrenColours = new LinkedList<>();
            childrenColours.add(colour);
            for (Node child: this.children){
                colour = ColourGenerator.getInstance().getRandomColourDifferentTo(childrenColours);
                child.paintBackground(colour, false);
                childrenColours.add(colour);
            }
        }
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
