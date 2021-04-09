package planegame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class updateFile {

	public updateFile() {
		// TODO Auto-generated constructor stub
	}

public static void updateFile(ArrayList<tempRank> results) {
	

		try {
			   File file = new File("data.txt");
			      
			      if(!file.exists()) {
			    	  file.createNewFile();
			      }
			      
			      
			      
			      FileWriter fw = new FileWriter(file,false);
			      BufferedWriter bw = new BufferedWriter(fw);
			      PrintWriter pw = new PrintWriter(bw);
			      
			      
			      Object[] updateResults = results.toArray();
			      
			      if(updateResults.length<10) {
			      for(int i = 0; i<updateResults.length;i++) {  
			    	  pw.println(((tempRank) updateResults[i]).getDate()+" " + ((tempRank) updateResults[i]).getPlayTime() + " "+ ((tempRank) updateResults[i]).getLevel());
			    	  pw.close();
			    	  System.out.println(((tempRank) updateResults[i])+" " + ((tempRank) updateResults[i]).getPlayTime() + " "+ ((tempRank) updateResults[i]).getLevel());
				      System.out.println("Successfully update to the file.");
			      }}
			      else {
			    	  for(int i = 0; i<10;i++) {  
				    	  pw.println(((tempRank) updateResults[i]).getDate()+" " + ((tempRank) updateResults[i]).getPlayTime() + " "+ ((tempRank) updateResults[i]).getLevel());
				    	  pw.close();
				    	  System.out.println(((tempRank) updateResults[i]).getDate()+" " + ((tempRank) updateResults[i]).getPlayTime() + " "+ ((tempRank) updateResults[i]).getLevel());
					      System.out.println("Successfully update to the file.");
			      }
			      
			      
	    }} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
			      
		
	}


}
