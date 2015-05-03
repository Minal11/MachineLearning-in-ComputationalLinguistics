package project;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class TestExtractionWoPOS {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C://Users//Minal//Downloads//EnglishLS.test.tar//EnglishLS.test//EnglishLS.test.key"));
		File file1=new File("C:\\Users\\Minal\\Downloads\\EnglishLS.test.tar\\EnglishLS.test\\EnglishLS.test.key");
	
	//	PrintWriter wr=new PrintWriter("C://Users//Minal//Downloads//Test_fileWithPOS.txt");
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//Test_featuresWoPOS.txt");
	//	MaxentTagger tagger = new MaxentTagger("C:\\Users\\Minal\\Downloads\\stanford-postagger-2015-04-20\\stanford-postagger-2015-04-20\\models\\english-left3words-distsim.tagger");
		File file = new File("C://Users//Minal//Downloads//EnglishLS.test.tar//EnglishLS.test//EnglishLS.test");
		String string = FileUtils.readFileToString(file);	
		int c1=0;
		int c2=0;
		ArrayList<String> sQueries = new ArrayList<String>();
		ArrayList<String> lQueries = new ArrayList<String>();
		
		String[] lex = StringUtils.substringsBetween(string, "<lexelt item=\"", "</lexelt>");
		int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        System.out.println(lines);
       
        int n=0;
		
		if(lex!=null){
			for (String query : lex) 
			{
				String[] instance = StringUtils.substringsBetween(query, "<instance id=\"", "</instance>");
				
				for(String s:instance){
					
					//context extraction
					String[] sentence=StringUtils.substringsBetween(s, "<context>", "</context>");
					
					//POS tagging of each sentence
					String sent=sentence[0];
		//			String tsentence=tagger.tagString(sent);
			//		wr.write(tsentence);
					
					//spliting the sentence based on <head> tags into 3 parts: before word and after
					String tag = "<head>|</head>|</head>.*";
					Pattern pattern = Pattern.compile(tag);
					String[] split = pattern.split(sent);
				//	System.out.println(split.length);
					//before part 
					System.out.println("Split.length="+split.length);
					String before=split[0];
					
					
					String[] bsplit=before.split("\\s+");
					if(bsplit.length>=3){
						if(bsplit[bsplit.length-1].equals(".") || bsplit[bsplit.length-1].equals(" ")){
						
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							
						}
						else if(bsplit[bsplit.length-2].equals(".") || bsplit[bsplit.length-2].equals(" ") ){
							
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
						}
						else{
							f.write(bsplit[bsplit.length-3]);
							f.write(" ");
							f.write(bsplit[bsplit.length-2]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
						}
						
					}
					else{
						if(bsplit.length==0){
							
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
						}
						else if(bsplit.length==1){
							
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
						}
						else {
							
							f.write('_');
							f.write(" ");
							f.write(bsplit[bsplit.length-2]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
						}
					}
					
					
									
					//after part
					String after=split[2];
					String[] asplit=before.split("\\s+");
					if(asplit.length>3){
						if(asplit[1].equals(".") || asplit[1].equals(" ") ){
							
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							
						}
						else if(asplit[2].equals(".") || asplit[2].equals(" ")){
							
							f.write(asplit[1]);
							f.write(" ");
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
						}
						else{
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[2]);
							f.write(" ");
							f.write(asplit[3]);
							f.write(" ");
						}
						
					}
					else{
						if(asplit.length==0 || asplit.length==1){
							
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
						}
						else if(asplit.length==2){
							
							f.write(asplit[1]);
							f.write(" ");
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
							
						}
						else{
							
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[2]);
							f.write(" ");
							f.write('_');
							f.write(" ");
						}
					}
					
				//writing sense id
				//	String[] id = StringUtils.substringsBetween(sense[0], "senseid=\"", "\"");
				//	System.out.println(id[0]);
					//f.write(id[0]);
					 String line = (String) FileUtils.readLines(file1).get(n++);
				       
					 String[] sense=line.split(" ");
					 
					 f.write(sense[sense.length-1]);
					f.write("\n");
				/*	for(int i=0;i<sense.length;i++){
						
						
						
					}*/
					
					
				//	System.out.println("--end--");
				}
			
				
						
			}					
		}
	

	}

}
