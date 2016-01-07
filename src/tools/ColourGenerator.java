package tools;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 06/01/16.
 */
public class ColourGenerator {

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
    public ColourGenerator(){
        PaletteTypes pick = PaletteTypes.values()[ThreadLocalRandom.current().nextInt(PaletteTypes.values().length)];
        palette = palettes.get(pick);
    }

    public String getRandomColour(){
        return palette[ThreadLocalRandom.current().nextInt(palette.length)];
    }


}
