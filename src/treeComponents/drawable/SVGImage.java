package treeComponents.drawable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Constants;
import tools.Strings;
import treeComponents.SVGElement;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGImage extends SVGElement {

    String preserveAspectRatio = "xMidYMid meet";
    String href;


    @Override
    public Element draw(Document document) {
        Element element = document.createElementNS(Constants.SVG_NAMESPACE, Strings.IMAGE);
        super.setAttributes(element);
        element.setAttributeNS(Constants.XLINK_NAMESPACE, Strings.XLINK_HREF, this.getHref());
        element.setAttributeNS(null, Strings.PRESERVE_ASPECT_RATIO, this.getPreserveAspectRatio());
        return element;
    }

//    public String toString(){
//        return super.toString() + getHref();
//    }

    public SVGImage(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }
    public SVGImage(Double x, Double y, Double width, Double height, String href) {
        super(x, y, width, height);
        this.href = href;
    }

    public String getPreserveAspectRatio() {
        return preserveAspectRatio;
    }

    public void setPreserveAspectRatio(String preserveAspectRatio) {
        this.preserveAspectRatio = preserveAspectRatio;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
