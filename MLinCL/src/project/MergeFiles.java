package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

public class MergeFiles {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//activate//FinalTest.txt");
		File file=new File("C://Users//Minal//Downloads//activate//Test2Features.txt");
		BufferedReader reader = new BufferedReader(new FileReader("C://Users//Minal//Downloads//activate//Test6Features.txt"));
		String line;
		int n=0;
		while ((line=reader.readLine() )!= null && n<228) {
			String[] split=line.split(" ");
			f.write(split[0]);
			f.write(" ");
			f.write(split[1]);
			f.write(" ");
			f.write(split[2]);
			f.write(" ");
			f.write(split[3]);
			f.write(" ");
			f.write(split[4]);
			f.write(" ");
			f.write(split[5]);
			f.write(" ");
			String l="";
			
				 l = (String) FileUtils.readLines(file).get(n++);
			
				
		    //System.out.println(l+" "+n);  
			f.write(l);
			f.write(" ");
			f.write(split[6]);
			f.write("\n");
			System.out.println(split[0]+" "+split[1]+" "+split[2]+" "+split[3]+" "+split[4]+" "+split[5]+" "+l+" "+split[6]);
			
			
			
		}
        reader.close();

		f.close();
		

	}

}
