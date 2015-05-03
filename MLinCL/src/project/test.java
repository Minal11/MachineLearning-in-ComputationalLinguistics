package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new FileReader("C://Users//Minal//Downloads//EnglishLS.test.tar//EnglishLS.test//EnglishLS.test.key"));
	File f=new File("C:\\Users\\Minal\\Downloads\\EnglishLS.test.tar\\EnglishLS.test\\EnglishLS.test.key");
		String string="Do_VB on_IN to_TO <head>_VB activate_VBP </head>_NN it_PRP ._. Used_VBN correctly_RB ";
		String tag = "<head>_|</head>_|</head>_.*";
		Pattern pattern = Pattern.compile(tag);
		String[] split = pattern.split(string);
            
            System.out.println(split[0]);
           System.out.println(split[1]);
           System.out.println(split[2]);
           
//       	Matcher matcher = pattern.matcher(string);
   		//while (matcher.find()) {
   			//System.out.println(matcher.group(1)); 
   	    //}
           System.out.println("split.length = " + split.length);
           
           int lines = 0;
           while (reader.readLine() != null) lines++;
           reader.close();
           System.out.println(lines);
           String line32 = (String) FileUtils.readLines(f).get(0);
           System.out.println(line32);
	}

}


/*
 * 	/*	String tag = "<lexelt item=\"(.*?)\">(.+?)</lexelt>";
		Pattern pattern = Pattern.compile(tag);
		Matcher matcher = pattern.matcher(string);
		while (matcher.find()) {
			System.out.println(matcher.group(1)); 
	    }*/
		
    /*    String[] split = pattern.split(string);
        System.out.println("split.length = " + split.length);
        
       
System.out.println("split.length = " + split.length);
        
        for(String element : split){
            System.out.println("element = " + element);
        }
		*/
 