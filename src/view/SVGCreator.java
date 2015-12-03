package view;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import tools.Constants;
import tools.Strings;
import treeComponents.*;
import treeComponents.drawable.SVGRectangle;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGCreator {

    private final DOMImplementation domImplementation = SVG12DOMImplementation.getDOMImplementation();
    private final Document document = domImplementation.createDocument(Strings.SVG_NAMESPACE, Strings.SVG, null);
    private final Element rootElement = document.getDocumentElement();

    public void drawSVGTree(SVGNode rootNode){
        rootElement.appendChild(rootNode.draw(document));
    }

    public SVGCreator() {
        this.rootElement.setAttributeNS(null, Strings.VERSION, Constants.SVG_VERSION);
        this.rootElement.setAttributeNS(null, Strings.WIDTH, Constants.SVG_ROOT_WIDTH);
        this.rootElement.setAttributeNS(null, Strings.HEIGHT, Constants.SVG_ROOT_HEIGHT);
    }

    public SVGDocument getSVGDocument(){
        return (SVGDocument)document;
    }


}

