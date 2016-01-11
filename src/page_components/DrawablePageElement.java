package page_components;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by warzesia on 07/01/16.
 */
public abstract class DrawablePageElement extends PageElement {

    public abstract DrawablePageElement copyWithDifferentPlacement(Double x, Double y, Double width, Double height);
    public DrawablePageElement(){
        super();
    }
    public DrawablePageElement(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }

}