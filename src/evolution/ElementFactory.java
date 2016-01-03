package evolution;

import tools.Constants;
import tools.Parameters;
import treeComponents.SVGElement;
import treeComponents.drawable.SVGRectangle;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 03/01/16.
 */
public class ElementFactory {

    //get random picture and create picture
    //bla

    static public SVGElement getRandomDrawable(){
        return new SVGRectangle(0.0, 0.0, 0.0, 0.0, "blue", 1.0, "white", 5, 1.0);
    }

    static public SVGRectangle getBackgroundRectangle(){
        return new SVGRectangle(0.0, 0.0, 1.0, 1.0, "blue", 1.0, "white", 5, 1.0);
    }


    static public String getRandomImage(){
        String[] imageFiles = new File(Constants.RESOURCE_IMAGES_PATH).list();

        for(String filename: imageFiles)
            System.out.println(filename);

        return imageFiles[ThreadLocalRandom.current().nextInt(imageFiles.length)];
    }
}
