package planegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
	
	static boolean read = true;
	static ArrayList <tempRank> results = new ArrayList<>();
	
	static String top1 ="";
	static String top2 ="";
	static String top3 ="";
	public ReadFile() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static void readFile() {
		
		if(read) {
		try(Scanner input = new Scanner(new File("data.txt")))
		{
			if(input.hasNextLine())
				//System.out.print(input.nextLine());
			
			while (input.hasNextLine()) {
				String date = input.next();				
				String time = input.next();
				int playTime = input.nextInt();
				int level = input.nextInt();
				input.nextLine();
				
				results.add(new tempRank(date,time,playTime,level));
				
				
				
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File data.ext could not be found");
		}finally {
			read = false;
				
		}
		
		Collections.sort(results, new SortbyPlayTime());	
		
		results.forEach((e)->{System.out.println(e);});
		getResults();
		
	}
	}

	/**
	 * @param read the read to set
	 */
	public static void setRead(boolean read) {
		ReadFile.read = read;
	}
	
	public static void getResults(){
		top1 ="";
		top2 ="";
		top3 ="";
		
		top1 += results.get(0);
		top2 += results.get(1);
		top3 += results.get(2);
		
	
			
		
	}
	
	
	
	
}
class SortbyPlayTime implements Comparator<tempRank>
{
    // Used for sorting order of tempRank
   
    public int compare(tempRank a, tempRank b)
    {
       
    	return b.playTime - a.playTime;
    }
    
}
