package evolution;

import tools.Parameters;
import tools.Parsers;
import tools.Ratio;
import treeComponents.SVGElement;
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

    static public LinkedList<SVGNode> getRandomTwinNodes(Integer parentLevel){
        //according to algorythm, dispose equal space between
        //later, produce a random twinChild
        SVGNode twinChild = LayoutFactory.getRandomNode(parentLevel);
        //and generate a LinkedList of SVGNode, containing a copy of this node
        //with different coordinates
        return new LinkedList<>();
    }

    static public LinkedList<SVGNode> getRandomNodes(Integer parentLevel){
        //according to algorythm, dispose equal space between
        //later, produce a random twinChild
        SVGNode twinChild = LayoutFactory.getRandomNode(parentLevel);
        //and generate a LinkedList of SVGNode, containing a copy of this node
        //with different coordinates
        return new LinkedList<>();
    }

    static private LinkedList<Coordinates> getRandomNodeCoordinates(Boolean twin){
        //according to algorytm, dispose random equal space between
        LinkedList<Coordinates> nodes = new LinkedList<>();

        Ratio ratio;
        Integer size;

        do{
            ratio = LayoutFactory.getRandomRatio();
        } while (!twin && ratio.equals(Ratio.SQUARE));
        //do not accept square ratio for a non-twin

        do{
            size = twin ? LayoutFactory.getRandomViewportGroupSize() : LayoutFactory.getRandomViewportSize();
        } while (twin && ratio.equals(Ratio.SQUARE) && (size%2)!=0);
        //do not accept odd-number size for square ratio


        Double xUnit = 1.0;
        Double yUnit = 1.0;


        if(twin){
            if (ratio.equals(Ratio.HORIZONTAL)) {
                // y = 100%
                // x = 100% / size
            }
            else if (ratio.equals(Ratio.VERTICAL)) {
                // y = 100% / size
                // x = 100%
            }
            else if (ratio.equals(Ratio.SQUARE)) {
                // if size>4
                // blah blah blah
            }
        } else {
            if (ratio.equals(Ratio.HORIZONTAL)) {

            }
            else if (ratio.equals(Ratio.VERTICAL)) {

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



}
