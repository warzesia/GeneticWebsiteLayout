package page_components.tree_components;

import content_generators.ColourGenerator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import page_components.PageElement;
import tools.Constants;
import tools.Strings;
import tools.Parsers;

import java.util.LinkedList;

/**
 * Created by warzesia on 28/11/15.
 */
public abstract class Node extends PageElement {

    protected Integer level;

    public Node copy() {
        return copyWithDifferentPlacement(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    public abstract Node copyWithDifferentPlacement(Double x, Double y, Double width, Double height);
    public abstract Node getRandomNode(int ttl);
    public abstract Node getRandomChild();
    public abstract void swapChild(Node childAlpha, Node childBeta);
    public abstract Node getMutation();
    public abstract LinkedList<ViewportNode> getMutations(int count);
    public abstract void paintBackground(String colour);
    public abstract void generateRandomly();

    public Node getCrossover(Node partner) {
        return getCrossoverAtMaxLevel(partner, 100);
    }
    public Node getCrossoverAtMaxLevel(Node partner, int ttl) {
        Node offspring = this.copy();
        Node offspringNode = offspring.getRandomNode(ttl);
        Node geneAlpha = offspringNode.getRandomChild();
        Node geneBeta = partner.getRandomNode(ttl).getRandomChild();
        offspringNode.swapChild(geneAlpha, geneBeta);
        offspring.paintBackground(ColourGenerator.getInstance().getRandomColour());
        return offspring;
    }

    @Override
    public Element draw(Document document) {
        Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.SVG);
        setAttributes(element);
        return element;
    }

    public Node(Integer level){
        super();
        this.level = level;
    }
    public Node(Double x, Double y, Double width, Double height, Integer level) {
        super(x, y, width, height);
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String toString(){
        return Parsers.LevelToPrefix(level) + super.toString();
    }

}
