package project;


import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.PorterStemmer;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.util.Sets;

import java.util.regex.*;
import java.util.*;

public class KeywordExtraction{
	private static HashSet<String> stopwords;
	static Map<String,Set<String>> map=new HashMap<String,Set<String>>();
	public static void main(String[] args) throws IOException {
	
		
	
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//TrainActivate_keywords.txt");
			//FileWriter f=new FileWriter("C://Users//Minal//Downloads//key.txt");
			PrintWriter wrt = new PrintWriter(new BufferedWriter(new FileWriter("C://Users//Minal//Downloads//Map_key.txt", true)));
		File file = new File("C://Users//Minal//Downloads//EnglishLS.train.tar//EnglishLS.train//Activate.train");
		String string = FileUtils.readFileToString(file);	
		
		
		
		String[] lex = StringUtils.substringsBetween(string, "<lexelt item=\"", "</lexelt>");
		
		
		if(lex!=null){
			for (String query : lex) 
			{
				String[] instance = StringUtils.substringsBetween(query, "<instance id=\"", "</instance>");
				
				for(String s:instance){
					//sense id extraction
					String[] sense = StringUtils.substringsBetween(s, "<answer instance=\"", "/>");
					String[] id = StringUtils.substringsBetween(sense[0], "senseid=\"", "\"");
					
					//context extraction
					String[] sentence=StringUtils.substringsBetween(s, "<context>", "</context>");
					
					//POS tagging of each sentence
					String sent=sentence[0];
					
					Set<String> hs=new HashSet<String>();
					hs=keywordExtract(sent);
				    //writing sense id
					f.write(id[0]);
					f.write(" ");
					int c=hs.size();
					f.write(""+c);
					f.write("\n");
					//System.out.println(id[0]+" "+c);
					if(!map.containsKey(id[0])){
						map.put(id[0], hs);
					}
					else{
						Set<String> temp=(Set<String>)map.get(id[0]);
						temp.addAll(hs);
						map.put(id[0], temp);
						
					}
					
			
				//	System.out.println("--end--");
				}
			
				
						
			}					
		}
		
		Iterator it=map.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry pairs=(Map.Entry)it.next();
			Set<String> t=(Set<String>) pairs.getValue();
			String s=pairs.getKey()+" "+t.size();
		//	System.out.println(s);
			wrt.println(s);
			//it.remove();
		}
	wrt.close();
	f.close();
	
	formTestKeywords();

	}

	public static void formTestKeywords() throws IOException {
		//System.out.println("i m here");
		// TODO Auto-generated method stub
	//	PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//Test_keywordsDemo.txt");
		//FileWriter f=new FileWriter("C://Users//Minal//Downloads//key.txt");
		PrintWriter f = new PrintWriter(new BufferedWriter(new FileWriter("C://Users//Minal//Downloads//activate//Test_keywords.txt", true)));
		File file = new File("C://Users//Minal//Downloads//EnglishLS.test.tar//EnglishLS.test//Activate.test");
		String string = FileUtils.readFileToString(file);	
	
		String[] lex = StringUtils.substringsBetween(string, "<lexelt item=\"", "</lexelt>");
	
		int n=0;
	
	if(lex!=null){
		for (String query : lex) 
		{
			String[] instance = StringUtils.substringsBetween(query, "<instance id=\"", "</instance>");
			
			for(String s:instance){
				int max=0;
				
				//context extraction
				String[] sentence=StringUtils.substringsBetween(s, "<context>", "</context>");
				
				//POS tagging of each sentence
				String sent=sentence[0];
				n++;
				
				Set<String> hs=new HashSet<String>();
				hs=keywordExtract(sent);
				
				//System.out.println(hs);
			    //writing sense id
				
				Iterator it=map.entrySet().iterator();
				String sense="";
				while(it.hasNext()){
				//	System.out.println("in map iterator");
					Map.Entry pairs=(Map.Entry)it.next();
					String key=(String)pairs.getKey();
					Set<String> t=(Set<String>) pairs.getValue();
					int intercount=Sets.intersection(t, hs).size();
					if(intercount>max){
						max=intercount;
						sense=key;
					}
				//	it.remove();
				}
				//System.out.println(map.size());
			//	f.write(""+n);
				f.write(" ");
				f.write(sense);
				f.write(" ");
				f.write(""+max);
				f.write("\n");
			//	System.out.println("--end--");
				System.out.println(sense+" "+max);
			}
		
			f.close();
			
		}					
	}
	
	


}

	
	
	
	
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
		//System.out.println(keywords);
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

		PorterStemmer stem = new PorterStemmer();
		 stem.setCurrent(term);
		 stem.stem();
		 String result = stem.getCurrent();
		 return result;
	}
	public static void removeStopWords(String str) throws IOException{
		System.out.println("In remove stop word");
		Set<String> res=new HashSet<String>();
		ArrayList<String> stop_Words = new ArrayList<String>();	
		//change the path of file accordingly, file given in folder
		File file = new File("E:\\InfoRetrievalFinalProject\\stopwords.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while(line!=null){
			stop_Words.add(line);
			line = br.readLine();
		}
		CharArraySet stopSet = new CharArraySet(stop_Words, true);	
		CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
		TokenStream tokenStream = new StandardTokenizer(Version.LUCENE_48, new StringReader(str.trim()));

	    tokenStream = new StopFilter(Version.LUCENE_48, tokenStream, stopSet);	
	    tokenStream = new StopFilter(Version.LUCENE_48, tokenStream, stopWords);
	    tokenStream = new PorterStemFilter(tokenStream);
	    
	    StringBuilder sb = new StringBuilder();
	    CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
	    tokenStream.reset();
	    while (tokenStream.incrementToken()) {
	        String term = charTermAttribute.toString();
	        term.replace("."," ");
	        sb.append(term + " ");
	    }
	    String tokenizedReview= sb.toString();
	//   System.out.println(tokenizedReview);
		
		//return res;
	}

}
