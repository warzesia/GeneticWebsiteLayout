package treeComponents.drawable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Strings;
import treeComponents.SVGElement;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGImage extends SVGElement {

    String preserveAspectRatio;
    String href = "file:///home/warzesia/Desktop/dissertation/svg_tests/jpg_example.jpg";;

    @Override
    public Element draw(Document document, String svgNamespace) {
        Element element = document.createElementNS(svgNamespace, Strings.RECTANGLE);
        super.setAttributes(element);
        element.setAttributeNS(Strings.XLINK_NAMESPACE, Strings.XLINK_HREF, this.getHref());
        element.setAttributeNS(null, Strings.PRESERVE_ASPECT_RATIO, this.getPreserveAspectRatio());
        return element;
    }

    public SVGImage(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
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
