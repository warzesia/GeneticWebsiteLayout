package content_generators.websiteContentFactories;

import content_generators.RandomContentGenerator;
import content_generators.RandomElementGenerator;
import content_generators.RandomLayoutGenerator;
import de.svenjacobs.loremipsum.LoremIpsum;
import page_components.tree_components.LeafNode;
import page_components.tree_components.Node;
import page_components.tree_components.ViewportNode;
import page_components.tree_components.ViewportGroupNode;
import page_components.SVGImage;
import page_components.SVGRectangle;
import page_components.SVGText;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 06/01/16.
 */
public class ConcreteNodesFactory {

    public static Node getPage(Integer level){

        ViewportNode pageViewport = new ViewportNode(level);
        LinkedList<Node> children = new LinkedList<>();
        children.add(getLogoPanel(level + 1));
        children.add(getButtonsPanel(level + 1));
        children.add(getPicture(level + 1));
        children.add(getParagraph(level + 1));

        pageViewport.setChildren(RandomLayoutGenerator.getRandomlyPlacedNodes(children, level));
        return pageViewport;

    }


    private static LoremIpsum loremIpsum = new LoremIpsum();

    public static Node getLogoPanel(Integer level){
        LeafNode logoLeaf = new LeafNode(level);
        SVGImage logo = RandomContentGenerator.getRandomLogo();
        logoLeaf.setContentElement(logo);

        return logoLeaf;
    }

    public static Node getPicture(Integer level){
        LeafNode textLeaf = new LeafNode(level);

        SVGText content = RandomElementGenerator.getRandomSVGText();
        SVGImage backgroungImage = RandomElementGenerator.getRandomSVGImage();
        backgroungImage.setHref(RandomContentGenerator.getRandomImageHref());

        content.setBackground(backgroungImage);
        String sentence = ThreadLocalRandom.current().nextInt(1)%2==0 ? loremIpsum.getWords(5) : "";
        content.setContent(sentence);

        textLeaf.setContentElement(content);
        return textLeaf;
    }


    public static Node getParagraph(Integer level){

        ViewportNode textViewport = new ViewportNode(level);

        LinkedList<Node> children = new LinkedList<>();
        LeafNode titleLeaf = new LeafNode(level + 1);
        LeafNode contentLeaf = new LeafNode(level + 1);

        SVGText title = RandomElementGenerator.getRandomSVGText();
        SVGText content = RandomElementGenerator.getRandomSVGText();

        title.setContent(loremIpsum.getWords(5));
        content.setContent(loremIpsum.getParagraphs(1));

        titleLeaf.setContentElement(title);
        contentLeaf.setContentElement(content);

        children.add(titleLeaf);
        children.add(contentLeaf);

        textViewport.setChildren(RandomLayoutGenerator.getRandomlyPlacedNodes(children, level + 1));
        return textViewport;

//        hey {be-nice-to-Magdus}
//        if "you are nice"
//        then "you remain in one piece";
    }

    public static Node getSentence(Integer level){

        LeafNode textLeaf = new LeafNode(level);
        SVGText content = RandomElementGenerator.getRandomSVGText();

        content.setContent(loremIpsum.getWords(5));
        textLeaf.setContentElement(content);

        return textLeaf;
    }


    public static Node getButtonsPanel(Integer level){
        ViewportGroupNode buttonGroup = new ViewportGroupNode(level);
        buttonGroup.setTwinChildren(RandomLayoutGenerator.getRandomlyPlacedTwinChildren(getPlainButton(level + 1), 5));
        return buttonGroup;
    }

    public static Node getRandomButton(Integer level){
        return ThreadLocalRandom.current().nextInt(1)%2==0 ? getPlainButton(level) : getDescriptionButton(level);
    }


    public static Node getPlainButton(Integer level){
        LeafNode buttonLeaf = new LeafNode(level);
        SVGText content = RandomElementGenerator.getRandomSVGText();

        content.setContent(loremIpsum.getWords(1));
        content.setBackground(RandomElementGenerator.getRandomSVGRectangle());
        buttonLeaf.setContentElement(content);

        return buttonLeaf;
    }

    public static Node getDescriptionButton(Integer level){
        ViewportNode buttonViewport = new ViewportNode(level);

        LinkedList<Node> children = new LinkedList<>();
        LeafNode buttonLeaf = new LeafNode(level + 1);
        LeafNode contentLeaf = new LeafNode(level + 1);

        SVGRectangle button = RandomElementGenerator.getRandomSVGRectangle();
        SVGText content = RandomElementGenerator.getRandomSVGText();

        buttonLeaf.setContentElement(button);
        contentLeaf.setContentElement(content);

        children.add(buttonLeaf);
        children.add(contentLeaf);

        buttonViewport.setChildren(RandomLayoutGenerator.getRandomlyPlacedNodes(children, level + 1));
        return buttonViewport;
    }

}
