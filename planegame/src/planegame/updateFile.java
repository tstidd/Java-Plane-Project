package planegame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class updateFile {



public static void update(ArrayList<tempRank> results) {
	

		try {
			   File file = new File("src/file/data.txt");
			      
			      if(!file.exists()) {
			    	  file.createNewFile();
			      }
  
			      FileWriter fw = new FileWriter(file,false);
			      BufferedWriter bw = new BufferedWriter(fw);
			      PrintWriter pw = new PrintWriter(bw);
			      pw.flush();
			      
			      
			      Object[] updateResults = results.toArray();
			      
			  
			    	  for(int i = 0; i<10;i++) {  
			    		  
				    	  pw.println(((tempRank) updateResults[i]).getDate()+" " +((tempRank) updateResults[i]).getTime()+" "+ ((tempRank) updateResults[i]).getPlayTime() + " "+ ((tempRank) updateResults[i]).getLevel());
				    	 
				    	 // System.out.println(((tempRank) updateResults[i]).getDate()+" " +((tempRank) updateResults[i]).getTime()+" "+ ((tempRank) updateResults[i]).getPlayTime() + " "+ ((tempRank) updateResults[i]).getLevel());
			      
			}	
			    	  System.out.println("Successfully Update file.");
			    	  pw.close();
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
			      
		
	}


}
