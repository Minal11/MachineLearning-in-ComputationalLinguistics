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

public class TestExtraction {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C://Users//Minal//Downloads//EnglishLS.test.tar//EnglishLS.test//Activate.test.key"));
		File file1=new File("C:\\Users\\Minal\\Downloads\\EnglishLS.test.tar\\EnglishLS.test\\Activate.test.key");
	
	//	PrintWriter wr=new PrintWriter("C://Users//Minal//Downloads//Test_fileWithPOS.txt");
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//activate//Test_featuresConsoleWriter.txt");
		MaxentTagger tagger = new MaxentTagger("C:\\Users\\Minal\\Downloads\\stanford-postagger-2015-04-20\\stanford-postagger-2015-04-20\\models\\english-left3words-distsim.tagger");
		File file = new File("C://Users//Minal//Downloads//EnglishLS.test.tar//EnglishLS.test//Activate.test");
		String string = FileUtils.readFileToString(file);	
		
		
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
					String tsentence=tagger.tagString(sent);
			//		wr.write(tsentence);
					
					//spliting the sentence based on <head> tags into 3 parts: before word and after
					String tag = "<head>_|</head>_|</head>_.*";
					Pattern pattern = Pattern.compile(tag);
					String[] split = pattern.split(tsentence);
				//	System.out.println(split.length);
					//before part 
					String before=split[0];
					
					String[] bsplit=before.split(" ");
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
					String[] asplit=before.split(" ");
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
					
					
							
					//wr.write("\n");
				
				//writing sense id
				//	String[] id = StringUtils.substringsBetween(sense[0], "senseid=\"", "\"");
				//	System.out.println(id[0]);
					//f.write(id[0]);
					 String line = (String) FileUtils.readLines(file1).get(n++);
				        //System.out.println(line32);
					 String[] sense=line.split(" ");
					 System.out.println(sense[2]);
					 f.write(sense[2]);
					f.write("\n");
				/*	for(int i=0;i<sense.length;i++){
						
						
						
					}*/
					
					
				//	System.out.println("--end--");
				}
			
				
						
			}					
		}
	

	}

}
