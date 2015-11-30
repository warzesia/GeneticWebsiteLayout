package treeComponents.drawable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Constants;
import tools.Strings;
import treeComponents.SVGElement;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGText extends SVGElement {

    String content;
    SVGRectangle backgroundRectangle;

    @Override
    public Element draw(Document document) {
        Element flowRootElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_ROOT);

        Element flowRegionElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_REGION);
        Element flowDivElement = document.createElementNS(Constants.SVG_NAMESPACE, Strings.FLOW_DIV);
        flowRootElement.appendChild(flowRegionElement);
        flowRootElement.appendChild(flowDivElement);

        Element bkgRectangleElement = this.getBackgroundRectangle().draw(document);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SVGRectangle getBackgroundRectangle() {
        return backgroundRectangle;
    }

    public void setBackgroundRectangle(SVGRectangle backgroundRectangle) {
        this.backgroundRectangle = backgroundRectangle;
    }
}
