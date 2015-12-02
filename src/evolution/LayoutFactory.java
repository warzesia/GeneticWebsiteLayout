package evolution;

import tools.Parameters;
import tools.Parsers;
import tools.Ratio;
import treeComponents.SVGElement;
import treeComponents.SVGLeaf;
import treeComponents.SVGNode;
import treeComponents.SVGViewport;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 30/11/15.
 */
public class LayoutFactory {

    private static Random rand = new Random();


    static public SVGElement getRandomDrawable(){
        return null;
    }

    static public SVGNode getRandomNode(Integer parentLevel){
        return new SVGViewport();
        //return null;
    }

    static public LinkedList<SVGNode> twinNode(SVGNode node){
        return null;
    }

//    static public LinkedList<SVGNode>


    static public SVGNode getRandomNode(Double x, Double y, Double width, Double height, Integer parentLevel){
        return new SVGLeaf(x, y, width, height, parentLevel);
    }

    static private LinkedList<SVGNode> getRandomlyPlacedNodes(Integer parentLevel, Boolean twin){

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


    private class Coordinates {
        Double x;
        Double y;
        Double width;
        Double height;

        public Coordinates(Double x, Double y, Double width, Double height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private static Ratio getRandomRatio(){
        return Parameters.ratioProbabilityList.
                get(ThreadLocalRandom.current().nextInt(Parameters.ratioProbabilityList.size()));
    }

    private static Integer getRandomViewportGroupSize(){
        return Parameters.viewportGroupSizeProbabilityList.
                get(ThreadLocalRandom.current().nextInt(Parameters.viewportGroupSizeProbabilityList.size()));
    }

    private static Integer getRandomViewportSize(){
        return Parameters.viewportSizeProbabilityList.
                get(ThreadLocalRandom.current().nextInt(Parameters.viewportSizeProbabilityList.size()));
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


}
