package evolution;

import tools.*;
import treeComponents.SVGElement;
import treeComponents.drawable.SVGImage;
import treeComponents.drawable.SVGRectangle;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 03/01/16.
 */
public class ElementFactory {

    //get random picture and create picture
    //bla

    static public SVGElement getRandomDrawable(){
        ElementType elementType = getRandomElementType();
        switch (elementType) {
            case IMAGE:return new SVGImage(0.1, 0.1, 0.8, 0.8, getRandomImageHref());
            case RECTANGLE: return new SVGRectangle(0.1, 0.1, 0.8, 0.8, "green", 1.0, "white", 5, 1.0);
//            case TEXT: return new SVGViewportGroup(x, y, width, height, childLevel);
            default: return new SVGRectangle(0.1, 0.1, 0.8, 0.8, "green", 1.0, "white", 5, 1.0);
        }
    }

        //return new SVGRectangle(0.0, 0.0, 0.0, 0.0, "blue", 1.0, "white", 5, 1.0);
    static public SVGRectangle getBackgroundRectangle(){
        return new SVGRectangle(0.0, 0.0, 1.0, 1.0, "blue", 1.0, "white", 5, 1.0);
    }


    static public String getRandomImageHref(){
        String[] imageFiles = new File(Constants.RESOURCE_IMAGES_PATH).list();

//        for(String filename: imageFiles)
//            System.out.println(filename);

        return Parsers.UriToHrefUri( Constants.RESOURCE_IMAGES_PATH + "/" +
                imageFiles[ThreadLocalRandom.current().nextInt(imageFiles.length)] );
    }

    private static ElementType getRandomElementType(){
        ArrayList<ElementType> probabilityList = Parameters.getElementTypeProbabilityList();
        return probabilityList.get(ThreadLocalRandom.current().nextInt(probabilityList.size()));
    }
}
