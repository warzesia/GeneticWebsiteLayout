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

    public static Double IntegerToDouble(Integer val){
        return val.doubleValue();
    }

    public static Integer DoubleToInteger(Double val){
        return (int)Math.round(Math.floor(val+1.0));
    }

    public static String LevelToPrefix(Integer level) {
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < level; i++){
            s.append("----");
        }
        return s.toString();
    }

    public static String ShortenedClassName(String name) {
        return name.substring(name.lastIndexOf(".") + 1);
    }



}
