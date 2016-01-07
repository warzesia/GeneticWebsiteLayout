package page_components;

import contentFactories.ElementFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Constants;
import tools.Strings;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGText extends DrawablePageElement {

    String content;
    DrawablePageElement background = ElementFactory.getBackgroundRectangle();


    @Override
    public Element draw(Document document) {
        Element flowRootElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_ROOT);

        Element flowRegionElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_REGION);
        Element flowDivElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_DIV);
        flowRootElement.appendChild(flowRegionElement);
        flowRootElement.appendChild(flowDivElement);

        Element bkgRectangleElement = this.getBackground().draw(document);
        flowRegionElement.appendChild(bkgRectangleElement);

        Element flowParaElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_PARA);
        flowParaElement.setTextContent(this.getContent());
        flowDivElement.appendChild(flowParaElement);

        super.setAttributes(flowRootElement);
        return flowRootElement;
    }

    public SVGText(Double x, Double y, Double width, Double height) {
        super(x, y, width, height);
    }

    public SVGText(Double x, Double y, Double width, Double height, String content) {
        super(x, y, width, height);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DrawablePageElement getBackground() {
        return background;
    }

    public void setBackground(DrawablePageElement background) {
        this.background = background;
    }
}
