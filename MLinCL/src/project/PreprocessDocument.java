package project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.tartarus.snowball.ext.PorterStemmer;

public class PreprocessDocument {
	
	private static HashSet<String> stopwords;

	public static Set<String> keywordExtract(String input) {
		// TODO Auto-generated method stub
		//fetchStopwordsFromFile();
		String[] k = removeStopwords(input);
		Set<String> keywords = new HashSet<String>();
		
		try {
			for(String term : k){
				
					keywords.add(stemmize(term));				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		System.out.println(keywords);
		return keywords;
	}
	
	public static void fetchStopwordsFromFile() {
        try{
            BufferedReader br;
            File f = new File("E:\\InfoRetrievalFinalProject\\stopwords.txt");
            br = new BufferedReader(new FileReader(f));
            String line;
            while((line=br.readLine())!=null) {
                stopwords.add(line);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	public static String[] removeStopwords(String inputText) {

        if(stopwords==null){
            stopwords = new HashSet<String>();
        }
        if(stopwords.isEmpty()) {
            fetchStopwordsFromFile();
        }

        ArrayList<String> result;
        result = new ArrayList<>();

        String[] words = inputText.split("[ \t\n,\\.\"!?$~()\\[\\]\\{\\}:;/\\\\<>+=%*]");
        for(int i=0; i < words.length; i++) {
            if(words[i] != null && !words[i].equals("")) {
                String word = words[i].toLowerCase();
                if(!stopwords.contains(word)) {
                    if(word.contains("'")){
                        StringBuilder output=new StringBuilder();
                        for(int j=0; j<word.length(); j++){
                            if(word.charAt(j)=='\''){
                                if(j==0 || word.charAt(j-1)!='\\'){
                                    output.append('\\');
                                }
                            }
                            output.append(word.charAt(j));
                        }
                        word=output.toString();
                    }
                    result.add(word);
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }
	@SuppressWarnings("resource")
	public static String stemmize(String term) throws IOException {

	  /*  // tokenize term
	    TokenStream tokenStream = new ClassicTokenizer();
	    // stemmize
	    tokenStream = new PorterStemFilter(tokenStream);

	    Set<String> stems = new HashSet<String>();
	    CharTermAttribute token = tokenStream.getAttribute(CharTermAttribute.class);
	    // for each token
	    while (tokenStream.incrementToken()) {
	        // add it in the dedicated set (to keep unicity)
	        stems.add(token.toString());
	    }

	    // if no stem or 2+ stems have been found, return null
	    if (stems.size() != 1) {
	        return null;
	    }

	    String stem = stems.iterator().next();

	    // if the stem has non-alphanumerical chars, return null
	    if (!stem.matches("[\\w-]+")) {
	        return null;
	    }

	    return stem; */
		PorterStemmer stem = new PorterStemmer();
		 stem.setCurrent(term);
		 stem.stem();
		 String result = stem.getCurrent();
		 return result;
	}
}
