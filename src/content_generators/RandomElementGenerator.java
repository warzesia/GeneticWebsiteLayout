package content_generators;

import page_components.*;
import page_components.enums.DrawableType;
import tools.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 03/01/16.
 */
public class RandomElementGenerator {

    static private ColourGenerator colourGenerator = ColourGenerator.getInstance();
    static private TextGenerator textGenerator = new TextGenerator();

    static public SVGImage getRandomSVGImage(){return getRandomSVGImage(0.1, 0.1, 0.8, 0.8);}
    static public SVGImage getRandomSVGImage(Double x, Double y, Double width, Double height){
        SVGImage image = new SVGImage(x, y, width, height);
        image.setHref(RandomContentGenerator.getRandomImageHref());
        return image;
    }

    static public SVGRectangle getRandomSVGRectangle(){return getRandomSVGRectangle(0.1, 0.1, 0.8, 0.8);}
    static public SVGRectangle getRandomSVGRectangle(Double x, Double y, Double width, Double height){
        SVGRectangle rectangle = new SVGRectangle(x, y, width, height);
        rectangle.setFillColour(colourGenerator.getRandomColour());
        return rectangle;
    }

    static public SVGText getRandomSVGText(){return getRandomSVGText(0.1, 0.1, 0.8, 0.8);}
    static public SVGText getRandomSVGText(Double x, Double y, Double width, Double height){
        SVGText text = new SVGText(x, y, width, height);
        text.setContent(textGenerator.getParagraph());
        text.setBackground(getRandomSVGRectangle());
        return text;
    }

    static public DrawablePageElement getRandomDrawable(Double x, Double y, Double width, Double height){
        DrawableType drawableType = getRandomDrawableType();
        switch (drawableType) {
            case IMAGE: return getRandomSVGImage(x, y, width, height);
            case RECTANGLE: return getRandomSVGRectangle(x, y, width, height);
            case TEXT: return getRandomSVGText(x, y, width, height);
            default: return null;
        }
    }
    static public DrawablePageElement getRandomDrawable(){
        DrawableType drawableType = getRandomDrawableType();
        switch (drawableType) {
            case IMAGE: return getRandomSVGImage();
            case RECTANGLE: return getRandomSVGRectangle();
            case TEXT: return getRandomSVGText();
            default: return null;
        }
    }

    static public DrawablePageElement getRandomlyPlacedDrawable(){
        Integer x1 = ThreadLocalRandom.current().nextInt(101);
        Integer y1 = ThreadLocalRandom.current().nextInt(101);
        Integer x2, y2;

        do{
            x2 = ThreadLocalRandom.current().nextInt(101);
            y2 = ThreadLocalRandom.current().nextInt(101);
        } while ((Math.abs(x1 - x2) < 30) && (Math.abs(y1 - y2) < 30));

        Double x, y, width, height;
        width = Parsers.IntegerToDouble(Math.abs(x1 - x2));
        height = Parsers.IntegerToDouble(Math.abs(y1 - y2));
        x = Parsers.IntegerToDouble(Math.min(x1, x2));
        y = Parsers.IntegerToDouble(Math.min(y1, y2));

        return getRandomDrawable(x, y, width, height);
    }

    static public DrawablePageElement getRandomlyPlacedDrawable(DrawablePageElement drawable){
        DrawablePageElement randomDrawable = getRandomlyPlacedDrawable();
        return drawable.copyWithDifferentPlacement(randomDrawable.getX(), randomDrawable.getY(), randomDrawable.getWidth(), randomDrawable.getHeight());
    }

    private static DrawableType getRandomDrawableType(){
        ArrayList<DrawableType> probabilityList = Parameters.getDrawableTypeProbabilityList();
        return probabilityList.get(ThreadLocalRandom.current().nextInt(probabilityList.size()));
    }



}
