package content_generators;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 06/01/16.
 */
public class ColourGenerator {

    private static ColourGenerator Instance;
    public static ColourGenerator getInstance(){
        if(Instance == null)
            Instance = new ColourGenerator();
        return Instance;
    }

    public enum PaletteTypes {
        RED, YELLOW, GREEN, VIOLET, BLUE
    }

    private static final HashMap<PaletteTypes, String[]> palettes = new HashMap<>();
    static {
        String[] red = {"#AA3939", "#FFAAAA", "#D46A6A", "#801515", "#550000"};
        String[] yellow = {"#AA8439", "#FFE3AA", "#D4B16A", "#805C15", "#553900"};
        String[] green = {"#AAAA39", "#FFFFAA", "#D4D46A", "#808015", "#555500"};
        String[] violet = {"#A996A1", "#E8E5E6", "#CABFC5", "#8D7382", "#745365"};
        String[] blue = {"#365378", "#C8CDD2", "#70849E", "#0E2C52", "#01142C"};
        palettes.put(PaletteTypes.RED, red);
        palettes.put(PaletteTypes.YELLOW, yellow);
        palettes.put(PaletteTypes.GREEN, green);
        palettes.put(PaletteTypes.VIOLET, violet);
        palettes.put(PaletteTypes.BLUE, blue);
    }

    private String[] palette;
    private String colour;
    private ColourGenerator(){
        PaletteTypes pick = PaletteTypes.values()[ThreadLocalRandom.current().nextInt(PaletteTypes.values().length)];
        this.palette = palettes.get(pick);
        this.colour = palette[ThreadLocalRandom.current().nextInt(palette.length)];
    }

    public String getRandomColour(){
        return palette[ThreadLocalRandom.current().nextInt(palette.length)];
//        return shadeColor(this.colour, getRandomPercent());
    }

    public String getRandomColourDifferentTo(String oldColour){
        String colour;
        do{
            colour = getRandomColour();
        } while (colour.equals(oldColour));
        return colour;
    }

    public String getRandomColourDifferentTo(LinkedList<String> oldColours){
        String colour;
        do{
            colour = getRandomColour();
        } while (oldColours.contains(colour));
        return colour;
    }


    private int getRandomPercent(){
        return (ThreadLocalRandom.current().nextInt(12)-6)*10;
    }


    public static String shadeColor(String color, Integer percent) {

        int r =  Integer.parseInt(color.substring(1,3),16);
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);

        r = r * (100 + percent) / 100;
        g = g * (100 + percent) / 100;
        b = b * (100 + percent) / 100;

        r = (r<255) ? r : 255;
        g = (g<255) ? g : 255;
        b = (b<255) ? b : 255;

        String rr = Integer.toHexString(r);
        String gg = Integer.toHexString(g);
        String bb = Integer.toHexString(b);

        rr = rr.length() == 1 ? "0"+rr : rr;
        gg = gg.length() == 1 ? "0"+gg : gg;
        bb = bb.length() == 1 ? "0"+bb : bb;

        return "#"+rr+gg+bb;
    }

    public static String grayscaleColor(String color) {

        int r =  Integer.parseInt(color.substring(1,3),16);
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);

        int res = (r + g + b) / 3;
        r = g = b = res;

        r = (r<255) ? r : 255;
        g = (g<255) ? g : 255;
        b = (b<255) ? b : 255;

        String rr = Integer.toHexString(r);
        String gg = Integer.toHexString(g);
        String bb = Integer.toHexString(b);

        rr = rr.length() == 1 ? "0"+rr : rr;
        gg = gg.length() == 1 ? "0"+gg : gg;
        bb = bb.length() == 1 ? "0"+bb : bb;

        return "#"+rr+gg+bb;
    }

    public static String getContrastColour(String color){
        String plusShade = shadeColor(color, 100);
        String minusShade = shadeColor(color, -100);

        int r =  Integer.parseInt(color.substring(1, 3), 16);
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);
        int rgb = r + g + b;

        int rPlus =  Integer.parseInt(plusShade.substring(1,3),16);
        int gPlus  = Integer.parseInt(plusShade.substring(3, 5), 16);
        int bPlus  = Integer.parseInt(plusShade.substring(5, 7), 16);
        int rgbPlus = rPlus + gPlus + bPlus;

        int rMinus =  Integer.parseInt(minusShade.substring(1,3),16);
        int gMinus = Integer.parseInt(minusShade.substring(3, 5), 16);
        int bMinus = Integer.parseInt(minusShade.substring(5, 7), 16);
        int rgbMinus = rMinus + gMinus + bMinus;

        int plusDiff = Math.abs(r - rPlus) + Math.abs(g - gPlus) + Math.abs(b - bPlus);
        int minusDiff = Math.abs(r - rMinus) + Math.abs(g - gMinus) + Math.abs(b - bMinus);

        return plusDiff > minusDiff ? plusShade : minusShade;
    }


}
