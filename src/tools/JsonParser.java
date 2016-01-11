package tools;

import content_generators.RandomContentGenerator;
import content_generators.RandomElementGenerator;
import content_generators.RandomLayoutGenerator;
import content_generators.TextGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import page_components.*;
import page_components.SVGImage;
import page_components.SVGRectangle;
import page_components.SVGText;
import page_components.enums.ContentType;
import page_components.enums.DrawableType;
import page_components.tree_components.LeafNode;
import page_components.tree_components.Node;
import page_components.tree_components.ViewportGroupNode;
import page_components.tree_components.ViewportNode;
import page_components.tree_components.enums.NodeType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by warzesia on 04/01/16.
 */
public class JsonParser {

    private static final String filePath = Constants.RESOURCE_PATTERNS_PATH + "/1.json";


    private static Node parseNode(JSONObject jsonObject, int level){

        NodeType nodeType = NodeType.valueOf((String)jsonObject.get("NodeType")) ;
        Double x = (Double.parseDouble((String) jsonObject.get("x")));
        Double y = (Double.parseDouble((String)jsonObject.get("y")));
        Double width = (Double.parseDouble((String)jsonObject.get("width")));
        Double height = (Double.parseDouble((String)jsonObject.get("height")));
        String contentType  = (String)jsonObject.get("tag");
        ContentType tag = contentType == null ? ContentType.DECOR : ContentType.valueOf(contentType) ;

        switch (nodeType) {
            case LEAF: {
                DrawablePageElement contentElement = parseElement((JSONObject)jsonObject.get("contentElement"), tag);
                LeafNode parsedSVGLeaf = new LeafNode(x, y, width, height, level);
                parsedSVGLeaf.setBackgroundRectangle(RandomContentGenerator.getBackgroundRectangle());
                parsedSVGLeaf.setContentElement(contentElement);
                parsedSVGLeaf.addMetadata(tag);
                return parsedSVGLeaf;
            }
            case VIEWPORT: {
                LinkedList<Node> children = new LinkedList<>();
                JSONArray childrenArray = (JSONArray) jsonObject.get("children");

                for (Object aChildrenArray : childrenArray) {
                    JSONObject innerObj = (JSONObject) aChildrenArray;
                    children.add(parseNode(innerObj, level + 1));
                }

                ViewportNode parsedSVGViewport = new ViewportNode(x, y, width, height, level);
                parsedSVGViewport.setChildren(children);
                parsedSVGViewport.addMetadata(tag);
                return parsedSVGViewport;
            }
            case VIEWPORT_GROUP: {
                LinkedList<Node> children = new LinkedList<>();
                JSONArray childrenArray = (JSONArray) jsonObject.get("children");

                Node twinNode = parseNode((JSONObject) childrenArray.get(0), level + 1);
                for (Object aChildrenArray : childrenArray) {
                    JSONObject innerObj = (JSONObject) aChildrenArray;

                    Double childX = (Double.parseDouble((String) innerObj.get("x")));
                    Double childY = (Double.parseDouble((String)innerObj.get("y")));
                    Double childWidth = (Double.parseDouble((String)innerObj.get("width")));
                    Double childHeight = (Double.parseDouble((String)innerObj.get("height")));

                    children.add(twinNode.copyWithDifferentPlacement(childX, childY, childWidth, childHeight));

                }

                ViewportGroupNode parsedTreeViewportGroupNode = new ViewportGroupNode(x, y, width, height, level);
                parsedTreeViewportGroupNode.setTwinChildren(children);
                parsedTreeViewportGroupNode.addMetadata(tag);
                return parsedTreeViewportGroupNode;
            }
            default: return null;
        }
    }

    private static DrawablePageElement parseElement(JSONObject jsonObject, ContentType tag){
        DrawableType drawableType = DrawableType.valueOf((String) jsonObject.get("DrawableType")) ;
        Double x = (Double.parseDouble((String)jsonObject.get("x")));
        Double y = (Double.parseDouble((String)jsonObject.get("y")));
        Double width = (Double.parseDouble((String)jsonObject.get("width")));
        Double height = (Double.parseDouble((String)jsonObject.get("height")));
        return RandomContentGenerator.getContent(drawableType, tag, x, y, width, height);
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
