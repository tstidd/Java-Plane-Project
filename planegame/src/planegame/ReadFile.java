package planegame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	
	static boolean read = true;

	public ReadFile() {
		// TODO Auto-generated constructor stub
	}
	
	public static void readFile() {
		
		if(read) {
		try(Scanner input = new Scanner(new File("data.txt")))
		{
			if(input.hasNextLine())
				System.out.print(input.nextLine());
			while (input.hasNextLine()) {
				String date = input.next();
				int time = input.nextInt();
				int level = input.nextInt();
				System.out.println(date+time+level);
				
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("File data.ext could not be found");
		}finally {
			read = false;
		}
	}
	}

	/**
	 * @param read the read to set
	 */
	public static void setRead(boolean read) {
		ReadFile.read = read;
	}
}
