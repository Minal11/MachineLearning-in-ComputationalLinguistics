package project;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.util.regex.*;
import java.util.*;

public class Extraction {
	
	public static void main(String[] args) throws IOException {
	
	//	PrintWriter wr=new PrintWriter("C://Users//Minal//Downloads//fileWithPOSWith.txt");
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//activate//features.txt");
		MaxentTagger tagger = new MaxentTagger("C:\\Users\\Minal\\Downloads\\stanford-postagger-2015-04-20\\stanford-postagger-2015-04-20\\models\\english-left3words-distsim.tagger");
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
					//context extraction
					String[] sentence=StringUtils.substringsBetween(s, "<context>", "</context>");
					
					//POS tagging of each sentence
					String sent=sentence[0];
					String tsentence=tagger.tagString(sent);
			//		wr.write(tsentence); 
					
					//spliting the sentence based on <head> tags into 3 parts: before word and after
					String tag = "<head>_|</head>_|</head>_.*";
					Pattern pattern = Pattern.compile(tag);
					String[] split = pattern.split(tsentence);
				//	System.out.println(split.length);
					//before part 
					String before=split[0];
					
					String[] bsplit=before.split("\\s+");
					if(bsplit.length>=3){
						if(bsplit[bsplit.length-1].equals('_')){
							
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							System.out.print(bsplit[bsplit.length-1]+" "+bsplit[bsplit.length-1]+" "+bsplit[bsplit.length-1]+" ");
						}
						else if(bsplit[bsplit.length-2].equals('_')){
							f.write(bsplit[bsplit.length-2]);
							f.write(" ");
							f.write(bsplit[bsplit.length-2]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							System.out.print(bsplit[bsplit.length-2]+" "+bsplit[bsplit.length-2]+" "+bsplit[bsplit.length-1]+" ");
						}
						else{
							f.write(bsplit[bsplit.length-3]);
							f.write(" ");
							f.write(bsplit[bsplit.length-2]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							System.out.print(bsplit[bsplit.length-3]+" "+bsplit[bsplit.length-2]+" "+bsplit[bsplit.length-1]+" ");
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
							System.out.print("_"+" "+"_"+" "+"_"+" ");
						}
						else if(bsplit.length==1){
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							System.out.print("_"+" "+"_"+" "+bsplit[bsplit.length-1]+" ");
						}
						else {
							f.write('_');
							f.write(" ");
							f.write(bsplit[bsplit.length-2]);
							f.write(" ");
							f.write(bsplit[bsplit.length-1]);
							f.write(" ");
							System.out.print("_"+" "+bsplit[bsplit.length-2]+" "+bsplit[bsplit.length-1]+" ");
						}
					}
					
					
									
					//after part
					String after=split[2];
					String[] asplit=before.split("\\s+");
					if(asplit.length>3){
						if(asplit[1].equals('_')){
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[1]);
							f.write(" ");
							System.out.print(asplit[1]+" "+asplit[1]+" "+asplit[1]+" ");
							
						}
						else if(asplit[2].equals('_')){
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[2]);
							f.write(" ");
							f.write(asplit[2]);
							f.write(" ");
							System.out.print(asplit[1]+" "+asplit[2]+" "+asplit[2]+" ");
						}
						else{
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[2]);
							f.write(" ");
							f.write(asplit[3]);
							f.write(" ");
							System.out.print(asplit[1]+" "+asplit[2]+" "+asplit[3]+" ");
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
							System.out.print("_"+" "+"_"+" "+"_"+" ");
						}
						else if(asplit.length==2){
							f.write(asplit[1]);
							f.write(" ");
							f.write('_');
							f.write(" ");
							f.write('_');
							f.write(" ");
							System.out.print(asplit[1]+" "+"_"+" "+"_"+" ");
							
						}
						else{
							f.write(asplit[1]);
							f.write(" ");
							f.write(asplit[2]);
							f.write(" ");
							f.write('_');
							f.write(" ");
							System.out.print(asplit[1]+" "+asplit[2]+" "+"_"+" ");
						}
					}
					
					
							
				//	wr.write("\n");
				
				//writing sense id
					String[] id = StringUtils.substringsBetween(sense[0], "senseid=\"", "\"");
					System.out.println(id[0]);
					
					
					
			//last column		
					f.write(id[0]);
					f.write("\n");
			
					
					
				//	System.out.println("--end--");
				}
			
				
						
			}					
		}
	

	}
}
