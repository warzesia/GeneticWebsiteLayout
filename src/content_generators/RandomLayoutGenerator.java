package content_generators;

import page_components.tree_components.LeafNode;
import page_components.tree_components.Node;
import page_components.tree_components.ViewportGroupNode;
import page_components.tree_components.ViewportNode;
import page_components.tree_components.enums.NodeType;
import tools.Parameters;
import tools.Parsers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 30/11/15.
 */
public class RandomLayoutGenerator {


    static public Node getRandomRootNode(){
        return getRandomNode(0.0, 0.0, 1.0, 1.0, 0);
    }

    public static Node getRandomNode(Double x, Double y, Double width, Double height, Integer parentLevel){
        Integer childLevel = parentLevel+1;

        //the limit
        NodeType nodeType = (childLevel.equals(Parameters.MAX_SVG_TREE_DEPTH)) ?
                NodeType.LEAF : getRandomNodeType(childLevel);

        switch (nodeType) {
            case LEAF: return new LeafNode(x, y, width, height, childLevel);
            case VIEWPORT: return new ViewportNode(x, y, width, height, childLevel);
            case VIEWPORT_GROUP: return new ViewportGroupNode(x, y, width, height, childLevel);
            default: return new LeafNode(x, y, width, height, childLevel);
        }
    }

    static public LinkedList<Node> getRandomlyPlacedNodes(LinkedList<Node> children, Integer parentLevel) {
        LinkedList<Node> placedRandomNodes;

        do{ //to obtain good setup
            placedRandomNodes = getRandomlyPlacedNodes(parentLevel);
        }
        while(placedRandomNodes.size() != children.size());

        LinkedList<Node> placedChildren = new LinkedList<>();
        for(int i=0; i<children.size(); i++){
            Node node = placedRandomNodes.get(i);
            placedChildren.add(i, children.get(i).copyWithDifferentPlacement(
                    node.getX(), node.getY(), node.getWidth(), node.getHeight()));
        }
        return placedChildren;
    }

    static public LinkedList<Node> getRandomlyPlacedNodes(Integer parentLevel) {

        LinkedList<Node> nodes = new LinkedList<>();
        Double angle = getRandomAngle();
        Double lineCutX = getRandomCut();
        Double lineCutY = getRandomCut();
        System.out.println("LineCutX: " + lineCutX);
        System.out.println("LineCutY: " + lineCutY);


        //Let's let the radius decide whether the viewport is gonna be cut horizontally, vertically or into 4
        if(angle<45){
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    0.0, 0.0,
                    Parsers.DoubleToRoundedDouble(lineCutX), 1.0,
                    parentLevel));
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    Parsers.DoubleToRoundedDouble(lineCutX), 0.0,
                    Parsers.DoubleToRoundedDouble(1.0 - lineCutX), 1.0,
                    parentLevel));

        } else if (angle==45) {
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    0.0, 0.0,
                    Parsers.DoubleToRoundedDouble(lineCutX), Parsers.DoubleToRoundedDouble(lineCutY),
                    parentLevel));
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    Parsers.DoubleToRoundedDouble(lineCutX), 0.0,
                    Parsers.DoubleToRoundedDouble(1.0 - lineCutX), Parsers.DoubleToRoundedDouble(lineCutY),
                    parentLevel));
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    0.0, Parsers.DoubleToRoundedDouble(lineCutY),
                    Parsers.DoubleToRoundedDouble(lineCutX), Parsers.DoubleToRoundedDouble(1.0 - lineCutY),
                    parentLevel));
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    Parsers.DoubleToRoundedDouble(lineCutX), Parsers.DoubleToRoundedDouble(lineCutY),
                    Parsers.DoubleToRoundedDouble(1.0 - lineCutX), Parsers.DoubleToRoundedDouble(1.0 - lineCutY),
                    parentLevel));

        } else if (angle>45){
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    0.0, 0.0,
                    1.0, Parsers.DoubleToRoundedDouble(lineCutY),
                    parentLevel));
            nodes.add(RandomLayoutGenerator.getRandomNode(
                    0.0, Parsers.DoubleToRoundedDouble(lineCutY),
                    1.0, Parsers.DoubleToRoundedDouble(1.0 - lineCutY),
                    parentLevel));
        }
        return nodes;
    }


    static public LinkedList<Node> getRandomlyPlacedTwinChildren(Node twinChild){

        LinkedList<Node> nodes = new LinkedList<>();
        Double angle = getRandomAngle();
        Double radius = getRandomRadius();
        Integer xUnits = Parsers.DoubleToInteger(Math.abs(Math.cos(angle)) * radius);
        Integer yUnits = Parsers.DoubleToInteger(Math.abs(Math.sin(angle)) * radius);

        Double lineCutX = Parsers.DoubleToRoundedDouble(1.0 / xUnits);
        Double lineCutY = Parsers.DoubleToRoundedDouble(1.0 / yUnits);

        for(int x=0; x<xUnits; x++)
            for(int y=0; y<yUnits; y++)
                nodes.add(twinChild.shallowCopyWithDifferentPlacement(lineCutX*x, lineCutY*y, lineCutX, lineCutY));

        return nodes;
    }

    static public LinkedList<Node> getRandomlyPlacedTwinChildren(Node twinChild, Integer limit){

        LinkedList<Node> twinChildren;
        do{
            twinChildren = getRandomlyPlacedTwinChildren(twinChild);
        } while(twinChildren.size()>limit);
        return twinChildren;
    }

    private static Double getRandomCut(){
        return Parsers.IntegerPercentToDouble(
                Parameters.lineCutProbabilityList.
                        get(ThreadLocalRandom.current().nextInt(Parameters.lineCutProbabilityList.size()))
        );
    }

    private static Double getRandomRadius(){
        return Parsers.IntegerToDouble(
                Parameters.radiusProbabilityList.
                        get(ThreadLocalRandom.current().nextInt(Parameters.radiusProbabilityList.size()))
        );
    }

    private static Double getRandomAngle(){
        return Parsers.IntegerToDouble(
                Parameters.angleProbabilityList.
                        get(ThreadLocalRandom.current().nextInt(Parameters.angleProbabilityList.size()))
        );
    }

    private static NodeType getRandomNodeType(Integer level){
        ArrayList<NodeType> probabilityList = Parameters.getNodeTypeProbabilityList(level);
        return probabilityList.get(ThreadLocalRandom.current().nextInt(probabilityList.size()));
    }




}
