package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SVMFeatureTest {
	static File file = new File("C://Users//Minal//Desktop//MLinClassignment4//SVMInteresttrain.txt");
	static File tfile = new File("C://Users//Minal//Desktop//MLinClassignment4//SVMInteresttest.txt");
	static Map<String,Integer> map=new HashMap<String, Integer>();
	static Map<String,Integer> classmap=new HashMap<String, Integer>();
	public static void makeTest() throws FileNotFoundException {
		int count=0;
		int classcount=0;
		try{
			if (!tfile.exists()) {
				tfile.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader("C://Users//Minal//Desktop//MLinClassignment4//TestInterest.test"));
			FileWriter fw = new FileWriter(tfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			int k=7;
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] parts = line.split(" ");
				
				if(!classmap.containsKey(parts[k]) ){
	    	   		classmap.put(parts[k],++classcount);
		    		System.out.print(classcount+" ");
		    		
		    		bw.write(classcount+" ");
					
	    	   	}
	    	   	else{
	    	   		System.out.print(classmap.get(parts[k])+"  ");
	    	   		bw.write(classmap.get(parts[k])+" ");
	    	   	}
		    	
				Map<Integer,Integer> unique=new TreeMap<Integer, Integer>();
			    for (int i = 0; i <parts.length-1; i++) {
			    	
			    		
			    	   	String key=parts[i];
			    	   
			    	   	if(map.containsKey(parts[i]) ){
			    	   		int key1=map.get(parts[i]);
			    	   		if(!unique.containsKey(key1)){
			    	   			unique.put(map.get(parts[i]),1);
				    	   		//System.out.print(map.get(parts[i])+":"+"1"+"  ");
			    	   		}
			    	   		else{
			    	   			int c=unique.get(map.get(parts[i]));
			    	   			unique.put(map.get(parts[i]),++c);
				    	   		//System.out.print(map.get(parts[i])+":"+c+"  ");
			    	   		}
			    	   		
			    	   	}
			    	   		
			       }
			    
			   System.out.println();
			   Iterator<Map.Entry<Integer, Integer>> entries = unique.entrySet().iterator();
			   while (entries.hasNext()) {
			       Map.Entry<Integer, Integer> entry = entries.next();
			       System.out.print(entry.getKey() + ":" + entry.getValue()+" ");
			       bw.write(entry.getKey() + ":" + entry.getValue()+" ");
			   }
			   bw.newLine();
		 
			
			}
			br.close();
			bw.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		// Construct BufferedReader from FileReader
		
		int count=0;
		int classcount=0;
		try{
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader("C://Users//Minal//Desktop//MLinClassignment4//TrainInterest.train"));
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			int k=7;
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] parts = line.split(" ");
				
				if(!classmap.containsKey(parts[k]) ){
	    	   		classmap.put(parts[k],++classcount);
		    		System.out.print(classcount+" ");
		    		
		    		bw.write(classcount+" ");
					
	    	   	}
	    	   	else{
	    	   		System.out.print(classmap.get(parts[k])+"  ");
	    	   		bw.write(classmap.get(parts[k])+" ");
	    	   	}
		    	
				Map<Integer,Integer> unique=new TreeMap<Integer, Integer>();
			    for (int i = 0; i <parts.length-1; i++) {
			    	
			    		
			    	   	String key=parts[i];
			    	   
			    	   	if(!map.containsKey(parts[i]) ){
			    	   		map.put(key,++count);
			    	   		unique.put(count,1);
				    		//System.out.print(count+":"+"1"+"  ");
				    		//array[i]=count;
				    		//bw.write(count+":"+"1"+" ");
			    	   		
			    	   		
			    	   		
							
			    	   	}
			    	   	else{
			    	   		//System.out.print(map.get(parts[i])+":"+"1"+"  ");
			    	   	//	bw.write(map.get(parts[i])+":"+"1"+" ");
			    	   		//array[i]=map.get(parts[i]);
			    	   		int key1=map.get(parts[i]);
			    	   		if(!unique.containsKey(key1)){
			    	   			unique.put(map.get(parts[i]),1);
				    	   		//System.out.print(map.get(parts[i])+":"+"1"+"  ");
			    	   		}
			    	   		else{
			    	   			int c=unique.get(map.get(parts[i]));
			    	   			unique.put(map.get(parts[i]),++c);
				    	   		//System.out.print(map.get(parts[i])+":"+c+"  ");
			    	   		}
			    	   		
			    	   	}
			    	   		
			       }
			    
			   System.out.println();
			   Iterator<Map.Entry<Integer, Integer>> entries = unique.entrySet().iterator();
			   while (entries.hasNext()) {
			       Map.Entry<Integer, Integer> entry = entries.next();
			       System.out.print(entry.getKey() + ":" + entry.getValue()+" ");
			       bw.write(entry.getKey() + ":" + entry.getValue()+" ");
			   }
			   bw.newLine();
		 
			
			}
			br.close();
			bw.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		makeTest();
	}


}
