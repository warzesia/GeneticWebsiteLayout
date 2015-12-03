package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by warzesia on 01/12/15.
 */
public class Parameters {

    public static final Integer MAX_SVG_TREE_DEPTH = 5;

    private static Map<NodeType, Integer> nodeTypeProbabilityMap = new HashMap<>();
    private static Map<Ratio, Integer> ratioProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> viewportSizeProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> viewportGroupSizeProbabilityMap = new HashMap<>();
    private static final Map<Integer, Integer> lineCutProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> radiusProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> angleProbabilityMap = new HashMap<>();

    public static  ArrayList<NodeType> nodeTypeProbabilityList;
    public static  ArrayList<Ratio> ratioProbabilityList;
    public static  ArrayList<Integer> viewportSizeProbabilityList;
    public static  ArrayList<Integer> viewportGroupSizeProbabilityList;
    public static final ArrayList<Integer> lineCutProbabilityList;
    public static  ArrayList<Integer> radiusProbabilityList;
    public static  ArrayList<Integer> angleProbabilityList;



    static {
        initializeNodeTypeProbabilityMap();
        nodeTypeProbabilityList = getNodeTypeProbabilityList();

        initializeRatioProbabilityMap();
        ratioProbabilityList = getRatioProbabilityList();

        initializeViewportSizeProbabilityMap();
        viewportSizeProbabilityList = getProbabilityList("ViewportSize");

        initializeViewportGroupSizeProbabilityMap();
        viewportGroupSizeProbabilityList = getProbabilityList("ViewportGroupSize");

        initializeLineCutProbabilityMap();
        lineCutProbabilityList = getProbabilityList("LineCut");

        initializeRadiusProbabilityMap();
        radiusProbabilityList = getProbabilityList("Radius");

        initializeAngleProbabilityMap();
        angleProbabilityList = getProbabilityList("Angle");

    }

    public static void updateNodeTypeProbabilityMap(NodeType nodeType, Integer p) {
        Parameters.nodeTypeProbabilityMap.put(nodeType, p);
        nodeTypeProbabilityList = getNodeTypeProbabilityList();
    }

    public static void updateViewportGroupSizeProbabilityMap(Integer size, Integer p) {
        Parameters.viewportGroupSizeProbabilityMap.put(size, p);
        viewportGroupSizeProbabilityList = getProbabilityList("ViewportGroupSize");
    }

    public static void updateViewportSizeProbabilityMap(Integer size, Integer p) {
        Parameters.viewportSizeProbabilityMap.put(size, p);
        viewportSizeProbabilityList = getProbabilityList("ViewportSize");
    }

    private static ArrayList<NodeType> getNodeTypeProbabilityList(){
        ArrayList<NodeType> probabilityList = new ArrayList<>();
        Iterator<NodeType> valueTypeIterator = nodeTypeProbabilityMap.keySet().iterator();
        while(valueTypeIterator.hasNext()){
            NodeType currValueType = valueTypeIterator.next();
            int p = nodeTypeProbabilityMap.get(currValueType);
            while(p>0){
                probabilityList.add(currValueType);
                p--;
            }
        }
        return probabilityList;
    }


    private static ArrayList<Ratio> getRatioProbabilityList(){
        ArrayList<Ratio> probabilityList = new ArrayList<>();
        Iterator<Ratio> valueTypeIterator = ratioProbabilityMap.keySet().iterator();
        while(valueTypeIterator.hasNext()){
            Ratio currValueType = valueTypeIterator.next();
            int p = ratioProbabilityMap.get(currValueType);
            while(p>0){
                probabilityList.add(currValueType);
                p--;
            }
        }
        return probabilityList;
    }

