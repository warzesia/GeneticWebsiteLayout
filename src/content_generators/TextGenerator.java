package content_generators;

import de.svenjacobs.loremipsum.LoremIpsum;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by warzesia on 04/01/16.
 */
public class TextGenerator {


    private LoremIpsum loremIpsum;
    public TextGenerator(){
        loremIpsum = new LoremIpsum();
    }

    public String getWord(){
        return loremIpsum.getWords(1, ThreadLocalRandom.current().nextInt(25));
    }

    public String getSentence(){
        return loremIpsum.getWords(ThreadLocalRandom.current().nextInt(7)+7, ThreadLocalRandom.current().nextInt(25));
    }

    public String getParagraph(){
        return loremIpsum.getParagraphs(1);
    }

}
