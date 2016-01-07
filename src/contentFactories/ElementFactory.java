package contentFactories;

import contentFactories.websiteContentFactories.TextFactory;
import tools.*;
import page_components.PageElement;
import page_components.SVGImage;
import page_components.SVGRectangle;
import page_components.SVGText;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 03/01/16.
 */
public class ElementFactory {

    static private ColourGenerator colourGenerator = new ColourGenerator();

    static public SVGImage getRandomSVGImage(){
        return new SVGImage(0.1, 0.1, 0.8, 0.8);
    }

    static public SVGRectangle getRandomSVGRectangle(){
        return new SVGRectangle(0.1, 0.1, 0.8, 0.8);
    }

    static public SVGText getRandomSVGText(){
        return new SVGText(0.1, 0.1, 0.8, 0.8);
    }

    static public PageElement getRandomDrawable(){
        ElementType elementType = getRandomElementType();
        switch (elementType) {
            case IMAGE:return new SVGImage(0.1, 0.1, 0.8, 0.8, getRandomImageHref());
            case RECTANGLE: return new SVGRectangle(0.1, 0.1, 0.8, 0.8, "green", 1.0, "white", 5, 1.0);
            case TEXT: return new SVGText(0.1, 0.1, 0.8, 0.8, TextFactory.getParagraph());
            default: return new SVGRectangle(0.1, 0.1, 0.8, 0.8, "green", 1.0, "white", 5, 1.0);
        }
    }

        //return new SVGRectangle(0.0, 0.0, 0.0, 0.0, "blue", 1.0, "white", 5, 1.0);
    static public SVGRectangle getBackgroundRectangle(){


        return new SVGRectangle(0.0, 0.0, 1.0, 1.0, getRandomColor(), 1.0, "white", 5, 1.0);
    }

    static private String getRandomColor(){
        return colourGenerator.getRandomColour();
    }


    static private String getRandomHref(String path){
        String[] imageFiles = new File(path).list();
        return Parsers.UriToHrefUri(path + "/" + imageFiles[ThreadLocalRandom.current().nextInt(imageFiles.length)]);
    }


    static public String getRandomImageHref(){
        return getRandomHref(Constants.RESOURCE_IMAGES_PATH);
    }

    static public String getRandomLogoHref(){
        return getRandomHref(Constants.RESOURCE_LOGOS_PATH);
    }

    private static ElementType getRandomElementType(){
        ArrayList<ElementType> probabilityList = Parameters.getElementTypeProbabilityList();
        return probabilityList.get(ThreadLocalRandom.current().nextInt(probabilityList.size()));
    }

    static public SVGImage getRandomLogo(){
        return new SVGImage(0.1, 0.1, 0.8, 0.8, getRandomLogoHref());
    }







}
