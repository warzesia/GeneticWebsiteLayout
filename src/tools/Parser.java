package tools;

/**
 * Created by warzesia on 29/11/15.
 */
public class Parser {

    public static String DoubleToStringPercent(Double val){
        Double percentageVal = val * 100;
        return Integer.toString(percentageVal.intValue()) + "%";
    }

    public static String DoubleToString(Double val){
        return val.toString();
    }


}