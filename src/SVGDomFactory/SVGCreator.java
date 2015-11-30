package SVGDomFactory;

import org.apache.batik.dom.svg12.SVG12DOMImplementation;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tools.Strings;
import treeComponents.*;

/**
 * Created by warzesia on 29/11/15.
 */
public class SVGCreator {

    static final String svgNamespace = SVG12DOMImplementation.SVG_NAMESPACE_URI;

    static final DOMImplementation domImplementation = SVG12DOMImplementation.getDOMImplementation();;
    static final Document document = domImplementation.createDocument(svgNamespace, "svg", null);
    static final Element rootElement;

    static {
        rootElement = SVGCreator.document.getDocumentElement();
        rootElement.setAttributeNS(null, Strings.VERSION, "1.2");
        rootElement.setAttributeNS(null, Strings.WIDTH, String.valueOf(500));
        rootElement.setAttributeNS(null, Strings.HEIGHT, String.valueOf(400));
    }

    public static void drawTree(SVGNode rootNode){
        rootElement.appendChild(rootNode.draw(document, svgNamespace));
    }

}

