package project;

import java.io.*;

public class Bigrams {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter f=new PrintWriter("C://Users//Minal//Downloads//Test_featuresWoPOSBigrams.txt");
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Minal\\SkyDrive\\Courses\\Spring 2015\\Applying ML in CL\\FinalProject\\Test_featuresWoPOS.txt"));
		String line;
		while ((line=reader.readLine() )!= null) {
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
			
			f.write(split[0]+split[1]);
			f.write(" ");
			f.write(split[1]+split[2]);
			f.write(" ");
			f.write(split[2]+split[3]);
			f.write(" ");
			f.write(split[3]+split[4]);
			f.write(" ");
			f.write(split[4]+split[5]);
			f.write(" ");
			
			f.write(split[6]);
			f.write("\n");
			
			
			
			
		}
        reader.close();

	}

}
