package content_generators;

import content_generators.RandomLayoutGenerator;
import page_components.DrawablePageElement;
import page_components.PageElement;
import page_components.tree_components.Node;
import tools.Constants;
import tools.Parameters;
import tools.Parsers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by warzesia on 10/01/16.
 */
public class LayoutGenerator {

    static public DrawablePageElement mutateDrawable(DrawablePageElement drawable){
        return LayoutGenerator.mutateDrawable(drawable, 1).get(0);
    }

    static public LinkedList<DrawablePageElement> mutateDrawable(DrawablePageElement drawable, int count){
        LinkedList<DrawablePageElement> mutatedDrawable = new LinkedList<>();
        for(int i=0; i<count+1; i++)
            mutatedDrawable.add(RandomElementGenerator.getRandomlyPlacedDrawable(drawable));
        return mutatedDrawable;
    }

    static public LinkedList<Node> mutateTwinChildren(Node child){
        return LayoutGenerator.mutateTwinChildren(child, 1).get(0);
    }
    static public List<LinkedList<Node>> mutateTwinChildren(Node child, int count) {
        LinkedList<LinkedList<Node>> mutatedChildrenList = new LinkedList<>();
        for(int i=0; i<count+1; i++)
            mutatedChildrenList.add(RandomLayoutGenerator.getRandomlyPlacedTwinChildren(child.copy()));
        return mutatedChildrenList;
    }


    static public LinkedList<Node> mutateChildren(LinkedList<Node> children){
        return LayoutGenerator.mutateChildren(children, 1).get(0);
    }
    static public List<LinkedList<Node>> mutateChildren(LinkedList<Node> children, int count) {
        LinkedList<LinkedList<Node>> mutatedChildrenList = new LinkedList<>();
        for(Node childOne: children)
            for(Node childTwo: children){
                if (isAbove(childOne, childTwo)){
                    mutatedChildrenList.add(mutateHorizontalBorder(childOne, childTwo, children));
                } else if (isOnLeft(childOne, childTwo)){
                    mutatedChildrenList.add(mutateVerticalBorder(childOne, childTwo, children));
                }
            }

        for(int i=mutatedChildrenList.size(); i<count+1; i++)
            mutatedChildrenList.add(i, children);

        return mutatedChildrenList.subList(0, count+1);
    }



    private static boolean isAbove(Node nodeUp, Node nodeDown){
        return (nodeUp.getX().equals(nodeDown.getX()) &&
                nodeUp.getWidth().equals(nodeDown.getWidth()) &&
                Parsers.DoubleToRoundedDouble(nodeUp.getY() + nodeUp.getHeight()).equals(nodeDown.getY()));
    }

    private static boolean isOnLeft(Node nodeLeft, Node nodeRight){
        return (nodeLeft.getY().equals(nodeRight.getY()) &&
                nodeLeft.getHeight().equals(nodeRight.getWidth()) &&
                Parsers.DoubleToRoundedDouble(nodeLeft.getX() + nodeLeft.getWidth()).equals(nodeRight.getX()));
    }

    private static LinkedList<Node> mutateHorizontalBorder(Node nodeUp, Node nodeDown, LinkedList<Node> children){
        Double move;
        if(bool()) move = - getMove(nodeUp.getHeight());
        else move = getMove(nodeDown.getHeight());

        Node mutatedNodeUp = nodeUp.copyWithDifferentPlacement(
                nodeUp.getX(), nodeUp.getY(),
                nodeUp.getWidth(), Parsers.DoubleToRoundedDouble(nodeUp.getHeight() + move));
        Node mutatedNodeDown = nodeDown.copyWithDifferentPlacement(
                nodeDown.getX(), Parsers.DoubleToRoundedDouble(nodeDown.getY() + move),
                nodeDown.getWidth(), Parsers.DoubleToRoundedDouble(nodeDown.getHeight() - move));

        return getMutatedChildren(nodeUp, nodeDown, mutatedNodeUp, mutatedNodeDown, children);
    }

    private static LinkedList<Node> mutateVerticalBorder(Node nodeLeft, Node nodeRight, LinkedList<Node> children){
        Double move;
        if(bool()) move = - getMove(nodeLeft.getWidth()*0.7);
        else move = getMove(nodeRight.getWidth());

        Node mutatedNodeLeft = nodeLeft.copyWithDifferentPlacement(
                nodeLeft.getX(), nodeLeft.getY(), nodeLeft.getWidth() + move, nodeLeft.getHeight());
        Node mutatedNodeRight = nodeRight.copyWithDifferentPlacement(
                nodeRight.getX() + move, nodeRight.getY(), nodeRight.getWidth() - move, nodeRight.getHeight());

        return getMutatedChildren(nodeLeft, nodeRight, mutatedNodeLeft, mutatedNodeRight, children);
    }

    static private Double getMove(Double range){
        return Parsers.DoubleToRoundedDouble(
                ThreadLocalRandom.current()
                .nextDouble(Math.max(range*Parameters.MAX_MUTATION_MOVE-Parameters.MIN_MUTATION_MOVE, 0.01))
                + Parameters.MIN_MUTATION_MOVE - 0.01);
    }

    private static LinkedList<Node> getMutatedChildren(Node node, Node node_, Node mutatedNode, Node mutatedNode_, LinkedList<Node> originalChildren){
        LinkedList<Node> mutatedChildren =
                originalChildren.stream().collect(Collectors.toCollection(() -> new LinkedList<>()));
        mutatedChildren.remove(node);
        mutatedChildren.remove(node_);
        mutatedChildren.add(mutatedNode);
        mutatedChildren.add(mutatedNode_);
        return mutatedChildren;
    }

    private static boolean bool(){
        Integer chosen = ThreadLocalRandom.current().nextInt(10);
        return chosen/2 == 0;
    }




}
