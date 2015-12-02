package tools;

/**
 * Created by warzesia on 29/11/15.
 */
public class Parsers {

    public static String DoubleToStringPercent(Double val){
        Double percentageVal = val * 100;
        return Integer.toString(percentageVal.intValue()) + "%";
    }

    public static String DoubleToString(Double val){
        return val.toString();
    }

    public static Double IntegerPercentToDouble(Integer val){
        return val.doubleValue() * 0.01;
    }



}
