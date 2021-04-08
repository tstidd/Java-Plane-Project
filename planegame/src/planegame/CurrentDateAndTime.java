package planegame;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateAndTime {

	public CurrentDateAndTime() {
		// TODO Auto-generated constructor stub
	}
	
	public static String time() {
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    return formatter.format(date);  
	}

}
