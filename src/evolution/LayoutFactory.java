package evolution;

import tools.NodeType;
import tools.Parameters;
import tools.Parsers;
import treeComponents.*;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 30/11/15.
 */
public class LayoutFactory {

    static public SVGElement getRandomDrawable(){
        return null;
    }

    static public LinkedList<SVGNode> getRandomlyPlacedNodes(Integer parentLevel, Boolean twin){

        LinkedList<SVGNode> nodes = new LinkedList<>();

        Double angle = getRandomAngle();

        if(twin){
            Double radius = getRandomRadius();
            Integer xUnits = Parsers.DoubleToInteger(Math.cos(angle) * radius);
            Integer yUnits = Parsers.DoubleToInteger(Math.sin(angle) * radius);

            Double lineCutX = 1.0 / xUnits;
            Double lineCutY = 1.0 / yUnits;

            SVGNode twinChild = LayoutFactory.getRandomNode(0.0, 0.0, 0.0, 0.0, parentLevel);
            for(int x=0; x<xUnits; x++)
                for(int y=0; y<yUnits; y++)
                    nodes.add(twinChild.copyWithDifferentPlacement(lineCutX*x, lineCutY*y, lineCutX, lineCutY));

        } else {
            Double lineCutX = getRandomCut();
            Double lineCutY = getRandomCut();

            //Let's let the radius decide whether the viewport is gonna be cut horizontally, vertically or into 4
            if(angle<45){
                nodes.add(LayoutFactory.getRandomNode(0.0, 0.0, lineCutX, 1.0, parentLevel));
                nodes.add(LayoutFactory.getRandomNode(lineCutX, 0.0, 1.0-lineCutX, 1.0, parentLevel));

            } else if (angle==45) {
                nodes.add(LayoutFactory.getRandomNode(0.0, 0.0, lineCutX, lineCutY, parentLevel));
                nodes.add(LayoutFactory.getRandomNode(lineCutX, 0.0, 1.0-lineCutX, lineCutY, parentLevel));
                nodes.add(LayoutFactory.getRandomNode(0.0, lineCutY, lineCutX, 1.0-lineCutY, parentLevel));
                nodes.add(LayoutFactory.getRandomNode(lineCutX, lineCutY, 1.0-lineCutX, 1.0-lineCutY, parentLevel));

            } else if (angle>45){
                nodes.add(LayoutFactory.getRandomNode(0.0, 0.0, 1.0, lineCutY, parentLevel));
                nodes.add(LayoutFactory.getRandomNode(0.0, lineCutY, 1.0, 1.0-lineCutY, parentLevel));
            }
        }
        return nodes;
    }



    static private SVGNode getRandomNode(Double x, Double y, Double width, Double height, Integer parentLevel){
        NodeType nodeType = Parameters.nodeTypeProbabilityList.
                get(ThreadLocalRandom.current().nextInt(Parameters.nodeTypeProbabilityList.size()));

        switch (nodeType) {
            case LEAF: return new SVGLeaf(x, y, width, height, parentLevel);
            case VIEWPORT: return new SVGViewport(x, y, width, height, parentLevel);
            case VIEWPORT_GROUP: return new SVGViewportGroup(x, y, width, height, parentLevel);
            default: return new SVGLeaf(x, y, width, height, parentLevel);
        }

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
