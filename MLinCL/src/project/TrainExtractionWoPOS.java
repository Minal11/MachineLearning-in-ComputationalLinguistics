package project;


import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.regex.*;
import java.util.*;

public class TrainExtractionWoPOS {
	
	public static void main(String[] args) throws IOException {
	
		//PrintWriter wr=new PrintWriter("C://Users//Minal//Downloads//fileWithPOS.txt");
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//featuresWoPOS.txt");
	//	MaxentTagger tagger = new MaxentTagger("C:\\Users\\Minal\\Downloads\\stanford-postagger-2015-04-20\\stanford-postagger-2015-04-20\\models\\english-left3words-distsim.tagger");
		File file = new File("C://Users//Minal//Downloads//EnglishLS.train.tar//EnglishLS.train//EnglishLS.train");
		String string = FileUtils.readFileToString(file);	
		int c1=0;
		int c2=0;
		ArrayList<String> sQueries = new ArrayList<String>();
		ArrayList<String> lQueries = new ArrayList<String>();
		
		String[] lex = StringUtils.substringsBetween(string, "<lexelt item=\"", "</lexelt>");
		
		
		if(lex!=null){
			for (String query : lex) 
			{
				String[] instance = StringUtils.substringsBetween(query, "<instance id=\"", "</instance>");
				
				for(String s:instance){
					//sense id extraction
					String[] sense = StringUtils.substringsBetween(s, "<answer instance=\"", "/>");
					//context extraction
					String[] sentence=StringUtils.substringsBetween(s, "<context>", "</context>");
					
					//POS tagging of each sentence
				String sent=sentence[0];
				//	String tsentence=tagger.tagString(sent);
				//	wr.write(tsentence); 
					
					//spliting the sentence based on <head> tags into 3 parts: before word and after
					String tag = "<head>|</head>|</head>.*";
					Pattern pattern = Pattern.compile(tag);
					String[] split = pattern.split(sent);
				//	System.out.println(split.length);
					//before part 
					String before=split[0];
					System.out.println(before);
					
					String[] bsplit=before.split("\\s+");
					if(bsplit.length>=3){
						if(bsplit[bsplit.length-1].equals(".")){
						
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							
						}
						else if(bsplit[bsplit.length-2].equals(".")){
							
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
						if(asplit[1].equals(".")){
							
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							f.write("_");
							f.write(" ");
							
						}
						else if(asplit[2].equals(".")){
							
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
					String[] id = StringUtils.substringsBetween(sense[0], "senseid=\"", "\"");
					System.out.println(id[0]);
					f.write(id[0]);
					f.write("\n");
				/*	for(int i=0;i<sense.length;i++){
						
						
						
					}*/
					
					
					System.out.println("--end--");
				}
			
				
						
			}					
		}
	

	}
}
