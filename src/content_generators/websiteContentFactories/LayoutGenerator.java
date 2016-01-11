package content_generators.websiteContentFactories;

import content_generators.RandomLayoutGenerator;
import page_components.PageElement;
import page_components.tree_components.Node;
import tools.Parameters;
import tools.Parsers;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 10/01/16.
 */
public class LayoutGenerator {

    static public LinkedList<Node> mutateChildren(LinkedList<Node> children) {
        for(Node childOne: children)
            for(Node childTwo: children){
                if (isAbove(childOne, childTwo) && bool()){

                    System.out.println(childOne.toString());
                    System.out.println("is above");
                    System.out.println(childTwo.toString());

                    return mutateHorizontalBorder(childOne, childTwo, children);

                } else if (isOnLeft(childOne, childTwo) && bool()){

                    System.out.println(childOne.toString());
                    System.out.println("is next to");
                    System.out.println(childTwo.toString());

                    return mutateVerticalBorder(childOne, childTwo, children);
                }

            }
        return children;
    }



    private static boolean isAbove(Node nodeUp, Node nodeDown){
        return (nodeUp.getX().equals(nodeDown.getX()) &&
                nodeUp.getWidth().equals(nodeDown.getWidth()) &&
                nodeUp.getY() + nodeUp.getHeight() == nodeDown.getY());
    }

    private static boolean isOnLeft(Node nodeLeft, Node nodeRight){
        return (nodeLeft.getY().equals(nodeRight.getY()) &&
                nodeLeft.getHeight().equals(nodeRight.getWidth()) &&
                nodeLeft.getX() + nodeLeft.getWidth() == nodeRight.getX());
    }

    private static LinkedList<Node> mutateHorizontalBorder(Node nodeUp, Node nodeDown, LinkedList<Node> children){
        Double move;
        if(bool()){
            //mutate UP
            move = - Parsers.DoubleToRoundedDouble(ThreadLocalRandom.current().nextDouble(nodeUp.getHeight()));
        } else {
            //mutate DOWN
            move = Parsers.DoubleToRoundedDouble(ThreadLocalRandom.current().nextDouble(nodeDown.getHeight()));
        }

        children.remove(nodeUp);
        children.remove(nodeDown);

        LinkedList<Node> mutatedChildren = new LinkedList<>();
        for(Node child: children)
            mutatedChildren.add(child.copy());

        Node nodeUp2 = nodeUp.copyWithDifferentPlacement(
                nodeUp.getX(), nodeUp.getY(), nodeUp.getWidth(), nodeUp.getHeight() + move);
        Node nodeDown2 = nodeDown.copyWithDifferentPlacement(
                nodeDown.getX(), nodeDown.getY() + move, nodeDown.getWidth(), nodeDown.getHeight() - move);


        mutatedChildren.add(nodeUp2);
        mutatedChildren.add(nodeDown2);

        children.add(nodeUp);
        children.add(nodeDown);

        return mutatedChildren;
    }

    private static LinkedList<Node> mutateVerticalBorder(Node nodeLeft, Node nodeRight, LinkedList<Node> children){
        Double move;
        if(bool()){
            //mutate LEFT
            move = -Parsers.DoubleToRoundedDouble(ThreadLocalRandom.current().nextDouble(nodeLeft.getWidth()));
        } else {
            //mutate RIGHT
            move = Parsers.DoubleToRoundedDouble(ThreadLocalRandom.current().nextDouble(nodeRight.getWidth()));
        }

        children.remove(nodeLeft);
        children.remove(nodeRight);

        LinkedList<Node> mutatedChildren = new LinkedList<>();
        for(Node child: children)
            mutatedChildren.add(child.copy());
        mutatedChildren.add(nodeLeft.copyWithDifferentPlacement(
                nodeLeft.getX(), nodeLeft.getY(), nodeLeft.getWidth() + move, nodeLeft.getHeight()));
        mutatedChildren.add(nodeRight.copyWithDifferentPlacement(
                nodeRight.getX() + move, nodeRight.getY(), nodeRight.getWidth() - move, nodeRight.getHeight() ));

        children.add(nodeLeft);
        children.add(nodeRight);

        return mutatedChildren;
    }

    private static boolean bool(){
        return ThreadLocalRandom.current().nextInt(2)/2 == 0;
    }




}