    private static ArrayList<Integer> getProbabilityList(String valueType){
        Map<Integer, Integer> probabilityMap;
        ArrayList<Integer> ProbabilityList = new ArrayList<>();
        Iterator<Integer> valueTypeIterator;

        switch (valueType) {
            case "ViewportSize":  probabilityMap = viewportSizeProbabilityMap;
                break;
            case "ViewportGroupSize":  probabilityMap = viewportGroupSizeProbabilityMap;
                break;
            case "LineCut":  probabilityMap = lineCutProbabilityMap;
                break;
            case "Radius":  probabilityMap = radiusProbabilityMap;
                break;
            case "Angle":  probabilityMap = angleProbabilityMap;
                break;
            default: probabilityMap = null;
                break;
        }
        if(!probabilityMap.equals(null)){
            valueTypeIterator = probabilityMap.keySet().iterator();
            while(valueTypeIterator.hasNext()){
                Integer currValueType = valueTypeIterator.next();
                int p = probabilityMap.get(currValueType);
                while(p>0){
                    ProbabilityList.add(currValueType);
                    p--;
                }
            }
        }
        return ProbabilityList;
    }


    private static void initializeLineCutProbabilityMap(){

        for (Integer i = 0; i < 25; i++)
            Parameters.lineCutProbabilityMap.put(i, i);
        for (Integer i = 25; i < 50; i++)
            Parameters.lineCutProbabilityMap.put(i, 50 - i);
        Parameters.lineCutProbabilityMap.put(50, 25);
        for (Integer i = 51; i < 75; i++)
            Parameters.lineCutProbabilityMap.put(i, i - 50);
        for (Integer i = 75; i < 100; i++)
            Parameters.lineCutProbabilityMap.put(i, 100 - i);


//        Integer p;
//        for (Integer i = 0; i <= 100; i++) {
//            if(i<25) p = i;
//            else if (i<50) p = 50 - i;
//            else if (i==50) p = 25;
//            else if (i<75) p = i - 50;
//            else p = 100 - i;
//            Parameters.lineCutProbabilityMap.put(i, p);
//        }
    }

    private static void initializeNodeTypeProbabilityMap(){
        //initialize probabilities for different node types
        nodeTypeProbabilityMap.put(NodeType.LEAF, 1);
        nodeTypeProbabilityMap.put(NodeType.VIEWPORT, 2);
        nodeTypeProbabilityMap.put(NodeType.VIEWPORT_GROUP, 0); //TODO: check whats happening
    }

    private static void initializeRatioProbabilityMap(){
        //initialize probabilities for different preferred ratio styles
        ratioProbabilityMap.put(Ratio.HORIZONTAL, 2);
        ratioProbabilityMap.put(Ratio.VERTICAL, 2);
        ratioProbabilityMap.put(Ratio.SQUARE, 1);
    }

    private static void initializeViewportSizeProbabilityMap(){
        //initialize probabilities for different sizes
        viewportSizeProbabilityMap.put(2, 4);
        viewportSizeProbabilityMap.put(3, 1);
        viewportSizeProbabilityMap.put(4, 6);
    }

    private static void initializeViewportGroupSizeProbabilityMap(){
        //initialize probabilities for different group sizes
        viewportGroupSizeProbabilityMap.put(1, 1);
        viewportGroupSizeProbabilityMap.put(2, 3);
        viewportGroupSizeProbabilityMap.put(3, 6);
        viewportGroupSizeProbabilityMap.put(4, 6);
        viewportGroupSizeProbabilityMap.put(5, 3);
        viewportGroupSizeProbabilityMap.put(6, 2);
    }

    private static void initializeRadiusProbabilityMap(){
        radiusProbabilityMap.put(1, 3);
        radiusProbabilityMap.put(2, 6);
        radiusProbabilityMap.put(3, 6);
        radiusProbabilityMap.put(4, 3);
        radiusProbabilityMap.put(5, 1);
        radiusProbabilityMap.put(6, 1);
        radiusProbabilityMap.put(7, 1);
        radiusProbabilityMap.put(8, 1);

    }

    private static void initializeAngleProbabilityMap(){
        angleProbabilityMap.put(0, 5);
        angleProbabilityMap.put(30, 2);
        angleProbabilityMap.put(45, 4);
        angleProbabilityMap.put(60, 2);
        angleProbabilityMap.put(90, 5);
    }





}
