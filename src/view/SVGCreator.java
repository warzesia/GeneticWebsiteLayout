package view;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;
import page_components.tree_components.Node;
import tools.Constants;
import tools.Strings;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGCreator {

    private final DOMImplementation domImplementation = SVG12DOMImplementation.getDOMImplementation();
    private final Document document = domImplementation.createDocument(Strings.SVG_NAMESPACE, Strings.SVG, null);
    private final Element rootElement = document.getDocumentElement();

    public void drawSVGTree(Node rootNode){
        rootElement.appendChild(rootNode.draw(document));
    }

    public SVGCreator(String width, String height) {
        this.rootElement.setAttributeNS(null, Strings.VERSION, Constants.SVG_VERSION);
        this.rootElement.setAttributeNS(null, Strings.WIDTH, width);
        this.rootElement.setAttributeNS(null, Strings.HEIGHT, height);
    }

    public SVGDocument getSVGDocument(){
        return (SVGDocument)document;
    }


}

