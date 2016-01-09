package content_generators;

import page_components.enums.DrawableType;
import tools.*;
import page_components.PageElement;
import page_components.SVGImage;
import page_components.SVGRectangle;
import page_components.SVGText;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 03/01/16.
 */
public class RandomElementGenerator {

    static private ColourGenerator colourGenerator = new ColourGenerator();
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

    static public PageElement getRandomDrawable(){
        DrawableType drawableType = getRandomDrawableType();
        switch (drawableType) {
            case IMAGE: return getRandomSVGImage();
            case RECTANGLE: return getRandomSVGRectangle();
            case TEXT: return getRandomSVGText();
            default: return null;
        }
    }

    private static DrawableType getRandomDrawableType(){
        ArrayList<DrawableType> probabilityList = Parameters.getDrawableTypeProbabilityList();
        return probabilityList.get(ThreadLocalRandom.current().nextInt(probabilityList.size()));
    }



}
