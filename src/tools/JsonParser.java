package tools;

import contentFactories.ElementFactory;
import contentFactories.websiteContentFactories.TextFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import page_components.*;
import page_components.SVGImage;
import page_components.SVGRectangle;
import page_components.SVGText;
import page_components.tree_components.LeafNode;
import page_components.tree_components.Node;
import page_components.tree_components.ViewportGroupNode;
import page_components.tree_components.ViewportNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by warzesia on 04/01/16.
 */
public class JsonParser {

    private static final String filePath = Constants.RESOURCE_PATTERNS_PATH + "/4.json";


    private static Node parseNode(JSONObject jsonObject, int level){

        NodeType nodeType = NodeType.valueOf((String)jsonObject.get("NodeType")) ;
        Double x = (Double.parseDouble((String) jsonObject.get("x")));
        Double y = (Double.parseDouble((String)jsonObject.get("y")));
        Double width = (Double.parseDouble((String)jsonObject.get("width")));
        Double height = (Double.parseDouble((String)jsonObject.get("height")));

        switch (nodeType) {
            case LEAF: {
                PageElement contentElement = parseElement((JSONObject)jsonObject.get("contentElement"));
                LeafNode parsedSVGLeaf = new LeafNode(x, y, width, height, level);
                parsedSVGLeaf.setContentElement(contentElement);
                return parsedSVGLeaf;
            }
            case VIEWPORT: {
                LinkedList<Node> children = new LinkedList<>();
                JSONArray childrenArray = (JSONArray) jsonObject.get("children");
                Iterator i = childrenArray.iterator();

                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    children.add(parseNode(innerObj, level+1));
                }

                ViewportNode parsedSVGViewport = new ViewportNode(x, y, width, height, level);
                parsedSVGViewport.setChildren(children);
                return parsedSVGViewport;
            }
            case VIEWPORT_GROUP: {
                LinkedList<Node> children = new LinkedList<>();
                JSONArray childrenArray = (JSONArray) jsonObject.get("children");
                Iterator i = childrenArray.iterator();

                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    children.add(parseNode(innerObj, level+1));
                }

                ViewportGroupNode parsedTreeViewportGroupNode = new ViewportGroupNode(x, y, width, height, level);
                parsedTreeViewportGroupNode.setTwinChildren(children);
                return parsedTreeViewportGroupNode;
            }
            default: return null;
        }
    }

    private static PageElement parseElement(JSONObject jsonObject){
        ElementType elementType = ElementType.valueOf((String)jsonObject.get("ElementType")) ;
        Double x = (Double.parseDouble((String)jsonObject.get("x")));
        Double y = (Double.parseDouble((String)jsonObject.get("y")));
        Double width = (Double.parseDouble((String)jsonObject.get("width")));
        Double height = (Double.parseDouble((String)jsonObject.get("height")));

        switch (elementType) {
            case IMAGE: {
                SVGImage image = new SVGImage(x, y, width, height);
                image.setHref(ElementFactory.getRandomImageHref());
                return image;
            }
            case RECTANGLE: {
                SVGRectangle rectangle = new SVGRectangle(x, y, width, height);
                return rectangle;
            }
            case TEXT: {
                SVGText text = new SVGText(x, y, width, height);
                text.setContent(TextFactory.getParagraph());
                text.setBackground(ElementFactory.getBackgroundRectangle());
                return text;
            }
            default: return null;
        }
    }

    public static Node run() {

        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            return parseNode(jsonObject, 1);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
