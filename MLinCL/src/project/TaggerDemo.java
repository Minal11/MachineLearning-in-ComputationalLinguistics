package project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

class TaggerDemo {

  private TaggerDemo() {}

  public static void main(String[] args) throws Exception {
    
	   MaxentTagger tagger = new MaxentTagger("C:\\Users\\Minal\\Downloads\\stanford-postagger-2015-04-20\\stanford-postagger-2015-04-20\\models\\english-left3words-distsim.tagger");
	    List<List<HasWord>> sentences = MaxentTagger.tokenizeText(new BufferedReader(new FileReader("C://Users//Minal//Downloads//EnglishLS.train.tar//EnglishLS.train//EnglishLS.train")));
	    for (List<HasWord> sentence : sentences) {
	      List<TaggedWord> tSentence = tagger.tagSentence(sentence);
	      System.out.println(Sentence.listToString(tSentence, false));
	    }
	    
	  
    
  }

}
