package evolution;

import tools.NodeType;
import tools.Parameters;
import tools.Parsers;
import treeComponents.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 30/11/15.
 */
public class LayoutFactory {

    static public SVGNode getRandomRootNode(){
        return getRandomNode(0.0, 0.0, 1.0, 1.0, 0);
    }

    public static SVGNode getRandomNode(Double x, Double y, Double width, Double height, Integer parentLevel){
        Integer childLevel = parentLevel+1;

        //the limit
        NodeType nodeType = (childLevel.equals(Parameters.MAX_SVG_TREE_DEPTH)) ?
                NodeType.LEAF : getRandomNodeType(childLevel);

        switch (nodeType) {
            case LEAF: return new SVGLeaf(x, y, width, height, childLevel);
            case VIEWPORT: return new SVGViewport(x, y, width, height, childLevel);
            case VIEWPORT_GROUP: return new SVGViewportGroup(x, y, width, height, childLevel);
            default: return new SVGLeaf(x, y, width, height, childLevel);
        }
    }

    static public LinkedList<SVGNode> getRandomlyPlacedNodes(Integer parentLevel) {

        LinkedList<SVGNode> nodes = new LinkedList<>();
        Double angle = getRandomAngle();
        Double lineCutX = getRandomCut();
        Double lineCutY = getRandomCut();
        System.out.println("LineCutX: " + lineCutX);
        System.out.println("LineCutY: " + lineCutY);


        //Let's let the radius decide whether the viewport is gonna be cut horizontally, vertically or into 4
        if(angle<45){
            nodes.add(LayoutFactory.getRandomNode(
                    0.0, 0.0,
                    Parsers.DoubleToRoundedDouble(lineCutX), 1.0,
                    parentLevel));
            nodes.add(LayoutFactory.getRandomNode(
                    Parsers.DoubleToRoundedDouble(lineCutX), 0.0,
                    Parsers.DoubleToRoundedDouble(1.0-lineCutX), 1.0,
                    parentLevel));

        } else if (angle==45) {
            nodes.add(LayoutFactory.getRandomNode(
                    0.0, 0.0,
                    Parsers.DoubleToRoundedDouble(lineCutX), Parsers.DoubleToRoundedDouble(lineCutY),
                    parentLevel));
            nodes.add(LayoutFactory.getRandomNode(
                    Parsers.DoubleToRoundedDouble(lineCutX), 0.0,
                    Parsers.DoubleToRoundedDouble(1.0-lineCutX), Parsers.DoubleToRoundedDouble(lineCutY),
                    parentLevel));
            nodes.add(LayoutFactory.getRandomNode(
                    0.0, Parsers.DoubleToRoundedDouble(lineCutY),
                    Parsers.DoubleToRoundedDouble(lineCutX), Parsers.DoubleToRoundedDouble(1.0-lineCutY),
                    parentLevel));
            nodes.add(LayoutFactory.getRandomNode(
                    Parsers.DoubleToRoundedDouble(lineCutX), Parsers.DoubleToRoundedDouble(lineCutY),
                    Parsers.DoubleToRoundedDouble(1.0-lineCutX), Parsers.DoubleToRoundedDouble(1.0-lineCutY),
                    parentLevel));

        } else if (angle>45){
            nodes.add(LayoutFactory.getRandomNode(
                    0.0, 0.0,
                    1.0, Parsers.DoubleToRoundedDouble(lineCutY),
                    parentLevel));
            nodes.add(LayoutFactory.getRandomNode(
                    0.0, Parsers.DoubleToRoundedDouble(lineCutY),
                    1.0, Parsers.DoubleToRoundedDouble(1.0-lineCutY),
                    parentLevel));
        }
        return nodes;
    }


    static public LinkedList<SVGNode> getRandomlyPlacedTwinChildren(SVGNode twinChild){

        LinkedList<SVGNode> nodes = new LinkedList<>();
        Double angle = getRandomAngle();
        Double radius = getRandomRadius();
        Integer xUnits = Parsers.DoubleToInteger(Math.abs(Math.cos(angle)) * radius);
        Integer yUnits = Parsers.DoubleToInteger(Math.abs(Math.sin(angle)) * radius);

        Double lineCutX = Parsers.DoubleToRoundedDouble(1.0 / xUnits);
        Double lineCutY = Parsers.DoubleToRoundedDouble(1.0 / yUnits);

        for(int x=0; x<xUnits; x++)
            for(int y=0; y<yUnits; y++)
                nodes.add(twinChild.copyWithDifferentPlacement(lineCutX*x, lineCutY*y, lineCutX, lineCutY));

        return nodes;
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


//    static public String getRandomColor(){
//        // Will produce a random colour with more red in it (usually "pink-ish")
//        float r = ThreadLocalRandom.current().nextFloat();
//        float g = ThreadLocalRandom.current().nextFloat() / 2f;
//        float b = ThreadLocalRandom.current().nextFloat() / 2f;
//        Color color = new Color(r, g, b);
//
//        //rgb(0,0,255)
//        return "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
//    }

//    private static Ratio getRandomRatio(){
//        return Parameters.ratioProbabilityList.
//                get(ThreadLocalRandom.current().nextInt(Parameters.ratioProbabilityList.size()));
//    }
//
//    private static Integer getRandomViewportGroupSize(){
//        return Parameters.viewportGroupSizeProbabilityList.
//                get(ThreadLocalRandom.current().nextInt(Parameters.viewportGroupSizeProbabilityList.size()));
//    }
//
//    private static Integer getRandomViewportSize(){
//        return Parameters.viewportSizeProbabilityList.
//                get(ThreadLocalRandom.current().nextInt(Parameters.viewportSizeProbabilityList.size()));
//    }


}
