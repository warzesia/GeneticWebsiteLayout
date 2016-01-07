package contentFactories.websiteContentFactories;

import de.svenjacobs.loremipsum.LoremIpsum;
/**
 * Created by warzesia on 04/01/16.
 */
public class TextFactory {

    private static LoremIpsum loremIpsum = new LoremIpsum();
    //generate random text blocks

    private static String getSentence(){
        return loremIpsum.getWords();
    }

    public static String getParagraph(){
        return loremIpsum.getParagraphs(1);
    }

}
