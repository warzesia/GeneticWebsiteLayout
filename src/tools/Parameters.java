package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by warzesia on 01/12/15.
 */
public class Parameters {

    private static Map<NodeType, Integer> nodeTypeProbabilityMap = new HashMap<>();
    private static Map<Ratio, Integer> ratioProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> viewportSizeProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> viewportGroupSizeProbabilityMap = new HashMap<>();
    private static Map<Integer, Integer> lineCutProbabilityMap = new HashMap<>();

    public static  ArrayList<NodeType> nodeTypeProbabilityList;
    public static  ArrayList<Ratio> ratioProbabilityList;
    public static  ArrayList<Integer> viewportSizeProbabilityList;
    public static  ArrayList<Integer> viewportGroupSizeProbabilityList;
    public static final ArrayList<Integer> lineCutProbabilityList;

    static {
        //initialize probabilities for different node types
        nodeTypeProbabilityMap.put(NodeType.LEAF, 3);
        nodeTypeProbabilityMap.put(NodeType.VIEWPORT, 5);
        nodeTypeProbabilityMap.put(NodeType.VIEWPORT_GROUP, 1);
        nodeTypeProbabilityList = getNodeTypeProbabilityList();

        //initialize probabilities for different preferred ratio styles
        ratioProbabilityMap.put(Ratio.HORIZONTAL, 2);
        ratioProbabilityMap.put(Ratio.VERTICAL, 2);
        ratioProbabilityMap.put(Ratio.SQUARE, 1);
        ratioProbabilityList = getRatioProbabilityList();

        //initialize probabilities for different sizes
        viewportSizeProbabilityMap.put(2, 4);
        viewportSizeProbabilityMap.put(3, 1);
        viewportSizeProbabilityMap.put(4, 6);
        viewportSizeProbabilityList = getProbabilityList("ViewportSize");

        //initialize probabilities for different group sizes
        viewportGroupSizeProbabilityMap.put(1, 1);
        viewportGroupSizeProbabilityMap.put(2, 3);
        viewportGroupSizeProbabilityMap.put(3, 6);
        viewportGroupSizeProbabilityMap.put(4, 6);
        viewportGroupSizeProbabilityMap.put(5, 3);
        viewportGroupSizeProbabilityMap.put(6, 2);
        viewportGroupSizeProbabilityList = getProbabilityList("ViewportGroupSize");

        initializeLineCutProbabilityMap();
        lineCutProbabilityList = getProbabilityList("LineCut");

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








}
