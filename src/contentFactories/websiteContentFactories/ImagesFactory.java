package contentFactories.websiteContentFactories;

import contentFactories.ElementFactory;
import tools.Constants;
import tools.Parsers;
import page_components.SVGImage;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 06/01/16.
 */
public class ImagesFactory {

    public static SVGImage getRandomLogo(){
        SVGImage image = ElementFactory.getRandomSVGImage();
        image.setHref(getRandomImageHref(Constants.RESOURCE_LOGOS_PATH));
        return image;
    }

    public static SVGImage getRandomBackgroundImage(){
        SVGImage image = ElementFactory.getRandomSVGImage();
        image.setHref(getRandomImageHref(Constants.RESOURCE_LOGOS_PATH));
        return image;
    }

    static public String getRandomImageHref(String path){
        String[] imageFiles = new File(path).list();
        return Parsers.UriToHrefUri(path + "/" + imageFiles[ThreadLocalRandom.current().nextInt(imageFiles.length)]);
    }


}
