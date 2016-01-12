package content_generators;

import page_components.DrawablePageElement;
import page_components.SVGImage;
import page_components.SVGRectangle;
import page_components.SVGText;
import page_components.enums.ContentType;
import page_components.enums.DrawableType;
import tools.Constants;
import tools.Parsers;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 08/01/16.
 */
public class RandomContentGenerator {

    static private TextGenerator textGenerator = new TextGenerator();

    static public SVGRectangle getBackgroundRectangle(){return getBackgroundRectangle(0.0, 0.0, 1.0, 1.0);}
    static public SVGRectangle getBackgroundRectangle(Double x, Double y, Double width, Double height){
        return RandomElementGenerator.getRandomSVGRectangle(x, y, width, height);
    }

    static public SVGImage getBackgroundImage(){return getBackgroundImage(0.0, 0.0, 1.0, 1.0);}
    static public SVGImage getBackgroundImage(Double x, Double y, Double width, Double height){
        SVGImage image = new SVGImage(x, y, width, height);
        image.setHref(getRandomImageHref());
        image.setPreserveAspectRatio("none");
        return image;
    }

    static public SVGImage getRandomPicture(){return getRandomPicture(0.1, 0.1, 0.8, 0.8);}
    static public SVGImage getRandomPicture(Double x, Double y, Double width, Double height){
        SVGImage image = new SVGImage(x, y, width, height);
        image.setHref(getRandomImageHref());
        return image;
    }

    static public SVGImage getRandomLogo(){return getRandomLogo(0.1, 0.1, 0.8, 0.8);}
    static public SVGImage getRandomLogo(Double x, Double y, Double width, Double height){
        SVGImage image = new SVGImage(x, y, width, height);
        image.setHref(getRandomLogoHref());
        return image;
    }


    static public SVGText getRandomParagraph(){return getRandomParagraph(0.1, 0.1, 0.8, 0.8);}
    static public SVGText getRandomParagraph(Double x, Double y, Double width, Double height){
        SVGText text = new SVGText(x, y, width, height);
        text.setContent(textGenerator.getParagraph());
        SVGRectangle bcgRec = RandomElementGenerator.getRandomSVGRectangle();
//        SVGRectangle bcgRec = getBackgroundRectangle(x, y, width, height);
//        bcgRec.setFillColour(ColourGenerator.grayscaleColor(bcgRec.getFillColour()));
        text.setBackground(bcgRec);
//        text.setFillColour(ColourGenerator.shadeColor(ColourGenerator.grayscaleColor(bcgRec.getFillColour()), 100));
        text.setFillColour(ColourGenerator.getContrastColour(bcgRec.getFillColour()));
        return text;
    }

    static public SVGText getRandomTitle(){return getRandomTitle(0.1, 0.1, 0.8, 0.8);}
    static public SVGText getRandomTitle(Double x, Double y, Double width, Double height){
        SVGText text = new SVGText(x, y, width, height);
        text.setContent(textGenerator.getSentence());
        text.setFontSize(text.getFontSize() + 2);
        text.setBackground(getBackgroundRectangle(x, y, width, height));
        return text;
    }

    static public SVGText getRandomWord(){return getRandomWord(0.1, 0.1, 0.8, 0.8);}
    static public SVGText getRandomWord(Double x, Double y, Double width, Double height){
        SVGText text = new SVGText(x, y, width, height);
        text.setContent(textGenerator.getWord());
        text.setBackground(getBackgroundRectangle(x, y, width, height));
        return text;
    }

    static public SVGText getRandomFooter(){return getRandomWord(0.1, 0.1, 0.8, 0.8);}
    static public SVGText getRandomFooter(Double x, Double y, Double width, Double height){
        SVGText text = new SVGText(x, y, width, height);
        text.setContent(textGenerator.getSentence());
        text.setFontSize(text.getFontSize() -2);
        text.setBackground(getBackgroundRectangle(x, y, width, height));
        return text;
    }



    static public String getRandomImageHref(){
        return getRandomHref(Constants.RESOURCE_IMAGES_PATH);
    }

    static public String getRandomLogoHref(){
        return getRandomHref(Constants.RESOURCE_LOGOS_PATH);
    }

    static private String getRandomHref(String path){
        String[] imageFiles = new File(path).list();
        return Parsers.UriToHrefUri(path + "/" + imageFiles[ThreadLocalRandom.current().nextInt(imageFiles.length)]);
    }

    static public DrawablePageElement getContent(DrawableType drawableType, ContentType tag, Double x, Double y, Double width, Double height){

        switch (drawableType) {
            case IMAGE:
                switch (tag) {
                    case PICTURE: return getRandomPicture(x, y, width, height);
                    case LOGO: return getRandomLogo(x, y, width, height);
                    case BACKGROUND: return getBackgroundImage(x, y, width, height);
                    default: return getRandomPicture(x, y, width, height);
                }
            case RECTANGLE:
                switch (tag) {
                    default: return RandomElementGenerator.getRandomSVGRectangle(x, y, width, height);
            }
            case TEXT:
                switch (tag) {
                    case TITLE: return getRandomTitle(x, y, width, height);
                    case PARAGRAPH: return getRandomParagraph(x, y, width, height);
                    case BUTTON: return getRandomWord(x, y, width, height);
                    case FOOTER: return getRandomFooter(x, y, width, height);
                    default: return getRandomParagraph(x, y, width, height);
                }
            default: return null;
        }
    }


}
