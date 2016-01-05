package tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import treeComponents.*;
import treeComponents.drawable.SVGImage;
import treeComponents.drawable.SVGRectangle;

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


    private static SVGNode parseNode(JSONObject jsonObject, int level){

        NodeType nodeType = NodeType.valueOf((String)jsonObject.get("NodeType")) ;
        Double x = (Double.parseDouble((String) jsonObject.get("x")));
        Double y = (Double.parseDouble((String)jsonObject.get("y")));
        Double width = (Double.parseDouble((String)jsonObject.get("width")));
        Double height = (Double.parseDouble((String)jsonObject.get("height")));

        switch (nodeType) {
            case LEAF: {
                SVGElement contentElement = parseElement((JSONObject)jsonObject.get("contentElement"));
                SVGLeaf parsedSVGLeaf = new SVGLeaf(x, y, width, height, level);
                parsedSVGLeaf.setContentElement(contentElement);
                return parsedSVGLeaf;
            }
            case VIEWPORT: {
                LinkedList<SVGNode> children = new LinkedList<>();
                JSONArray childrenArray = (JSONArray) jsonObject.get("children");
                Iterator i = childrenArray.iterator();

                while (i.hasNext()) {
                    JSONObject innerObj = (JSONObject) i.next();
                    children.add(parseNode(innerObj, level+1));
                }

                SVGViewport parsedSVGViewport = new SVGViewport(x, y, width, height, level);
                parsedSVGViewport.setChildren(children);
                return parsedSVGViewport;
            }
//            case VIEWPORT_GROUP: {
//                return new SVGViewportGroup(x, y, width, height, childLevel);
//            }
            default: return null;
        }
    }

    private static SVGElement parseElement(JSONObject jsonObject){
        ElementType elementType = ElementType.valueOf((String)jsonObject.get("ElementType")) ;
        Double x = (Double.parseDouble((String)jsonObject.get("x")));
        Double y = (Double.parseDouble((String)jsonObject.get("y")));
        Double width = (Double.parseDouble((String)jsonObject.get("width")));
        Double height = (Double.parseDouble((String)jsonObject.get("height")));

        switch (elementType) {
            case IMAGE: return new SVGImage(x, y, width, height);
            case RECTANGLE: return new SVGRectangle(x, y, width, height);
            default: return null;
        }
    }

    public static SVGNode run() {

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
